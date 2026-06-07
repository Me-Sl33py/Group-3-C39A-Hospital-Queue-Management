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
    private HomePanel homePanel;
    private AppointmentPanel appointmentPanel;
    private QueuePanel queuePanel;
    private MedicalRecordPanel medicalRecordPanel;
    private RatingPanel ratingPanel;
    private AccountPanel accountPanel;
    private LogoutPanel logoutPanel;
    
    private PatientDao patientDAO = new PatientDao();
    private DepartmentDAO departmentDAO = new DepartmentDAO();
    private DoctorDAO doctorDAO = new DoctorDAO();
    private AppointmentDAO appointmentDAO = new AppointmentDAO();
    private QueueDAO queueDAO = new QueueDAO();
    private MedicalRecordDAO medicalRecordDAO = new MedicalRecordDAO();
    private RatingDAO ratingDAO = new RatingDAO();

    public PatientController(Patients mainView) {
        this.mainView = mainView;
        
        homePanel = new HomePanel();
        appointmentPanel = new AppointmentPanel();
        queuePanel = new QueuePanel();
        medicalRecordPanel = new MedicalRecordPanel();
        ratingPanel = new RatingPanel();
        accountPanel = new AccountPanel();
        logoutPanel = new LogoutPanel();

        setupListeners();
        
        loadHomeData();
        showPanel(homePanel);
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
            showPanel(accountPanel);
        });

        mainView.btnLogout.addActionListener(e -> showPanel(logoutPanel));
        
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
            Department selectedDept = (Department) appointmentPanel.getCmbDepartment().getSelectedItem();
            if (selectedDept != null) {
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

    private void showPanel(JPanel panel) {
        mainView.contentPanel.removeAll();
        mainView.contentPanel.add(panel);
        mainView.contentPanel.revalidate();
        mainView.contentPanel.repaint();
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
        QueueItem q = queueDAO.getCurrentQueueForPatient(PatientSession.getPatientId());
        if (q != null) {
            queuePanel.displayQueue(q);
        } else {
            queuePanel.displayNoQueue();
        }
    }

    private void loadMedicalRecords() {
        List<MedicalRecord> records = medicalRecordDAO.getMedicalRecordsByPatient(PatientSession.getPatientId());
        medicalRecordPanel.setMedicalRecords(records);
    }

    private void loadRatingData() {
        ratingPanel.getCmbAppointments().removeAllItems();
        List<Appointment> apps = appointmentDAO.getCompletedAppointmentsWithoutRating(PatientSession.getPatientId());
        for (Appointment a : apps) {
            ratingPanel.getCmbAppointments().addItem(a);
        }
        ratingPanel.getTxtFeedback().setText("");
    }

    private void submitRating() {
        Appointment app = (Appointment) ratingPanel.getCmbAppointments().getSelectedItem();
        Integer stars = (Integer) ratingPanel.getCmbStars().getSelectedItem();
        String feedback = ratingPanel.getTxtFeedback().getText();

        if (app == null) {
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
        }
        
        accountPanel.getTxtPhone().setText("");
        accountPanel.getTxtEmail().setText("");
        accountPanel.getTxtPassword().setText("");
        accountPanel.getTxtCurrentPassword().setText("");
    }

    private void updateProfile() {
        java.util.Date utilDob = accountPanel.getTxtDob().getDate();
        String phone = accountPanel.getTxtPhone().getText().trim();
        String email = accountPanel.getTxtEmail().getText().trim();
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

        patientDAO.updatePatientProfile(p.getPatientId(), dob, age, finalPhone, p.getAddress());

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
