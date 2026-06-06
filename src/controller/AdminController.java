package controller;

import view.*;
import view.admin;

public class AdminController {

    private admin adminFrame;
    private java.awt.CardLayout cardLayout;
    private javax.swing.JPanel contentPanel;

    // Panels
    private DashboardPanel       dashboardPanel;
    private ManageuserPanel      manageUserPanel;
    private CreateuserPanel      createUserPanel;
    private LogoutPanel          logoutPanel;
    private managedoctoranddepartment doctorDeptPanel;
    private ReportPanel          reportPanel;
    private SchedulePanel        schedulePanel;
    private NotificationPanel    notificationPanel;

    // Controllers
    private DashboardController      dashboardController;
    private ManageUserController     manageUserController;
    private CreateUserController     createUserController;
    private DoctorDeptController     doctorDeptController;
    private ReportController         reportController;
    private ScheduleController       scheduleController;
    private NotificationController   notificationController;
    private LogoutController         logoutController;

    public AdminController(admin adminFrame) {
        this.adminFrame   = adminFrame;
        this.cardLayout   = adminFrame.getCardLayout();
        this.contentPanel = adminFrame.getContentPanel();
        loadPanels();
        initControllers();
        initNavigation();
    }

    private void loadPanels() {
        dashboardPanel    = new DashboardPanel();
        manageUserPanel   = new ManageuserPanel();
        createUserPanel   = new CreateuserPanel();
        logoutPanel       = new LogoutPanel();
        doctorDeptPanel   = new managedoctoranddepartment();
        reportPanel       = new ReportPanel();
        schedulePanel     = new SchedulePanel();
        notificationPanel = new NotificationPanel();

        contentPanel.add(new HomePanel(),   "home");
        contentPanel.add(dashboardPanel,    "dashboard");
        contentPanel.add(schedulePanel,     "schedule");
        contentPanel.add(reportPanel,       "report");
        contentPanel.add(notificationPanel, "notification");
        contentPanel.add(createUserPanel,   "createuser");
        contentPanel.add(manageUserPanel,   "manageuser");
        contentPanel.add(doctorDeptPanel,   "managedoctor");
        contentPanel.add(logoutPanel,       "logout");

        cardLayout.show(contentPanel, "home");
    }

    private void initControllers() {
        dashboardController    = new DashboardController(dashboardPanel);
        manageUserController   = new ManageUserController(manageUserPanel, adminFrame);
        createUserController   = new CreateUserController(createUserPanel, adminFrame);
        doctorDeptController   = new DoctorDeptController(doctorDeptPanel, adminFrame);
        reportController       = new ReportController(reportPanel);
        scheduleController     = new ScheduleController(schedulePanel);
        notificationController = new NotificationController(notificationPanel);
        logoutController       = new LogoutController(logoutPanel, adminFrame, cardLayout, contentPanel);
    }

    private void initNavigation() {
        adminFrame.getBtnHome()
            .addActionListener(e -> cardLayout.show(contentPanel, "home"));

        adminFrame.getBtnDashboard()
            .addActionListener(e -> {
                dashboardController.loadAll();
                cardLayout.show(contentPanel, "dashboard");
            });

        adminFrame.getBtnSchedule()
            .addActionListener(e -> {
                scheduleController.loadAll();
                cardLayout.show(contentPanel, "schedule");
            });

        adminFrame.getBtnReport()
            .addActionListener(e -> {
                reportController.loadAll();
                cardLayout.show(contentPanel, "report");
            });

        adminFrame.getBtnNotification()
            .addActionListener(e -> {
                notificationController.loadAll();
                cardLayout.show(contentPanel, "notification");
            });

        adminFrame.getBtnCreateUser()
            .addActionListener(e -> cardLayout.show(contentPanel, "createuser"));

        adminFrame.getBtnManageUser()
            .addActionListener(e -> {
                manageUserController.loadAllUsers();
                cardLayout.show(contentPanel, "manageuser");
            });

        adminFrame.getBtnManageDoctor()
            .addActionListener(e -> {
                doctorDeptController.loadAllDoctors();
                doctorDeptController.loadAllDepartments();
                cardLayout.show(contentPanel, "managedoctor");
            });

        adminFrame.getBtnLogout()
            .addActionListener(e -> cardLayout.show(contentPanel, "logout"));
    }
}