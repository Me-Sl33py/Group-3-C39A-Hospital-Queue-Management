package controller;

import dao.ReceptionistDAO;
import view.ReceptionistAccountSettingsView;
import view.WithTabbedPane;
import javax.swing.JOptionPane;

public class ReceptionistAccountSettingsController {

    private final ReceptionistAccountSettingsView view;
    private final WithTabbedPane mainFrame;
    private final ReceptionistDAO dao;
    
    // We hardcode userId 3 (Ram Receptionist) since there is no login manager yet
    private final int currentUserId = 3; 
    private String currentDbPassword = "";

    public ReceptionistAccountSettingsController(ReceptionistAccountSettingsView view, WithTabbedPane mainFrame) {
        this.view = view;
        this.mainFrame = mainFrame;
        this.dao = new ReceptionistDAO();
        
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

            // Populate forms
            view.getTxtFullName().setText("");
            view.getTxtPhone().setText("");
            
            // Clear passwords
            view.getTxtCurrentPwd().setText("");
            view.getTxtNewPwd().setText("");
            view.getTxtConfirmPwd().setText("");
        }
    }

    private void saveChanges() {
        String newFullName = view.getTxtFullName().getText().trim();
        String newPhone = view.getTxtPhone().getText().trim();
        
        String currentPwdInput = new String(view.getTxtCurrentPwd().getPassword());
        String newPwdInput = new String(view.getTxtNewPwd().getPassword());
        String confirmPwdInput = new String(view.getTxtConfirmPwd().getPassword());

        if (newFullName.isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame, "Full Name cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
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

        boolean success = dao.updateReceptionistProfile(currentUserId, newFullName, newPhone, targetPassword);

        if (success) {
            JOptionPane.showMessageDialog(mainFrame, "Profile updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadProfileData(); // refresh the UI with newly saved data
        } else {
            JOptionPane.showMessageDialog(mainFrame, "Failed to update profile.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
