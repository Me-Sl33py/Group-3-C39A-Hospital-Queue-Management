package controller;

import view.WithTabbedPane;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class MainController {

    private final WithTabbedPane mainFrame;

    // Sub-controllers
    private DashboardController dashboardController;
    private RegisterWalkinController registerWalkinController;
    private GenerateTokenController generateTokenController;
    private AssignToDoctorController assignToDoctorController;
    private ReceptionistAccountSettingsController receptionistAccountSettingsController;

    public MainController(WithTabbedPane mainFrame) {
        this.mainFrame = mainFrame;
        
        initComponents();
        initEventHandlers();
        
        // Set default active tab
        mainFrame.getJTabbedPane1().setSelectedIndex(0);
        updateSidebarSelection(mainFrame.getBtnManageWaitlist1());
    }

    private void initComponents() {
        // Instantiate the sub-controllers by passing their respective panels and the main frame
        this.dashboardController = new DashboardController(mainFrame.getDashboardView(), mainFrame);
        this.registerWalkinController = new RegisterWalkinController(mainFrame.getRegisterWalkinView(), mainFrame);
        this.generateTokenController = new GenerateTokenController(mainFrame.getGenerateTokenView(), mainFrame);
        this.assignToDoctorController = new AssignToDoctorController(mainFrame.getAssignToDoctorView(), mainFrame);
        this.receptionistAccountSettingsController = new ReceptionistAccountSettingsController(mainFrame.getReceptionistAccountSettingsView(), mainFrame);
        
        // Pass sub-controllers to main frame just in case other parts of the application need them
        mainFrame.setDashboardController(this.dashboardController);
        mainFrame.setRegisterWalkinController(this.registerWalkinController);
        mainFrame.setGenerateTokenController(this.generateTokenController);
        mainFrame.setAssignToDoctorController(this.assignToDoctorController);
    }

    private void initEventHandlers() {
        mainFrame.getBtnRegisterWalkin().addActionListener(e -> {
            mainFrame.getJTabbedPane1().setSelectedIndex(1);
            updateSidebarSelection(mainFrame.getBtnRegisterWalkin());
        });
        
        mainFrame.getBtnGenerateToken().addActionListener(e -> {
            mainFrame.getJTabbedPane1().setSelectedIndex(2);
            updateSidebarSelection(mainFrame.getBtnGenerateToken());
        });
        
        mainFrame.getBtnAssignDoctor().addActionListener(e -> {
            mainFrame.getJTabbedPane1().setSelectedIndex(3);
            updateSidebarSelection(mainFrame.getBtnAssignDoctor());
        });
        
        mainFrame.getBtnManageWaitlist1().addActionListener(e -> {
            mainFrame.getJTabbedPane1().setSelectedIndex(0);
            updateSidebarSelection(mainFrame.getBtnManageWaitlist1());
        });
        
        mainFrame.getBtnAccounts().addActionListener(e -> {
            mainFrame.getJTabbedPane1().setSelectedIndex(4);
            updateSidebarSelection(mainFrame.getBtnAccounts());
        });
        
        mainFrame.getBtnLogout().addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(
                    mainFrame,
                    "Are you sure you want to log out?",
                    "Logout",
                    JOptionPane.YES_NO_OPTION
            );
            if (option == JOptionPane.YES_OPTION) {
                mainFrame.dispose();
                System.exit(0);
            }
        });
    }

    private void updateSidebarSelection(JButton selectedBtn) {
        JButton[] btns = {
            mainFrame.getBtnRegisterWalkin(), 
            mainFrame.getBtnGenerateToken(), 
            mainFrame.getBtnAssignDoctor(), 
            mainFrame.getBtnManageWaitlist1(), 
            mainFrame.getBtnAccounts()
        };
        for (JButton btn : btns) {
            if (btn == selectedBtn) {
                btn.setBackground(new java.awt.Color(10, 75, 120)); // Darker blue for selected
            } else {
                btn.setBackground(new java.awt.Color(22, 137, 176)); // Normal sidebar color
            }
        }
    }
}
