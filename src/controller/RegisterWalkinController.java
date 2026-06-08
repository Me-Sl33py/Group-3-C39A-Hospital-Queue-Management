package controller;

import view.RegisterWalkinView;
import javax.swing.*;

public class RegisterWalkinController {
    private final RegisterWalkinView view;
    private final view.WithTabbedPane mainFrame;

    public RegisterWalkinController(RegisterWalkinView view, view.WithTabbedPane mainFrame) {
        this.view = view;
        this.mainFrame = mainFrame;
        if (view.getCbGender() != null) {
            view.getCbGender().setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Gender", "Male", "Female", "Others" }));
        }
        initEventHandlers();
        loadInitialData();
    }

    private void initEventHandlers() {
        view.getBtnReset().addActionListener(e -> resetFields());
        view.getBtnSaveContinue().addActionListener(e -> saveAndContinue());
    }

    private void loadInitialData() {
        refreshData();
        resetFields();
    }

    public void refreshData() {
        dao.TokenDAO tokenDAO = new dao.TokenDAO();
        int totalWaiting = tokenDAO.countTotalWaiting();
        view.getLblLoadVal().setText(String.valueOf(totalWaiting));
    }

    private void resetFields() {
        view.getTfName().setText("");
        view.getTfDob().setDate(null);
        view.getCbGender().setSelectedIndex(0);
        view.getTfPhone().setText("");
        view.getCbBloodGroup().setSelectedIndex(0);
    }

    private void saveAndContinue() {
        String name = view.getTfName().getText().trim();
        java.util.Date selectedDob = view.getTfDob().getDate();
        String gender = (String) view.getCbGender().getSelectedItem();
        String phone = view.getTfPhone().getText().trim();
        String bloodGroup = (String) view.getCbBloodGroup().getSelectedItem();

        if (name.isEmpty() || selectedDob == null || phone.isEmpty() || gender.equals("Select Gender")) {
            JOptionPane.showMessageDialog(view, "Please fill in all details accurately before saving.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        java.sql.Date sqlDob = new java.sql.Date(selectedDob.getTime());
        
        java.util.Calendar dobCal = java.util.Calendar.getInstance();
        dobCal.setTime(selectedDob);
        java.util.Calendar today = java.util.Calendar.getInstance();
        int age = today.get(java.util.Calendar.YEAR) - dobCal.get(java.util.Calendar.YEAR);
        if (today.get(java.util.Calendar.DAY_OF_YEAR) < dobCal.get(java.util.Calendar.DAY_OF_YEAR)) {
            age--;
        }

        // Generate a short ID to fit inside VARCHAR(10) or VARCHAR(15)
        String patientId = "P-" + (100000 + (int)(Math.random() * 899999));
        model.Patient patient = new model.Patient(patientId, name, sqlDob, age, gender, phone, "", bloodGroup, "", new java.sql.Timestamp(System.currentTimeMillis()));

        dao.PatientDAO patientDAO = new dao.PatientDAO();
        String savedId = patientDAO.insertPatient(patient);

        if (savedId != null) {
            JOptionPane.showMessageDialog(view, "Patient registered successfully!\nProceeding to Token Generation...", "Success", JOptionPane.INFORMATION_MESSAGE);
            mainFrame.getGenerateTokenController().updatePatientDetails(savedId, name, String.valueOf(age), gender, phone);
            mainFrame.switchToTab(2);
            resetFields();
        } else {
            JOptionPane.showMessageDialog(view, "Database Error: Could not save patient.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
