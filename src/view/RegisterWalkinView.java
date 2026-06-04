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
        btnRegisterWalkin.setBackground(new java.awt.Color(18, 116, 210));
        btnRegisterWalkin.setBorderPainted(false);
        btnRegisterWalkin.setOpaque(true);
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
        lblIntakeSubtitle = new javax.swing.JLabel();
        lblWalkinTitle = new javax.swing.JLabel();
        lblWalkinTitle.setForeground(new java.awt.Color(80, 80, 80));
        lblWalkinDesc = new javax.swing.JLabel();
        formPanel = new javax.swing.JPanel();
        formPanel.setBackground(new java.awt.Color(255, 255, 255));
        formPanel.setOpaque(true);
        lblNameLabel = new javax.swing.JLabel();
        lblDobLabel = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        tfDob = new javax.swing.JTextField();
        lblGenderLabel = new javax.swing.JLabel();
        lblPhoneLabel = new javax.swing.JLabel();
        cbGender = new javax.swing.JComboBox();
        tfPhone = new javax.swing.JTextField();
        lblReasonLabel = new javax.swing.JLabel();
        reasonScroll = new javax.swing.JScrollPane();
        taReason = new javax.swing.JTextArea();
        rightColumnPanel = new javax.swing.JPanel();
        rightColumnPanel.setOpaque(false);
        loadCard = new javax.swing.JPanel();
        loadCard.setBackground(new java.awt.Color(5, 150, 105));
        loadCard.setOpaque(true);
        loadCard.setOpaque(true);
        lblLoadTitle = new javax.swing.JLabel();
        lblLoadTitle.setForeground(new java.awt.Color(240, 240, 240));
        lblLoadVal = new javax.swing.JLabel();
        lblLoadVal.setForeground(new java.awt.Color(255, 255, 255));
        lblLoadSubtitle = new javax.swing.JLabel();
        lblLoadSubtitle.setForeground(new java.awt.Color(230, 230, 230));
        tipsCard = new javax.swing.JPanel();
        tipsCard.setBackground(new java.awt.Color(255, 255, 255));
        tipsCard.setOpaque(true);
        lblTipsTitle = new javax.swing.JLabel();
        lblTipsTitle.setForeground(new java.awt.Color(80, 80, 80));
        lblTip1 = new javax.swing.JLabel();
        lblTip1.setForeground(new java.awt.Color(128, 128, 128));
        lblTip2 = new javax.swing.JLabel();
        lblTip2.setForeground(new java.awt.Color(128, 128, 128));
        lblTip3 = new javax.swing.JLabel();
        lblTip3.setForeground(new java.awt.Color(128, 128, 128));
        imageCard = new javax.swing.JPanel();
        imageCard.setBackground(new java.awt.Color(255, 255, 255));
        imageCard.setOpaque(true);
        lblWaitingRoomImage = new javax.swing.JLabel();
        buttonsPanel = new javax.swing.JPanel();
        buttonsPanel.setOpaque(false);
        btnReset = new javax.swing.JButton();
        btnReset.setBackground(new java.awt.Color(255, 255, 255));
        btnReset.setForeground(new java.awt.Color(80, 80, 80));
        btnReset.setOpaque(true);
        btnSaveContinue = new javax.swing.JButton();
        btnSaveContinue.setForeground(new java.awt.Color(255, 255, 255));
        btnSaveContinue.setBackground(new java.awt.Color(5, 150, 105));
        btnSaveContinue.setBorderPainted(false);
        btnSaveContinue.setOpaque(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hospicare - Register Walk-in");

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

        btnRegisterWalkin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
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

        btnGenerateToken.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
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

        lblHeaderSubtitle.setText("Sarah Jenkins - Head Receptionist");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 10, 10);
        headerPanel.add(lblHeaderSubtitle, gridBagConstraints);

        mainPanel.add(headerPanel, java.awt.BorderLayout.NORTH);

        bodyPanel.setLayout(new java.awt.GridBagLayout());

        lblIntakeSubtitle.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblIntakeSubtitle.setForeground(new java.awt.Color(16, 185, 129));
        lblIntakeSubtitle.setText("PATIENT INTAKE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 3, 20);
        bodyPanel.add(lblIntakeSubtitle, gridBagConstraints);

        lblWalkinTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblWalkinTitle.setText("Register Walk-in");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 5, 20);
        bodyPanel.add(lblWalkinTitle, gridBagConstraints);

        lblWalkinDesc.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblWalkinDesc.setText("Enter patient details accurately to initiate clinical triaging.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 20, 20);
        bodyPanel.add(lblWalkinDesc, gridBagConstraints);

        formPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(230, 230, 230), 1, true));
        formPanel.setLayout(new java.awt.GridBagLayout());

        lblNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblNameLabel.setText("Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 5, 10);
        formPanel.add(lblNameLabel, gridBagConstraints);

        lblDobLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDobLabel.setText("Date of Birth");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(20, 10, 5, 20);
        formPanel.add(lblDobLabel, gridBagConstraints);

        tfName.setPreferredSize(new java.awt.Dimension(150, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 15, 10);
        formPanel.add(tfName, gridBagConstraints);

        tfDob.setPreferredSize(new java.awt.Dimension(150, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 15, 20);
        formPanel.add(tfDob, gridBagConstraints);

        lblGenderLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblGenderLabel.setText("Gender");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 5, 10);
        formPanel.add(lblGenderLabel, gridBagConstraints);

        lblPhoneLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPhoneLabel.setText("Phone");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 5, 20);
        formPanel.add(lblPhoneLabel, gridBagConstraints);

        cbGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Gender", "Male", "Female", "Other" }));
        cbGender.setPreferredSize(new java.awt.Dimension(150, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 15, 10);
        formPanel.add(cbGender, gridBagConstraints);

        tfPhone.setPreferredSize(new java.awt.Dimension(150, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 15, 20);
        formPanel.add(tfPhone, gridBagConstraints);

        lblReasonLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblReasonLabel.setText("Reason");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 5, 20);
        formPanel.add(lblReasonLabel, gridBagConstraints);

        taReason.setColumns(20);
        taReason.setRows(5);
        taReason.setLineWrap(true);
        taReason.setWrapStyleWord(true);
        reasonScroll.setViewportView(taReason);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 20, 20);
        formPanel.add(reasonScroll, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.55;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 15, 10);
        bodyPanel.add(formPanel, gridBagConstraints);

        rightColumnPanel.setLayout(new java.awt.GridBagLayout());

        loadCard.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(5, 150, 105), 1, true));
        loadCard.setLayout(new java.awt.GridBagLayout());

        lblLoadTitle.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblLoadTitle.setText("Today's Load");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 5, 15);
        loadCard.add(lblLoadTitle, gridBagConstraints);

        lblLoadVal.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblLoadVal.setText("14");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 2, 15);
        loadCard.add(lblLoadVal, gridBagConstraints);

        lblLoadSubtitle.setText("Patients Waiting");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 15, 15);
        loadCard.add(lblLoadSubtitle, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        rightColumnPanel.add(loadCard, gridBagConstraints);

        tipsCard.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(230, 230, 230), 1, true));
        tipsCard.setLayout(new java.awt.GridBagLayout());

        lblTipsTitle.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTipsTitle.setText("Quick Tips");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 10, 15);
        tipsCard.add(lblTipsTitle, gridBagConstraints);

        lblTip1.setText("<html><b>1</b> Verify patient identification documents before finalizing.</html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 8, 15);
        tipsCard.add(lblTip1, gridBagConstraints);

        lblTip2.setText("<html><b>2</b> Assign priority tags for emergency walk-ins immediately.</html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 8, 15);
        tipsCard.add(lblTip2, gridBagConstraints);

        lblTip3.setText("<html><b>3</b> Ensure the phone number is active for token SMS alerts.</html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 15, 15);
        tipsCard.add(lblTip3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        rightColumnPanel.add(tipsCard, gridBagConstraints);

        imageCard.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(230, 230, 230), 1, true));
        imageCard.setLayout(new java.awt.GridBagLayout());

        lblWaitingRoomImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWaitingRoomImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/waiting_room.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        imageCard.add(lblWaitingRoomImage, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        rightColumnPanel.add(imageCard, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.45;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 15, 20);
        bodyPanel.add(rightColumnPanel, gridBagConstraints);

        buttonsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 15, 5));

        btnReset.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnReset.setText("Reset");
        btnReset.setPreferredSize(new java.awt.Dimension(120, 40));
        btnReset.setFocusPainted(false);
        buttonsPanel.add(btnReset);

        btnSaveContinue.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSaveContinue.setText("Save & Continue");
        btnSaveContinue.setPreferredSize(new java.awt.Dimension(160, 40));
        btnSaveContinue.setFocusPainted(false);
        buttonsPanel.add(btnSaveContinue);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 20, 20);
        bodyPanel.add(buttonsPanel, gridBagConstraints);

        bodyScroll.setViewportView(bodyPanel);

        mainPanel.add(bodyScroll, java.awt.BorderLayout.CENTER);

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JScrollPane bodyScroll;
    private javax.swing.JButton btnAssignDoctor;
    private javax.swing.JButton btnGenerateToken;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnManageWaitlist;
    private javax.swing.JButton btnRegisterWalkin;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSaveContinue;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JComboBox cbGender;
    private javax.swing.JPanel formPanel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JPanel imageCard;
    private javax.swing.JLabel lblDobLabel;
    private javax.swing.JLabel lblGenderLabel;
    private javax.swing.JLabel lblHeaderSubtitle;
    private javax.swing.JLabel lblHeaderTitle;
    private javax.swing.JLabel lblHospicare;
    private javax.swing.JLabel lblIntakeSubtitle;
    private javax.swing.JLabel lblLoadSubtitle;
    private javax.swing.JLabel lblLoadTitle;
    private javax.swing.JLabel lblLoadVal;
    private javax.swing.JLabel lblNameLabel;
    private javax.swing.JLabel lblPhoneLabel;
    private javax.swing.JLabel lblReasonLabel;
    private javax.swing.JLabel lblTip1;
    private javax.swing.JLabel lblTip2;
    private javax.swing.JLabel lblTip3;
    private javax.swing.JLabel lblTipsTitle;
    private javax.swing.JLabel lblWaitingRoomImage;
    private javax.swing.JLabel lblWalkinDesc;
    private javax.swing.JLabel lblWalkinTitle;
    private javax.swing.JPanel loadCard;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JScrollPane reasonScroll;
    private javax.swing.JPanel rightColumnPanel;
    private javax.swing.JPanel sidebarPanel;
    private javax.swing.JTextArea taReason;
    private javax.swing.JTextField tfDob;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfPhone;
    private javax.swing.JPanel tipsCard;
    // End of variables declaration//GEN-END:variables
}
