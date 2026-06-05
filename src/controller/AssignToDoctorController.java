package controller;

import view.AssignToDoctorView;
import view.DashboardView;
import view.RegisterWalkinView;
import view.GenerateTokenView;

import javax.swing.*;

public class AssignToDoctorController {
    private final AssignToDoctorView view;
    private final view.WithTabbedPane mainFrame;

    public AssignToDoctorController(AssignToDoctorView view, view.WithTabbedPane mainFrame) {
        this.view = view;
        this.mainFrame = mainFrame;
        initEventHandlers();
    }

    private void initEventHandlers() {        view.getBtnAssignPatient().addActionListener(e -> {
            String doctor = (String) view.getCbDoctors().getSelectedItem();
            if (doctor == null || doctor.equals("Select an available doctor")) {
                JOptionPane.showMessageDialog(view, "Please select a doctor to assign the patient.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(view, "Patient successfully assigned to " + doctor + " in selected room.", "Success", JOptionPane.INFORMATION_MESSAGE);
            
            // Redirect to dashboard after assigning
            mainFrame.switchToTab(0);
        });
    }
}
