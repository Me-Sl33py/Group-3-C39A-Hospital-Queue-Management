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
        bodyScroll = new javax.swing.JScrollPane();
        bodyPanel = new javax.swing.JPanel();
        navTabsPanel = new javax.swing.JPanel();
        lblTab1 = new javax.swing.JLabel();
        lblTab2 = new javax.swing.JLabel();
        lblTab3 = new javax.swing.JLabel();
        leftColumnPanel = new javax.swing.JPanel();
        patientInfoPanel = new javax.swing.JPanel();
        lblPatientInfoTitle = new javax.swing.JLabel();
        lblPreRegisteredBadge = new javax.swing.JLabel();
        lblPatientName = new javax.swing.JLabel();
        lblPatientID = new javax.swing.JLabel();
        lblAgeGenTitle = new javax.swing.JLabel();
        lblContactTitle = new javax.swing.JLabel();
        lblAgeGenVal = new javax.swing.JLabel();
        lblContactVal = new javax.swing.JLabel();
        lblBloodTitle = new javax.swing.JLabel();
        lblRegDateTitle = new javax.swing.JLabel();
        lblBloodVal = new javax.swing.JLabel();
        lblRegDateVal = new javax.swing.JLabel();
        vitalsPanel = new javax.swing.JPanel();
        lblVitalsTitle = new javax.swing.JLabel();
        cardBP = new javax.swing.JPanel();
        lblBPTitle = new javax.swing.JLabel();
        lblBPVal = new javax.swing.JLabel();
        cardTemp = new javax.swing.JPanel();
        lblTempTitle = new javax.swing.JLabel();
        lblTempVal = new javax.swing.JLabel();
        cardSpo2 = new javax.swing.JPanel();
        lblSpo2Title = new javax.swing.JLabel();
        lblSpo2Val = new javax.swing.JLabel();
        rightColumnPanel = new javax.swing.JPanel();
        genTokenCard = new javax.swing.JPanel();
        lblGenTitle = new javax.swing.JLabel();
        lblGenSubtitle = new javax.swing.JLabel();
        lblDeptLabel = new javax.swing.JLabel();
        cbDepartment = new javax.swing.JComboBox<>();
        tipPanel = new javax.swing.JPanel();
        lblTipText = new javax.swing.JLabel();
        btnGenerateTokenSubmit = new javax.swing.JButton();
        bottomPanel = new javax.swing.JPanel();
        tableHeaderArea = new javax.swing.JPanel();
        lblLiveQueueTitle = new javax.swing.JLabel();
        tableScroll = new javax.swing.JScrollPane();
        tblLiveQueue = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hospicare - Generate Token");
        getContentPane().setLayout(new java.awt.BorderLayout());

        // Sidebar
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

        // Sidebar buttons
        JButton[] sidebarBtns = {btnRegisterWalkin, btnGenerateToken, btnAssignDoctor, btnManageWaitlist};
        String[] sidebarText = {"Register Walk-in", "Generate Token", "Assign to Doctor", "Manage Waitlist"};
        for (int i = 0; i < sidebarBtns.length; i++) {
            sidebarBtns[i].setFont(new java.awt.Font("Segoe UI", i == 1 ? 1 : 0, 14));
            sidebarBtns[i].setForeground(java.awt.Color.WHITE);
            sidebarBtns[i].setText(sidebarText[i]);
            sidebarBtns[i].setBorderPainted(false);
            if (i == 1) {
                sidebarBtns[i].setBackground(new java.awt.Color(18, 116, 210));
            } else {
                sidebarBtns[i].setContentAreaFilled(false);
            }
            sidebarBtns[i].setFocusPainted(false);
            sidebarBtns[i].setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            gbc = new java.awt.GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = i + 1;
            gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gbc.anchor = java.awt.GridBagConstraints.WEST;
            gbc.insets = new java.awt.Insets(5, 15, 5, 15);
            gbc.weightx = 1.0;
            sidebarPanel.add(sidebarBtns[i], gbc);
        }

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

        // Main Panel
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
        lblHeaderSubtitle.setText("Sarah Jenkins - Chief Receptionist");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(0, 20, 10, 10);
        gbc.weightx = 1.0;
        headerPanel.add(lblHeaderSubtitle, gbc);

        mainPanel.add(headerPanel, java.awt.BorderLayout.NORTH);

        bodyScroll.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        bodyPanel.setBackground(new java.awt.Color(245, 246, 248));
        bodyPanel.setLayout(new java.awt.GridBagLayout());

        // Nav Tabs Panel
        navTabsPanel.setOpaque(false);
        navTabsPanel.setLayout(new java.awt.GridBagLayout());

        lblTab1.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblTab1.setForeground(new java.awt.Color(150, 150, 150));
        lblTab1.setText("Patient Registration   >");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(0, 5, 0, 10);
        navTabsPanel.add(lblTab1, gbc);

        lblTab2.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTab2.setForeground(new java.awt.Color(16, 185, 129));
        lblTab2.setText("Generate Token   >");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(0, 10, 0, 10);
        navTabsPanel.add(lblTab2, gbc);

        lblTab3.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblTab3.setForeground(new java.awt.Color(150, 150, 150));
        lblTab3.setText("Doctor Assignment");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(0, 10, 0, 10);
        gbc.weightx = 1.0;
        navTabsPanel.add(lblTab3, gbc);

        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new java.awt.Insets(15, 15, 10, 15);
        bodyPanel.add(navTabsPanel, gbc);

        // Left Column (Patient Info & Vitals)
        leftColumnPanel.setOpaque(false);
        leftColumnPanel.setLayout(new java.awt.GridBagLayout());

        // Patient Info Panel
        patientInfoPanel.setBackground(java.awt.Color.WHITE);
        patientInfoPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230), 1, true));
        patientInfoPanel.setLayout(new java.awt.GridBagLayout());

        lblPatientInfoTitle.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lblPatientInfoTitle.setForeground(new java.awt.Color(80, 80, 80));
        lblPatientInfoTitle.setText("Patient Information");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(15, 15, 15, 15);
        gbc.weightx = 1.0;
        patientInfoPanel.add(lblPatientInfoTitle, gbc);

        lblPreRegisteredBadge.setFont(new java.awt.Font("Segoe UI", 1, 11));
        lblPreRegisteredBadge.setForeground(new java.awt.Color(16, 185, 129));
        lblPreRegisteredBadge.setText("PRE-REGISTERED");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = java.awt.GridBagConstraints.EAST;
        gbc.insets = new java.awt.Insets(15, 15, 15, 15);
        patientInfoPanel.add(lblPreRegisteredBadge, gbc);

        lblPatientName.setFont(new java.awt.Font("Segoe UI", 1, 18));
        lblPatientName.setForeground(new java.awt.Color(50, 50, 50));
        lblPatientName.setText("Mr. Alexander Thompson");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(0, 15, 5, 15);
        gbc.weightx = 1.0;
        patientInfoPanel.add(lblPatientName, gbc);

        lblPatientID.setFont(new java.awt.Font("Segoe UI", 0, 12));
        lblPatientID.setForeground(new java.awt.Color(120, 120, 120));
        lblPatientID.setText("Patient ID: #HP-2024-8891");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(0, 15, 20, 15);
        gbc.weightx = 1.0;
        patientInfoPanel.add(lblPatientID, gbc);

        // Details grid
        lblAgeGenTitle.setFont(new java.awt.Font("Segoe UI", 1, 11));
        lblAgeGenTitle.setForeground(new java.awt.Color(160, 160, 160));
        lblAgeGenTitle.setText("AGE / GENDER");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(0, 15, 3, 10);
        gbc.weightx = 0.5;
        patientInfoPanel.add(lblAgeGenTitle, gbc);

        lblContactTitle.setFont(new java.awt.Font("Segoe UI", 1, 11));
        lblContactTitle.setForeground(new java.awt.Color(160, 160, 160));
        lblContactTitle.setText("CONTACT");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(0, 10, 3, 15);
        gbc.weightx = 0.5;
        patientInfoPanel.add(lblContactTitle, gbc);

        lblAgeGenVal.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblAgeGenVal.setForeground(new java.awt.Color(50, 50, 50));
        lblAgeGenVal.setText("34 Years / Male");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(0, 15, 15, 10);
        gbc.weightx = 0.5;
        patientInfoPanel.add(lblAgeGenVal, gbc);

        lblContactVal.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblContactVal.setForeground(new java.awt.Color(50, 50, 50));
        lblContactVal.setText("+1 (555) 012-3456");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(0, 10, 15, 15);
        gbc.weightx = 0.5;
        patientInfoPanel.add(lblContactVal, gbc);

        lblBloodTitle.setFont(new java.awt.Font("Segoe UI", 1, 11));
        lblBloodTitle.setForeground(new java.awt.Color(160, 160, 160));
        lblBloodTitle.setText("BLOOD GROUP");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(0, 15, 3, 10);
        gbc.weightx = 0.5;
        patientInfoPanel.add(lblBloodTitle, gbc);

        lblRegDateTitle.setFont(new java.awt.Font("Segoe UI", 1, 11));
        lblRegDateTitle.setForeground(new java.awt.Color(160, 160, 160));
        lblRegDateTitle.setText("REGISTRATION DATE");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(0, 10, 3, 15);
        gbc.weightx = 0.5;
        patientInfoPanel.add(lblRegDateTitle, gbc);

        lblBloodVal.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblBloodVal.setForeground(new java.awt.Color(16, 185, 129));
        lblBloodVal.setText("O Positive (O+)");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(0, 15, 20, 10);
        gbc.weightx = 0.5;
        patientInfoPanel.add(lblBloodVal, gbc);

        lblRegDateVal.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblRegDateVal.setForeground(new java.awt.Color(50, 50, 50));
        lblRegDateVal.setText("Oct 24, 2023 | 09:15 AM");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(0, 10, 20, 15);
        gbc.weightx = 0.5;
        patientInfoPanel.add(lblRegDateVal, gbc);

        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        leftColumnPanel.add(patientInfoPanel, gbc);

        // Vitals Panel
        vitalsPanel.setBackground(java.awt.Color.WHITE);
        vitalsPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230), 1, true));
        vitalsPanel.setLayout(new java.awt.GridBagLayout());

        lblVitalsTitle.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lblVitalsTitle.setForeground(new java.awt.Color(80, 80, 80));
        lblVitalsTitle.setText("Patient Vitals");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(15, 15, 15, 15);
        gbc.weightx = 1.0;
        vitalsPanel.add(lblVitalsTitle, gbc);

        // BP Card
        cardBP.setBackground(new java.awt.Color(250, 250, 250));
        cardBP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(235, 235, 235), 1, true));
        cardBP.setPreferredSize(new java.awt.Dimension(120, 70));
        cardBP.setLayout(new java.awt.GridBagLayout());

        lblBPTitle.setFont(new java.awt.Font("Segoe UI", 1, 11));
        lblBPTitle.setForeground(new java.awt.Color(160, 160, 160));
        lblBPTitle.setText("BP");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(10, 15, 2, 15);
        gbc.weightx = 1.0;
        cardBP.add(lblBPTitle, gbc);

        lblBPVal.setFont(new java.awt.Font("Segoe UI", 1, 18));
        lblBPVal.setForeground(new java.awt.Color(50, 50, 50));
        lblBPVal.setText("120/80");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(0, 15, 10, 15);
        gbc.weightx = 1.0;
        cardBP.add(lblBPVal, gbc);

        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(0, 15, 20, 10);
        gbc.weightx = 0.33;
        vitalsPanel.add(cardBP, gbc);

        // Temp Card
        cardTemp.setBackground(new java.awt.Color(250, 250, 250));
        cardTemp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(235, 235, 235), 1, true));
        cardTemp.setPreferredSize(new java.awt.Dimension(120, 70));
        cardTemp.setLayout(new java.awt.GridBagLayout());

        lblTempTitle.setFont(new java.awt.Font("Segoe UI", 1, 11));
        lblTempTitle.setForeground(new java.awt.Color(160, 160, 160));
        lblTempTitle.setText("TEMP");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(10, 15, 2, 15);
        gbc.weightx = 1.0;
        cardTemp.add(lblTempTitle, gbc);

        lblTempVal.setFont(new java.awt.Font("Segoe UI", 1, 18));
        lblTempVal.setForeground(new java.awt.Color(50, 50, 50));
        lblTempVal.setText("98.6°F");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(0, 15, 10, 15);
        gbc.weightx = 1.0;
        cardTemp.add(lblTempVal, gbc);

        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(0, 10, 20, 10);
        gbc.weightx = 0.33;
        vitalsPanel.add(cardTemp, gbc);

        // Spo2 Card
        cardSpo2.setBackground(new java.awt.Color(250, 250, 250));
        cardSpo2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(235, 235, 235), 1, true));
        cardSpo2.setPreferredSize(new java.awt.Dimension(120, 70));
        cardSpo2.setLayout(new java.awt.GridBagLayout());

        lblSpo2Title.setFont(new java.awt.Font("Segoe UI", 1, 11));
        lblSpo2Title.setForeground(new java.awt.Color(160, 160, 160));
        lblSpo2Title.setText("SPO2");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(10, 15, 2, 15);
        gbc.weightx = 1.0;
        cardSpo2.add(lblSpo2Title, gbc);

        lblSpo2Val.setFont(new java.awt.Font("Segoe UI", 1, 18));
        lblSpo2Val.setForeground(new java.awt.Color(16, 185, 129));
        lblSpo2Val.setText("99%");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(0, 15, 10, 15);
        gbc.weightx = 1.0;
        cardSpo2.add(lblSpo2Val, gbc);

        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(0, 10, 20, 15);
        gbc.weightx = 0.34;
        vitalsPanel.add(cardSpo2, gbc);

        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        leftColumnPanel.add(vitalsPanel, gbc);

        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = java.awt.GridBagConstraints.BOTH;
        gbc.weightx = 0.55;
        gbc.insets = new java.awt.Insets(0, 15, 15, 10);
        bodyPanel.add(leftColumnPanel, gbc);

        // Right Column (Generate Queue Token Card)
        rightColumnPanel.setOpaque(false);
        rightColumnPanel.setLayout(new java.awt.GridBagLayout());

        genTokenCard.setBackground(java.awt.Color.WHITE);
        genTokenCard.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230), 1, true));
        genTokenCard.setLayout(new java.awt.GridBagLayout());

        lblGenTitle.setFont(new java.awt.Font("Segoe UI", 1, 18));
        lblGenTitle.setForeground(new java.awt.Color(50, 50, 50));
        lblGenTitle.setText("Generate Queue Token");
        lblGenTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(25, 20, 5, 20);
        gbc.weightx = 1.0;
        genTokenCard.add(lblGenTitle, gbc);

        lblGenSubtitle.setFont(new java.awt.Font("Segoe UI", 0, 12));
        lblGenSubtitle.setForeground(new java.awt.Color(120, 120, 120));
        lblGenSubtitle.setText("<html><center>Select the appropriate department to assign a token<br>number for this patient session.</center></html>");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new java.awt.Insets(0, 20, 20, 20);
        genTokenCard.add(lblGenSubtitle, gbc);

        lblDeptLabel.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblDeptLabel.setForeground(new java.awt.Color(50, 50, 50));
        lblDeptLabel.setText("Department");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(0, 20, 5, 20);
        gbc.weightx = 1.0;
        genTokenCard.add(lblDeptLabel, gbc);

        cbDepartment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {
            "Choose Department", "General Medicine", "Cardiology", "Dermatology", "Pediatrics"
        }));
        cbDepartment.setPreferredSize(new java.awt.Dimension(200, 35));
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(0, 20, 15, 20);
        gbc.weightx = 1.0;
        genTokenCard.add(cbDepartment, gbc);

        // Est Waiting Info Panel
        tipPanel.setBackground(new java.awt.Color(236, 252, 240));
        tipPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(210, 240, 220), 1, true));
        tipPanel.setLayout(new java.awt.GridBagLayout());

        lblTipText.setFont(new java.awt.Font("Segoe UI", 0, 12));
        lblTipText.setForeground(new java.awt.Color(20, 120, 70));
        lblTipText.setText("<html>Estimated waiting time for <b>General Medicine</b> is currently <b>12 minutes</b> with 4 patients ahead in queue.</html>");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = java.awt.GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new java.awt.Insets(10, 10, 10, 10);
        tipPanel.add(lblTipText, gbc);

        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(0, 20, 20, 20);
        gbc.weightx = 1.0;
        genTokenCard.add(tipPanel, gbc);

        btnGenerateTokenSubmit.setBackground(new java.awt.Color(16, 185, 129));
        btnGenerateTokenSubmit.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnGenerateTokenSubmit.setForeground(java.awt.Color.WHITE);
        btnGenerateTokenSubmit.setText("Generate Token");
        btnGenerateTokenSubmit.setPreferredSize(new java.awt.Dimension(150, 40));
        btnGenerateTokenSubmit.setFocusPainted(false);
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.anchor = java.awt.GridBagConstraints.SOUTH;
        gbc.insets = new java.awt.Insets(0, 20, 25, 20);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        genTokenCard.add(btnGenerateTokenSubmit, gbc);

        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = java.awt.GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        rightColumnPanel.add(genTokenCard, gbc);

        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = java.awt.GridBagConstraints.BOTH;
        gbc.weightx = 0.45;
        gbc.insets = new java.awt.Insets(0, 10, 15, 15);
        bodyPanel.add(rightColumnPanel, gbc);

        // Bottom Panel (Live Queue Table)
        bottomPanel.setBackground(java.awt.Color.WHITE);
        bottomPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230), 1, true));
        bottomPanel.setLayout(new java.awt.BorderLayout());

        tableHeaderArea.setOpaque(false);
        tableHeaderArea.setPreferredSize(new java.awt.Dimension(400, 50));
        tableHeaderArea.setLayout(new java.awt.GridBagLayout());

        lblLiveQueueTitle.setFont(new java.awt.Font("Segoe UI", 1, 16));
        lblLiveQueueTitle.setForeground(new java.awt.Color(50, 50, 50));
        lblLiveQueueTitle.setText("Live Queue Status");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(10, 15, 10, 10);
        gbc.weightx = 1.0;
        tableHeaderArea.add(lblLiveQueueTitle, gbc);
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
            boolean[] canEdit = new boolean [] { false, false, false, false, false };
            public boolean isCellEditable(int rowIndex, int columnIndex) { return canEdit [columnIndex]; }
        });
        tblLiveQueue.setRowHeight(40);
        tblLiveQueue.setShowGrid(false);
        tableScroll.setViewportView(tblLiveQueue);
        bottomPanel.add(tableScroll, java.awt.BorderLayout.CENTER);

        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = java.awt.GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.5;
        gbc.insets = new java.awt.Insets(10, 15, 15, 15);
        bodyPanel.add(bottomPanel, gbc);

        bodyScroll.setViewportView(bodyPanel);
        mainPanel.add(bodyScroll, java.awt.BorderLayout.CENTER);

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }
    // </editor-fold>//GEN-END:initComponents

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
    private javax.swing.JComboBox<String> cbDepartment;
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
