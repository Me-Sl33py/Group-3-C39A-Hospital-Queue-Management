/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.DoctorDAO;
import dao.MedicalRecordDAO;
import dao.PatientDAO;
import model.Doctor;
import model.MedicalRecord;
import model.Patient;
import view.DoctorPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class DoctorController {

    // ── Dependencies ──────────────────────────────────────────────────────────
    private final DoctorPanel      view;
    private final PatientDAO       patientDAO;
    private final MedicalRecordDAO recordDAO;
    private final DoctorDAO        doctorDAO;

    // ── Session state ─────────────────────────────────────────────────────────
    private Doctor  currentDoctor;
    private Patient activePatient;
    private javax.swing.Timer refreshTimer;

    // =========================================================================
    // Constructor — wires all buttons
    // =========================================================================
    public DoctorController(DoctorPanel view) {
        this.view       = view;
        this.patientDAO = new PatientDAO();
        this.recordDAO  = new MedicalRecordDAO();
        this.doctorDAO  = new DoctorDAO();

        patientDAO.createTableIfNotExists();
        recordDAO.createTableIfNotExists();
        doctorDAO.createTableIfNotExists();

        attachListeners();
        loadQueueTable();

        // Auto-refresh the dashboard stats every 5 seconds
        refreshTimer = new javax.swing.Timer(5000, e -> loadQueueTable());
        refreshTimer.start();
        
        setActiveButton(view.getBtnMyQueue());
    }

    // =========================================================================
    // Wire buttons to action methods
    // =========================================================================
    private void attachListeners() {

        // ── Sidebar navigation ────────────────────────────────────────────────
        view.getBtnMyQueue().addActionListener(e -> { view.getTabbedPane().setSelectedIndex(0); setActiveButton(view.getBtnMyQueue()); });
        view.getBtnCallNextPatient().addActionListener(e -> { view.getTabbedPane().setSelectedIndex(1); setActiveButton(view.getBtnCallNextPatient()); });
        view.getBtnAddRecords().addActionListener(e -> { view.getTabbedPane().setSelectedIndex(2); setActiveButton(view.getBtnAddRecords()); });
        view.getBtnAccount().addActionListener(e -> {
            int tabCount = view.getTabbedPane().getTabCount();
            int accountTabIndex = tabCount - 1; // Account tab is always the last one
            if (accountTabIndex >= 0) {
               view.getTabbedPane().setSelectedIndex(accountTabIndex);
            }
            loadAccountData();
            setActiveButton(view.getBtnAccount());
        });
        // ── Tab 1 : dashboard "Call Next Patient" card button ─────────────────
        view.getBtnCallNextDashboard().addActionListener(e -> callNextPatient());

        // ── Tab 2 : active consultation ───────────────────────────────────────
        view.getBtnCallNext().addActionListener(e -> callNextPatient());
        view.getBtnEndSession().addActionListener(e -> endSession());
        view.getBtnSkipPatient().addActionListener(e -> skipPatient());
        view.getBtnViewFullQueue().addActionListener(e -> {
            loadQueueTable();
            view.getTabbedPane().setSelectedIndex(0);
        });

        // ── Tab 3 : medical record form ───────────────────────────────────────
        view.getBtnSubmitRecord().addActionListener(e -> submitMedicalRecord());
        view.getBtnCancelRecord().addActionListener(e -> clearRecordForm());
        view.getBtnSearchPatient().addActionListener(e -> searchPatient());

        // ── Tab 4 : account settings ──────────────────────────────────────────
        view.getBtnUpdateProfile().addActionListener(e -> saveAccountChanges());
        view.getBtnChangePassword().addActionListener(e -> changePassword());

        // ── Sidebar : logout ──────────────────────────────────────────────────
        view.getBtnLogout().addActionListener(e -> logout());

        // ── Tab 2 : No Show table — click a row to recall the patient ─────────
        view.getNoShowTable().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int row = view.getNoShowTable().getSelectedRow();
                if (row < 0) return;
                String patientId = view.getNoShowTable().getValueAt(row, 2).toString();
                String patientName = view.getNoShowTable().getValueAt(row, 1).toString();
                int confirm = JOptionPane.showConfirmDialog(view,
                        "Recall " + patientName + " (" + patientId + ") back to the waiting queue?",
                        "Recall No-Show Patient",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    boolean ok = patientDAO.recallNoShowPatient(patientId);
                    if (ok) {
                        JOptionPane.showMessageDialog(view,
                                patientName + " has been moved back to the waiting queue.",
                                "Patient Recalled",
                                JOptionPane.INFORMATION_MESSAGE);
                        loadQueueTable();
                    } else {
                        JOptionPane.showMessageDialog(view,
                                "Failed to recall patient. Please try again.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    private void setActiveButton(JButton activeBtn) {
        JButton[] navBtns = {
            view.getBtnMyQueue(),
            view.getBtnCallNextPatient(),
            view.getBtnAddRecords(),
            view.getBtnAccount()
        };
        java.awt.Color activeColor = new java.awt.Color(51, 102, 255); // Admin active blue
        java.awt.Color inactiveColor = new java.awt.Color(22, 54, 120); // Admin normal blue
        
        for (JButton btn : navBtns) {
            btn.setBorderPainted(false);
            btn.setContentAreaFilled(false);
            btn.setOpaque(true);
            btn.setFocusPainted(false);
            btn.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));
            if (btn == activeBtn) {
                btn.setBackground(activeColor);
            } else {
                btn.setBackground(inactiveColor);
            }
        }
    }

    // =========================================================================
    // Tab 1 — My Queue
    // =========================================================================
    public void loadQueueTable() {
        if (currentDoctor == null) return;

        List<Object[]> rows = patientDAO.getQueueByDoctor(currentDoctor.getDoctorId());
        DefaultTableModel model = (DefaultTableModel) view.getQueueTable().getModel();
        model.setRowCount(0);

        int waiting = 0;
        int confirmed = 0;
        int noShow = 0;
        int skipped = 0;
        int inConsultation = 0;
        int completed = 0;

        for (Object[] row : rows) {
            model.addRow(row);
            String status = row[6] != null ? row[6].toString().toLowerCase() : "";
            if (status.equals("waiting")) {
                waiting++;
            } else if (status.equals("confirmed")) {
                confirmed++;
            } else if (status.equals("no show") || status.equals("noshow")) {
                noShow++;
            } else if (status.equals("skipped")) {
                skipped++;
            } else if (status.equals("in consultation")) {
                inConsultation++;
            } else if (status.equals("completed")) {
                completed++;
            }
        }
        
        // Update Dashboard Cards with live data from DB
        view.getLblWaitingCount().setText(String.format("%02d", waiting));
        view.getLblConfirmedCount().setText(String.format("%02d", confirmed + inConsultation));
        view.getLblNoShowCount().setText(String.format("%02d", noShow));
        view.getLblCompletedCount().setText(String.format("%02d", completed));
        view.getLblRemainingCount().setText("You have " + (waiting + skipped + inConsultation) + " patients remaining");
        
        updateQueueLabels(rows);
        loadNoShowTable();
    }

    /**
     * Loads the No Show patients table on Tab 2 from the database.
     */
    private void loadNoShowTable() {
        if (currentDoctor == null) return;
        List<Object[]> noShowRows = patientDAO.getNoShowPatientsByDoctor(currentDoctor.getDoctorId());
        DefaultTableModel noShowModel = (DefaultTableModel) view.getNoShowTable().getModel();
        noShowModel.setRowCount(0);
        for (Object[] row : noShowRows) {
            noShowModel.addRow(row);
        }
    }

    private void updateQueueLabels(List<Object[]> upcoming) {
        if (upcoming.size() > 0) {
            view.getJPatientQueue().setVisible(true);
            view.getLblQueueName1().setText(upcoming.get(0)[1].toString());
            view.getLblQueueDesc1().setText("Token #" + upcoming.get(0)[0].toString());
            view.getLblPatientQueueNum1().setText(upcoming.get(0)[0].toString());
        } else {
            view.getJPatientQueue().setVisible(false);
            view.getLblQueueName1().setText("—");
            view.getLblQueueDesc1().setText("");
            view.getLblPatientQueueNum1().setText("-");
        }
        
        if (upcoming.size() > 1) {
            view.getJPatientQueue1().setVisible(true);
            view.getLblQueueName2().setText(upcoming.get(1)[1].toString());
            view.getLblQueueDesc2().setText("Token #" + upcoming.get(1)[0].toString());
            view.getLblPatientQueueNum2().setText(upcoming.get(1)[0].toString());
        } else {
            view.getJPatientQueue1().setVisible(false);
            view.getLblQueueName2().setText("—");
            view.getLblQueueDesc2().setText("");
            view.getLblPatientQueueNum2().setText("-");
        }
    }

    // =========================================================================
    // Tab 2 — Call Next Patient
    // =========================================================================
    public void callNextPatient() {
        if (currentDoctor == null) return;
        String docId = currentDoctor.getDoctorId();
        
        Patient next = patientDAO.getNextWaitingPatient(docId);

        // Auto-recall skipped patients if waiting queue is empty
        if (next == null) {
            next = patientDAO.getNextSkippedPatient(docId);
        }

        if (next == null) {
            JOptionPane.showMessageDialog(view,
                    "No more patients in the queue.",
                    "Queue Empty",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Mark the old active patient as completed (if any)
        if (activePatient != null) {
            patientDAO.updateQueueStatus(activePatient.getPatientId(), "completed");
            addRowToHistory(activePatient);
        }

        // Update status in DB
        patientDAO.updateQueueStatus(next.getPatientId(), "in consultation");
        activePatient = next;

        // Update the UI labels
        view.getLblActivePatientName().setText(next.getFullName());
        view.getLblActivePatientId().setText(next.getPatientId());

        // Update Tab 3 patient info fields
        updateMedicalRecordTab();

        // Refresh the queue table and update the upcoming labels
        loadQueueTable();
    }

    public void endSession() {
        if (activePatient == null) {
            JOptionPane.showMessageDialog(view,
                    "No active consultation to end.",
                    "No Session",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        patientDAO.updateQueueStatus(activePatient.getPatientId(), "completed");
        addRowToHistory(activePatient);
        activePatient = null;

        view.getLblActivePatientName().setText("—");
        view.getLblActivePatientId().setText("—");
        updateMedicalRecordTab();
        loadQueueTable();
    }

    public void skipPatient() {
        if (activePatient == null) {
            JOptionPane.showMessageDialog(view,
                    "No active consultation to skip.",
                    "No Session",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String pId = activePatient.getPatientId();
        patientDAO.incrementSkipCount(pId);
        int skips = patientDAO.getSkipCount(pId);

        if (skips >= 2) {
            // Reached max skips, mark as no show and cancel consultation
            patientDAO.updateQueueStatus(pId, "no show");
            addRowToHistory(activePatient);
            JOptionPane.showMessageDialog(view,
                    "Patient " + activePatient.getFullName() + " skipped twice. Marked as NO SHOW and Receptionist notified.",
                    "Patient Skipped - Limit Reached",
                    JOptionPane.WARNING_MESSAGE);
            // TODO: In a full system, you would send a notification to the receptionist queue here.
        } else {
            // Mark as skipped but keep them around for later
            patientDAO.updateQueueStatus(pId, "skipped");
            JOptionPane.showMessageDialog(view,
                    "Patient " + activePatient.getFullName() + " skipped. They will be recalled after the waiting queue empties.",
                    "Patient Skipped",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        activePatient = null;
        view.getLblActivePatientName().setText("—");
        view.getLblActivePatientId().setText("—");
        updateMedicalRecordTab();
        loadQueueTable();
    }

    private void addRowToHistory(Patient patient) {
        DefaultTableModel historyModel =
                (DefaultTableModel) view.getSessionHistoryTable().getModel();

        String time = java.time.LocalTime.now()
                .format(java.time.format.DateTimeFormatter.ofPattern("hh:mm a"));

        historyModel.addRow(new Object[]{
            time,
            patient.getFullName(),
            "COMPLETED",
            "View File"
        });
    }

    // =========================================================================
    // Tab 3 — Add Medical Records
    // =========================================================================
    public void submitMedicalRecord() {
        if (activePatient == null) {
            JOptionPane.showMessageDialog(view,
                    "No active patient. Please call a patient first.",
                    "No Active Patient",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String diagnosis = view.getTaDiagnosis().getText().trim();
        String prescription = view.getTaPrescription().getText().trim();
        String notes = view.getTaNotes().getText().trim();

        // Clear placeholders if they were left unchanged
        if (diagnosis.startsWith("Enter detailed clinical notes")) diagnosis = "";
        if (prescription.isEmpty()) prescription = "";
        if (notes.isEmpty()) notes = "";

        String doctorId = (currentDoctor != null) ? currentDoctor.getDoctorId() : "";

        // Use PatientDAO to save it because it looks up the correct appointment_id from the queue
        boolean saved = patientDAO.saveMedicalRecord(activePatient.getPatientId(), doctorId, diagnosis, prescription, notes);

        if (saved) {
            JOptionPane.showMessageDialog(view,
                    "Medical record saved successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            clearRecordForm();
            loadMedicalHistory();
        } else {
            JOptionPane.showMessageDialog(view,
                    "Failed to save medical record. Please try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void clearRecordForm() {
        view.getTaDiagnosis().setText("Enter detailed clinical notes, patient history update, and recommended next steps...");
        view.getTaDiagnosis().setForeground(java.awt.Color.GRAY);
        view.getTaPrescription().setText("");
        view.getTaNotes().setText("");
    }

    public void loadMedicalHistory() {
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) view.getTblMedicalHistory().getModel();
        model.setRowCount(0);
        if (activePatient == null || currentDoctor == null) return;

        String sql = "SELECT record_id, created_at, diagnosis FROM medical_records WHERE patient_id = ? AND doctor_id = ? ORDER BY created_at DESC";
        try (java.sql.Connection conn = database.MySqlConnection.getConnection();
             java.sql.PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, activePatient.getPatientId());
            ps.setString(2, currentDoctor.getDoctorId());
            try (java.sql.ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Object[] row = new Object[]{
                        "MR-" + String.format("%03d", rs.getInt("record_id")),
                        activePatient.getFullName(),
                        rs.getDate("created_at"),
                        rs.getString("diagnosis"),
                        "View Details"
                    };
                    model.addRow(row);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateMedicalRecordTab() {
        if (activePatient != null && currentDoctor != null) {
            view.getTxtDocPatientId().setText(activePatient.getPatientId());
            view.getTxtDocPatientName().setText(activePatient.getFullName());
            
            // Get appointment date/time
            String sql = "SELECT a.appointment_date, a.appointment_time FROM queue q JOIN appointments a ON q.appointment_id = a.appointment_id WHERE q.patient_id = ? AND q.doctor_id = ? ORDER BY q.queue_id DESC LIMIT 1";
            try (java.sql.Connection conn = database.MySqlConnection.getConnection();
                 java.sql.PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, activePatient.getPatientId());
                ps.setString(2, currentDoctor.getDoctorId());
                try (java.sql.ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        java.sql.Date d = rs.getDate(1);
                        java.sql.Time t = rs.getTime(2);
                        view.getTxtDocApptDate().setText(d != null ? d.toString() : "");
                        view.getTxtDocApptTime().setText(t != null ? t.toString() : "");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            view.getTxtDocPatientId().setText("");
            view.getTxtDocPatientName().setText("");
            view.getTxtDocApptDate().setText("");
            view.getTxtDocApptTime().setText("");
        }
        loadMedicalHistory();
    }

    // =========================================================================
    // Tab 4 — Account Settings
    // =========================================================================
    public void loadAccountData() {
        if (currentDoctor == null) return;
        Doctor d = doctorDAO.getDoctorById(currentDoctor.getDoctorId());
        if (d == null) return;
        currentDoctor = d;

        view.getLblTopName().setText(d.getFullName());
        view.getLblTopSpecialization().setText(d.getSpecialization());
        view.getLblDoctorIdVal().setText("Doctor ID: " + d.getDoctorId());

        view.getTxtFullName().setText(d.getFullName());
        view.getTxtAccountUsername().setText(d.getUsername() != null ? d.getUsername() : "");
        view.getTxtSpecialization().setText(d.getSpecialization());
        view.getTxtAccountDepartment().setText(d.getDepartmentName() != null ? d.getDepartmentName() : "");
        
        view.getTxtPhone().setText(d.getContactNumber() != null ? d.getContactNumber() : "");
        view.getCmbAvailability().setSelectedItem(d.getAvailability() != null ? d.getAvailability() : "available");
        view.getTxtAccountAddress().setText(d.getAddress() != null ? d.getAddress() : "");
        view.getCmbBloodGroup().setSelectedItem(d.getBloodGroup() != null ? d.getBloodGroup() : "Unknown");
    }

    public void saveAccountChanges() {
        if (currentDoctor == null) {
            JOptionPane.showMessageDialog(view, "No doctor session found.", "Session Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        javax.swing.JPasswordField pwd = new javax.swing.JPasswordField(10);
        int action = JOptionPane.showConfirmDialog(view, pwd, "Enter Current Password to Update Profile", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (action != JOptionPane.OK_OPTION) {
            return;
        }
        
        String currentPwd = new String(pwd.getPassword());
        if (!doctorDAO.verifyPassword(currentDoctor.getUserId(), currentPwd)) {
            JOptionPane.showMessageDialog(view, "Incorrect password. Profile not updated.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        currentDoctor.setContactNumber(view.getTxtPhone().getText().trim());
        currentDoctor.setAvailability((String) view.getCmbAvailability().getSelectedItem());
        currentDoctor.setAddress(view.getTxtAccountAddress().getText().trim());
        currentDoctor.setBloodGroup((String) view.getCmbBloodGroup().getSelectedItem());

        boolean updated = doctorDAO.updateDoctorProfile(currentDoctor);

        if (updated) {
            JOptionPane.showMessageDialog(view, "Profile updated successfully.", "Saved", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(view, "Failed to update profile. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void changePassword() {
        if (currentDoctor == null) return;
        
        String oldPwd = new String(view.getPwdCurrent().getPassword());
        String newPwd = new String(view.getPwdNew().getPassword());
        String confirmPwd = new String(view.getPwdConfirm().getPassword());
        
        if (oldPwd.isEmpty() || newPwd.isEmpty() || confirmPwd.isEmpty()) {
            JOptionPane.showMessageDialog(view, "All password fields are required.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!newPwd.equals(confirmPwd)) {
            JOptionPane.showMessageDialog(view, "New Password and Confirm Password do not match.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        boolean updated = doctorDAO.changePassword(currentDoctor.getUserId(), oldPwd, newPwd);

        if (updated) {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dt = sdf.format(new java.util.Date());
            new dao.NotificationDAO().addNotification("Password Reset", "Password is changed for Doctor " + currentDoctor.getFullName() + " by Doctor " + currentDoctor.getFullName());

            JOptionPane.showMessageDialog(view, "Password changed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            view.getPwdCurrent().setText("");
            view.getPwdNew().setText("");
            view.getPwdConfirm().setText("");
        } else {
            JOptionPane.showMessageDialog(view, "Failed to change password. Please check your current password.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // =========================================================================
    // Logout
    // =========================================================================
    public void logout() {
        int confirm = JOptionPane.showConfirmDialog(view,
                "Are you sure you want to logout?",
                "Confirm Logout",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            view.dispose();
            new view.UserLogin().setVisible(true);
        }
    }

    // =========================================================================
    // Setters (called by login controller after authentication)
    // =========================================================================
    public void setCurrentDoctor(Doctor doctor) {
        this.currentDoctor = doctor;
        loadAccountData();
        loadQueueTable();
        updateGreeting();
    }

    private void updateGreeting() {
        if (currentDoctor == null) return;
        
        java.time.LocalTime time = java.time.LocalTime.now();
        int hour = time.getHour();
        String greeting;
        if (hour >= 5 && hour < 12) {
            greeting = "Good Morning";
        } else if (hour >= 12 && hour < 17) {
            greeting = "Good Afternoon";
        } else {
            greeting = "Good Evening";
        }
        
        String fullName = currentDoctor.getFullName();
        if (fullName != null && !fullName.toLowerCase().startsWith("dr.")) {
            fullName = "Dr. " + fullName;
        }
        
        if (view.getLblTitle4() != null) {
            view.getLblTitle4().setText(greeting + ", " + fullName);
        }
    }

    public Doctor getCurrentDoctor() {
        return currentDoctor;
    }

    private void searchPatient() {
        String id = view.getTxtPatientIdField().getText().trim();
        String name = view.getTxtPatientNameField().getText().trim();
        
        dao.PatientDAO pDao = new dao.PatientDAO();
        
        if (!name.isEmpty() && id.isEmpty()) {
            java.util.List<model.Patient> all = pDao.getAllPatients();
            model.Patient found = null;
            for (model.Patient p : all) {
                if (p.getFullName().equalsIgnoreCase(name)) {
                    found = p; break;
                }
            }
            if (found == null) {
                javax.swing.JOptionPane.showMessageDialog(view, "Patient doesn't exist!");
                return;
            }
            id = found.getPatientId();
            name = found.getFullName();
            view.getTxtPatientIdField().setText(id);
        } else if (!id.isEmpty()) {
            model.Patient p = pDao.getPatientById(id);
            if (p == null) {
                javax.swing.JOptionPane.showMessageDialog(view, "Patient doesn't exist!");
                return;
            }
            name = p.getFullName();
            view.getTxtPatientNameField().setText(name);
        } else {
            javax.swing.JOptionPane.showMessageDialog(view, "Please enter Patient ID or Name to search.");
            return;
        }
        
        dao.MedicalRecordDAO mrDao = new dao.MedicalRecordDAO();
        java.util.List<model.MedicalRecord> records = mrDao.getRecordsByPatient(id);
        view.loadMedicalHistory(id, name, records);
    }
}