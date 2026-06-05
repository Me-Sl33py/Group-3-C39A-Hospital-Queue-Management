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
    private final view.WithTabbedPane mainFrame;

    public DashboardController(DashboardView view, view.WithTabbedPane mainFrame) {
        this.view = view;
        this.mainFrame = mainFrame;
        initEventHandlers();
        loadInitialData();
    }

    // Main entry point logic should now be managed by WithTabbedPane, not here.

    private void initEventHandlers() {

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