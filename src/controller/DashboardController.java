package controller;

import view.DashboardView;
import view.GenerateTokenView;
import view.RegisterWalkinView;
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

    // Method to start the dashboard
    public static void startApplication() {
        DashboardView view = new DashboardView();
        new DashboardController(view);
        view.setVisible(true);
    }

    private void initEventHandlers() {

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

        view.getBtnAssignDoctor().addActionListener(e -> {
            JOptionPane.showMessageDialog(view,
                    "Assign to Doctor Action Triggered",
                    "Hospicare",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        view.getBtnManageWaitlist().addActionListener(e -> {
            JOptionPane.showMessageDialog(view,
                    "Manage Waitlist Selected",
                    "Hospicare",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        view.getBtnLogout().addActionListener(e -> {

            int option = JOptionPane.showConfirmDialog(
                    view,
                    "Are you sure you want to log out?",
                    "Logout",
                    JOptionPane.YES_NO_OPTION
            );

            if (option == JOptionPane.YES_OPTION) {
                view.dispose();
                System.exit(0);
            }
        });

        view.getBtnNewPatientReg().addActionListener(e -> {
            JOptionPane.showMessageDialog(view,
                    "New Patient Registration Form will open",
                    "Hospicare",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        view.getBtnGenEmergency().addActionListener(e -> {
            JOptionPane.showMessageDialog(view,
                    "Emergency Token successfully generated and added to priority queue",
                    "Hospicare",
                    JOptionPane.WARNING_MESSAGE);
        });

        view.getBtnDailyReport().addActionListener(e -> {
            JOptionPane.showMessageDialog(view,
                    "Generating Daily Queue Report PDF...",
                    "Hospicare",
                    JOptionPane.INFORMATION_MESSAGE);
        });
    }

    private void loadInitialData() {

        view.getLblTotalVal().setText("1,284");
        view.getLblTokensVal().setText("156");
        view.getLblWaitingVal().setText("12");
        view.getLblDoctorsVal().setText("08/10");

        view.getPbCardio().setValue(85);
        view.getLblCardioVal().setText("85% Capacity");

        view.getPbOrtho().setValue(40);
        view.getLblOrthoVal().setText("40% Capacity");

        view.getPbPediatrics().setValue(62);
        view.getLblPediatricsVal().setText("62% Capacity");
    }
}