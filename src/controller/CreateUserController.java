package controller;

import dao.UserDAO;
import view.CreateuserPanel;
import javax.swing.*;

public class CreateUserController {

    private CreateuserPanel panel;
    private UserDAO dao;
    private JFrame parentFrame;

    public CreateUserController(CreateuserPanel panel, JFrame parentFrame) {
        this.panel       = panel;
        this.dao         = new UserDAO();
        this.parentFrame = parentFrame;
        initListeners();
        updateShiftVisibility(); // set correct initial state
    }

    private void initListeners() {
        panel.getSaveButton().addActionListener(e -> saveUser());
        panel.getClearButton().addActionListener(e -> clearForm());
        panel.getRoleComboBox().addActionListener(e -> updateShiftVisibility());
    }

    private void updateShiftVisibility() {
        String role = panel.getRoleComboBox().getSelectedItem().toString().trim();
        panel.setShiftVisible("Receptionist".equalsIgnoreCase(role));
    }

    private void saveUser() {
        String rawName   = panel.getNameField().getText().trim();
        String phone     = panel.getNameField1().getText().trim();
        String gender    = panel.getGenderCombobox().getSelectedItem().toString().trim();
        java.util.Date dobDate = panel.getDobField().getDate();
        String dob = dobDate != null ? new java.text.SimpleDateFormat("yyyy-MM-dd").format(dobDate) : "";
        String role      = panel.getRoleComboBox().getSelectedItem().toString().trim();
        String password  = new String(panel.getPasswordField().getPassword()).trim();

        if (rawName.isEmpty() || phone.isEmpty() || dobDate == null || password.isEmpty()) {
            JOptionPane.showMessageDialog(parentFrame, "Please fill in all fields.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Password validation
        if (password.length() < 6) {
            JOptionPane.showMessageDialog(parentFrame, "Password must be at least 6 characters long.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Phone validation
        if (!phone.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(parentFrame, "Phone number must be exactly 10 digits.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Name validation and formatting
        rawName = rawName.replaceAll("(?i)^Dr\\.\\s*", "");
        rawName = rawName.replaceAll("(?i)\\s*Receptionist$", "");

        if (rawName.matches(".*\\d.*")) {
            JOptionPane.showMessageDialog(parentFrame, "Name cannot contain digits.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String[] parts = rawName.split("\\s+");
        if (parts.length < 2) {
            JOptionPane.showMessageDialog(parentFrame, "Full name must contain at least 2 words.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        StringBuilder titleCase = new StringBuilder();
        for (String p : parts) {
            if (p.length() > 0) {
                titleCase.append(p.substring(0, 1).toUpperCase()).append(p.substring(1).toLowerCase()).append(" ");
            }
        }
        String formattedName = titleCase.toString().trim();
        String firstName = parts[0].toLowerCase();
        String username;

        if ("Doctor".equalsIgnoreCase(role)) {
            formattedName = "Dr. " + formattedName;
            username = "doc_" + firstName;
        } else if ("Receptionist".equalsIgnoreCase(role)) {
            formattedName = formattedName + " Receptionist";
            username = firstName + "_receptionist";
        } else {
            username = formattedName.toLowerCase().replace(" ", "_");
        }

        // Shift logic
        String shift = null;
        if ("Receptionist".equalsIgnoreCase(role)) {
            shift = panel.getShiftComboBox().getSelectedItem().toString().trim();
        }

        boolean success = dao.createUser(username, formattedName, phone, gender, dob, role, password, shift);

        if (success) {
            String message =
                "✅ User Created Successfully!\n" +
                "─────────────────────────────\n" +
                "Full Name : " + formattedName + "\n" +
                "Username  : " + username      + "\n" +
                "Phone     : " + phone         + "\n" +
                "Gender    : " + gender        + "\n" +
                "DOB       : " + dob           + "\n" +
                "Role      : " + role          + "\n" +
                (shift != null ? "Shift     : " + shift + "\n" : "") +
                "Status    : Active";
            JOptionPane.showMessageDialog(parentFrame, message, "User Created", JOptionPane.INFORMATION_MESSAGE);
            clearForm();
        } else {
            JOptionPane.showMessageDialog(parentFrame, "Failed to create user.\nUsername might already exist.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        panel.getNameField().setText("");
        panel.getNameField1().setText("");
        panel.getDobField().setDate(null);
        panel.getPasswordField().setText("");
        panel.getGenderCombobox().setSelectedIndex(0);
        panel.getRoleComboBox().setSelectedIndex(0);
        panel.getShiftComboBox().setSelectedIndex(0);
        updateShiftVisibility();
    }
}