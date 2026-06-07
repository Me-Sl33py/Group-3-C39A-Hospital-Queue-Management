package view;

public class ReceptionistAccountSettingsView extends javax.swing.JPanel {

    public ReceptionistAccountSettingsView() {
        initComponents();
    }

    // Getters for UI components
    public javax.swing.JTextField getTxtFullName() { return txtFullName; }
    public javax.swing.JTextField getTxtPhone() { return txtPhone; }
    public javax.swing.JPasswordField getTxtCurrentPwd() { return txtCurrentPwd; }
    public javax.swing.JPasswordField getTxtNewPwd() { return txtNewPwd; }
    public javax.swing.JPasswordField getTxtConfirmPwd() { return txtConfirmPwd; }
    public javax.swing.JButton getBtnSave() { return btnSave; }
    public javax.swing.JButton getBtnCancel() { return btnCancel; }
    public javax.swing.JLabel getLblWelcome() { return lblWelcome; }
    public javax.swing.JLabel getLblFullNameVal() { return lblFullNameVal; }
    public javax.swing.JLabel getLblPhoneVal() { return lblPhoneVal; }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        headerPanel = new javax.swing.JPanel();
        headerLeft = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        headerRight = new javax.swing.JPanel();
        lblWelcome = new javax.swing.JLabel();
        lblRoleTop = new javax.swing.JLabel();
        mainScroll = new javax.swing.JScrollPane();
        bodyPanel = new javax.swing.JPanel();
        summaryPanel = new javax.swing.JPanel();
        cardFullName = new javax.swing.JPanel();
        lblFullNameTitle = new javax.swing.JLabel();
        lblFullNameVal = new javax.swing.JLabel();
        cardEmpId = new javax.swing.JPanel();
        lblEmpIdTitle = new javax.swing.JLabel();
        lblEmpIdVal = new javax.swing.JLabel();
        cardRole = new javax.swing.JPanel();
        lblRoleTitle = new javax.swing.JLabel();
        lblRoleVal = new javax.swing.JLabel();
        cardDept = new javax.swing.JPanel();
        lblDeptTitle = new javax.swing.JLabel();
        lblDeptVal = new javax.swing.JLabel();
        cardPhone = new javax.swing.JPanel();
        lblPhoneTitle = new javax.swing.JLabel();
        lblPhoneVal = new javax.swing.JLabel();
        cardJoinDate = new javax.swing.JPanel();
        lblJoinDateTitle = new javax.swing.JLabel();
        lblJoinDateVal = new javax.swing.JLabel();
        cardShift = new javax.swing.JPanel();
        lblShiftTitle = new javax.swing.JLabel();
        lblShiftVal = new javax.swing.JLabel();
        formsPanel = new javax.swing.JPanel();
        leftForm = new javax.swing.JPanel();
        lblEditProfile = new javax.swing.JLabel();
        lblFn = new javax.swing.JLabel();
        lblPn = new javax.swing.JLabel();
        txtFullName = new javax.swing.JTextField();
        txtPhone = new javax.swing.JTextField();
        lblCp = new javax.swing.JLabel();
        lblNp = new javax.swing.JLabel();
        lblCnp = new javax.swing.JLabel();
        txtCurrentPwd = new javax.swing.JPasswordField();
        txtNewPwd = new javax.swing.JPasswordField();
        txtConfirmPwd = new javax.swing.JPasswordField();
        rightForm = new javax.swing.JPanel();
        lblWorkInfo = new javax.swing.JLabel();
        lblWEmp = new javax.swing.JLabel();
        txtWorkEmpId = new javax.swing.JTextField();
        lblWDept = new javax.swing.JLabel();
        cbDept = new javax.swing.JComboBox();
        lblWCounter = new javax.swing.JLabel();
        cbCounter = new javax.swing.JComboBox();
        lblWSup = new javax.swing.JLabel();
        cbSupervisor = new javax.swing.JComboBox();
        lblWShift = new javax.swing.JLabel();
        cbShift = new javax.swing.JComboBox();
        notifPanel = new javax.swing.JPanel();
        lblNotifTitle = new javax.swing.JLabel();
        lblQA = new javax.swing.JLabel();
        chkQA = new javax.swing.JCheckBox();
        lblPRA = new javax.swing.JLabel();
        chkPRA = new javax.swing.JCheckBox();
        lblAA = new javax.swing.JLabel();
        chkAA = new javax.swing.JCheckBox();
        lblSN = new javax.swing.JLabel();
        chkSN = new javax.swing.JCheckBox();
        actionPanel = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setBackground(new java.awt.Color(249, 250, 251));
        setLayout(new java.awt.BorderLayout());

        headerPanel.setBackground(new java.awt.Color(255, 255, 255));
        headerPanel.setPreferredSize(new java.awt.Dimension(800, 70));
        headerPanel.setLayout(new java.awt.BorderLayout());

        headerLeft.setOpaque(false);
        headerLeft.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lblTitle.setText("Receptionist Account Settings");
        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        headerLeft.add(lblTitle);

        headerPanel.add(headerLeft, java.awt.BorderLayout.WEST);

        headerRight.setOpaque(false);
        headerRight.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        lblWelcome.setText("Welcome, Sarah Johnson");
        lblWelcome.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        headerRight.add(lblWelcome);

        lblRoleTop.setText("Receptionist");
        lblRoleTop.setForeground(new java.awt.Color(128, 128, 128));
        headerRight.add(lblRoleTop);

        headerPanel.add(headerRight, java.awt.BorderLayout.EAST);

        add(headerPanel, java.awt.BorderLayout.NORTH);

        mainScroll.setBorder(null);

        bodyPanel.setBackground(new java.awt.Color(249, 250, 251));
        bodyPanel.setLayout(new java.awt.GridBagLayout());

        summaryPanel.setOpaque(false);
        summaryPanel.setLayout(new java.awt.GridLayout(2, 4, 15, 15));

        cardFullName.setBackground(new java.awt.Color(255, 255, 255));
        cardFullName.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        cardFullName.setLayout(new java.awt.GridLayout(2, 1, 0, 5));

        lblFullNameTitle.setText("FULL NAME");
        lblFullNameTitle.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblFullNameTitle.setForeground(new java.awt.Color(128, 128, 128));
        cardFullName.add(lblFullNameTitle);

        lblFullNameVal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cardFullName.add(lblFullNameVal);

        summaryPanel.add(cardFullName);

        cardEmpId.setBackground(new java.awt.Color(255, 255, 255));
        cardEmpId.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        cardEmpId.setLayout(new java.awt.GridLayout(2, 1, 0, 5));

        lblEmpIdTitle.setText("EMPLOYEE ID");
        lblEmpIdTitle.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblEmpIdTitle.setForeground(new java.awt.Color(128, 128, 128));
        cardEmpId.add(lblEmpIdTitle);

        lblEmpIdVal.setText("REC-102");
        lblEmpIdVal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cardEmpId.add(lblEmpIdVal);

        summaryPanel.add(cardEmpId);

        cardRole.setBackground(new java.awt.Color(255, 255, 255));
        cardRole.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        cardRole.setLayout(new java.awt.GridLayout(2, 1, 0, 5));

        lblRoleTitle.setText("ROLE");
        lblRoleTitle.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblRoleTitle.setForeground(new java.awt.Color(128, 128, 128));
        cardRole.add(lblRoleTitle);

        lblRoleVal.setText("Receptionist");
        lblRoleVal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cardRole.add(lblRoleVal);

        summaryPanel.add(cardRole);

        cardDept.setBackground(new java.awt.Color(255, 255, 255));
        cardDept.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        cardDept.setLayout(new java.awt.GridLayout(2, 1, 0, 5));

        lblDeptTitle.setText("DEPARTMENT");
        lblDeptTitle.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblDeptTitle.setForeground(new java.awt.Color(128, 128, 128));
        cardDept.add(lblDeptTitle);

        lblDeptVal.setText("Reception");
        lblDeptVal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cardDept.add(lblDeptVal);

        summaryPanel.add(cardDept);

        cardPhone.setBackground(new java.awt.Color(255, 255, 255));
        cardPhone.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        cardPhone.setLayout(new java.awt.GridLayout(2, 1, 0, 5));

        lblPhoneTitle.setText("PHONE NO.");
        lblPhoneTitle.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblPhoneTitle.setForeground(new java.awt.Color(128, 128, 128));
        cardPhone.add(lblPhoneTitle);

        lblPhoneVal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cardPhone.add(lblPhoneVal);

        summaryPanel.add(cardPhone);

        cardJoinDate.setBackground(new java.awt.Color(255, 255, 255));
        cardJoinDate.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        cardJoinDate.setLayout(new java.awt.GridLayout(2, 1, 0, 5));

        lblJoinDateTitle.setText("JOIN DATE");
        lblJoinDateTitle.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblJoinDateTitle.setForeground(new java.awt.Color(128, 128, 128));
        cardJoinDate.add(lblJoinDateTitle);

        lblJoinDateVal.setText("15 Mar 2023");
        lblJoinDateVal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cardJoinDate.add(lblJoinDateVal);

        summaryPanel.add(cardJoinDate);

        cardShift.setBackground(new java.awt.Color(255, 255, 255));
        cardShift.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        cardShift.setLayout(new java.awt.GridLayout(2, 1, 0, 5));

        lblShiftTitle.setText("SHIFT TIMING");
        lblShiftTitle.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblShiftTitle.setForeground(new java.awt.Color(128, 128, 128));
        cardShift.add(lblShiftTitle);

        lblShiftVal.setText("08:00 AM - 04:00 PM");
        lblShiftVal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cardShift.add(lblShiftVal);

        summaryPanel.add(cardShift);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 20);
        bodyPanel.add(summaryPanel, gridBagConstraints);

        formsPanel.setOpaque(false);
        formsPanel.setLayout(new java.awt.GridLayout(1, 2, 20, 0));

        leftForm.setBackground(new java.awt.Color(255, 255, 255));
        leftForm.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        leftForm.setLayout(new java.awt.GridBagLayout());

        lblEditProfile.setText("Edit Profile Information");
        lblEditProfile.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        leftForm.add(lblEditProfile, gridBagConstraints);

        lblFn.setText("FULL NAME");
        lblFn.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblFn.setForeground(new java.awt.Color(128, 128, 128));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 5, 0);
        leftForm.add(lblFn, gridBagConstraints);

        lblPn.setText("PHONE NUMBER");
        lblPn.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblPn.setForeground(new java.awt.Color(128, 128, 128));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        leftForm.add(lblPn, gridBagConstraints);

        txtFullName.setMargin(new java.awt.Insets(5, 10, 5, 10));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 15, 0);
        leftForm.add(txtFullName, gridBagConstraints);

        txtPhone.setMargin(new java.awt.Insets(5, 10, 5, 10));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        leftForm.add(txtPhone, gridBagConstraints);

        lblCp.setText("CURRENT PASSWORD");
        lblCp.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblCp.setForeground(new java.awt.Color(128, 128, 128));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.33;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 5, 0);
        leftForm.add(lblCp, gridBagConstraints);

        lblNp.setText("NEW PASSWORD");
        lblNp.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblNp.setForeground(new java.awt.Color(128, 128, 128));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.33;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 5, 0);
        leftForm.add(lblNp, gridBagConstraints);

        lblCnp.setText("CONFIRM NEW PASSWORD");
        lblCnp.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblCnp.setForeground(new java.awt.Color(128, 128, 128));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.34;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        leftForm.add(lblCnp, gridBagConstraints);

        txtCurrentPwd.setMargin(new java.awt.Insets(5, 10, 5, 10));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.33;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        leftForm.add(txtCurrentPwd, gridBagConstraints);

        txtNewPwd.setMargin(new java.awt.Insets(5, 10, 5, 10));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.33;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        leftForm.add(txtNewPwd, gridBagConstraints);

        txtConfirmPwd.setMargin(new java.awt.Insets(5, 10, 5, 10));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.34;
        gridBagConstraints.weighty = 1.0;
        leftForm.add(txtConfirmPwd, gridBagConstraints);

        formsPanel.add(leftForm);

        rightForm.setBackground(new java.awt.Color(255, 255, 255));
        rightForm.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        rightForm.setLayout(new java.awt.GridBagLayout());

        lblWorkInfo.setText("Work Information");
        lblWorkInfo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        rightForm.add(lblWorkInfo, gridBagConstraints);

        lblWEmp.setText("EMPLOYEE ID");
        lblWEmp.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblWEmp.setForeground(new java.awt.Color(128, 128, 128));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        rightForm.add(lblWEmp, gridBagConstraints);

        txtWorkEmpId.setText("REC-102");
        txtWorkEmpId.setEditable(false);
        txtWorkEmpId.setBackground(new java.awt.Color(240, 240, 240));
        txtWorkEmpId.setMargin(new java.awt.Insets(5, 10, 5, 10));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        rightForm.add(txtWorkEmpId, gridBagConstraints);

        lblWDept.setText("DEPARTMENT");
        lblWDept.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblWDept.setForeground(new java.awt.Color(128, 128, 128));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        rightForm.add(lblWDept, gridBagConstraints);

        cbDept.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Reception" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        rightForm.add(cbDept, gridBagConstraints);

        lblWCounter.setText("ASSIGNED COUNTER");
        lblWCounter.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblWCounter.setForeground(new java.awt.Color(128, 128, 128));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        rightForm.add(lblWCounter, gridBagConstraints);

        cbCounter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Counter 1" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        rightForm.add(cbCounter, gridBagConstraints);

        lblWSup.setText("SUPERVISOR");
        lblWSup.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblWSup.setForeground(new java.awt.Color(128, 128, 128));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        rightForm.add(lblWSup, gridBagConstraints);

        cbSupervisor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dr. James Wilson" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        rightForm.add(cbSupervisor, gridBagConstraints);

        lblWShift.setText("CURRENT SHIFT");
        lblWShift.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblWShift.setForeground(new java.awt.Color(128, 128, 128));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        rightForm.add(lblWShift, gridBagConstraints);

        cbShift.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "08:00 AM - 04:00 PM" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        rightForm.add(cbShift, gridBagConstraints);

        formsPanel.add(rightForm);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 20, 20);
        bodyPanel.add(formsPanel, gridBagConstraints);

        notifPanel.setBackground(new java.awt.Color(255, 255, 255));
        notifPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        notifPanel.setLayout(new java.awt.GridBagLayout());

        lblNotifTitle.setText("Notification Preferences");
        lblNotifTitle.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        notifPanel.add(lblNotifTitle, gridBagConstraints);

        lblQA.setText("<html><b>Queue Alerts</b><br><font color='#808080'>Get notified for long queues</font></html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        notifPanel.add(lblQA, gridBagConstraints);

        chkQA.setSelected(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 15, 0);
        notifPanel.add(chkQA, gridBagConstraints);

        lblPRA.setText("<html><b>Patient Registration Alerts</b><br><font color='#808080'>Get notified for new patient registrations</font></html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        notifPanel.add(lblPRA, gridBagConstraints);

        chkPRA.setSelected(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        notifPanel.add(chkPRA, gridBagConstraints);

        lblAA.setText("<html><b>Appointment Alerts</b><br><font color='#808080'>Get notified for new appointments</font></html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        notifPanel.add(lblAA, gridBagConstraints);

        chkAA.setSelected(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        notifPanel.add(chkAA, gridBagConstraints);

        lblSN.setText("<html><b>System Notifications</b><br><font color='#808080'>Get notified for system updates and announcements</font></html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        notifPanel.add(lblSN, gridBagConstraints);

        chkSN.setSelected(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        notifPanel.add(chkSN, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 20, 20);
        bodyPanel.add(notifPanel, gridBagConstraints);

        actionPanel.setOpaque(false);
        actionPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        btnSave.setText("Save Changes");
        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSave.setForeground(new java.awt.Color(18, 116, 210));
        btnSave.setPreferredSize(new java.awt.Dimension(140, 40));
        btnSave.setFocusPainted(false);
        btnSave.setOpaque(true);
        actionPanel.add(btnSave);

        btnCancel.setText("Cancel");
        btnCancel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancel.setPreferredSize(new java.awt.Dimension(120, 40));
        btnCancel.setFocusPainted(false);
        btnCancel.setOpaque(true);
        actionPanel.add(btnCancel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 20, 20);
        bodyPanel.add(actionPanel, gridBagConstraints);

        mainScroll.setViewportView(bodyPanel);

        add(mainScroll, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel actionPanel;
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSave;
    private javax.swing.JPanel cardDept;
    private javax.swing.JPanel cardEmpId;
    private javax.swing.JPanel cardFullName;
    private javax.swing.JPanel cardJoinDate;
    private javax.swing.JPanel cardPhone;
    private javax.swing.JPanel cardRole;
    private javax.swing.JPanel cardShift;
    private javax.swing.JComboBox cbCounter;
    private javax.swing.JComboBox cbDept;
    private javax.swing.JComboBox cbShift;
    private javax.swing.JComboBox cbSupervisor;
    private javax.swing.JCheckBox chkAA;
    private javax.swing.JCheckBox chkPRA;
    private javax.swing.JCheckBox chkQA;
    private javax.swing.JCheckBox chkSN;
    private javax.swing.JPanel formsPanel;
    private javax.swing.JPanel headerLeft;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JPanel headerRight;
    private javax.swing.JLabel lblAA;
    private javax.swing.JLabel lblCnp;
    private javax.swing.JLabel lblCp;
    private javax.swing.JLabel lblDeptTitle;
    private javax.swing.JLabel lblDeptVal;
    private javax.swing.JLabel lblEditProfile;
    private javax.swing.JLabel lblEmpIdTitle;
    private javax.swing.JLabel lblEmpIdVal;
    private javax.swing.JLabel lblFn;
    private javax.swing.JLabel lblFullNameTitle;
    private javax.swing.JLabel lblFullNameVal;
    private javax.swing.JLabel lblJoinDateTitle;
    private javax.swing.JLabel lblJoinDateVal;
    private javax.swing.JLabel lblNotifTitle;
    private javax.swing.JLabel lblNp;
    private javax.swing.JLabel lblPRA;
    private javax.swing.JLabel lblPhoneTitle;
    private javax.swing.JLabel lblPhoneVal;
    private javax.swing.JLabel lblPn;
    private javax.swing.JLabel lblQA;
    private javax.swing.JLabel lblRoleTitle;
    private javax.swing.JLabel lblRoleTop;
    private javax.swing.JLabel lblRoleVal;
    private javax.swing.JLabel lblSN;
    private javax.swing.JLabel lblShiftTitle;
    private javax.swing.JLabel lblShiftVal;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblWCounter;
    private javax.swing.JLabel lblWDept;
    private javax.swing.JLabel lblWEmp;
    private javax.swing.JLabel lblWShift;
    private javax.swing.JLabel lblWSup;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JLabel lblWorkInfo;
    private javax.swing.JPanel leftForm;
    private javax.swing.JScrollPane mainScroll;
    private javax.swing.JPanel notifPanel;
    private javax.swing.JPanel rightForm;
    private javax.swing.JPanel summaryPanel;
    private javax.swing.JPasswordField txtConfirmPwd;
    private javax.swing.JPasswordField txtCurrentPwd;
    private javax.swing.JTextField txtFullName;
    private javax.swing.JPasswordField txtNewPwd;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtWorkEmpId;
    // End of variables declaration//GEN-END:variables
}
