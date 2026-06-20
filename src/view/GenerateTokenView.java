package view;

import javax.swing.*;
import java.awt.*;

/**
 * Generate Token View class compatible with the NetBeans GUI Builder.
 * Exposes Swing controls through getters for the GenerateTokenController.
 */
public class GenerateTokenView extends javax.swing.JPanel {

    public GenerateTokenView() {
        initComponents();
    }

    

    
    public JComboBox<model.Department> getCbDepartment() { return cbDepartment; }
    public JComboBox<model.Doctor> getCbDoctor() { return cbDoctor; }
    public JButton getBtnGenerateTokenSubmit() { return btnGenerateTokenSubmit; }
    public JTable getTblLiveQueue() { return tblLiveQueue; }
    public JLabel getLblTipText() { return lblTipText; }

    public JLabel getLblPatientName() { return lblPatientName; }
    public JLabel getLblPatientID() { return lblPatientID; }
    public JLabel getLblAgeGenVal() { return lblAgeGenVal; }
    public JLabel getLblContactVal() { return lblContactVal; }
    public JLabel getLblBloodVal() { return lblBloodVal; }
    public JLabel getLblRegDateVal() { return lblRegDateVal; }
    public JComboBox<model.Patient> getCbPatientSearch() { return cbPatientSearch; }

    public JPanel getMainPanel() { return mainPanel; }

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

        btnManageWaitlist = new javax.swing.JButton();
        btnManageWaitlist.setForeground(new java.awt.Color(255, 255, 255));
        btnManageWaitlist.setBorderPainted(false);
        btnManageWaitlist.setContentAreaFilled(false);
        btnLogout = new javax.swing.JButton();
        btnLogout.setForeground(new java.awt.Color(255, 0, 0));
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
        searchPanel = new javax.swing.JPanel();
        lblSearchPatient = new javax.swing.JLabel();
        cbPatientSearch = new javax.swing.JComboBox<>();
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
        cbDepartment = new javax.swing.JComboBox<>();
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
        tableScroll.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tableScroll.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        tblLiveQueue = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1024, 768));

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



        btnManageWaitlist.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnManageWaitlist.setText("Manage Waitlist");
        btnManageWaitlist.setFocusPainted(false);
        btnManageWaitlist.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
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

        setLayout(new java.awt.BorderLayout());

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

        leftColumnPanel.setOpaque(false);
        leftColumnPanel.setLayout(new java.awt.GridBagLayout());

        searchPanel.setOpaque(false);
        searchPanel.setLayout(new java.awt.GridBagLayout());

        lblSearchPatient.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSearchPatient.setText("Select Patient:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 10);
        searchPanel.add(lblSearchPatient, gridBagConstraints);

        cbPatientSearch.setPreferredSize(new java.awt.Dimension(300, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        searchPanel.add(cbPatientSearch, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        leftColumnPanel.add(searchPanel, gridBagConstraints);

        patientInfoPanel.setBackground(new java.awt.Color(255, 255, 255));
        patientInfoPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(230, 230, 230), 1, true));
        patientInfoPanel.setOpaque(true);
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
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        leftColumnPanel.add(patientInfoPanel, gridBagConstraints);

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

        cbDepartment.setModel(new javax.swing.DefaultComboBoxModel<>(new model.Department[]{ new model.Department(-1, "Choose Department", "") }));
        cbDepartment.setPreferredSize(new java.awt.Dimension(200, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 15, 20);
        genTokenCard.add(cbDepartment, gridBagConstraints);
        
        lblDoctorLabel = new javax.swing.JLabel();
        lblDoctorLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDoctorLabel.setForeground(new java.awt.Color(80, 80, 80));
        lblDoctorLabel.setText("Doctor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 5, 20);
        genTokenCard.add(lblDoctorLabel, gridBagConstraints);

        cbDoctor = new javax.swing.JComboBox<>();
        cbDoctor.setPreferredSize(new java.awt.Dimension(200, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 15, 20);
        genTokenCard.add(cbDoctor, gridBagConstraints);

        tipPanel = new javax.swing.JPanel();
        tipPanel.setBackground(new java.awt.Color(236, 253, 245));
        tipPanel.setOpaque(true);
        tipPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(167, 243, 208), 1, true));
        tipPanel.setLayout(new java.awt.GridBagLayout());

        lblTipText = new javax.swing.JLabel();
        lblTipText.setForeground(new java.awt.Color(6, 95, 70));
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
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 20, 20);
        genTokenCard.add(tipPanel, gridBagConstraints);

        btnGenerateTokenSubmit = new javax.swing.JButton();
        btnGenerateTokenSubmit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGenerateTokenSubmit.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerateTokenSubmit.setBackground(new java.awt.Color(5, 150, 105));
        btnGenerateTokenSubmit.setBorderPainted(false);
        btnGenerateTokenSubmit.setOpaque(true);
        btnGenerateTokenSubmit.setText("Generate Token");
        btnGenerateTokenSubmit.setPreferredSize(new java.awt.Dimension(150, 40));
        btnGenerateTokenSubmit.setFocusPainted(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
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
            },
            new String [] {
                "TOKEN", "PATIENT", "DEPARTMENT", "DOCTOR", "STATUS", "EST. WAIT TIME", "TIME"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLiveQueue.setRowHeight(40);
        tblLiveQueue.setShowGrid(false);
        tblLiveQueue.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        // Set minimum column widths so horizontal scroll activates
        tblLiveQueue.getColumnModel().getColumn(0).setPreferredWidth(80); // TOKEN
        tblLiveQueue.getColumnModel().getColumn(1).setPreferredWidth(160); // PATIENT
        tblLiveQueue.getColumnModel().getColumn(2).setPreferredWidth(160); // DEPARTMENT
        tblLiveQueue.getColumnModel().getColumn(3).setPreferredWidth(160); // DOCTOR
        tblLiveQueue.getColumnModel().getColumn(4).setPreferredWidth(100); // STATUS
        tblLiveQueue.getColumnModel().getColumn(5).setPreferredWidth(150); // EST. WAIT TIME
        tblLiveQueue.getColumnModel().getColumn(6).setPreferredWidth(100); // TIME
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

        add(mainPanel, java.awt.BorderLayout.CENTER);

    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JScrollPane bodyScroll;
    private javax.swing.JPanel bottomPanel;

    private javax.swing.JButton btnGenerateToken;
    private javax.swing.JButton btnGenerateTokenSubmit;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnManageWaitlist;
    private javax.swing.JButton btnRegisterWalkin;
    private javax.swing.JComboBox<model.Department> cbDepartment;
    private javax.swing.JPanel genTokenCard;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel lblAgeGenTitle;
    private javax.swing.JLabel lblAgeGenVal;
    private javax.swing.JLabel lblBloodTitle;
    private javax.swing.JLabel lblBloodVal;
    private javax.swing.JLabel lblContactTitle;
    private javax.swing.JLabel lblContactVal;
    private javax.swing.JLabel lblDeptLabel;
    private javax.swing.JLabel lblDoctorLabel;
    private javax.swing.JComboBox<model.Doctor> cbDoctor;
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
    private javax.swing.JLabel lblTab1;
    private javax.swing.JLabel lblTab2;
    private javax.swing.JLabel lblTab3;
    private javax.swing.JLabel lblTipText;
    private javax.swing.JPanel leftColumnPanel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel navTabsPanel;
    private javax.swing.JPanel patientInfoPanel;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JLabel lblSearchPatient;
    private javax.swing.JComboBox<model.Patient> cbPatientSearch;
    private javax.swing.JPanel rightColumnPanel;
    private javax.swing.JPanel sidebarPanel;
    private javax.swing.JPanel tableHeaderArea;
    private javax.swing.JScrollPane tableScroll;
    private javax.swing.JTable tblLiveQueue;
    private javax.swing.JPanel tipPanel;
    // End of variables declaration//GEN-END:variables
}
