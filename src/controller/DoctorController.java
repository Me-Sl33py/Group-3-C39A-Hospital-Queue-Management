/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.DoctorDAO;
import dao.MedicalRecordDAO;
import dao.PatientDao;
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
    private final PatientDao       patientDAO;
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
        this.patientDAO = new PatientDao();
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
    }

    // =========================================================================
    // Wire buttons to action methods
    // =========================================================================
    private void attachListeners() {

        // ── Sidebar navigation ────────────────────────────────────────────────
        view.getBtnMyQueue().addActionListener(e -> view.getTabbedPane().setSelectedIndex(0));
        view.getBtnCallNextPatient().addActionListener(e -> view.getTabbedPane().setSelectedIndex(1));
        view.getBtnAddRecords().addActionListener(e -> view.getTabbedPane().setSelectedIndex(2));
        view.getBtnAccount().addActionListener(e -> view.getTabbedPane().setSelectedIndex(3));

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

        // ── Tab 4 : account settings ──────────────────────────────────────────
        view.getBtnSave().addActionListener(e -> saveAccountChanges());
        view.getBtnCancelAccount().addActionListener(e -> loadAccountData());

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
        view.getLblRemainingCount().setText("You have " + (waiting + skipped + inConsultation) + " patients remaining in your daily queue");
        
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

        String notes = view.getTaMessage().getText().trim();
        if (notes.isEmpty() ||
            notes.equals("Enter detailed clinical notes, patient history update, " +
                         "and recommended next steps...")) {
            JOptionPane.showMessageDialog(view,
                    "Please enter clinical notes before submitting.",
                    "Empty Notes",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String doctorId = (currentDoctor != null) ? currentDoctor.getDoctorId() : "";

        // Use PatientDao to save it because it looks up the correct appointment_id from the queue
        boolean saved = patientDAO.saveMedicalRecord(activePatient.getPatientId(), doctorId, notes);

        if (saved) {
            JOptionPane.showMessageDialog(view,
                    "Medical record saved successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            clearRecordForm();
        } else {
            JOptionPane.showMessageDialog(view,
                    "Failed to save medical record. Please try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void clearRecordForm() {
        view.getTaMessage().setText(
            "Enter detailed clinical notes, patient history update, " +
            "and recommended next steps...");
    }

    public void updateMedicalRecordTab() {
        if (activePatient != null) {
            view.getTxtPatientIdField().setText(activePatient.getPatientId());
            view.getTxtPatientNameField().setText(activePatient.getFullName());
            view.getTxtPatientIdField().setEditable(false);
            view.getTxtPatientNameField().setEditable(false);
        } else {
            view.getTxtPatientIdField().setText("");
            view.getTxtPatientNameField().setText("");
        }
    }

    // =========================================================================
    // Tab 4 — Account Settings
    // =========================================================================
    public void loadAccountData() {
    if (currentDoctor == null) return;

    Doctor d = doctorDAO.getDoctorById(currentDoctor.getDoctorId());
    if (d == null) return;

    currentDoctor = d;

    view.getTxtFullName().setText(d.getFullName());
    view.getTxtPhone().setText(d.getContactNumber());
    view.getTxtSpecialization().setText(d.getSpecialization());
    view.getTxtRoom().setText(d.getDepartmentName() != null ? d.getDepartmentName() : ""); // ADD THIS
    view.getLblDoctorIdVal().setText(d.getDoctorId());
    view.getLblAccountStatusVal().setText(d.getAvailability());
}

    public void saveAccountChanges() {
        if (currentDoctor == null) {
            JOptionPane.showMessageDialog(view,
                    "No doctor session found.",
                    "Session Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        currentDoctor.setFullName(view.getTxtFullName().getText().trim());
        currentDoctor.setContactNumber(view.getTxtPhone().getText().trim());
        currentDoctor.setSpecialization(view.getTxtSpecialization().getText().trim());

        boolean updated = doctorDAO.updateDoctorProfile(currentDoctor);

        if (updated) {
            JOptionPane.showMessageDialog(view,
                    "Profile updated successfully.",
                    "Saved",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(view,
                    "Failed to update profile. Please try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
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
            // TODO: open your LoginFrame here, e.g.:
            // new LoginFrame().setVisible(true);
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
}