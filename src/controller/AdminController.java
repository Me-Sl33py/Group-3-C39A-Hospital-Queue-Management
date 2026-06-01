package controller;

import view.admin.*;
import dao.AdminDAO;

public class AdminController {

    private view.admin.admin adminFrame;
    private AdminDAO adminDAO;
    private java.awt.CardLayout cardLayout;
    private javax.swing.JPanel contentPanel;

    public AdminController(view.admin.admin adminFrame) {
        this.adminFrame   = adminFrame;
        this.adminDAO     = new AdminDAO();
        this.cardLayout   = adminFrame.getCardLayout();
        this.contentPanel = adminFrame.getContentPanel();

        loadPanels();
        initNavigation();
    }

    private void loadPanels() {
        contentPanel.add(new HomePanel(),         "home");
        contentPanel.add(new DashboardPanel(),    "dashboard");
        contentPanel.add(new SchedulePanel(),     "schedule");
        contentPanel.add(new ReportPanel(),       "report");
        contentPanel.add(new NotificationPanel(), "notification");
        contentPanel.add(new CreateuserPanel(),   "createuser");
        contentPanel.add(new LogoutPanel(),       "logout");

        cardLayout.show(contentPanel, "home");
    }

    private void initNavigation() {
        adminFrame.getBtnHome()
            .addActionListener(e -> cardLayout.show(contentPanel, "home"));

        adminFrame.getBtnDashboard()
            .addActionListener(e -> cardLayout.show(contentPanel, "dashboard"));

        adminFrame.getBtnSchedule()
            .addActionListener(e -> cardLayout.show(contentPanel, "schedule"));

        adminFrame.getBtnReport()
            .addActionListener(e -> cardLayout.show(contentPanel, "report"));

        adminFrame.getBtnNotification()
            .addActionListener(e -> cardLayout.show(contentPanel, "notification"));

        adminFrame.getBtnCreateUser()
            .addActionListener(e -> cardLayout.show(contentPanel, "createuser"));

        adminFrame.getBtnLogout()
            .addActionListener(e -> cardLayout.show(contentPanel, "logout"));
    }

    private void handleLogout() {
        int ok = javax.swing.JOptionPane.showConfirmDialog(
            adminFrame, "Are you sure you want to logout?",
            "Logout", javax.swing.JOptionPane.YES_NO_OPTION);
        if (ok == javax.swing.JOptionPane.YES_OPTION) {
            adminFrame.dispose();
        }
    }

    public AdminDAO getAdminDAO() { return adminDAO; }
}

