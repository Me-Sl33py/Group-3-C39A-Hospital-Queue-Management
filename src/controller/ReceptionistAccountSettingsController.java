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
            String secQ = (String) profile[4];
            String secA = (String) profile[5];

            // Update Header and Summary Cards
            view.getLblWelcome().setText("Welcome, " + fullName);
            view.getLblFullNameVal().setText(fullName);
            view.getLblPhoneVal().setText(phone != null ? phone : "N/A");

            // Populate forms
            view.getTxtFullName().setText(fullName);
            view.getTxtPhone().setText(phone != null ? phone : "");
            
            if (secQ != null && !secQ.isEmpty()) {
                view.getCbSecurityQuestion().setSelectedItem(secQ);
            } else {
                view.getCbSecurityQuestion().setSelectedIndex(0);
            }
            view.getTxtSecurityAnswer().setText(secA != null ? secA : "");
            
            // Clear passwords
            view.getTxtCurrentPwd().setText("");
            view.getTxtNewPwd().setText("");
            view.getTxtConfirmPwd().setText("");
        }
    }

    private void saveChanges() {
        String newFullName = view.getTxtFullName().getText().trim();
        String newPhone = view.getTxtPhone().getText().trim();
        String securityQuestion = (String) view.getCbSecurityQuestion().getSelectedItem();
        String securityAnswer = view.getTxtSecurityAnswer().getText().trim();
        
        String currentPwdInput = new String(view.getTxtCurrentPwd().getPassword());
        String newPwdInput = new String(view.getTxtNewPwd().getPassword());
        String confirmPwdInput = new String(view.getTxtConfirmPwd().getPassword());

        if (newFullName.isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame, "Full Name cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (view.getCbSecurityQuestion().getSelectedIndex() == 0 && !securityAnswer.isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame, "Please select a security question.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (view.getCbSecurityQuestion().getSelectedIndex() != 0 && securityAnswer.isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame, "Please enter an answer for your security question.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
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

        String finalSecQ = view.getCbSecurityQuestion().getSelectedIndex() == 0 ? null : securityQuestion;
        String finalSecA = securityAnswer.isEmpty() ? null : securityAnswer;

        boolean success = dao.updateReceptionistProfile(currentUserId, newFullName, newPhone, targetPassword, finalSecQ, finalSecA);

        if (success) {
            JOptionPane.showMessageDialog(mainFrame, "Profile updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadProfileData(); // refresh the UI with newly saved data
        } else {
            JOptionPane.showMessageDialog(mainFrame, "Failed to update profile.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
