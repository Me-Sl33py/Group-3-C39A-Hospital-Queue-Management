package view;

import javax.swing.*;
import java.awt.*;

/**
 * Hospicare Reception Dashboard View class.
 * This class is designed to be fully compatible with the NetBeans GUI Builder (Matisse).
 * Do NOT add business logic in this file; it is bound to the controller via getters.
 */
public class DashboardView extends javax.swing.JPanel {

    public DashboardView() {
        initComponents();
    }

    

    public JButton getBtnRegisterWalkin() { return btnRegisterWalkin; }
    public JButton getBtnGenerateToken() { return btnGenerateToken; }
    public JButton getBtnAssignDoctor() { return btnAssignDoctor; }
    public JButton getBtnManageWaitlist() { return btnManageWaitlist; }
    public JButton getBtnLogout() { return btnLogout; }
    public JButton getBtnNewPatientReg() { return btnNewPatientReg; }
    public JButton getBtnGenEmergency() { return btnGenEmergency; }
    public JButton getBtnDailyReport() { return btnDailyReport; }
    public JTable getTblWaitlist() { return tblWaitlist; }
    public JLabel getLblDate() { return lblDate; }
    public JLabel getLblTotalVal() { return lblTotalVal; }
    public JLabel getLblTokensVal() { return lblTokensVal; }
    public JLabel getLblWaitingVal() { return lblWaitingVal; }
    public JLabel getLblDoctorsVal() { return lblDoctorsVal; }
    public JProgressBar getPbCardio() { return pbCardio; }
    public JProgressBar getPbOrtho() { return pbOrtho; }
    public JProgressBar getPbPediatrics() { return pbPediatrics; }
    public JLabel getLblCardioVal() { return lblCardioVal; }
    public JLabel getLblOrthoVal() { return lblOrthoVal; }
    public JLabel getLblPediatricsVal() { return lblPediatricsVal; }
    public JPanel getMainPanel() { return mainPanel; }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        btnRegisterWalkin = new javax.swing.JButton();
        btnRegisterWalkin.setForeground(new java.awt.Color(255, 255, 255));
        btnRegisterWalkin.setBorderPainted(false);
        btnRegisterWalkin.setContentAreaFilled(false);
        btnGenerateToken = new javax.swing.JButton();
        btnGenerateToken.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerateToken.setBorderPainted(false);
        btnGenerateToken.setContentAreaFilled(false);
        btnAssignDoctor = new javax.swing.JButton();
        btnAssignDoctor.setForeground(new java.awt.Color(255, 255, 255));
        btnAssignDoctor.setBorderPainted(false);
        btnAssignDoctor.setContentAreaFilled(false);
        btnManageWaitlist = new javax.swing.JButton();
        btnManageWaitlist.setForeground(new java.awt.Color(255, 255, 255));
        btnManageWaitlist.setBackground(new java.awt.Color(18, 116, 210));
        btnManageWaitlist.setBorderPainted(false);
        btnManageWaitlist.setOpaque(true);
        btnLogout = new javax.swing.JButton();
        btnLogout.setForeground(new java.awt.Color(255, 100, 100));
        btnLogout.setBorderPainted(false);
        btnLogout.setContentAreaFilled(false);
        mainPanel = new javax.swing.JPanel();
        mainPanel.setBackground(new java.awt.Color(249, 250, 251));
        mainPanel.setOpaque(true);
        headerPanel = new javax.swing.JPanel();
        headerPanel.setBackground(new java.awt.Color(255, 255, 255));
        headerPanel.setOpaque(true);
        lblHeaderTitle = new javax.swing.JLabel();
        lblHeaderTitle.setForeground(new java.awt.Color(80, 80, 80));
        lblHeaderSubtitle = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        bodyScroll = new javax.swing.JScrollPane();
        bodyPanel = new javax.swing.JPanel();
        bodyPanel.setBackground(new java.awt.Color(249, 250, 251));
        bodyPanel.setOpaque(true);
        bannerPanel = new javax.swing.JPanel();
        bannerPanel.setBackground(new java.awt.Color(5, 150, 105));
        bannerPanel.setOpaque(true);
        lblGreeting = new javax.swing.JLabel();
        lblGreeting.setForeground(new java.awt.Color(255, 255, 255));
        lblGreetingDesc = new javax.swing.JLabel();
        lblGreetingDesc.setForeground(new java.awt.Color(230, 230, 230));
        statsPanel = new javax.swing.JPanel();
        statsPanel.setOpaque(false);
        cardTotalPatients = new javax.swing.JPanel();
        cardTotalPatients.setBackground(new java.awt.Color(255, 255, 255));
        cardTotalPatients.setOpaque(true);
        lblTotalTitle = new javax.swing.JLabel();
        lblTotalVal = new javax.swing.JLabel();
        lblTotalVal.setForeground(new java.awt.Color(80, 80, 80));
        cardTokensGen = new javax.swing.JPanel();
        cardTokensGen.setBackground(new java.awt.Color(255, 255, 255));
        cardTokensGen.setOpaque(true);
        lblTokensTitle = new javax.swing.JLabel();
        lblTokensVal = new javax.swing.JLabel();
        lblTokensVal.setForeground(new java.awt.Color(80, 80, 80));
        cardWaiting = new javax.swing.JPanel();
        cardWaiting.setBackground(new java.awt.Color(5, 150, 105));
        cardWaiting.setOpaque(true);
        lblWaitingTitle = new javax.swing.JLabel();
        lblWaitingTitle.setForeground(new java.awt.Color(230, 230, 230));
        lblWaitingVal = new javax.swing.JLabel();
        lblWaitingVal.setForeground(new java.awt.Color(255, 255, 255));
        cardDoctors = new javax.swing.JPanel();
        cardDoctors.setBackground(new java.awt.Color(255, 255, 255));
        cardDoctors.setOpaque(true);
        lblDoctorsTitle = new javax.swing.JLabel();
        lblDoctorsVal = new javax.swing.JLabel();
        lblDoctorsVal.setForeground(new java.awt.Color(80, 80, 80));
        bottomLeftPanel = new javax.swing.JPanel();
        bottomLeftPanel.setBackground(new java.awt.Color(255, 255, 255));
        bottomLeftPanel.setOpaque(true);
        tableHeaderArea = new javax.swing.JPanel();
        tableHeaderArea.setBackground(new java.awt.Color(255, 255, 255));
        tableHeaderArea.setOpaque(true);
        lblWaitlistTitle = new javax.swing.JLabel();
        lblWaitlistTitle.setForeground(new java.awt.Color(80, 80, 80));
        tableScroll = new javax.swing.JScrollPane();
        tblWaitlist = new javax.swing.JTable();
        bottomRightPanel = new javax.swing.JPanel();
        bottomRightPanel.setOpaque(false);
        quickActionsPanel = new javax.swing.JPanel();
        quickActionsPanel.setBackground(new java.awt.Color(255, 255, 255));
        quickActionsPanel.setOpaque(true);
        lblQuickActions = new javax.swing.JLabel();
        lblQuickActions.setForeground(new java.awt.Color(80, 80, 80));
        btnNewPatientReg = new javax.swing.JButton();
        btnNewPatientReg.setForeground(new java.awt.Color(255, 255, 255));
        btnNewPatientReg.setBackground(new java.awt.Color(5, 150, 105));
        btnNewPatientReg.setBorderPainted(false);
        btnNewPatientReg.setOpaque(true);
        btnGenEmergency = new javax.swing.JButton();
        btnGenEmergency.setForeground(new java.awt.Color(80, 80, 80));
        btnDailyReport = new javax.swing.JButton();
        btnDailyReport.setForeground(new java.awt.Color(80, 80, 80));
        deptLoadPanel = new javax.swing.JPanel();
        deptLoadPanel.setBackground(new java.awt.Color(255, 255, 255));
        deptLoadPanel.setOpaque(true);
        lblDeptLoadTitle = new javax.swing.JLabel();
        lblDeptLoadTitle.setForeground(new java.awt.Color(80, 80, 80));
        lblCardio = new javax.swing.JLabel();
        lblCardioVal = new javax.swing.JLabel();
        pbCardio = new javax.swing.JProgressBar();
        lblOrtho = new javax.swing.JLabel();
        lblOrthoVal = new javax.swing.JLabel();
        pbOrtho = new javax.swing.JProgressBar();
        lblPediatrics = new javax.swing.JLabel();
        lblPediatricsVal = new javax.swing.JLabel();
        pbPediatrics = new javax.swing.JProgressBar();

        // setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1024, 768));

        // gridBagConstraints setup removed
        setLayout(new java.awt.BorderLayout());
        // sidebarPanel removed

        mainPanel.setLayout(new java.awt.BorderLayout());

        headerPanel.setPreferredSize(new java.awt.Dimension(800, 70));
        headerPanel.setLayout(new java.awt.GridBagLayout());

        lblHeaderTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblHeaderTitle.setText("Reception Dashboard");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 5, 10);
        headerPanel.add(lblHeaderTitle, gridBagConstraints);

        lblHeaderSubtitle.setText("Reception");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 10, 10);
        headerPanel.add(lblHeaderSubtitle, gridBagConstraints);

        lblDate.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblDate.setText("Monday, Oct 23");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 20);
        headerPanel.add(lblDate, gridBagConstraints);

        mainPanel.add(headerPanel, java.awt.BorderLayout.NORTH);

        bodyPanel.setLayout(new java.awt.GridBagLayout());

        bannerPanel.setPreferredSize(new java.awt.Dimension(760, 90));
        bannerPanel.setLayout(new java.awt.GridBagLayout());

        lblGreeting.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblGreeting.setText("Good morning, Sarah.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 20, 5, 20);
        bannerPanel.add(lblGreeting, gridBagConstraints);

        lblGreetingDesc.setText("The morning shift is picking up. You have 12 patients currently waiting and 4 doctors active on floor.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 15, 20);
        bannerPanel.add(lblGreetingDesc, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        bodyPanel.add(bannerPanel, gridBagConstraints);

        statsPanel.setLayout(new java.awt.GridLayout(1, 4, 15, 0));

        cardTotalPatients.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(230, 230, 230), 1, true));
        cardTotalPatients.setLayout(new java.awt.GridBagLayout());

        lblTotalTitle.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblTotalTitle.setText("TOTAL PATIENTS");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 5, 15);
        cardTotalPatients.add(lblTotalTitle, gridBagConstraints);

        lblTotalVal.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTotalVal.setText("1,284");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 15, 15);
        cardTotalPatients.add(lblTotalVal, gridBagConstraints);

        statsPanel.add(cardTotalPatients);

        cardTokensGen.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(230, 230, 230), 1, true));
        cardTokensGen.setLayout(new java.awt.GridBagLayout());

        lblTokensTitle.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblTokensTitle.setText("TOKENS GENERATED");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 5, 15);
        cardTokensGen.add(lblTokensTitle, gridBagConstraints);

        lblTokensVal.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTokensVal.setText("156");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 15, 15);
        cardTokensGen.add(lblTokensVal, gridBagConstraints);

        statsPanel.add(cardTokensGen);

        cardWaiting.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(5, 150, 105), 1, true));
        cardWaiting.setLayout(new java.awt.GridBagLayout());

        lblWaitingTitle.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblWaitingTitle.setText("WAITING PATIENTS");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 5, 15);
        cardWaiting.add(lblWaitingTitle, gridBagConstraints);

        lblWaitingVal.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblWaitingVal.setText("12");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 15, 15);
        cardWaiting.add(lblWaitingVal, gridBagConstraints);

        statsPanel.add(cardWaiting);

        cardDoctors.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(230, 230, 230), 1, true));
        cardDoctors.setLayout(new java.awt.GridBagLayout());

        lblDoctorsTitle.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblDoctorsTitle.setText("DOCTORS AVAILABLE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 5, 15);
        cardDoctors.add(lblDoctorsTitle, gridBagConstraints);

        lblDoctorsVal.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblDoctorsVal.setText("08/10");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 15, 15);
        cardDoctors.add(lblDoctorsVal, gridBagConstraints);

        statsPanel.add(cardDoctors);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 15, 15);
        bodyPanel.add(statsPanel, gridBagConstraints);

        bottomLeftPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(230, 230, 230), 1, true));
        bottomLeftPanel.setLayout(new java.awt.BorderLayout());

        tableHeaderArea.setPreferredSize(new java.awt.Dimension(400, 50));
        tableHeaderArea.setLayout(new java.awt.GridBagLayout());

        lblWaitlistTitle.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblWaitlistTitle.setText("Active Waitlist");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 10);
        tableHeaderArea.add(lblWaitlistTitle, gridBagConstraints);

        bottomLeftPanel.add(tableHeaderArea, java.awt.BorderLayout.NORTH);

        tblWaitlist.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"#TK-402", "Robert Fox (Male, 45 yrs)", "Cardiology", "In Consultation", "[Call]"},
                {"#TK-405", "Esther Howard (Female, 28 yrs)", "Dermatology", "Waiting", "[Call]"},
                {"#TK-406", "Cameron Williamson (Male, 32 yrs)", "General Medicine", "Waiting", "[Call]"}
            },
            new String [] {
                "Token", "Patient Name", "Department", "Status", "Actions"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblWaitlist.setRowHeight(40);
        tblWaitlist.setShowGrid(false);
        tableScroll.setViewportView(tblWaitlist);

        bottomLeftPanel.add(tableScroll, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.7;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 15, 10);
        bodyPanel.add(bottomLeftPanel, gridBagConstraints);

        bottomRightPanel.setLayout(new java.awt.GridBagLayout());

        quickActionsPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(230, 230, 230), 1, true));
        quickActionsPanel.setLayout(new java.awt.GridBagLayout());

        lblQuickActions.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblQuickActions.setText("Quick Actions");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 10, 15);
        quickActionsPanel.add(lblQuickActions, gridBagConstraints);

        btnNewPatientReg.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnNewPatientReg.setText("New Patient Registration");
        btnNewPatientReg.setFocusPainted(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 15);
        quickActionsPanel.add(btnNewPatientReg, gridBagConstraints);

        btnGenEmergency.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnGenEmergency.setText("Generate Emergency Token");
        btnGenEmergency.setFocusPainted(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 15);
        quickActionsPanel.add(btnGenEmergency, gridBagConstraints);

        btnDailyReport.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnDailyReport.setText("Daily Report");
        btnDailyReport.setFocusPainted(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 15, 15);
        quickActionsPanel.add(btnDailyReport, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        bottomRightPanel.add(quickActionsPanel, gridBagConstraints);

        deptLoadPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(230, 230, 230), 1, true));
        deptLoadPanel.setLayout(new java.awt.GridBagLayout());

        lblDeptLoadTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDeptLoadTitle.setText("Department Load");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 10, 15);
        deptLoadPanel.add(lblDeptLoadTitle, gridBagConstraints);

        lblCardio.setText("Cardiology");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 5);
        deptLoadPanel.add(lblCardio, gridBagConstraints);

        lblCardioVal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCardioVal.setText("85% Capacity");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 15);
        deptLoadPanel.add(lblCardioVal, gridBagConstraints);

        pbCardio.setValue(85);
        pbCardio.setForeground(new java.awt.Color(16, 185, 129));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 10, 15);
        deptLoadPanel.add(pbCardio, gridBagConstraints);

        lblOrtho.setText("Orthopedics");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 5);
        deptLoadPanel.add(lblOrtho, gridBagConstraints);

        lblOrthoVal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblOrthoVal.setText("40% Capacity");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 15);
        deptLoadPanel.add(lblOrthoVal, gridBagConstraints);

        pbOrtho.setValue(40);
        pbOrtho.setForeground(new java.awt.Color(16, 185, 129));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 10, 15);
        deptLoadPanel.add(pbOrtho, gridBagConstraints);

        lblPediatrics.setText("Pediatrics");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 5);
        deptLoadPanel.add(lblPediatrics, gridBagConstraints);

        lblPediatricsVal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPediatricsVal.setText("62% Capacity");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 15);
        deptLoadPanel.add(lblPediatricsVal, gridBagConstraints);

        pbPediatrics.setValue(62);
        pbPediatrics.setForeground(new java.awt.Color(16, 185, 129));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 15, 15);
        deptLoadPanel.add(pbPediatrics, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        bottomRightPanel.add(deptLoadPanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 15, 15);
        bodyPanel.add(bottomRightPanel, gridBagConstraints);

        bodyScroll.setViewportView(bodyPanel);

        mainPanel.add(bodyScroll, java.awt.BorderLayout.CENTER);

        add(mainPanel, java.awt.BorderLayout.CENTER);

    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnManageWaitlistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageWaitlistActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnManageWaitlistActionPerformed

    private void btnRegisterWalkinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterWalkinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegisterWalkinActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bannerPanel;
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JScrollPane bodyScroll;
    private javax.swing.JPanel bottomLeftPanel;
    private javax.swing.JPanel bottomRightPanel;
    private javax.swing.JButton btnAssignDoctor;
    private javax.swing.JButton btnDailyReport;
    private javax.swing.JButton btnGenEmergency;
    private javax.swing.JButton btnGenerateToken;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnManageWaitlist;
    private javax.swing.JButton btnNewPatientReg;
    private javax.swing.JButton btnRegisterWalkin;
    private javax.swing.JPanel cardDoctors;
    private javax.swing.JPanel cardTokensGen;
    private javax.swing.JPanel cardTotalPatients;
    private javax.swing.JPanel cardWaiting;
    private javax.swing.JPanel deptLoadPanel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel lblCardio;
    private javax.swing.JLabel lblCardioVal;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDeptLoadTitle;
    private javax.swing.JLabel lblDoctorsTitle;
    private javax.swing.JLabel lblDoctorsVal;
    private javax.swing.JLabel lblGreeting;
    private javax.swing.JLabel lblGreetingDesc;
    private javax.swing.JLabel lblHeaderSubtitle;
    private javax.swing.JLabel lblHeaderTitle;
    private javax.swing.JLabel lblHospicare;
    private javax.swing.JLabel lblOrtho;
    private javax.swing.JLabel lblOrthoVal;
    private javax.swing.JLabel lblPediatrics;
    private javax.swing.JLabel lblPediatricsVal;
    private javax.swing.JLabel lblQuickActions;
    private javax.swing.JLabel lblTokensTitle;
    private javax.swing.JLabel lblTokensVal;
    private javax.swing.JLabel lblTotalTitle;
    private javax.swing.JLabel lblTotalVal;
    private javax.swing.JLabel lblWaitingTitle;
    private javax.swing.JLabel lblWaitingVal;
    private javax.swing.JLabel lblWaitlistTitle;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JProgressBar pbCardio;
    private javax.swing.JProgressBar pbOrtho;
    private javax.swing.JProgressBar pbPediatrics;
    private javax.swing.JPanel quickActionsPanel;
    // private javax.swing.JPanel sidebarPanel;
    private javax.swing.JPanel statsPanel;
    private javax.swing.JPanel tableHeaderArea;
    private javax.swing.JScrollPane tableScroll;
    private javax.swing.JTable tblWaitlist;
    // End of variables declaration//GEN-END:variables
}
