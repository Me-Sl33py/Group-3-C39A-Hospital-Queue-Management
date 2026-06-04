package view;

import javax.swing.*;
import java.awt.*;

/**
 * Generate Token View class compatible with the NetBeans GUI Builder.
 * Exposes Swing controls through getters for the GenerateTokenController.
 */
public class GenerateTokenView extends javax.swing.JFrame {

    public GenerateTokenView() {
        initComponents();
    }

    

    public JButton getBtnRegisterWalkin() { return btnRegisterWalkin; }
    public JButton getBtnGenerateToken() { return btnGenerateToken; } // Sidebar button
    public JButton getBtnAssignDoctor() { return btnAssignDoctor; }
    public JButton getBtnManageWaitlist() { return btnManageWaitlist; } // Sidebar button
    public JButton getBtnLogout() { return btnLogout; }
    
    public JComboBox<String> getCbDepartment() { return cbDepartment; }
    public JButton getBtnGenerateTokenSubmit() { return btnGenerateTokenSubmit; }
    public JTable getTblLiveQueue() { return tblLiveQueue; }
    public JLabel getLblTipText() { return lblTipText; }

    public JLabel getLblPatientName() { return lblPatientName; }
    public JLabel getLblPatientID() { return lblPatientID; }
    public JLabel getLblAgeGenVal() { return lblAgeGenVal; }
    public JLabel getLblContactVal() { return lblContactVal; }
    public JLabel getLblBloodVal() { return lblBloodVal; }
    public JLabel getLblRegDateVal() { return lblRegDateVal; }

    public JLabel getLblBPVal() { return lblBPVal; }
    public JLabel getLblTempVal() { return lblTempVal; }
    public JLabel getLblSpo2Val() { return lblSpo2Val; }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        sidebarPanel = new javax.swing.JPanel();
        sidebarPanel.setBackground(new java.awt.Color(22, 137, 176));
        sidebarPanel.setOpaque(true);
        lblHospicare = new javax.swing.JLabel();
        lblHospicare.setForeground(new java.awt.Color(255, 255, 255));
        btnRegisterWalkin = new javax.swing.JButton();
        btnRegisterWalkin.setForeground(new java.awt.Color(255, 255, 255));
        btnRegisterWalkin.setBorderPainted(false);
        btnRegisterWalkin.setContentAreaFilled(false);
        btnGenerateToken = new javax.swing.JButton();
        btnGenerateToken.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerateToken.setBackground(new java.awt.Color(18, 116, 210));
        btnGenerateToken.setBorderPainted(false);
        btnGenerateToken.setOpaque(true);
        btnAssignDoctor = new javax.swing.JButton();
        btnAssignDoctor.setForeground(new java.awt.Color(255, 255, 255));
        btnAssignDoctor.setBorderPainted(false);
        btnAssignDoctor.setContentAreaFilled(false);
        btnManageWaitlist = new javax.swing.JButton();
        btnManageWaitlist.setForeground(new java.awt.Color(255, 255, 255));
        btnManageWaitlist.setBorderPainted(false);
        btnManageWaitlist.setContentAreaFilled(false);
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
        bodyScroll = new javax.swing.JScrollPane();
        bodyPanel = new javax.swing.JPanel();
        bodyPanel.setBackground(new java.awt.Color(249, 250, 251));
        bodyPanel.setOpaque(true);
        navTabsPanel = new javax.swing.JPanel();
        navTabsPanel.setOpaque(false);
        lblTab1 = new javax.swing.JLabel();
        lblTab2 = new javax.swing.JLabel();
        lblTab3 = new javax.swing.JLabel();
        leftColumnPanel = new javax.swing.JPanel();
        leftColumnPanel.setOpaque(false);
        patientInfoPanel = new javax.swing.JPanel();
        patientInfoPanel.setBackground(new java.awt.Color(255, 255, 255));
        patientInfoPanel.setOpaque(true);
        lblPatientInfoTitle = new javax.swing.JLabel();
        lblPatientInfoTitle.setForeground(new java.awt.Color(128, 128, 128));
        lblPreRegisteredBadge = new javax.swing.JLabel();
        lblPreRegisteredBadge.setForeground(new java.awt.Color(6, 95, 70));
        lblPreRegisteredBadge.setBackground(new java.awt.Color(209, 250, 233));
        lblPreRegisteredBadge.setOpaque(true);
        lblPatientName = new javax.swing.JLabel();
        lblPatientName.setForeground(new java.awt.Color(80, 80, 80));
        lblPatientID = new javax.swing.JLabel();
        lblAgeGenTitle = new javax.swing.JLabel();
        lblContactTitle = new javax.swing.JLabel();
        lblAgeGenVal = new javax.swing.JLabel();
        lblAgeGenVal.setForeground(new java.awt.Color(80, 80, 80));
        lblContactVal = new javax.swing.JLabel();
        lblContactVal.setForeground(new java.awt.Color(80, 80, 80));
        lblBloodTitle = new javax.swing.JLabel();
        lblRegDateTitle = new javax.swing.JLabel();
        lblBloodVal = new javax.swing.JLabel();
        lblBloodVal.setForeground(new java.awt.Color(16, 185, 129));
        lblRegDateVal = new javax.swing.JLabel();
        lblRegDateVal.setForeground(new java.awt.Color(80, 80, 80));
        vitalsPanel = new javax.swing.JPanel();
        vitalsPanel.setBackground(new java.awt.Color(255, 255, 255));
        vitalsPanel.setOpaque(true);
        lblVitalsTitle = new javax.swing.JLabel();
        lblVitalsTitle.setForeground(new java.awt.Color(128, 128, 128));
        cardBP = new javax.swing.JPanel();
        cardBP.setBackground(new java.awt.Color(255, 255, 255));
        cardBP.setOpaque(true);
        lblBPTitle = new javax.swing.JLabel();
        lblBPVal = new javax.swing.JLabel();
        lblBPVal.setForeground(new java.awt.Color(80, 80, 80));
        cardTemp = new javax.swing.JPanel();
        cardTemp.setBackground(new java.awt.Color(255, 255, 255));
        cardTemp.setOpaque(true);
        lblTempTitle = new javax.swing.JLabel();
        lblTempVal = new javax.swing.JLabel();
        lblTempVal.setForeground(new java.awt.Color(80, 80, 80));
        cardSpo2 = new javax.swing.JPanel();
        cardSpo2.setBackground(new java.awt.Color(255, 255, 255));
        cardSpo2.setOpaque(true);
        lblSpo2Title = new javax.swing.JLabel();
        lblSpo2Val = new javax.swing.JLabel();
        rightColumnPanel = new javax.swing.JPanel();
        rightColumnPanel.setOpaque(false);
        genTokenCard = new javax.swing.JPanel();
        genTokenCard.setBackground(new java.awt.Color(255, 255, 255));
        genTokenCard.setOpaque(true);
        lblGenTitle = new javax.swing.JLabel();
        lblGenTitle.setForeground(new java.awt.Color(80, 80, 80));
        lblGenSubtitle = new javax.swing.JLabel();
        lblDeptLabel = new javax.swing.JLabel();
        lblDeptLabel.setForeground(new java.awt.Color(80, 80, 80));
        cbDepartment = new javax.swing.JComboBox();
        tipPanel = new javax.swing.JPanel();
        tipPanel.setBackground(new java.awt.Color(236, 253, 245));
        tipPanel.setOpaque(true);
        lblTipText = new javax.swing.JLabel();
        lblTipText.setForeground(new java.awt.Color(6, 95, 70));
        btnGenerateTokenSubmit = new javax.swing.JButton();
        btnGenerateTokenSubmit.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerateTokenSubmit.setBackground(new java.awt.Color(5, 150, 105));
        btnGenerateTokenSubmit.setBorderPainted(false);
        btnGenerateTokenSubmit.setOpaque(true);
        bottomPanel = new javax.swing.JPanel();
        bottomPanel.setBackground(new java.awt.Color(255, 255, 255));
        bottomPanel.setOpaque(true);
        tableHeaderArea = new javax.swing.JPanel();
        tableHeaderArea.setBackground(new java.awt.Color(255, 255, 255));
        tableHeaderArea.setOpaque(true);
        lblLiveQueueTitle = new javax.swing.JLabel();
        lblLiveQueueTitle.setForeground(new java.awt.Color(80, 80, 80));
        tableScroll = new javax.swing.JScrollPane();
        tblLiveQueue = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hospicare - Generate Token");

        sidebarPanel.setPreferredSize(new java.awt.Dimension(240, 700));
        sidebarPanel.setLayout(new java.awt.GridBagLayout());

        lblHospicare.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblHospicare.setText("HOSPICARE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(30, 20, 40, 20);
        sidebarPanel.add(lblHospicare, gridBagConstraints);

        btnRegisterWalkin.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnRegisterWalkin.setText("Register Walk-in");
        btnRegisterWalkin.setFocusPainted(false);
        btnRegisterWalkin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 15);
        sidebarPanel.add(btnRegisterWalkin, gridBagConstraints);

        btnGenerateToken.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGenerateToken.setText("Generate Token");
        btnGenerateToken.setFocusPainted(false);
        btnGenerateToken.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 15);
        sidebarPanel.add(btnGenerateToken, gridBagConstraints);

        btnAssignDoctor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAssignDoctor.setText("Assign to Doctor");
        btnAssignDoctor.setFocusPainted(false);
        btnAssignDoctor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 15);
        sidebarPanel.add(btnAssignDoctor, gridBagConstraints);

        btnManageWaitlist.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnManageWaitlist.setText("Manage Waitlist");
        btnManageWaitlist.setFocusPainted(false);
        btnManageWaitlist.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 15);
        sidebarPanel.add(btnManageWaitlist, gridBagConstraints);

        btnLogout.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLogout.setText("Logout");
        btnLogout.setFocusPainted(false);
        btnLogout.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 30, 15);
        sidebarPanel.add(btnLogout, gridBagConstraints);

        getContentPane().add(sidebarPanel, java.awt.BorderLayout.WEST);

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

        lblHeaderSubtitle.setText("Sarah Jenkins - Chief Receptionist");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 10, 10);
        headerPanel.add(lblHeaderSubtitle, gridBagConstraints);

        mainPanel.add(headerPanel, java.awt.BorderLayout.NORTH);

        bodyPanel.setLayout(new java.awt.GridBagLayout());

        navTabsPanel.setLayout(new java.awt.GridBagLayout());

        lblTab1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblTab1.setText("Patient Registration   >");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 10);
        navTabsPanel.add(lblTab1, gridBagConstraints);

        lblTab2.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTab2.setForeground(new java.awt.Color(16, 185, 129));
        lblTab2.setText("Generate Token   >");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        navTabsPanel.add(lblTab2, gridBagConstraints);

        lblTab3.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblTab3.setText("Doctor Assignment");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        navTabsPanel.add(lblTab3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 10, 15);
        bodyPanel.add(navTabsPanel, gridBagConstraints);

        leftColumnPanel.setLayout(new java.awt.GridBagLayout());

        patientInfoPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(230, 230, 230), 1, true));
        patientInfoPanel.setLayout(new java.awt.GridBagLayout());

        lblPatientInfoTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPatientInfoTitle.setText("Patient Information");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        patientInfoPanel.add(lblPatientInfoTitle, gridBagConstraints);

        lblPreRegisteredBadge.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblPreRegisteredBadge.setText("PRE-REGISTERED");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        patientInfoPanel.add(lblPreRegisteredBadge, gridBagConstraints);

        lblPatientName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblPatientName.setText("Mr. Alexander Thompson");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 5, 15);
        patientInfoPanel.add(lblPatientName, gridBagConstraints);

        lblPatientID.setText("Patient ID: #HP-2024-8891");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 20, 15);
        patientInfoPanel.add(lblPatientID, gridBagConstraints);

        lblAgeGenTitle.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblAgeGenTitle.setText("AGE / GENDER");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 3, 10);
        patientInfoPanel.add(lblAgeGenTitle, gridBagConstraints);

        lblContactTitle.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblContactTitle.setText("CONTACT");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 3, 15);
        patientInfoPanel.add(lblContactTitle, gridBagConstraints);

        lblAgeGenVal.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblAgeGenVal.setText("34 Years / Male");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 15, 10);
        patientInfoPanel.add(lblAgeGenVal, gridBagConstraints);

        lblContactVal.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblContactVal.setText("+1 (555) 012-3456");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 15, 15);
        patientInfoPanel.add(lblContactVal, gridBagConstraints);

        lblBloodTitle.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblBloodTitle.setText("BLOOD GROUP");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 3, 10);
        patientInfoPanel.add(lblBloodTitle, gridBagConstraints);

        lblRegDateTitle.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblRegDateTitle.setText("REGISTRATION DATE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 3, 15);
        patientInfoPanel.add(lblRegDateTitle, gridBagConstraints);

        lblBloodVal.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblBloodVal.setText("O Positive (O+)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 20, 10);
        patientInfoPanel.add(lblBloodVal, gridBagConstraints);

        lblRegDateVal.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblRegDateVal.setText("Oct 24, 2023 | 09:15 AM");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 20, 15);
        patientInfoPanel.add(lblRegDateVal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        leftColumnPanel.add(patientInfoPanel, gridBagConstraints);

        vitalsPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(230, 230, 230), 1, true));
        vitalsPanel.setLayout(new java.awt.GridBagLayout());

        lblVitalsTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblVitalsTitle.setText("Patient Vitals");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        vitalsPanel.add(lblVitalsTitle, gridBagConstraints);

        cardBP.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(230, 230, 230), 1, true));
        cardBP.setPreferredSize(new java.awt.Dimension(120, 70));
        cardBP.setLayout(new java.awt.GridBagLayout());

        lblBPTitle.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblBPTitle.setText("BP");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 2, 15);
        cardBP.add(lblBPTitle, gridBagConstraints);

        lblBPVal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblBPVal.setText("120/80");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 10, 15);
        cardBP.add(lblBPVal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.33;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 20, 10);
        vitalsPanel.add(cardBP, gridBagConstraints);

        cardTemp.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(230, 230, 230), 1, true));
        cardTemp.setPreferredSize(new java.awt.Dimension(120, 70));
        cardTemp.setLayout(new java.awt.GridBagLayout());

        lblTempTitle.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblTempTitle.setText("TEMP");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 2, 15);
        cardTemp.add(lblTempTitle, gridBagConstraints);

        lblTempVal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTempVal.setText("98.6°F");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 10, 15);
        cardTemp.add(lblTempVal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.33;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 20, 10);
        vitalsPanel.add(cardTemp, gridBagConstraints);

        cardSpo2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(230, 230, 230), 1, true));
        cardSpo2.setPreferredSize(new java.awt.Dimension(120, 70));
        cardSpo2.setLayout(new java.awt.GridBagLayout());

        lblSpo2Title.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblSpo2Title.setText("SPO2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 2, 15);
        cardSpo2.add(lblSpo2Title, gridBagConstraints);

        lblSpo2Val.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblSpo2Val.setForeground(new java.awt.Color(16, 185, 129));
        lblSpo2Val.setText("99%");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 10, 15);
        cardSpo2.add(lblSpo2Val, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.34;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 20, 15);
        vitalsPanel.add(cardSpo2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        leftColumnPanel.add(vitalsPanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.55;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 15, 10);
        bodyPanel.add(leftColumnPanel, gridBagConstraints);

        rightColumnPanel.setLayout(new java.awt.GridBagLayout());

        genTokenCard.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(230, 230, 230), 1, true));
        genTokenCard.setLayout(new java.awt.GridBagLayout());

        lblGenTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblGenTitle.setText("Generate Queue Token");
        lblGenTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(25, 20, 5, 20);
        genTokenCard.add(lblGenTitle, gridBagConstraints);

        lblGenSubtitle.setText("<html><center>Select the appropriate department to assign a token<br>number for this patient session.</center></html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 20, 20);
        genTokenCard.add(lblGenSubtitle, gridBagConstraints);

        lblDeptLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDeptLabel.setText("Department");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 5, 20);
        genTokenCard.add(lblDeptLabel, gridBagConstraints);

        cbDepartment.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose Department", "Cardiology", "Dermatology", "Pediatrics", "General Medicine" }));
        cbDepartment.setPreferredSize(new java.awt.Dimension(200, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 15, 20);
        genTokenCard.add(cbDepartment, gridBagConstraints);

        tipPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(167, 243, 208), 1, true));
        tipPanel.setLayout(new java.awt.GridBagLayout());

        lblTipText.setText("<html>Estimated waiting time for <b>General Medicine</b> is currently <b>12 minutes</b> with 4 patients ahead in queue.</html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        tipPanel.add(lblTipText, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 20, 20);
        genTokenCard.add(tipPanel, gridBagConstraints);

        btnGenerateTokenSubmit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGenerateTokenSubmit.setText("Generate Token");
        btnGenerateTokenSubmit.setPreferredSize(new java.awt.Dimension(150, 40));
        btnGenerateTokenSubmit.setFocusPainted(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 25, 20);
        genTokenCard.add(btnGenerateTokenSubmit, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        rightColumnPanel.add(genTokenCard, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.45;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 15, 15);
        bodyPanel.add(rightColumnPanel, gridBagConstraints);

        bottomPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(230, 230, 230), 1, true));
        bottomPanel.setLayout(new java.awt.BorderLayout());

        tableHeaderArea.setPreferredSize(new java.awt.Dimension(400, 50));
        tableHeaderArea.setLayout(new java.awt.GridBagLayout());

        lblLiveQueueTitle.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblLiveQueueTitle.setText("Live Queue Status");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 10);
        tableHeaderArea.add(lblLiveQueueTitle, gridBagConstraints);

        bottomPanel.add(tableHeaderArea, java.awt.BorderLayout.NORTH);

        tblLiveQueue.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"#GM-452", "Robert Chen", "General Medicine", "Active", "10:45 AM"},
                {"#CD-102", "Maria Garcia", "Cardiology", "Waiting", "10:52 AM"}
            },
            new String [] {
                "TOKEN", "PATIENT", "DEPARTMENT", "STATUS", "TIME"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLiveQueue.setRowHeight(40);
        tblLiveQueue.setShowGrid(false);
        tableScroll.setViewportView(tblLiveQueue);

        bottomPanel.add(tableScroll, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 15, 15);
        bodyPanel.add(bottomPanel, gridBagConstraints);

        bodyScroll.setViewportView(bodyPanel);

        mainPanel.add(bodyScroll, java.awt.BorderLayout.CENTER);

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JScrollPane bodyScroll;
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JButton btnAssignDoctor;
    private javax.swing.JButton btnGenerateToken;
    private javax.swing.JButton btnGenerateTokenSubmit;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnManageWaitlist;
    private javax.swing.JButton btnRegisterWalkin;
    private javax.swing.JPanel cardBP;
    private javax.swing.JPanel cardSpo2;
    private javax.swing.JPanel cardTemp;
    private javax.swing.JComboBox cbDepartment;
    private javax.swing.JPanel genTokenCard;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel lblAgeGenTitle;
    private javax.swing.JLabel lblAgeGenVal;
    private javax.swing.JLabel lblBPTitle;
    private javax.swing.JLabel lblBPVal;
    private javax.swing.JLabel lblBloodTitle;
    private javax.swing.JLabel lblBloodVal;
    private javax.swing.JLabel lblContactTitle;
    private javax.swing.JLabel lblContactVal;
    private javax.swing.JLabel lblDeptLabel;
    private javax.swing.JLabel lblGenSubtitle;
    private javax.swing.JLabel lblGenTitle;
    private javax.swing.JLabel lblHeaderSubtitle;
    private javax.swing.JLabel lblHeaderTitle;
    private javax.swing.JLabel lblHospicare;
    private javax.swing.JLabel lblLiveQueueTitle;
    private javax.swing.JLabel lblPatientID;
    private javax.swing.JLabel lblPatientInfoTitle;
    private javax.swing.JLabel lblPatientName;
    private javax.swing.JLabel lblPreRegisteredBadge;
    private javax.swing.JLabel lblRegDateTitle;
    private javax.swing.JLabel lblRegDateVal;
    private javax.swing.JLabel lblSpo2Title;
    private javax.swing.JLabel lblSpo2Val;
    private javax.swing.JLabel lblTab1;
    private javax.swing.JLabel lblTab2;
    private javax.swing.JLabel lblTab3;
    private javax.swing.JLabel lblTempTitle;
    private javax.swing.JLabel lblTempVal;
    private javax.swing.JLabel lblTipText;
    private javax.swing.JLabel lblVitalsTitle;
    private javax.swing.JPanel leftColumnPanel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel navTabsPanel;
    private javax.swing.JPanel patientInfoPanel;
    private javax.swing.JPanel rightColumnPanel;
    private javax.swing.JPanel sidebarPanel;
    private javax.swing.JPanel tableHeaderArea;
    private javax.swing.JScrollPane tableScroll;
    private javax.swing.JTable tblLiveQueue;
    private javax.swing.JPanel tipPanel;
    private javax.swing.JPanel vitalsPanel;
    // End of variables declaration//GEN-END:variables
}
