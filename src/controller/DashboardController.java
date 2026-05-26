package controller;

import view.DashboardView;
import javax.swing.JOptionPane;

/**
 * Controller class for the Reception Dashboard.
 * Coordinates user interactions from the DashboardView and updates the database/model.
 */
public class DashboardController {
    private final DashboardView view;

    public DashboardController(DashboardView view) {
        this.view = view;
        initEventHandlers();
        loadInitialData();
    }

    private void initEventHandlers() {
        view.getBtnRegisterWalkin().addActionListener(e -> {
            JOptionPane.showMessageDialog(view, "Register Walk-in Action Triggered", "Hospicare", JOptionPane.INFORMATION_MESSAGE);
        });

        view.getBtnGenerateToken().addActionListener(e -> {
            JOptionPane.showMessageDialog(view, "Generate Token Action Triggered", "Hospicare", JOptionPane.INFORMATION_MESSAGE);
        });

        view.getBtnAssignDoctor().addActionListener(e -> {
            JOptionPane.showMessageDialog(view, "Assign to Doctor Action Triggered", "Hospicare", JOptionPane.INFORMATION_MESSAGE);
        });

        view.getBtnManageWaitlist().addActionListener(e -> {
            JOptionPane.showMessageDialog(view, "Manage Waitlist Selected", "Hospicare", JOptionPane.INFORMATION_MESSAGE);
        });

        view.getBtnLogout().addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(view, "Are you sure you want to log out?", "Logout", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                view.dispose();
                System.exit(0);
            }
        });

        view.getBtnNewPatientReg().addActionListener(e -> {
            JOptionPane.showMessageDialog(view, "New Patient Registration Form will open", "Hospicare", JOptionPane.INFORMATION_MESSAGE);
        });

        view.getBtnGenEmergency().addActionListener(e -> {
            JOptionPane.showMessageDialog(view, "Emergency Token successfully generated and added to priority queue", "Hospicare", JOptionPane.WARNING_MESSAGE);
        });

        view.getBtnDailyReport().addActionListener(e -> {
            JOptionPane.showMessageDialog(view, "Generating Daily Queue Report PDF...", "Hospicare", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    private void loadInitialData() {
        // Sets dynamic dashboard label text and values (could be retrieved from a DB model later)
        view.getLblTotalVal().setText("1,284");
        view.getLblTokensVal().setText("156");
        view.getLblWaitingVal().setText("12");
        view.getLblDoctorsVal().setText("08/10");
        
        // Setup capacities
        view.getPbCardio().setValue(85);
        view.getLblCardioVal().setText("85% Capacity");
        
        view.getPbOrtho().setValue(40);
        view.getLblOrthoVal().setText("40% Capacity");
        
        view.getPbPediatrics().setValue(62);
        view.getLblPediatricsVal().setText("62% Capacity");
    }
}
