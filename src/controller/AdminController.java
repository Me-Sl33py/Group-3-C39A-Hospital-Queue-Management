package controller;

import view.*;
import view.admin;

public class AdminController {

    private admin adminFrame;
    private java.awt.CardLayout cardLayout;
    private javax.swing.JPanel contentPanel;

    // Panels
    private HomePanel homePanel;
    private ManageuserPanel      manageUserPanel;
    private CreateuserPanel      createUserPanel;
    private LogoutPanel          logoutPanel;
    private managedoctoranddepartment doctorDeptPanel;
    private ReportPanel          reportPanel;
    private SchedulePanel        schedulePanel;
    private NotificationPanel    notificationPanel;

    // Controllers
    private HomeController homeController;
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
        adminFrame.setActiveButton(adminFrame.getBtnHome());
    }

    private void loadPanels() {
        manageUserPanel   = new ManageuserPanel();
        createUserPanel   = new CreateuserPanel();
        logoutPanel       = new LogoutPanel();
        doctorDeptPanel   = new managedoctoranddepartment();
        reportPanel       = new ReportPanel();
        schedulePanel     = new SchedulePanel();
        notificationPanel = new NotificationPanel();

        
        homePanel = new HomePanel();
        contentPanel.add(homePanel, "home");
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
    manageUserController   = new ManageUserController(manageUserPanel, adminFrame);
    createUserController   = new CreateUserController(createUserPanel, adminFrame);
    doctorDeptController   = new DoctorDeptController(doctorDeptPanel, adminFrame);
    reportController       = new ReportController(reportPanel);
    scheduleController     = new ScheduleController(schedulePanel);
    notificationController = new NotificationController(notificationPanel);
    logoutController       = new LogoutController(logoutPanel, adminFrame, cardLayout, contentPanel);
    homeController = new HomeController(homePanel);
    homeController.loadAll();
}

    private void initNavigation() {
        
      adminFrame.getBtnHome()
       .addActionListener(e -> {
         adminFrame.setActiveButton(adminFrame.getBtnHome());
         homeController.loadAll();
        cardLayout.show(contentPanel, "home");
    });
        adminFrame.getBtnSchedule()
            .addActionListener(e -> {
                adminFrame.setActiveButton(adminFrame.getBtnSchedule());
                scheduleController.loadAll();
                cardLayout.show(contentPanel, "schedule");
            });

        adminFrame.getBtnReport()
            .addActionListener(e -> {
                adminFrame.setActiveButton(adminFrame.getBtnReport());
                reportController.loadAll();
                cardLayout.show(contentPanel, "report");
            });

        adminFrame.getBtnNotification()
            .addActionListener(e -> {
                adminFrame.setActiveButton(adminFrame.getBtnNotification());
                notificationController.loadAll();
                cardLayout.show(contentPanel, "notification");
            });

        adminFrame.getBtnCreateUser()
            .addActionListener(e -> {
                adminFrame.setActiveButton(adminFrame.getBtnCreateUser());
                cardLayout.show(contentPanel, "createuser");
            });

        adminFrame.getBtnManageUser()
            .addActionListener(e -> {
                adminFrame.setActiveButton(adminFrame.getBtnManageUser());
                manageUserController.loadAllUsers();
                cardLayout.show(contentPanel, "manageuser");
            });

        adminFrame.getBtnManageDoctor()
            .addActionListener(e -> {
                adminFrame.setActiveButton(adminFrame.getBtnManageDoctor());
                doctorDeptController.loadAllDoctors();
                doctorDeptController.loadAllDepartments();
                cardLayout.show(contentPanel, "managedoctor");
            });

        adminFrame.getBtnLogout()
            .addActionListener(e -> {
                adminFrame.setActiveButton(adminFrame.getBtnLogout());
                cardLayout.show(contentPanel, "logout");
            });
    }
}