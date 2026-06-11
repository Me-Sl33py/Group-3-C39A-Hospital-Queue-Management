package controller;

import dao.ReceptionistDAO;
import view.ReceptionistAccountSettingsView;
import view.WithTabbedPane;
import javax.swing.JOptionPane;

public class ReceptionistAccountSettingsController {

    private final ReceptionistAccountSettingsView view;
    private final WithTabbedPane mainFrame;
    private final ReceptionistDAO dao;
    
    private final int currentUserId; 
    private String currentDbPassword = "";

    public ReceptionistAccountSettingsController(ReceptionistAccountSettingsView view, WithTabbedPane mainFrame, int currentUserId) {
        this.view = view;
        this.mainFrame = mainFrame;
        this.dao = new ReceptionistDAO();
        this.currentUserId = currentUserId;
        
        initEventHandlers();
        loadProfileData();
    }

    private void initEventHandlers() {
        view.getBtnSave().addActionListener(e -> saveChanges());
        view.getBtnCancel().addActionListener(e -> loadProfileData()); // reset fields
    }

    private void loadProfileData() {
        Object[] profile = dao.getReceptionistProfile(currentUserId);
        if (profile[0] != null) {
            String fullName = (String) profile[0];
            String phone = (String) profile[1];
            currentDbPassword = (String) profile[2];
            String empId = (String) profile[3];

            // Update Header and Summary Cards
            view.getLblWelcome().setText("Welcome, " + fullName);
            view.getLblFullNameVal().setText(fullName);
            view.getLblPhoneVal().setText(phone != null ? phone : "N/A");
            view.getLblEmpIdVal().setText(empId != null ? empId : "N/A");
            view.getTxtWorkEmpId().setText(empId != null ? empId : "");

            // Populate forms (keep editable fields blank on load/reset)
            view.getTxtFullName().setText("");
            view.getTxtPhone().setText("");
            view.getCbSecurityQuestion().setSelectedIndex(0);
            view.getTxtSecurityAnswer().setText("");
            
            // Clear passwords
            view.getTxtCurrentPwd().setText("");
            view.getTxtNewPwd().setText("");
            view.getTxtConfirmPwd().setText("");
        }
    }

    private void saveChanges() {
        Object[] profile = dao.getReceptionistProfile(currentUserId);
        String currentFullName = (String) profile[0];
        String currentPhone = (String) profile[1];
        currentDbPassword = (String) profile[2];
        String currentSecQ = (String) profile[4];
        String currentSecA = (String) profile[5];

        String newFullName = view.getTxtFullName().getText().trim();
        String newPhone = view.getTxtPhone().getText().trim();
        String securityQuestion = (String) view.getCbSecurityQuestion().getSelectedItem();
        String securityAnswer = view.getTxtSecurityAnswer().getText().trim();
        
        String currentPwdInput = new String(view.getTxtCurrentPwd().getPassword());
        String newPwdInput = new String(view.getTxtNewPwd().getPassword());
        String confirmPwdInput = new String(view.getTxtConfirmPwd().getPassword());

        // Fallback to current database values if fields are left blank
        String targetFullName = newFullName.isEmpty() ? currentFullName : newFullName;
        String targetPhone = newPhone.isEmpty() ? currentPhone : newPhone;

        if (targetFullName == null || targetFullName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame, "Full Name cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!targetPhone.isEmpty()) {
            if (!targetPhone.matches("^[0-9]+$")) {
                JOptionPane.showMessageDialog(mainFrame, "Phone number can only contain numbers.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (targetPhone.length() != 10) {
                JOptionPane.showMessageDialog(mainFrame, "Phone number must be exactly 10 digits.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        String targetPassword = currentDbPassword;

        // If they entered a new password
        if (!newPwdInput.isEmpty()) {
            if (currentPwdInput.isEmpty()) {
                JOptionPane.showMessageDialog(mainFrame, "Please enter your current password to change it.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!currentPwdInput.equals(currentDbPassword)) {
                JOptionPane.showMessageDialog(mainFrame, "Current password does not match.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!newPwdInput.equals(confirmPwdInput)) {
                JOptionPane.showMessageDialog(mainFrame, "New Password and Confirm Password do not match.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            targetPassword = newPwdInput;
        }

        // Handle security questions logic
        String finalSecQ = currentSecQ;
        String finalSecA = currentSecA;

        if (view.getCbSecurityQuestion().getSelectedIndex() != 0) {
            if (securityAnswer.isEmpty()) {
                JOptionPane.showMessageDialog(mainFrame, "Please enter an answer for your security question.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            finalSecQ = securityQuestion;
            finalSecA = securityAnswer;
        } else if (!securityAnswer.isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame, "Please select a security question.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean success = dao.updateReceptionistProfile(currentUserId, targetFullName, targetPhone, targetPassword, finalSecQ, finalSecA);

        if (success) {
            JOptionPane.showMessageDialog(mainFrame, "Profile updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadProfileData(); // refresh UI to display newly updated name/phone in cards
        } else {
            JOptionPane.showMessageDialog(mainFrame, "Failed to update profile.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
