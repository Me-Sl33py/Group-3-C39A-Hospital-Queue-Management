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

        // Phone validation: exactly 10 digits
        if (!phone.matches("^\\d{10}$")) {
            JOptionPane.showMessageDialog(view, "Phone number must be exactly 10 digits.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Name validation: cannot start with a digit
        if (Character.isDigit(name.charAt(0))) {
            JOptionPane.showMessageDialog(view, "Name cannot start with a digit.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Name validation: at least two words
        String[] nameParts = name.split("\\s+");
        if (nameParts.length < 2) {
            JOptionPane.showMessageDialog(view, "Please enter full name (at least two words).", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Capitalize first letter of each word
        StringBuilder formattedName = new StringBuilder();
        for (String part : nameParts) {
            if (!part.isEmpty()) {
                formattedName.append(Character.toUpperCase(part.charAt(0)))
                             .append(part.substring(1).toLowerCase())
                             .append(" ");
            }
        }
        name = formattedName.toString().trim();
        view.getTfName().setText(name);

        java.sql.Date sqlDob = new java.sql.Date(selectedDob.getTime());
        
        java.util.Calendar dobCal = java.util.Calendar.getInstance();
        dobCal.setTime(selectedDob);
        java.util.Calendar today = java.util.Calendar.getInstance();
        int age = today.get(java.util.Calendar.YEAR) - dobCal.get(java.util.Calendar.YEAR);
        if (today.get(java.util.Calendar.DAY_OF_YEAR) < dobCal.get(java.util.Calendar.DAY_OF_YEAR)) {
            age--;
        }

        dao.PatientDAO patientDAO = new dao.PatientDAO();
        String patientId = patientDAO.generatePatientId();
        model.Patient patient = new model.Patient(patientId, name, sqlDob, age, gender, phone, "", bloodGroup, "", new java.sql.Timestamp(System.currentTimeMillis()));

        String savedId = patientDAO.insertPatient(patient);

        if (savedId != null) {
            JOptionPane.showMessageDialog(view, "Account is created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            mainFrame.getGenerateTokenController().updatePatientDetails(savedId, name, String.valueOf(age), gender, phone);
            mainFrame.switchToTab(2);
            resetFields();
        } else {
            JOptionPane.showMessageDialog(view, "Database Error: Could not save patient.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
