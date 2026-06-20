package controller;

import view.*;
import dao.*;
import model.*;
import session.PatientSession;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.Date;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;

public class PatientController {

    private Patients mainView;
    private PatientHomePanel homePanel;
    private AppointmentPanel appointmentPanel;
    private QueuePanel queuePanel;
    private MedicalRecordPanel medicalRecordPanel;
    private RatingPanel ratingPanel;
    private AccountPanel accountPanel;
    private PatientLogoutPanel logoutPanel;
    private javax.swing.JScrollPane accountScrollPane;
    
    // Notification Fields
    private javax.swing.Timer notificationTimer;
    private String lastNotificationState = "none";
    
    private PatientDAO patientDAO = new PatientDAO();
    private DepartmentDAO departmentDAO = new DepartmentDAO();
    private DoctorDAO doctorDAO = new DoctorDAO();
    private AppointmentDAO appointmentDAO = new AppointmentDAO();
    private QueueDAO queueDAO = new QueueDAO();
    private MedicalRecordDAO medicalRecordDAO = new MedicalRecordDAO();
    private RatingDAO ratingDAO = new RatingDAO();
    private dao.SecurityQuestionsDao securityDAO = new dao.SecurityQuestionsDao();

    public PatientController(Patients mainView) {
        this.mainView = mainView;
        
        homePanel = new PatientHomePanel();
        appointmentPanel = new AppointmentPanel();
        queuePanel = new QueuePanel();
        medicalRecordPanel = new MedicalRecordPanel();
        ratingPanel = new RatingPanel();
        accountPanel = new AccountPanel();
        logoutPanel = new PatientLogoutPanel();
        accountScrollPane = new javax.swing.JScrollPane(accountPanel);
        accountScrollPane.setBorder(null);
        accountScrollPane.getVerticalScrollBar().setUnitIncrement(16);

        setupListeners();
        
        loadHomeData();
        showPanel(homePanel);
        
        startNotificationPoller();
    }

    private void startNotificationPoller() {
        // Trigger every 5 seconds (5000 ms) automatically
        notificationTimer = new javax.swing.Timer(5000, e -> {
            String patientId = session.PatientSession.getPatientId();
            if (patientId == null) return;
            
            // Get patient's current active queue
            QueueItem q = queueDAO.getCurrentQueueForPatient(patientId);
            if (q == null || "completed".equals(q.getStatus())) {
                // Stop notifications if completed
                lastNotificationState = "none";
                return;
            }
            
            int myToken = q.getTokenNumber();
            int servingToken = queueDAO.getDoctorCurrentlyServingToken(q.getDoctorId());
            
            if (servingToken == -1) {
                // Doctor hasn't started serving or no active consultation
                return;
            }
            
            String currentState = "waiting";
            String uiMessage = "";
            
            if (myToken == servingToken) {
                currentState = "now";
                uiMessage = "Your turn is now!";
            } else if (myToken == servingToken + 1) {
                currentState = "next";
                uiMessage = "Your turn is next!";
            } else if (myToken == servingToken + 2) {
                currentState = "after next";
                uiMessage = "Your turn is after next person!";
            } else {
                uiMessage = "Waiting in queue... (" + (myToken - servingToken) + " ahead)";
            }
            
            // Update the home panel label
            homePanel.getLblNotificationText().setText(uiMessage);
            
            // Only play sound and show popup if state ACTUALLY changes
            if (!currentState.equals(lastNotificationState)) {
                if (currentState.equals("now") || currentState.equals("next") || currentState.equals("after next")) {
                    playNotificationSound();
                    
                    // Show a quick visual notification dialog
                    JOptionPane.showMessageDialog(mainView, "Queue Notification: " + uiMessage, "Queue Update", JOptionPane.INFORMATION_MESSAGE);
                }
                lastNotificationState = currentState;
            }
        });
        notificationTimer.start();
    }
    
    public void playNotificationSound() {
        // Run in a background thread so we don't freeze the UI for 2 seconds
        new Thread(() -> {
            try {
                // Synthesize a double "bell ring" (ding-ding!) for 2 seconds
                int sampleRate = 8000;
                int durationSeconds = 2;
                byte[] buf = new byte[sampleRate * durationSeconds];
                for (int i = 0; i < buf.length; i++) {
                    double t = i / (double) sampleRate;
                    
                    // Two bell strikes with fast exponential decay
                    double env1 = t < 0.5 ? Math.exp(-t * 8.0) : 0;
                    double env2 = t >= 0.5 ? Math.exp(-(t - 0.5) * 8.0) : 0;
                    double env = env1 + env2;
                    
                    // C6 note (1046.5 Hz) + an overtone for a bell-like timbre
                    double angle1 = t * 1046.5 * 2.0 * Math.PI;
                    double angle2 = t * 2093.0 * 2.0 * Math.PI; 
                    
                    double val = (Math.sin(angle1) + 0.5 * Math.sin(angle2)) * env;
                    buf[i] = (byte) (val * 85.0); // Volume control
                }
                
                javax.sound.sampled.AudioFormat af = new javax.sound.sampled.AudioFormat(sampleRate, 8, 1, true, false);
                javax.sound.sampled.SourceDataLine sdl = javax.sound.sampled.AudioSystem.getSourceDataLine(af);
                sdl.open(af);
                sdl.start();
                sdl.write(buf, 0, buf.length);
                sdl.drain();
                sdl.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void setupListeners() {
        // Sidebar listeners
        mainView.btnHome.addActionListener(e -> {
            loadHomeData();
            showPanel(homePanel);
        });

        mainView.btnBookAppointment.addActionListener(e -> {
            loadAppointmentData();
            showPanel(appointmentPanel);
        });

        mainView.btnQueue.addActionListener(e -> {
            loadQueueData();
            showPanel(queuePanel);
        });

        mainView.btnMedicalRecord.addActionListener(e -> {
            loadMedicalRecords();
            showPanel(medicalRecordPanel);
        });

        mainView.btnRating.addActionListener(e -> {
            loadRatingData();
            showPanel(ratingPanel);
        });

        mainView.btnAccount.addActionListener(e -> {
            loadAccountData();
            showPanel(accountScrollPane);
        });

        mainView.btnLogout.addActionListener(e -> showPanel(logoutPanel));
        
        queuePanel.getCmbDepartment().addActionListener(e -> {
            Department selectedDept = (Department) queuePanel.getCmbDepartment().getSelectedItem();
            if (selectedDept != null) {
                QueueItem q = queueDAO.getCurrentQueueForPatientInDept(PatientSession.getPatientId(), selectedDept.getDepartmentId());
                if (q != null) {
                    queuePanel.displayQueue(q);
                } else {
                    queuePanel.displayNoQueue();
                }
            }
            updateQueueMetrics();
        });
        
        logoutPanel.getBtnCancel().addActionListener(e -> {
            loadHomeData();
            showPanel(homePanel);
        });
        
        logoutPanel.getBtnLogout().addActionListener(e -> {
            session.PatientSession.clearSession();
            mainView.dispose();
            view.UserLogin loginView = new view.UserLogin();
            loginView.setVisible(true);
        });

        // Home Panel Buttons
        homePanel.getBtnBookNow().addActionListener(e -> {
            loadAppointmentData();
            showPanel(appointmentPanel);
        });
        
        homePanel.getBtnViewQueue().addActionListener(e -> {
            loadQueueData();
            showPanel(queuePanel);
        });

        // Appointment Panel Logic
        appointmentPanel.getCmbDepartment().addActionListener(e -> {
            Object item = appointmentPanel.getCmbDepartment().getSelectedItem();
            if (item instanceof Department) {
                Department selectedDept = (Department) item;
                List<Doctor> docs = doctorDAO.getAvailableDoctorsByDepartment(selectedDept.getDepartmentId());
                appointmentPanel.getCmbDoctor().removeAllItems();
                for (Doctor d : docs) {
                    appointmentPanel.getCmbDoctor().addItem(d);
                }
            }
        });

        appointmentPanel.getBtnConfirm().addActionListener(e -> confirmAppointment());

        // Queue Panel Logic
        queuePanel.getBtnCancel().addActionListener(e -> {
            QueueItem q = queueDAO.getCurrentQueueForPatient(PatientSession.getPatientId());
            if (q != null) {
                int confirm = JOptionPane.showConfirmDialog(mainView, "Are you sure you want to cancel your queue?", "Cancel Queue", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (queueDAO.cancelQueue(q.getQueueId(), PatientSession.getPatientId())) {
                        JOptionPane.showMessageDialog(mainView, "Queue cancelled successfully.");
                        loadQueueData();
                    } else {
                        JOptionPane.showMessageDialog(mainView, "Failed to cancel queue.");
                    }
                }
            }
        });

        // Rating Panel Logic
        ratingPanel.getBtnSubmit().addActionListener(e -> submitRating());

        // Account Panel Logic
        accountPanel.getBtnConfirm().addActionListener(e -> updateProfile());
    }

    private void showPanel(javax.swing.JComponent panel) {
        mainView.contentPanel.removeAll();
        mainView.contentPanel.add(panel, java.awt.BorderLayout.CENTER);
        mainView.contentPanel.revalidate();
        mainView.contentPanel.repaint();
        
        java.awt.Color defaultBgColor = new java.awt.Color(30, 100, 180); // Original sidebar blue color
        java.awt.Color activeBgColor = new java.awt.Color(15, 60, 130); // Darker blue for active tab
        java.awt.Color defaultFgColor = java.awt.Color.WHITE;
        java.awt.Color activeFgColor = java.awt.Color.WHITE;

        javax.swing.JButton[] buttons = { mainView.btnHome, mainView.btnBookAppointment, mainView.btnQueue, mainView.btnMedicalRecord, mainView.btnRating, mainView.btnAccount };
        javax.swing.JComponent[] panels = { homePanel, appointmentPanel, queuePanel, medicalRecordPanel, ratingPanel, accountScrollPane };

        for (int i = 0; i < buttons.length; i++) {
            boolean isActive = (panel == panels[i]);
            buttons[i].setBackground(isActive ? activeBgColor : defaultBgColor);
            buttons[i].setForeground(isActive ? activeFgColor : defaultFgColor);
            buttons[i].setOpaque(isActive);
            buttons[i].setContentAreaFilled(isActive);
        }
    }

    private void loadHomeData() {
        Patient p = null;
        if (PatientSession.getPatientId() != null) {
            p = patientDAO.getPatientById(PatientSession.getPatientId());
        }

        if (p != null) {
            homePanel.setWelcomeName(p.getFullName());
        } else {
            homePanel.setWelcomeName("Guest");
            JOptionPane.showMessageDialog(mainView, "Warning: Patient profile not found. Please relogin.");
        }
    }

    private void loadAppointmentData() {
        try {
            appointmentPanel.getCmbDepartment().removeAllItems();
            List<Department> depts = departmentDAO.getAllDepartments();
            if (depts == null || depts.isEmpty()) {
                JOptionPane.showMessageDialog(mainView, "Warning: No departments available in the system.");
            } else {
                for (Department d : depts) {
                    appointmentPanel.getCmbDepartment().addItem(d);
                }
            }

            appointmentPanel.getCmbDate().setDate(java.sql.Date.valueOf(LocalDate.now().plusDays(1)));
            appointmentPanel.getTxtTime().setText("10:30");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(mainView, "Error loading appointment data: " + ex.getMessage() + "\n" + ex.toString());
        }
    }

    private void confirmAppointment() {
        Doctor doc = (Doctor) appointmentPanel.getCmbDoctor().getSelectedItem();
        java.util.Date utilDate = appointmentPanel.getCmbDate().getDate();
        String timeStr = appointmentPanel.getTxtTime().getText();
        String reason = appointmentPanel.getTxtReason().getText();

        if (doc == null || utilDate == null || timeStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(mainView, "Please select all required fields and valid time.");
            return;
        }

        try {
            LocalDate date = new java.sql.Date(utilDate.getTime()).toLocalDate();
            if (date.isBefore(LocalDate.now())) {
                JOptionPane.showMessageDialog(mainView, "Error: Cannot book an appointment in the past.");
                return;
            }

            LocalTime time;
            if(timeStr.length() == 5) time = LocalTime.parse(timeStr);
            else time = LocalTime.parse(timeStr + ":00"); // Add seconds if needed

            if (appointmentDAO.createAppointment(PatientSession.getPatientId(), doc.getDoctorId(), date, time, reason)) {
                JOptionPane.showMessageDialog(mainView, "Appointment booked successfully!");
                appointmentPanel.getTxtReason().setText("");
                appointmentPanel.getTxtDescription().setText("");
                // Refresh UI by showing home panel or queue panel
                loadHomeData();
                showPanel(homePanel);
            } else {
                JOptionPane.showMessageDialog(mainView, "Failed to book appointment.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(mainView, "Invalid time format. Please use HH:mm (e.g., 10:30 or 14:00).");
        }
    }

    private void loadQueueData() {
        if (queuePanel.getCmbDepartment().getItemCount() == 0) {
            List<Department> deps = departmentDAO.getAllDepartments();
            for (Department d : deps) {
                queuePanel.getCmbDepartment().addItem(d);
            }
        }
        
        QueueItem qAny = queueDAO.getCurrentQueueForPatient(PatientSession.getPatientId());
        if (qAny != null) {
            for (int i = 0; i < queuePanel.getCmbDepartment().getItemCount(); i++) {
                Department d = (Department) queuePanel.getCmbDepartment().getItemAt(i);
                if (d.getDepartmentName().equals(qAny.getDepartmentName())) {
                    queuePanel.getCmbDepartment().setSelectedIndex(i);
                    break;
                }
            }
        }

        Department selectedDept = (Department) queuePanel.getCmbDepartment().getSelectedItem();
        if (selectedDept != null) {
            QueueItem q = queueDAO.getCurrentQueueForPatientInDept(PatientSession.getPatientId(), selectedDept.getDepartmentId());
            if (q != null) {
                queuePanel.displayQueue(q);
            } else {
                queuePanel.displayNoQueue();
            }
        } else {
            queuePanel.displayNoQueue();
        }
        updateQueueMetrics();
    }

    private void updateQueueMetrics() {
        Department selectedDept = (Department) queuePanel.getCmbDepartment().getSelectedItem();
        if (selectedDept != null) {
            int deptId = selectedDept.getDepartmentId();
            
            int servingToken = queueDAO.getCurrentlyServingToken(deptId);
            String servingStr = (servingToken != -1) ? String.valueOf(servingToken) : "0";
            
            int myToken = queueDAO.getPatientQueueToken(PatientSession.getPatientId(), deptId);
            
            if (myToken != -1) {
                int aheadCount = queueDAO.getPeopleAheadCount(deptId, myToken);
                queuePanel.setQueueMetrics(servingStr, String.valueOf(aheadCount));
            } else {
                queuePanel.setQueueMetrics(servingStr, "0");
            }
            
            // Total in queue
            int totalQueue = queueDAO.getTotalQueueCount(deptId);
            queuePanel.setWaitlistNumber(String.valueOf(totalQueue));
        }
    }

    private void loadMedicalRecords() {
        List<MedicalRecord> records = medicalRecordDAO.getMedicalRecordsByPatient(PatientSession.getPatientId());
        medicalRecordPanel.setMedicalRecords(records);
    }

    private void loadRatingData() {
        ratingPanel.getCmbAppointments().removeAllItems();
        List<Appointment> apps = appointmentDAO.getCompletedAppointmentsWithoutRating(PatientSession.getPatientId());
        if (apps == null || apps.isEmpty()) {
            Appointment dummy = new Appointment();
            dummy.setAppointmentId(-1);
            dummy.setDoctorName("No unrated appointments found");
            ratingPanel.getCmbAppointments().addItem(dummy);
        } else {
            for (Appointment a : apps) {
                ratingPanel.getCmbAppointments().addItem(a);
            }
        }
        ratingPanel.getTxtFeedback().setText("");
    }

    private void submitRating() {
        Appointment app = (Appointment) ratingPanel.getCmbAppointments().getSelectedItem();
        Integer stars = (Integer) ratingPanel.getCmbStars().getSelectedItem();
        String feedback = ratingPanel.getTxtFeedback().getText();

        if (app == null || app.getAppointmentId() == -1) {
            JOptionPane.showMessageDialog(mainView, "No appointments available to rate.");
            return;
        }

        if (stars == null || stars < 1 || stars > 5) {
            JOptionPane.showMessageDialog(mainView, "Please select a valid rating between 1 and 5.");
            return;
        }

        if (feedback == null || feedback.trim().isEmpty() || feedback.equals("Great service and very helpful staff!")) {
            JOptionPane.showMessageDialog(mainView, "Please provide some valid feedback.");
            return;
        }

        if (ratingDAO.submitRating(app.getAppointmentId(), PatientSession.getPatientId(), app.getDoctorId(), stars, feedback)) {
            JOptionPane.showMessageDialog(mainView, "Thank you for your feedback!");
            loadRatingData(); // refresh list
        } else {
            JOptionPane.showMessageDialog(mainView, "Failed to submit rating. You might have already rated this.");
        }
    }

    private void loadAccountData() {
        Patient p = patientDAO.getPatientById(PatientSession.getPatientId());
        String username = patientDAO.getUsernameByUserId(PatientSession.getUserId());
        if (p != null) {
            accountPanel.displayPatientProfile(p, username != null ? username : "");
            if (p.getDob() != null) {
                accountPanel.getTxtDob().setDate(new java.sql.Date(p.getDob().getTime()));
            } else {
                accountPanel.getTxtDob().setDate(null);
            }
            if (p.getBloodGroup() != null) {
                accountPanel.getCmbBloodGroup().setSelectedItem(p.getBloodGroup());
            } else {
                accountPanel.getCmbBloodGroup().setSelectedItem("Unknown");
            }
        }
        
        accountPanel.getTxtQ1().setText("");
        accountPanel.getTxtQ2().setText("");
        accountPanel.getTxtQ3().setText("");
        accountPanel.getTxtQ4().setText("");
        accountPanel.getTxtQ5().setText("");
        
        accountPanel.getTxtPhone().setText(p != null ? p.getContactNumber() : "");
        accountPanel.getTxtEmail().setText(username != null ? username : "");
        accountPanel.getTxtPassword().setText("");
        accountPanel.getTxtCurrentPassword().setText("");
    }

    private void updateProfile() {
        java.util.Date utilDob = accountPanel.getTxtDob().getDate();
        String phone = accountPanel.getTxtPhone().getText().trim();
        String email = accountPanel.getTxtEmail().getText().trim();
        if (!email.isEmpty() && !email.startsWith("Enter")) {
            if (email.contains(" ")) {
                JOptionPane.showMessageDialog(mainView, "Username must be exactly one word with no spaces.");
                return;
            }
            if (Character.isDigit(email.charAt(0))) {
                JOptionPane.showMessageDialog(mainView, "Username cannot start with a number.");
                return;
            }
            if (!Character.isLowerCase(email.charAt(0))) {
                JOptionPane.showMessageDialog(mainView, "Username must start with a lowercase letter.");
                return;
            }
        }
        String newPass = accountPanel.getTxtPassword().getText().trim();
        String currPass = accountPanel.getTxtCurrentPassword().getText().trim();

        if (currPass.isEmpty() || currPass.equals("Enter current password")) {
            JOptionPane.showMessageDialog(mainView, "Current password is required to save changes.");
            return;
        }

        if (!patientDAO.validateCurrentPassword(PatientSession.getUserId(), currPass)) {
            JOptionPane.showMessageDialog(mainView, "Incorrect current password!");
            return;
        }

        if (!newPass.isEmpty() && !newPass.startsWith("Enter") && newPass.equals(currPass)) {
            JOptionPane.showMessageDialog(mainView, "New password cannot be the same as the current password.");
            return;
        }

        Patient p = patientDAO.getPatientById(PatientSession.getPatientId());
        if (p == null) {
            JOptionPane.showMessageDialog(mainView, "Error: Patient record not found.");
            return;
        }

        Date dob = p.getDob() != null ? new Date(p.getDob().getTime()) : null;
        int age = p.getAge();
        if (utilDob != null) {
            dob = new Date(utilDob.getTime());
            age = LocalDate.now().getYear() - dob.toLocalDate().getYear();
        }

        String finalPhone = p.getContactNumber();
        if (!phone.isEmpty() && !phone.startsWith("Enter")) {
            if (phone.length() != 10 || !phone.matches("\\d+")) {
                JOptionPane.showMessageDialog(mainView, "Phone number must be exactly 10 numeric digits.");
                return;
            }
            finalPhone = phone;
        }

        String bg = accountPanel.getCmbBloodGroup().getSelectedItem().toString();
        patientDAO.updatePatientProfile(p.getPatientId(), dob, age, finalPhone, p.getAddress(), bg);

        String a1 = accountPanel.getTxtQ1().getText().trim();
        String a2 = accountPanel.getTxtQ2().getText().trim();
        String a3 = accountPanel.getTxtQ3().getText().trim();
        String a4 = accountPanel.getTxtQ4().getText().trim();
        String a5 = accountPanel.getTxtQ5().getText().trim();
        
        String[] existAns = securityDAO.getSecurityAnswers(PatientSession.getUserId());
        if (existAns != null && existAns.length == 5) {
            if (a1.isEmpty()) a1 = existAns[0];
            if (a2.isEmpty()) a2 = existAns[1];
            if (a3.isEmpty()) a3 = existAns[2];
            if (a4.isEmpty()) a4 = existAns[3];
            if (a5.isEmpty()) a5 = existAns[4];
        }
        
        if (!a1.isEmpty() || !a2.isEmpty() || !a3.isEmpty() || !a4.isEmpty() || !a5.isEmpty()) {
            securityDAO.updateSecurityQuestions(PatientSession.getUserId(),
                "Your Favourite food?", a1,
                "Your First pet's name?", a2,
                "Your Favourite game?", a3,
                "Your Best Friend's name?", a4,
                "Your Favourite Place to visit?", a5);
        }

        if (!email.isEmpty() && !email.startsWith("Enter")) {
            if (!newPass.isEmpty() && !newPass.startsWith("Enter")) {
                patientDAO.updateUsernameAndPassword(PatientSession.getUserId(), email, newPass);
            } else {
                patientDAO.updateUsername(PatientSession.getUserId(), email);
            }
        } else if (!newPass.isEmpty() && !newPass.startsWith("Enter")) {
            patientDAO.updateUsernameAndPassword(PatientSession.getUserId(), PatientSession.getUsername(), newPass);
        }

        JOptionPane.showMessageDialog(mainView, "Profile updated successfully!");
        loadAccountData(); // Refresh UI
    }
}
