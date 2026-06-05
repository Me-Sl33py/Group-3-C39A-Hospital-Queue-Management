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

    public RegisterWalkinController(RegisterWalkinView view) {
        this.view = view;
        if (view.getCbGender() != null) {
            view.getCbGender().setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Gender", "Male", "Female", "Other" }));
        }
        initEventHandlers();
        loadInitialData();
    }

    private void initEventHandlers() {
        // Navigation: Manage Waitlist (Dashboard)
        view.getBtnManageWaitlist().addActionListener(e -> {
            view.dispose();
            DashboardView dashboard = new DashboardView();
            new DashboardController(dashboard);
            dashboard.setVisible(true);
        });

        // Navigation: Generate Token
        view.getBtnGenerateToken().addActionListener(e -> {
            view.dispose();
            GenerateTokenView genView = new GenerateTokenView();
            new GenerateTokenController(genView);
            genView.setVisible(true);
        });

        // Navigation: Sidebar Register Walk-in (current screen, do nothing or dialog)
        view.getBtnRegisterWalkin().addActionListener(e -> {
            // Already on this screen
        });

        view.getBtnAssignDoctor().addActionListener(e -> {
            view.dispose();
            view.AssignToDoctorView assignView = new view.AssignToDoctorView();
            new AssignToDoctorController(assignView);
            assignView.setVisible(true);
        });

        // Navigation: Logout
        view.getBtnLogout().addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(view, "Are you sure you want to log out?", "Logout", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                view.dispose();
                System.exit(0);
            }
        });

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

        // Transition: Open GenerateTokenView with registered patient details
        view.dispose();
        GenerateTokenView genView = new GenerateTokenView();
        new GenerateTokenController(genView, name, dob, gender, phone);
        genView.setVisible(true);
    }
}
