package controller;

import view.AssignToDoctorView;
import view.DashboardView;
import view.RegisterWalkinView;
import view.GenerateTokenView;

import javax.swing.*;

public class AssignToDoctorController {
    private final AssignToDoctorView view;

    public AssignToDoctorController(AssignToDoctorView view) {
        this.view = view;
        initEventHandlers();
    }

    private void initEventHandlers() {
        view.getBtnManageWaitlist().addActionListener(e -> {
            view.dispose();
            DashboardView dashboard = new DashboardView();
            new DashboardController(dashboard);
            dashboard.setVisible(true);
        });

        view.getBtnRegisterWalkin().addActionListener(e -> {
            view.dispose();
            RegisterWalkinView walkinView = new RegisterWalkinView();
            new RegisterWalkinController(walkinView);
            walkinView.setVisible(true);
        });

        view.getBtnGenerateToken().addActionListener(e -> {
            view.dispose();
            GenerateTokenView genView = new GenerateTokenView();
            new GenerateTokenController(genView);
            genView.setVisible(true);
        });
        
        view.getBtnLogout().addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(view, "Are you sure you want to log out?", "Logout", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                view.dispose();
                System.exit(0);
            }
        });

        view.getBtnAssignPatient().addActionListener(e -> {
            String doctor = (String) view.getCbDoctors().getSelectedItem();
            if (doctor == null || doctor.equals("Select an available doctor")) {
                JOptionPane.showMessageDialog(view, "Please select a doctor to assign the patient.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(view, "Patient successfully assigned to " + doctor + " in selected room.", "Success", JOptionPane.INFORMATION_MESSAGE);
            
            // Redirect to dashboard after assigning
            view.dispose();
            DashboardView dashboard = new DashboardView();
            new DashboardController(dashboard);
            dashboard.setVisible(true);
        });
    }
}
