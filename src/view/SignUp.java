package view;

import controller.RegistrationController;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * SignUp.java — Patient Registration screen.
 *
 * VIEW RULES (MVC):
 *   ✔ Keep: constructor, initComponents(), getter methods, action performed stubs
 *   ✘ NO logic here — all logic is in RegistrationController
 *   ✘ Do NOT edit inside //GEN-BEGIN ... //GEN-END blocks
 *
 * @author Group 3 C39A
 */
public class SignUp extends javax.swing.JFrame {

    // ====================================================================
    //  CONTROLLER REFERENCE
    //  Stored here so the action stubs below can call controller methods.
    // ====================================================================
    private RegistrationController controller;

    // ====================================================================
    //  CONSTRUCTOR — called when you open this screen
    // ====================================================================
    public SignUp() {
        initComponents();                          // ← NetBeans manages this (Design tab)
        controller = new RegistrationController(this); // ← store reference so stubs can call it
    }

    // ====================================================================
    //  !! DO NOT EDIT BETWEEN GEN-BEGIN AND GEN-END !!
    //  Use the Design tab in NetBeans to modify this section visually.
    // ====================================================================
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rightPanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        subtitleLabel = new javax.swing.JLabel();
        separator = new javax.swing.JSeparator();
        fullNameLabel = new javax.swing.JLabel();
        fullNameField = new javax.swing.JTextField();
        dobLabel = new javax.swing.JLabel();
        genderLabel = new javax.swing.JLabel();
        genderComboBox = new javax.swing.JComboBox();
        phoneLabel = new javax.swing.JLabel();
        phoneField = new javax.swing.JTextField();
        locationLabel = new javax.swing.JLabel();
        locationField = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        show1 = new javax.swing.JLabel();
        hide1 = new javax.swing.JLabel();
        confirmPasswordLabel = new javax.swing.JLabel();
        confirmPasswordField = new javax.swing.JPasswordField();
        show2 = new javax.swing.JLabel();
        hide2 = new javax.swing.JLabel();
        signUpButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        alreadyAccountLabel = new javax.swing.JLabel();
        loginLinkButton = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        leftPanel = new javax.swing.JPanel();
        hospitalNameLabel = new javax.swing.JLabel();
        taglineLabel = new javax.swing.JLabel();
        imageLabel = new javax.swing.JLabel();
        bottomQuoteLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hospital Queue Management — Sign Up");
        setMinimumSize(new java.awt.Dimension(916, 600));
        setResizable(false);
        getContentPane().setLayout(null);

        rightPanel.setBackground(new java.awt.Color(255, 255, 255));
        rightPanel.setLayout(null);

        titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(33, 33, 33));
        titleLabel.setText("Create Account");
        rightPanel.add(titleLabel);
        titleLabel.setBounds(30, 22, 320, 32);

        subtitleLabel.setForeground(new java.awt.Color(120, 120, 120));
        subtitleLabel.setText("Fill in your details below to register");
        rightPanel.add(subtitleLabel);
        subtitleLabel.setBounds(30, 52, 320, 18);

        separator.setForeground(new java.awt.Color(205, 210, 218));
        rightPanel.add(separator);
        separator.setBounds(30, 76, 545, 2);

        fullNameLabel.setForeground(new java.awt.Color(120, 120, 120));
        fullNameLabel.setText("Full Name");
        rightPanel.add(fullNameLabel);
        fullNameLabel.setBounds(28, 93, 132, 18);

        fullNameField.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        rightPanel.add(fullNameField);
        fullNameField.setBounds(165, 87, 340, 28);

        dobLabel.setForeground(new java.awt.Color(120, 120, 120));
        dobLabel.setText("Date of Birth");
        rightPanel.add(dobLabel);
        dobLabel.setBounds(28, 137, 132, 18);

        genderLabel.setForeground(new java.awt.Color(120, 120, 120));
        genderLabel.setText("Gender");
        rightPanel.add(genderLabel);
        genderLabel.setBounds(28, 181, 132, 18);

        genderComboBox.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        genderComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Gender", "Male", "Female", "Others", "Prefer not to say" }));
        rightPanel.add(genderComboBox);
        genderComboBox.setBounds(165, 175, 340, 28);

        phoneLabel.setForeground(new java.awt.Color(120, 120, 120));
        phoneLabel.setText("Phone Number");
        rightPanel.add(phoneLabel);
        phoneLabel.setBounds(28, 225, 132, 18);

        phoneField.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        rightPanel.add(phoneField);
        phoneField.setBounds(165, 219, 340, 28);

        locationLabel.setForeground(new java.awt.Color(120, 120, 120));
        locationLabel.setText("Location");
        rightPanel.add(locationLabel);
        locationLabel.setBounds(28, 269, 132, 18);

        locationField.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        rightPanel.add(locationField);
        locationField.setBounds(165, 263, 340, 28);

        passwordLabel.setForeground(new java.awt.Color(120, 120, 120));
        passwordLabel.setText("Create Password");
        rightPanel.add(passwordLabel);
        passwordLabel.setBounds(28, 313, 132, 18);

        passwordField.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        rightPanel.add(passwordField);
        passwordField.setBounds(165, 307, 340, 28);

        show1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/show password.png"))); // NOI18N
        rightPanel.add(show1);
        show1.setBounds(511, 309, 24, 24);

        hide1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hide password.png"))); // NOI18N
        hide1.setVisible(false);
        rightPanel.add(hide1);
        hide1.setBounds(511, 309, 24, 24);

        confirmPasswordLabel.setForeground(new java.awt.Color(120, 120, 120));
        confirmPasswordLabel.setText("Confirm Password");
        rightPanel.add(confirmPasswordLabel);
        confirmPasswordLabel.setBounds(28, 357, 132, 18);

        confirmPasswordField.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        confirmPasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmPasswordFieldActionPerformed(evt);
            }
        });
        rightPanel.add(confirmPasswordField);
        confirmPasswordField.setBounds(165, 351, 340, 28);

        show2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/show password.png"))); // NOI18N
        rightPanel.add(show2);
        show2.setBounds(511, 353, 24, 24);

        hide2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hide password.png"))); // NOI18N
        hide2.setVisible(false);
        rightPanel.add(hide2);
        hide2.setBounds(511, 353, 24, 24);

        signUpButton.setBackground(new java.awt.Color(21, 101, 192));
        signUpButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        signUpButton.setForeground(new java.awt.Color(255, 255, 255));
        signUpButton.setText("Sign Up");
        signUpButton.setBorderPainted(false);
        signUpButton.setFocusPainted(false);
        signUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signUpButtonActionPerformed(evt);
            }
        });
        rightPanel.add(signUpButton);
        signUpButton.setBounds(100, 400, 182, 37);

        backButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        backButton.setForeground(new java.awt.Color(21, 101, 192));
        backButton.setText("Back");
        backButton.setFocusPainted(false);
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        rightPanel.add(backButton);
        backButton.setBounds(290, 400, 182, 37);

        alreadyAccountLabel.setForeground(new java.awt.Color(120, 120, 120));
        alreadyAccountLabel.setText("Already have an Account?");
        rightPanel.add(alreadyAccountLabel);
        alreadyAccountLabel.setBounds(180, 450, 150, 20);

        loginLinkButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        loginLinkButton.setForeground(new java.awt.Color(21, 101, 192));
        loginLinkButton.setText("Login");
        loginLinkButton.setBorderPainted(false);
        loginLinkButton.setContentAreaFilled(false);
        loginLinkButton.setFocusPainted(false);
        loginLinkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginLinkButtonActionPerformed(evt);
            }
        });
        rightPanel.add(loginLinkButton);
        loginLinkButton.setBounds(310, 450, 70, 20);
        rightPanel.add(jDateChooser1);
        jDateChooser1.setBounds(165, 131, 340, 28);

        getContentPane().add(rightPanel);
        rightPanel.setBounds(10, 0, 540, 555);

        leftPanel.setBackground(new java.awt.Color(255, 249, 236));
        leftPanel.setLayout(null);

        hospitalNameLabel.setBackground(new java.awt.Color(255, 255, 255));
        hospitalNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        hospitalNameLabel.setForeground(new java.awt.Color(153, 153, 255));
        hospitalNameLabel.setText("MediQueue");
        leftPanel.add(hospitalNameLabel);
        hospitalNameLabel.setBounds(90, 40, 225, 36);

        taglineLabel.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        taglineLabel.setForeground(new java.awt.Color(153, 153, 255));
        taglineLabel.setText("<html>Smart Hospital Queue Management</html>");
        leftPanel.add(taglineLabel);
        taglineLabel.setBounds(90, 70, 225, 45);

        imageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/it-consulting-doctors.png"))); // NOI18N
        leftPanel.add(imageLabel);
        imageLabel.setBounds(10, 110, 350, 340);

        bottomQuoteLabel.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        bottomQuoteLabel.setForeground(new java.awt.Color(153, 153, 255));
        bottomQuoteLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bottomQuoteLabel.setText("<html><center><i>Your health is our priority</i></center></html>");
        leftPanel.add(bottomQuoteLabel);
        bottomQuoteLabel.setBounds(70, 450, 245, 30);

        getContentPane().add(leftPanel);
        leftPanel.setBounds(545, 0, 370, 555);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // ── Button event stubs — logic handled by RegistrationController ─────
    private void signUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signUpButtonActionPerformed
        // Delegate to controller — all logic lives there (MVC rule)
        if (controller != null) {
            controller.handleRegister();
        }
    }//GEN-LAST:event_signUpButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // Delegate to controller — all logic lives there (MVC rule)
        if (controller != null) {
            controller.handleBack();
        }
    }//GEN-LAST:event_backButtonActionPerformed

    private void loginLinkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginLinkButtonActionPerformed
        // Delegate to controller — all logic lives there (MVC rule)
        if (controller != null) {
            controller.handleLoginLink();
        }
    }//GEN-LAST:event_loginLinkButtonActionPerformed

    private void confirmPasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmPasswordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_confirmPasswordFieldActionPerformed

    // ====================================================================
    //  GETTER METHODS — Used by RegistrationController to read field values
    //  Rule: NO logic here — only return the component reference.
    // ====================================================================

    /** @return Full Name text field */
    public JTextField getFullNameField()                             { return fullNameField; }

    /**
     * @return Date of Birth — JDateChooser component.
     * Call .getDate() on this to get the selected java.util.Date.
     * Returns null if the user has not selected a date yet.
     */
    public com.toedter.calendar.JDateChooser getDobField()           { return jDateChooser1; }

    /**
     * @return Gender JComboBox.
     * Options: "Select Gender" | "Male" | "Female" | "Others" | "Prefer not to say"
     */
    public javax.swing.JComboBox<String> getGenderComboBox()         { return genderComboBox; }

    /** @return Phone Number text field */
    public JTextField getPhoneField()                                { return phoneField; }

    /** @return Location / Address text field */
    public JTextField getLocationField()                             { return locationField; }

    /** @return Create Password text field */
    public javax.swing.JPasswordField getPasswordField()             { return passwordField; }

    /** @return Confirm Password text field */
    public javax.swing.JPasswordField getConfirmPasswordField()      { return confirmPasswordField; }

    /** @return Sign Up (Register) button */
    public javax.swing.JButton getSignUpButton()                     { return signUpButton; }

    /** @return Back button */
    public javax.swing.JButton getBackButton()                       { return backButton; }

    /** @return Login link button (bottom of form) */
    public javax.swing.JButton getLoginLinkButton()                  { return loginLinkButton; }

    /** @return Eye-open icon label for password field (controller attaches mouse listener) */
    public JLabel getShowPasswordLabel()                             { return show1; }

    /** @return Eye-closed icon label for password field */
    public JLabel getHidePasswordLabel()                             { return hide1; }

    /** @return Eye-open icon label for confirm-password field */
    public JLabel getShowConfirmPasswordLabel()                      { return show2; }

    /** @return Eye-closed icon label for confirm-password field */
    public JLabel getHideConfirmPasswordLabel()                      { return hide2; }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alreadyAccountLabel;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel bottomQuoteLabel;
    private javax.swing.JPasswordField confirmPasswordField;
    private javax.swing.JLabel confirmPasswordLabel;
    private javax.swing.JLabel dobLabel;
    private javax.swing.JTextField fullNameField;
    private javax.swing.JLabel fullNameLabel;
    private javax.swing.JComboBox genderComboBox;
    private javax.swing.JLabel genderLabel;
    private javax.swing.JLabel hide1;
    private javax.swing.JLabel hide2;
    private javax.swing.JLabel hospitalNameLabel;
    private javax.swing.JLabel imageLabel;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JTextField locationField;
    private javax.swing.JLabel locationLabel;
    private javax.swing.JButton loginLinkButton;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JTextField phoneField;
    private javax.swing.JLabel phoneLabel;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JSeparator separator;
    private javax.swing.JLabel show1;
    private javax.swing.JLabel show2;
    private javax.swing.JButton signUpButton;
    private javax.swing.JLabel subtitleLabel;
    private javax.swing.JLabel taglineLabel;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
