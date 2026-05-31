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
 
/**
 * DoctorController — ALL action logic lives here.
 * The view has zero business logic; the controller reads/writes it via getters.
 *
 * How to use:
 *   DoctorPanel view = new DoctorPanel();
 *   new DoctorController(view);   // wires everything
 *   view.setVisible(true);
 */
public class DoctorController {
 
    // ── Dependencies ──────────────────────────────────────────────────────────
    private final DoctorPanel       view;
    private final PatientDAO        patientDAO;
    private final MedicalRecordDAO  recordDAO;
    private final DoctorDAO         doctorDAO;
 
    // ── Session state ─────────────────────────────────────────────────────────
    /** The logged-in doctor. Set this after login before opening the panel. */
    private Doctor currentDoctor;
 
    /** The patient currently in consultation. */
    private Patient activePatient;
 
    // =========================================================================
    // Constructor — wires all buttons
    // =========================================================================
    public DoctorController(DoctorPanel view) {
        this.view       = view;
        this.patientDAO = new PatientDAO();
        this.recordDAO  = new MedicalRecordDAO();
        this.doctorDAO  = new DoctorDAO();
 
        // Ensure tables exist
        patientDAO.createTableIfNotExists();
        recordDAO.createTableIfNotExists();
        doctorDAO.createTableIfNotExists();
 
        attachListeners();
        loadQueueTable();       // populate Tab 1 on startup
    }
 
    // =========================================================================
    // Wire buttons to action methods
    // =========================================================================
    private void attachListeners() {
 
        // ── Tab 1 : dashboard "Call Next Patient" card button ─────────────────
        view.getBtnCallNextDashboard().addActionListener(e -> callNextPatient());
 
        // ── Tab 2 : active consultation ───────────────────────────────────────
        view.getBtnCallNext().addActionListener(e -> callNextPatient());
        view.getBtnEndSession().addActionListener(e -> endSession());
        view.getBtnViewFullQueue().addActionListener(e -> loadQueueTable());
 
        // ── Tab 3 : medical record form ───────────────────────────────────────
        view.getBtnSubmitRecord().addActionListener(e -> submitMedicalRecord());
        view.getBtnCancelRecord().addActionListener(e -> clearRecordForm());
 
        // ── Tab 4 : account settings ──────────────────────────────────────────
        view.getBtnSave().addActionListener(e -> saveAccountChanges());
        view.getBtnCancelAccount().addActionListener(e -> loadAccountData());
 
        // ── Sidebar : logout ──────────────────────────────────────────────────
        view.getBtnLogout().addActionListener(e -> logout());
    }
 
    // =========================================================================
    // Tab 1 — My Queue
    // =========================================================================
 
    /**
     * Fetches all patients from the database and populates jTable2 (queue table).
     */
    public void loadQueueTable() {
        List<Patient> patients = patientDAO.getAllPatients();
 
        DefaultTableModel model = (DefaultTableModel) view.getQueueTable().getModel();
        model.setRowCount(0); // clear existing rows
 
        for (Patient p : patients) {
            model.addRow(new Object[]{
                p.getPatientId(),
                p.getName(),
                p.getStatus(),
                "View File"
            });
        }
    }
 
    // =========================================================================
    // Tab 2 — Call Next Patient
    // =========================================================================
 
    /**
     * Pulls the next waiting patient from DB, marks them as active,
     * and updates the Active Consultation panel.
     */
    public void callNextPatient() {
        Patient next = patientDAO.getNextWaitingPatient();
 
        if (next == null) {
            JOptionPane.showMessageDialog(view,
                    "No more patients in the queue.",
                    "Queue Empty",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
 
        // Mark the old active patient as Completed (if any)
        if (activePatient != null) {
            patientDAO.updatePatientStatus(activePatient.getPatientId(), "Completed");
            addRowToHistory(activePatient);
        }
 
        // Update status in DB to "In Progress" (so it no longer shows as Waiting)
        patientDAO.updatePatientStatus(next.getPatientId(), "In Progress");
        activePatient = next;
 
        // Update the UI labels
        view.getLblActivePatientName().setText(next.getName());
        view.getLblActivePatientId().setText("#HOSP-" + next.getPatientId());
 
        // Also update Tab 3 patient info
        view.getLblRecordPatientId().setText("Patient ID:  #HOSP-" + next.getPatientId());
        view.getLblRecordPatientName().setText("Patient Name:  " + next.getName());
 
        // Refresh the queue table
        loadQueueTable();
    }
 
    /**
     * Ends the current session without calling the next patient.
     */
    public void endSession() {
        if (activePatient == null) {
            JOptionPane.showMessageDialog(view,
                    "No active consultation to end.",
                    "No Session",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
 
        patientDAO.updatePatientStatus(activePatient.getPatientId(), "Completed");
        addRowToHistory(activePatient);
        activePatient = null;
 
        view.getLblActivePatientName().setText("—");
        view.getLblActivePatientId().setText("—");
        loadQueueTable();
    }
 
    /** Adds a completed patient row to the session history table. */
    private void addRowToHistory(Patient patient) {
        DefaultTableModel historyModel =
                (DefaultTableModel) view.getSessionHistoryTable().getModel();
 
        String time = java.time.LocalTime.now()
                .format(java.time.format.DateTimeFormatter.ofPattern("hh:mm a"));
 
        historyModel.addRow(new Object[]{
            time,
            patient.getName(),
            "COMPLETED",
            "View File"
        });
    }
 
    // =========================================================================
    // Tab 3 — Add Medical Records
    // =========================================================================
 
    /**
     * Reads the clinical notes from the text area, builds a MedicalRecord,
     * and inserts it into the database.
     */
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
 
        int doctorId = (currentDoctor != null) ? currentDoctor.getDoctorId() : 0;
 
        MedicalRecord record = new MedicalRecord();
        record.setPatientId(activePatient.getPatientId());
        record.setPatientName(activePatient.getName());
        record.setDoctorId(doctorId);
        record.setClinicalNotes(notes);
 
        boolean saved = recordDAO.insertRecord(record);
 
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
 
    /** Clears the clinical notes text area. */
    public void clearRecordForm() {
        view.getTaMessage().setText(
            "Enter detailed clinical notes, patient history update, " +
            "and recommended next steps...");
    }
 
    // =========================================================================
    // Tab 4 — Account Settings
    // =========================================================================
 
    /**
     * Loads the current doctor's data from the DB and populates the form fields.
     * Call this after setting currentDoctor.
     */
    public void loadAccountData() {
        if (currentDoctor == null) return;
 
        Doctor d = doctorDAO.getDoctorById(currentDoctor.getDoctorId());
        if (d == null) return;
 
        currentDoctor = d; // refresh
 
        view.getTxtFullName().setText(d.getFullName());
        view.getTxtEmail().setText(d.getEmail());
        view.getTxtPhone().setText(d.getPhone());
        view.getTxtSpecialization().setText(d.getSpecialization());
        view.getTxtRoom().setText(d.getAssignedRoom());
        view.getLblShiftHoursVal().setText(d.getShiftHours());
        view.getLblDoctorIdVal().setText("#DOC-" + d.getDoctorId());
        view.getLblSecurityLevelVal().setText(d.getSecurityLevel());
        view.getLblAccountStatusVal().setText(d.getAccountStatus());
        view.getLblLastLoginVal().setText(d.getLastLogin());
    }
 
    /**
     * Reads form fields and persists changes to the database.
     */
    public void saveAccountChanges() {
        if (currentDoctor == null) {
            JOptionPane.showMessageDialog(view,
                    "No doctor session found.",
                    "Session Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
 
        currentDoctor.setFullName(view.getTxtFullName().getText().trim());
        currentDoctor.setEmail(view.getTxtEmail().getText().trim());
        currentDoctor.setPhone(view.getTxtPhone().getText().trim());
        currentDoctor.setSpecialization(view.getTxtSpecialization().getText().trim());
        currentDoctor.setAssignedRoom(view.getTxtRoom().getText().trim());
 
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
    }
 
    public Doctor getCurrentDoctor() {
        return currentDoctor;
    }
}
 