package controller;

import view.RegisterWalkinView;
import view.GenerateTokenView;
import view.DashboardView;
import javax.swing.*;

/**
 * Controller class for the Register Walk-in Screen.
 * Implements MVC logic for patient form input, resetting, and view navigation.
 */
public class RegisterWalkinController {
    private final RegisterWalkinView view;
    private final view.WithTabbedPane mainFrame;

    public RegisterWalkinController(RegisterWalkinView view, view.WithTabbedPane mainFrame) {
        this.view = view;
        this.mainFrame = mainFrame;
        if (view.getCbGender() != null) {
            view.getCbGender().setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Gender", "Male", "Female", "Other" }));
        }
        initEventHandlers();
        loadInitialData();
    }

    private void initEventHandlers() {

        // Form: Reset Button
        view.getBtnReset().addActionListener(e -> {
            resetFields();
        });

        // Form: Save & Continue Button
        view.getBtnSaveContinue().addActionListener(e -> {
            saveAndContinue();
        });
    }

    private void loadInitialData() {
        // Default statistic counter matching mockup
        view.getLblLoadVal().setText("14");
        resetFields();
    }

    private void resetFields() {
        view.getTfName().setText("");
        view.getTfDob().setText("");
        view.getCbGender().setSelectedIndex(0);
        view.getTfPhone().setText("");
        view.getTaReason().setText("");
    }

    private void saveAndContinue() {
        String name = view.getTfName().getText().trim();
        String dob = view.getTfDob().getText().trim();
        String gender = (String) view.getCbGender().getSelectedItem();
        String phone = view.getTfPhone().getText().trim();
        String reason = view.getTaReason().getText().trim();

        // Simple validation
        if (name.isEmpty() || dob.isEmpty() || phone.isEmpty() || reason.isEmpty() || gender.equals("Select Gender")) {
            JOptionPane.showMessageDialog(view, "Please fill in all details accurately before saving.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Success message
        JOptionPane.showMessageDialog(view, "Patient registered successfully!\nProceeding to Token Generation...", "Success", JOptionPane.INFORMATION_MESSAGE);

        // Transition: Switch to Generate Token tab and update patient details
        mainFrame.getGenerateTokenController().updatePatientDetails(name, dob, gender, phone);
        mainFrame.switchToTab(2);
    }
}
