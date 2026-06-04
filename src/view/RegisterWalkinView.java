package view;

import javax.swing.*;
import java.awt.*;

/**
 * Register Walk-in View class compatible with the NetBeans GUI Builder.
 * Exposes form elements via getters to follow strict MVC architecture.
 */
public class RegisterWalkinView extends javax.swing.JFrame {

    public RegisterWalkinView() {
        initComponents();
        loadWaitingRoomImage();
    }

    public JButton getBtnRegisterWalkin() { return btnRegisterWalkin; }
    public JButton getBtnGenerateToken() { return btnGenerateToken; }
    public JButton getBtnAssignDoctor() { return btnAssignDoctor; }
    public JButton getBtnManageWaitlist() { return btnManageWaitlist; }
    public JButton getBtnLogout() { return btnLogout; }

    public JTextField getTfName() { return tfName; }
    public JTextField getTfDob() { return tfDob; }
    public JComboBox<String> getCbGender() { return cbGender; }
    public JTextField getTfPhone() { return tfPhone; }
    public JTextArea getTaReason() { return taReason; }

    public JButton getBtnReset() { return btnReset; }
    public JButton getBtnSaveContinue() { return btnSaveContinue; }
    public JLabel getLblLoadVal() { return lblLoadVal; }

    private void loadWaitingRoomImage() {
        try {
            java.net.URL imgUrl = getClass().getResource("/images/waiting_room.png");
            if (imgUrl != null) {
                // Scale image to fit the container roughly
                ImageIcon icon = new ImageIcon(imgUrl);
                Image img = icon.getImage().getScaledInstance(280, 160, Image.SCALE_SMOOTH);
                lblWaitingRoomImage.setIcon(new ImageIcon(img));
            }
        } catch (Exception e) {
            System.err.println("Could not load waiting room image: " + e.getMessage());
        }
    }

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
        lblIntakeSubtitle = new javax.swing.JLabel();
        lblWalkinTitle = new javax.swing.JLabel();
        lblWalkinDesc = new javax.swing.JLabel();
        formPanel = new javax.swing.JPanel();
        lblNameLabel = new javax.swing.JLabel();
        lblDobLabel = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        tfDob = new javax.swing.JTextField();
        lblGenderLabel = new javax.swing.JLabel();
        lblPhoneLabel = new javax.swing.JLabel();
        cbGender = new javax.swing.JComboBox<>();
        tfPhone = new javax.swing.JTextField();
        lblReasonLabel = new javax.swing.JLabel();
        reasonScroll = new javax.swing.JScrollPane();
        taReason = new javax.swing.JTextArea();
        rightColumnPanel = new javax.swing.JPanel();
        loadCard = new javax.swing.JPanel();
        lblLoadTitle = new javax.swing.JLabel();
        lblLoadVal = new javax.swing.JLabel();
        lblLoadSubtitle = new javax.swing.JLabel();
        tipsCard = new javax.swing.JPanel();
        lblTipsTitle = new javax.swing.JLabel();
        lblTip1 = new javax.swing.JLabel();
        lblTip2 = new javax.swing.JLabel();
        lblTip3 = new javax.swing.JLabel();
        imageCard = new javax.swing.JPanel();
        lblWaitingRoomImage = new javax.swing.JLabel();
        buttonsPanel = new javax.swing.JPanel();
        btnReset = new javax.swing.JButton();
        btnSaveContinue = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hospicare - Register Walk-in");
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
            sidebarBtns[i].setFont(new java.awt.Font("Segoe UI", i == 0 ? 1 : 0, 14));
            sidebarBtns[i].setForeground(java.awt.Color.WHITE);
            sidebarBtns[i].setText(sidebarText[i]);
            sidebarBtns[i].setBorderPainted(false);
            if (i == 0) {
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
        lblHeaderSubtitle.setText("Sarah Jenkins - Head Receptionist");
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

        // Header Section inside body
        lblIntakeSubtitle.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblIntakeSubtitle.setForeground(new java.awt.Color(16, 185, 129));
        lblIntakeSubtitle.setText("PATIENT INTAKE");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(20, 20, 3, 20);
        gbc.weightx = 1.0;
        bodyPanel.add(lblIntakeSubtitle, gbc);

        lblWalkinTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblWalkinTitle.setForeground(new java.awt.Color(50, 50, 50));
        lblWalkinTitle.setText("Register Walk-in");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(0, 20, 5, 20);
        gbc.weightx = 1.0;
        bodyPanel.add(lblWalkinTitle, gbc);

        lblWalkinDesc.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblWalkinDesc.setForeground(new java.awt.Color(120, 120, 120));
        lblWalkinDesc.setText("Enter patient details accurately to initiate clinical triaging.");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(0, 20, 20, 20);
        gbc.weightx = 1.0;
        bodyPanel.add(lblWalkinDesc, gbc);

        // Form Panel (Left)
        formPanel.setBackground(java.awt.Color.WHITE);
        formPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230), 1, true));
        formPanel.setLayout(new java.awt.GridBagLayout());

        lblNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblNameLabel.setText("Name");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(20, 20, 5, 10);
        gbc.weightx = 0.5;
        formPanel.add(lblNameLabel, gbc);

        lblDobLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDobLabel.setText("Date of Birth");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(20, 10, 5, 20);
        gbc.weightx = 0.5;
        formPanel.add(lblDobLabel, gbc);

        tfName.setPreferredSize(new java.awt.Dimension(150, 35));
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(0, 20, 15, 10);
        gbc.weightx = 0.5;
        formPanel.add(tfName, gbc);

        tfDob.setPreferredSize(new java.awt.Dimension(150, 35));
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(0, 10, 15, 20);
        gbc.weightx = 0.5;
        formPanel.add(tfDob, gbc);

        lblGenderLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblGenderLabel.setText("Gender");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(0, 20, 5, 10);
        gbc.weightx = 0.5;
        formPanel.add(lblGenderLabel, gbc);

        lblPhoneLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPhoneLabel.setText("Phone");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(0, 10, 5, 20);
        gbc.weightx = 0.5;
        formPanel.add(lblPhoneLabel, gbc);

        cbGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Gender", "Male", "Female", "Other" }));
        cbGender.setPreferredSize(new java.awt.Dimension(150, 35));
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(0, 20, 15, 10);
        gbc.weightx = 0.5;
        formPanel.add(cbGender, gbc);

        tfPhone.setPreferredSize(new java.awt.Dimension(150, 35));
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(0, 10, 15, 20);
        gbc.weightx = 0.5;
        formPanel.add(tfPhone, gbc);

        lblReasonLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblReasonLabel.setText("Reason");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(0, 20, 5, 20);
        gbc.weightx = 1.0;
        formPanel.add(lblReasonLabel, gbc);

        taReason.setColumns(20);
        taReason.setRows(5);
        taReason.setLineWrap(true);
        taReason.setWrapStyleWord(true);
        reasonScroll.setViewportView(taReason);

        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = java.awt.GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new java.awt.Insets(0, 20, 20, 20);
        formPanel.add(reasonScroll, gbc);

        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = java.awt.GridBagConstraints.BOTH;
        gbc.weightx = 0.55;
        gbc.weighty = 1.0;
        gbc.insets = new java.awt.Insets(0, 20, 15, 10);
        bodyPanel.add(formPanel, gbc);

        // Right Column
        rightColumnPanel.setOpaque(false);
        rightColumnPanel.setLayout(new java.awt.GridBagLayout());

        // Today's Load
        loadCard.setBackground(new java.awt.Color(5, 150, 105));
        loadCard.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(5, 150, 105), 1, true));
        loadCard.setLayout(new java.awt.GridBagLayout());

        lblLoadTitle.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblLoadTitle.setForeground(new java.awt.Color(240, 240, 240));
        lblLoadTitle.setText("Today's Load");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(15, 15, 5, 15);
        gbc.weightx = 1.0;
        loadCard.add(lblLoadTitle, gbc);

        lblLoadVal.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblLoadVal.setForeground(java.awt.Color.WHITE);
        lblLoadVal.setText("14");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(0, 15, 2, 15);
        gbc.weightx = 1.0;
        loadCard.add(lblLoadVal, gbc);

        lblLoadSubtitle.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblLoadSubtitle.setForeground(new java.awt.Color(230, 230, 230));
        lblLoadSubtitle.setText("Patients Waiting");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(0, 15, 15, 15);
        gbc.weightx = 1.0;
        loadCard.add(lblLoadSubtitle, gbc);

        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new java.awt.Insets(0, 0, 15, 0);
        rightColumnPanel.add(loadCard, gbc);

        // Tips Card
        tipsCard.setBackground(java.awt.Color.WHITE);
        tipsCard.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230), 1, true));
        tipsCard.setLayout(new java.awt.GridBagLayout());

        lblTipsTitle.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTipsTitle.setForeground(new java.awt.Color(50, 50, 50));
        lblTipsTitle.setText("Quick Tips");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(15, 15, 10, 15);
        gbc.weightx = 1.0;
        tipsCard.add(lblTipsTitle, gbc);

        lblTip1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblTip1.setForeground(new java.awt.Color(80, 80, 80));
        lblTip1.setText("<html><b>1</b> Verify patient identification documents before finalizing.</html>");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(0, 15, 8, 15);
        gbc.weightx = 1.0;
        tipsCard.add(lblTip1, gbc);

        lblTip2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblTip2.setForeground(new java.awt.Color(80, 80, 80));
        lblTip2.setText("<html><b>2</b> Assign priority tags for emergency walk-ins immediately.</html>");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(0, 15, 8, 15);
        gbc.weightx = 1.0;
        tipsCard.add(lblTip2, gbc);

        lblTip3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblTip3.setForeground(new java.awt.Color(80, 80, 80));
        lblTip3.setText("<html><b>3</b> Ensure the phone number is active for token SMS alerts.</html>");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(0, 15, 15, 15);
        gbc.weightx = 1.0;
        tipsCard.add(lblTip3, gbc);

        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new java.awt.Insets(0, 0, 15, 0);
        rightColumnPanel.add(tipsCard, gbc);

        // Image Card
        imageCard.setBackground(java.awt.Color.WHITE);
        imageCard.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230), 1, true));
        imageCard.setLayout(new java.awt.GridBagLayout());

        lblWaitingRoomImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = java.awt.GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new java.awt.Insets(5, 5, 5, 5);
        imageCard.add(lblWaitingRoomImage, gbc);

        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = java.awt.GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        rightColumnPanel.add(imageCard, gbc);

        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = java.awt.GridBagConstraints.BOTH;
        gbc.weightx = 0.45;
        gbc.insets = new java.awt.Insets(0, 10, 15, 20);
        bodyPanel.add(rightColumnPanel, gbc);

        // Buttons Panel
        buttonsPanel.setOpaque(false);
        buttonsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 15, 0));

        btnReset.setBackground(java.awt.Color.WHITE);
        btnReset.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnReset.setForeground(new java.awt.Color(50, 50, 50));
        btnReset.setText("Reset");
        btnReset.setPreferredSize(new java.awt.Dimension(120, 40));
        btnReset.setFocusPainted(false);
        buttonsPanel.add(btnReset);

        btnSaveContinue.setBackground(new java.awt.Color(5, 150, 105));
        btnSaveContinue.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSaveContinue.setForeground(java.awt.Color.WHITE);
        btnSaveContinue.setText("Save & Continue");
        btnSaveContinue.setPreferredSize(new java.awt.Dimension(160, 40));
        btnSaveContinue.setFocusPainted(false);
        buttonsPanel.add(btnSaveContinue);

        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(10, 20, 20, 20);
        bodyPanel.add(buttonsPanel, gbc);

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
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JButton btnAssignDoctor;
    private javax.swing.JButton btnGenerateToken;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnManageWaitlist;
    private javax.swing.JButton btnRegisterWalkin;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSaveContinue;
    private javax.swing.JComboBox<String> cbGender;
    private javax.swing.JPanel formPanel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JPanel imageCard;
    private javax.swing.JLabel lblHeaderSubtitle;
    private javax.swing.JLabel lblHeaderTitle;
    private javax.swing.JLabel lblHospicare;
    private javax.swing.JLabel lblIntakeSubtitle;
    private javax.swing.JLabel lblLoadSubtitle;
    private javax.swing.JLabel lblLoadTitle;
    private javax.swing.JLabel lblLoadVal;
    private javax.swing.JLabel lblTip1;
    private javax.swing.JLabel lblTip2;
    private javax.swing.JLabel lblTip3;
    private javax.swing.JLabel lblTipsTitle;
    private javax.swing.JLabel lblWaitingRoomImage;
    private javax.swing.JLabel lblWalkinDesc;
    private javax.swing.JLabel lblWalkinTitle;
    private javax.swing.JPanel loadCard;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel rightColumnPanel;
    private javax.swing.JPanel sidebarPanel;
    private javax.swing.JPanel tipsCard;
    private javax.swing.JScrollPane reasonScroll;
    private javax.swing.JTextArea taReason;
    private javax.swing.JTextField tfDob;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfPhone;
    private javax.swing.JLabel lblNameLabel;
    private javax.swing.JLabel lblDobLabel;
    private javax.swing.JLabel lblGenderLabel;
    private javax.swing.JLabel lblPhoneLabel;
    private javax.swing.JLabel lblReasonLabel;
    // End of variables declaration//GEN-END:variables
}
