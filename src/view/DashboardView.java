package view;

import javax.swing.*;
import java.awt.*;

/**
 * Hospicare Reception Dashboard View class.
 * This class is designed to be fully compatible with the NetBeans GUI Builder (Matisse).
 * Do NOT add business logic in this file; it is bound to the controller via getters.
 */
public class DashboardView extends javax.swing.JFrame {

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

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        GridBagConstraints gbc;

        sidebarPanel = new javax.swing.JPanel();
        lblHospicare = new javax.swing.JLabel();
        btnRegisterWalkin = new javax.swing.JButton();
        btnGenerateToken = new javax.swing.JButton();
        btnAssignDoctor = new javax.swing.JButton();
        btnManageWaitlist = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        lblHeaderTitle = new javax.swing.JLabel();
        lblHeaderSubtitle = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        bodyScroll = new javax.swing.JScrollPane();
        bodyPanel = new javax.swing.JPanel();
        bannerPanel = new javax.swing.JPanel();
        lblGreeting = new javax.swing.JLabel();
        lblGreetingDesc = new javax.swing.JLabel();
        statsPanel = new javax.swing.JPanel();
        cardTotalPatients = new javax.swing.JPanel();
        lblTotalTitle = new javax.swing.JLabel();
        lblTotalVal = new javax.swing.JLabel();
        cardTokensGen = new javax.swing.JPanel();
        lblTokensTitle = new javax.swing.JLabel();
        lblTokensVal = new javax.swing.JLabel();
        cardWaiting = new javax.swing.JPanel();
        lblWaitingTitle = new javax.swing.JLabel();
        lblWaitingVal = new javax.swing.JLabel();
        cardDoctors = new javax.swing.JPanel();
        lblDoctorsTitle = new javax.swing.JLabel();
        lblDoctorsVal = new javax.swing.JLabel();
        bottomLeftPanel = new javax.swing.JPanel();
        tableHeaderArea = new javax.swing.JPanel();
        lblWaitlistTitle = new javax.swing.JLabel();
        tableScroll = new javax.swing.JScrollPane();
        tblWaitlist = new javax.swing.JTable();
        bottomRightPanel = new javax.swing.JPanel();
        quickActionsPanel = new javax.swing.JPanel();
        lblQuickActions = new javax.swing.JLabel();
        btnNewPatientReg = new javax.swing.JButton();
        btnGenEmergency = new javax.swing.JButton();
        btnDailyReport = new javax.swing.JButton();
        deptLoadPanel = new javax.swing.JPanel();
        lblDeptLoadTitle = new javax.swing.JLabel();
        lblCardio = new javax.swing.JLabel();
        lblCardioVal = new javax.swing.JLabel();
        pbCardio = new javax.swing.JProgressBar();
        lblOrtho = new javax.swing.JLabel();
        lblOrthoVal = new javax.swing.JLabel();
        pbOrtho = new javax.swing.JProgressBar();
        lblPediatrics = new javax.swing.JLabel();
        lblPediatricsVal = new javax.swing.JLabel();
        pbPediatrics = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hospicare - Reception Dashboard");
        getContentPane().setLayout(new java.awt.BorderLayout());

        sidebarPanel.setBackground(new java.awt.Color(22, 137, 176));
        sidebarPanel.setPreferredSize(new java.awt.Dimension(240, 700));
        sidebarPanel.setLayout(new java.awt.GridBagLayout());

        lblHospicare.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblHospicare.setForeground(new java.awt.Color(255, 255, 255));
        lblHospicare.setText("HOSPICARE");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gbc.insets = new java.awt.Insets(30, 20, 40, 20);
        gbc.weightx = 1.0;
        sidebarPanel.add(lblHospicare, gbc);

        btnRegisterWalkin.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnRegisterWalkin.setForeground(new java.awt.Color(255, 255, 255));
        btnRegisterWalkin.setText("Register Walk-in");
        btnRegisterWalkin.setBorderPainted(false);
        btnRegisterWalkin.setContentAreaFilled(false);
        btnRegisterWalkin.setFocusPainted(false);
        btnRegisterWalkin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(5, 15, 5, 15);
        gbc.weightx = 1.0;
        sidebarPanel.add(btnRegisterWalkin, gbc);

        btnGenerateToken.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnGenerateToken.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerateToken.setText("Generate Token");
        btnGenerateToken.setBorderPainted(false);
        btnGenerateToken.setContentAreaFilled(false);
        btnGenerateToken.setFocusPainted(false);
        btnGenerateToken.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(5, 15, 5, 15);
        gbc.weightx = 1.0;
        sidebarPanel.add(btnGenerateToken, gbc);

        btnAssignDoctor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAssignDoctor.setForeground(new java.awt.Color(255, 255, 255));
        btnAssignDoctor.setText("Assign to Doctor");
        btnAssignDoctor.setBorderPainted(false);
        btnAssignDoctor.setContentAreaFilled(false);
        btnAssignDoctor.setFocusPainted(false);
        btnAssignDoctor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(5, 15, 5, 15);
        gbc.weightx = 1.0;
        sidebarPanel.add(btnAssignDoctor, gbc);

        btnManageWaitlist.setBackground(new java.awt.Color(18, 116, 210));
        btnManageWaitlist.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnManageWaitlist.setForeground(new java.awt.Color(255, 255, 255));
        btnManageWaitlist.setText("Manage Waitlist");
        btnManageWaitlist.setBorderPainted(false);
        btnManageWaitlist.setFocusPainted(false);
        btnManageWaitlist.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(5, 15, 5, 15);
        gbc.weightx = 1.0;
        sidebarPanel.add(btnManageWaitlist, gbc);

        btnLogout.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 100, 100));
        btnLogout.setText("Logout");
        btnLogout.setBorderPainted(false);
        btnLogout.setContentAreaFilled(false);
        btnLogout.setFocusPainted(false);
        btnLogout.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.anchor = java.awt.GridBagConstraints.SOUTH;
        gbc.insets = new java.awt.Insets(5, 15, 30, 15);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        sidebarPanel.add(btnLogout, gbc);

        getContentPane().add(sidebarPanel, java.awt.BorderLayout.WEST);

        mainPanel.setBackground(new java.awt.Color(245, 246, 248));
        mainPanel.setLayout(new java.awt.BorderLayout());

        headerPanel.setBackground(java.awt.Color.WHITE);
        headerPanel.setPreferredSize(new java.awt.Dimension(800, 70));
        headerPanel.setLayout(new java.awt.GridBagLayout());

        lblHeaderTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblHeaderTitle.setForeground(new java.awt.Color(50, 50, 50));
        lblHeaderTitle.setText("Reception Dashboard");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(10, 20, 5, 10);
        headerPanel.add(lblHeaderTitle, gbc);

        lblHeaderSubtitle.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblHeaderSubtitle.setForeground(new java.awt.Color(120, 120, 120));
        lblHeaderSubtitle.setText("Reception");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(0, 20, 10, 10);
        gbc.weightx = 1.0;
        headerPanel.add(lblHeaderSubtitle, gbc);

        lblDate.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblDate.setForeground(new java.awt.Color(100, 100, 100));
        lblDate.setText("Monday, Oct 23");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.anchor = java.awt.GridBagConstraints.EAST;
        gbc.insets = new java.awt.Insets(10, 10, 10, 20);
        headerPanel.add(lblDate, gbc);

        mainPanel.add(headerPanel, java.awt.BorderLayout.NORTH);

        bodyScroll.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        bodyPanel.setBackground(new java.awt.Color(245, 246, 248));
        bodyPanel.setLayout(new java.awt.GridBagLayout());

        bannerPanel.setBackground(new java.awt.Color(16, 185, 129));
        bannerPanel.setPreferredSize(new java.awt.Dimension(760, 90));
        bannerPanel.setLayout(new java.awt.GridBagLayout());

        lblGreeting.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblGreeting.setForeground(java.awt.Color.WHITE);
        lblGreeting.setText("Good morning, Sarah.");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(15, 20, 5, 20);
        gbc.weightx = 1.0;
        bannerPanel.add(lblGreeting, gbc);

        lblGreetingDesc.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblGreetingDesc.setForeground(new java.awt.Color(240, 240, 240));
        lblGreetingDesc.setText("The morning shift is picking up. You have 12 patients currently waiting and 4 doctors active on floor.");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(0, 20, 15, 20);
        gbc.weightx = 1.0;
        bannerPanel.add(lblGreetingDesc, gbc);

        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new java.awt.Insets(15, 15, 15, 15);
        bodyPanel.add(bannerPanel, gbc);

        statsPanel.setOpaque(false);
        statsPanel.setLayout(new java.awt.GridLayout(1, 4, 15, 0));

        // Card Total Patients
        cardTotalPatients.setBackground(java.awt.Color.WHITE);
        cardTotalPatients.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230), 1, true));
        cardTotalPatients.setLayout(new java.awt.GridBagLayout());

        lblTotalTitle.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblTotalTitle.setForeground(new java.awt.Color(120, 120, 120));
        lblTotalTitle.setText("TOTAL PATIENTS");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(15, 15, 5, 15);
        gbc.weightx = 1.0;
        cardTotalPatients.add(lblTotalTitle, gbc);

        lblTotalVal.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTotalVal.setForeground(new java.awt.Color(50, 50, 50));
        lblTotalVal.setText("1,284");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(0, 15, 15, 15);
        gbc.weightx = 1.0;
        cardTotalPatients.add(lblTotalVal, gbc);

        statsPanel.add(cardTotalPatients);

        // Card Tokens Generated
        cardTokensGen.setBackground(java.awt.Color.WHITE);
        cardTokensGen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230), 1, true));
        cardTokensGen.setLayout(new java.awt.GridBagLayout());

        lblTokensTitle.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblTokensTitle.setForeground(new java.awt.Color(120, 120, 120));
        lblTokensTitle.setText("TOKENS GENERATED");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(15, 15, 5, 15);
        gbc.weightx = 1.0;
        cardTokensGen.add(lblTokensTitle, gbc);

        lblTokensVal.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTokensVal.setForeground(new java.awt.Color(50, 50, 50));
        lblTokensVal.setText("156");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(0, 15, 15, 15);
        gbc.weightx = 1.0;
        cardTokensGen.add(lblTokensVal, gbc);

        statsPanel.add(cardTokensGen);

        // Card Waiting Patients
        cardWaiting.setBackground(new java.awt.Color(5, 150, 105));
        cardWaiting.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(5, 150, 105), 1, true));
        cardWaiting.setLayout(new java.awt.GridBagLayout());

        lblWaitingTitle.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblWaitingTitle.setForeground(new java.awt.Color(240, 240, 240));
        lblWaitingTitle.setText("WAITING PATIENTS");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(15, 15, 5, 15);
        gbc.weightx = 1.0;
        cardWaiting.add(lblWaitingTitle, gbc);

        lblWaitingVal.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblWaitingVal.setForeground(java.awt.Color.WHITE);
        lblWaitingVal.setText("12");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(0, 15, 15, 15);
        gbc.weightx = 1.0;
        cardWaiting.add(lblWaitingVal, gbc);

        statsPanel.add(cardWaiting);

        // Card Doctors Available
        cardDoctors.setBackground(java.awt.Color.WHITE);
        cardDoctors.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230), 1, true));
        cardDoctors.setLayout(new java.awt.GridBagLayout());

        lblDoctorsTitle.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblDoctorsTitle.setForeground(new java.awt.Color(120, 120, 120));
        lblDoctorsTitle.setText("DOCTORS AVAILABLE");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(15, 15, 5, 15);
        gbc.weightx = 1.0;
        cardDoctors.add(lblDoctorsTitle, gbc);

        lblDoctorsVal.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblDoctorsVal.setForeground(new java.awt.Color(50, 50, 50));
        lblDoctorsVal.setText("08/10");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(0, 15, 15, 15);
        gbc.weightx = 1.0;
        cardDoctors.add(lblDoctorsVal, gbc);

        statsPanel.add(cardDoctors);

        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new java.awt.Insets(0, 15, 15, 15);
        bodyPanel.add(statsPanel, gbc);

        // Bottom Left: Active Waitlist Table
        bottomLeftPanel.setBackground(java.awt.Color.WHITE);
        bottomLeftPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230), 1, true));
        bottomLeftPanel.setLayout(new java.awt.BorderLayout());

        tableHeaderArea.setOpaque(false);
        tableHeaderArea.setPreferredSize(new java.awt.Dimension(400, 50));
        tableHeaderArea.setLayout(new java.awt.GridBagLayout());

        lblWaitlistTitle.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblWaitlistTitle.setForeground(new java.awt.Color(50, 50, 50));
        lblWaitlistTitle.setText("Active Waitlist");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(10, 15, 10, 10);
        gbc.weightx = 1.0;
        tableHeaderArea.add(lblWaitlistTitle, gbc);

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
            boolean[] canEdit = new boolean [] { false, false, false, false, false };
            public boolean isCellEditable(int rowIndex, int columnIndex) { return canEdit [columnIndex]; }
        });
        tblWaitlist.setRowHeight(40);
        tblWaitlist.setShowGrid(false);
        tableScroll.setViewportView(tblWaitlist);

        bottomLeftPanel.add(tableScroll, java.awt.BorderLayout.CENTER);

        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = java.awt.GridBagConstraints.BOTH;
        gbc.weightx = 0.7;
        gbc.weighty = 1.0;
        gbc.insets = new java.awt.Insets(0, 15, 15, 10);
        bodyPanel.add(bottomLeftPanel, gbc);

        // Bottom Right: Quick Actions & Department Load
        bottomRightPanel.setOpaque(false);
        bottomRightPanel.setLayout(new java.awt.GridBagLayout());

        quickActionsPanel.setBackground(java.awt.Color.WHITE);
        quickActionsPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230), 1, true));
        quickActionsPanel.setLayout(new java.awt.GridBagLayout());

        lblQuickActions.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblQuickActions.setForeground(new java.awt.Color(50, 50, 50));
        lblQuickActions.setText("Quick Actions");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(15, 15, 10, 15);
        gbc.weightx = 1.0;
        quickActionsPanel.add(lblQuickActions, gbc);

        btnNewPatientReg.setBackground(new java.awt.Color(16, 185, 129));
        btnNewPatientReg.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnNewPatientReg.setForeground(java.awt.Color.WHITE);
        btnNewPatientReg.setText("New Patient Registration");
        btnNewPatientReg.setFocusPainted(false);
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(5, 15, 5, 15);
        gbc.weightx = 1.0;
        quickActionsPanel.add(btnNewPatientReg, gbc);

        btnGenEmergency.setBackground(java.awt.Color.WHITE);
        btnGenEmergency.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnGenEmergency.setForeground(new java.awt.Color(50, 50, 50));
        btnGenEmergency.setText("Generate Emergency Token");
        btnGenEmergency.setFocusPainted(false);
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(5, 15, 5, 15);
        gbc.weightx = 1.0;
        quickActionsPanel.add(btnGenEmergency, gbc);

        btnDailyReport.setBackground(java.awt.Color.WHITE);
        btnDailyReport.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnDailyReport.setForeground(new java.awt.Color(50, 50, 50));
        btnDailyReport.setText("Daily Report");
        btnDailyReport.setFocusPainted(false);
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(5, 15, 15, 15);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        quickActionsPanel.add(btnDailyReport, gbc);

        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = java.awt.GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.5;
        gbc.insets = new java.awt.Insets(0, 0, 15, 0);
        bottomRightPanel.add(quickActionsPanel, gbc);

        deptLoadPanel.setBackground(java.awt.Color.WHITE);
        deptLoadPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230), 1, true));
        deptLoadPanel.setLayout(new java.awt.GridBagLayout());

        lblDeptLoadTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDeptLoadTitle.setForeground(new java.awt.Color(50, 50, 50));
        lblDeptLoadTitle.setText("Department Load");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(15, 15, 10, 15);
        gbc.weightx = 1.0;
        deptLoadPanel.add(lblDeptLoadTitle, gbc);

        lblCardio.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblCardio.setText("Cardiology");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(5, 15, 5, 5);
        gbc.weightx = 0.5;
        deptLoadPanel.add(lblCardio, gbc);

        lblCardioVal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCardioVal.setText("85% Capacity");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = java.awt.GridBagConstraints.EAST;
        gbc.insets = new java.awt.Insets(5, 5, 5, 15);
        gbc.weightx = 0.5;
        deptLoadPanel.add(lblCardioVal, gbc);

        pbCardio.setBackground(new java.awt.Color(240, 240, 240));
        pbCardio.setForeground(new java.awt.Color(22, 137, 22));
        pbCardio.setValue(85);
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(0, 15, 10, 15);
        gbc.weightx = 1.0;
        deptLoadPanel.add(pbCardio, gbc);

        lblOrtho.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblOrtho.setText("Orthopedics");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(5, 15, 5, 5);
        gbc.weightx = 0.5;
        deptLoadPanel.add(lblOrtho, gbc);

        lblOrthoVal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblOrthoVal.setText("40% Capacity");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = java.awt.GridBagConstraints.EAST;
        gbc.insets = new java.awt.Insets(5, 5, 5, 15);
        gbc.weightx = 0.5;
        deptLoadPanel.add(lblOrthoVal, gbc);

        pbOrtho.setBackground(new java.awt.Color(240, 240, 240));
        pbOrtho.setForeground(new java.awt.Color(22, 137, 22));
        pbOrtho.setValue(40);
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(0, 15, 10, 15);
        gbc.weightx = 1.0;
        deptLoadPanel.add(pbOrtho, gbc);

        lblPediatrics.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblPediatrics.setText("Pediatrics");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(5, 15, 5, 5);
        gbc.weightx = 0.5;
        deptLoadPanel.add(lblPediatrics, gbc);

        lblPediatricsVal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPediatricsVal.setText("62% Capacity");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = java.awt.GridBagConstraints.EAST;
        gbc.insets = new java.awt.Insets(5, 5, 5, 15);
        gbc.weightx = 0.5;
        deptLoadPanel.add(lblPediatricsVal, gbc);

        pbPediatrics.setBackground(new java.awt.Color(240, 240, 240));
        pbPediatrics.setForeground(new java.awt.Color(22, 137, 22));
        pbPediatrics.setValue(62);
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(0, 15, 15, 15);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        deptLoadPanel.add(pbPediatrics, gbc);

        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = java.awt.GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.5;
        gbc.insets = new java.awt.Insets(0, 0, 0, 0);
        bottomRightPanel.add(deptLoadPanel, gbc);

        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = java.awt.GridBagConstraints.BOTH;
        gbc.weightx = 0.3;
        gbc.weighty = 1.0;
        gbc.insets = new java.awt.Insets(0, 5, 15, 15);
        bodyPanel.add(bottomRightPanel, gbc);

        bodyScroll.setViewportView(bodyPanel);
        mainPanel.add(bodyScroll, java.awt.BorderLayout.CENTER);

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }
    // </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bannerPanel;
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JScrollPane bodyScroll;
    private javax.swing.JPanel bottomLeftPanel;
    private javax.swing.JPanel bottomRightPanel;
    private javax.swing.JButton btnAssignDoctor;
    private javax.swing.JButton btnDailyReport;
    private javax.swing.JButton btnEmergencyToken;
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
    private javax.swing.JPanel sidebarPanel;
    private javax.swing.JPanel tableHeaderArea;
    private javax.swing.JScrollPane tableScroll;
    private javax.swing.JTable tblWaitlist;
    // End of variables declaration//GEN-END:variables
}
