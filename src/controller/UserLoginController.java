package controller;

import dao.UserDAO;
import model.User;
import view.UserLogin;
import view.SignUp;
import view.ForgotPassword;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Controller class for the UserLogin view.
 * Handles action listeners for login, remember me, forgot password, and signup transitions.
 * Follows MVC clean architecture guidelines.
 */
public class UserLoginController {

    // References to the View and DAO layers
    private UserLogin view;
    private UserDAO userDAO;
    
    // Track visibility state of password field (false = hidden/dots, true = visible)
    private boolean passwordVisible = false;

    /**
     * Constructor - initializes view and DAO, and registers action listeners
     * @param view the Login view JFrame
     */
    public UserLoginController(UserLogin view) {
        this.view = view;
        this.userDAO = new UserDAO();

        // Set up placeholder text in the input fields (moved from view)
        setupPlaceholders();

        // Load saved credentials from file if Remember Me was used (moved from view)
        loadRememberedCredentials();

        // Register action listeners for buttons on the view
        this.view.getLoginButton().addActionListener(new LoginButtonListener());
        this.view.getForgotPasswordButton().addActionListener(new ForgotPasswordLinkListener());
        this.view.getSignUpButton().addActionListener(new SignUpLinkListener());

        // Setup eye icon toggle: Show_password
        this.view.getShowPasswordLabel().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                togglePasswordField();
            }
        });
        // Set the cursor to hand for the eye icon
        this.view.getShowPasswordLabel().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    // ==================== Placeholder & Credential Loading Methods ====================
    // (These were moved here from UserLogin view to keep all logic in the controller)

    /**
     * Sets up grey placeholder text for all login text fields.
     * When user clicks a field, the placeholder disappears.
     * When user leaves the field empty, placeholder reappears.
     */
    private void setupPlaceholders() {
        addPlaceholder(view.getIdField(),       "Enter username");
        addPlaceholder(view.getPasswordField(), "Enter password");
        addPlaceholder(view.getPhoneField(),    "Enter phone number");
    }

    /**
     * Helper: attaches focus-driven placeholder text to one JTextField.
     * Works for both plain JTextField and JPasswordField.
     *
     * @param field       the input field
     * @param placeholder the hint text to show when field is empty
     */
    private void addPlaceholder(javax.swing.JTextField field, String placeholder) {
        // Set initial placeholder text in grey
        field.setText(placeholder);
        field.setForeground(java.awt.Color.GRAY);
        if (field instanceof javax.swing.JPasswordField) {
            // Show placeholder as plain text (no masking)
            ((javax.swing.JPasswordField) field).setEchoChar((char) 0);
        }

        field.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                // When user clicks the field, clear the placeholder text
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(java.awt.Color.BLACK);
                    if (field instanceof javax.swing.JPasswordField) {
                        // Restore password masking
                        ((javax.swing.JPasswordField) field).setEchoChar('●');
                    }
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                // When user clicks away and field is empty, restore placeholder
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(java.awt.Color.GRAY);
                    if (field instanceof javax.swing.JPasswordField) {
                        // Show placeholder without masking
                        ((javax.swing.JPasswordField) field).setEchoChar((char) 0);
                    }
                }
            }
        });
    }

    /**
     * Reads saved credentials from remember_me.dat and pre-fills the login fields.
     * If the file exists, it means the user checked "Remember Me" during their last login.
     */
    private void loadRememberedCredentials() {
        java.io.File file = new java.io.File("remember_me.dat");
        if (file.exists()) {
            try (java.io.BufferedReader reader = new java.io.BufferedReader(
                    new java.io.FileReader(file))) {
                String savedId       = reader.readLine(); // saved username or phone
                String savedPassword = reader.readLine(); // saved password

                // Fill username field if data is present
                if (savedId != null && !savedId.isEmpty()) {
                    view.getIdField().setText(savedId);
                    view.getIdField().setForeground(java.awt.Color.BLACK);
                }
                // Fill password field if data is present
                if (savedPassword != null && !savedPassword.isEmpty()) {
                    view.getPasswordField().setText(savedPassword);
                    view.getPasswordField().setForeground(java.awt.Color.BLACK);
                    view.getPasswordField().setEchoChar('●'); // show dots for security
                }
                // Check the Remember Me checkbox
                view.getRememberMeCheckBox().setSelected(true);
            } catch (Exception e) {
                System.out.println("[UserLoginController] Error loading remembered credentials: " + e);
            }
        }
    }

    /**
     * Called directly from UserLogin.jButton4ActionPerformed (Login button).
     * Delegates to the same logic as LoginButtonListener.
     */
    public void handleLogin() {
        // Retrieve values from the view
        String username = view.getIdField().getText().trim();
        String phone = view.getPhoneField().getText().trim();
        String password = new String(view.getPasswordField().getPassword());

        // Check if the fields contain the placeholder text, if so, treat them as empty
        if (username.equals("Enter username")) {
            username = "";
        }
        if (phone.equals("Enter phone number")) {
            phone = "";
        }

        // If BOTH username and phone are empty, show a popup and stop
        if (username.isEmpty() && phone.isEmpty()) {
            JOptionPane.showMessageDialog(view, "please enter your username or phone number");
            return;
        }

        // If password is empty (or is the placeholder), show a popup and stop
        if (password.isEmpty() || password.equals("Enter password")) {
            JOptionPane.showMessageDialog(view, "please enter your password");
            return;
        }

        // Decide which identifier to use (if username is not empty, use it, else use phone)
        String identifier = !username.isEmpty() ? username : phone;

        // Call the DAO checkLogin method using the identifier and password
        String role = userDAO.checkLogin(identifier, password);

        // If login is successful (role is found), show welcome popup. Else show error.
        if (role != null) {
            handleRememberMe(username, phone, password);
            showWelcomePopup(role);
        } else {
            JOptionPane.showMessageDialog(view, "invalid credentials please try again");
        }
    }

    private void showWelcomePopup(String role) {
        JDialog dialog = new JDialog();
        dialog.setUndecorated(true);
        dialog.setSize(350, 200);
        dialog.setLocationRelativeTo(view);
        dialog.getContentPane().setBackground(Color.WHITE);
        dialog.setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel checkmark = new JLabel("✓");
        checkmark.setForeground(Color.GREEN);
        checkmark.setFont(new Font("Arial", Font.BOLD, 50));
        checkmark.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel("Welcome Back!");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel text = new JLabel("You are logged in as " + role);
        text.setFont(new Font("Arial", Font.PLAIN, 14));
        text.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(checkmark);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(title);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(text);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);

        JButton continueButton = new JButton("Continue");
        continueButton.setBackground(new Color(33, 97, 172));
        continueButton.setForeground(Color.WHITE);
        continueButton.setFocusPainted(false);
        
        continueButton.addActionListener(e -> {
            dialog.dispose();
            view.dispose(); // close Login window
            switch (role.toLowerCase()) {
                case "patient":
                    // new PatientDashboard().setVisible(true);
                    break;
                case "doctor":
                    // new DoctorDashboard().setVisible(true);
                    break;
                case "receptionist":
                    // new ReceptionistDashboard().setVisible(true);
                    break;
                case "admin":
                    // new AdminDashboard().setVisible(true);
                    break;
            }
        });

        bottomPanel.add(continueButton);

        dialog.add(centerPanel, BorderLayout.CENTER);
        dialog.add(bottomPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    /**
     * Called directly from UserLogin.jButton2ActionPerformed (Forgot Password button).
     * Opens the ForgotPassword screen.
     */
    public void handleForgotPassword() {
        view.dispose();
        ForgotPassword forgotPassFrame = new ForgotPassword();
        forgotPassFrame.setVisible(true);
    }

    /**
     * Called directly from UserLogin.jButton1ActionPerformed (Sign Up link button).
     * Opens the SignUp screen.
     */
    public void handleSignUp() {
        view.dispose();
        SignUp signUpFrame = new SignUp();
        signUpFrame.setVisible(true);
    }

    /**
     * Helper method to get the value of a text field, filtering out the placeholder text
     * @param field the text field to read
     * @param placeholder the placeholder string to ignore
     * @return the entered text or empty string if placeholder
     */
    private String getCleanInput(javax.swing.JTextField field, String placeholder) {
        String text = field.getText().trim();
        if (text.equalsIgnoreCase(placeholder)) {
            return "";
        }
        return text;
    }

    /**
     * Helper method for password fields
     */
    private String getCleanInput(javax.swing.JPasswordField field, String placeholder) {
        String text = new String(field.getPassword()).trim();
        if (text.equalsIgnoreCase(placeholder)) {
            return "";
        }
        return text;
    }

    /**
     * Toggles the password field between visible text and hidden (dots).
     */
    private void togglePasswordField() {
        javax.swing.JPasswordField passField = view.getPasswordField();
        String currentText = new String(passField.getPassword());
        if (currentText.equals("Enter password")) return; // Don't toggle if placeholder

        if (!passwordVisible) {
            // Currently hidden — show real text
            passField.setEchoChar((char) 0);
            view.getShowPasswordLabel().setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hide password.png")));
            passwordVisible = true;
        } else {
            // Currently visible — hide with dots
            passField.setEchoChar('●');
            view.getShowPasswordLabel().setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/show password.png")));
            passwordVisible = false;
        }
    }

    /**
     * Inner class implementing ActionListener to handle the Login button click
     */
    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            handleLogin();
        }
    }

    /**
     * Helper to handle Remember Me credentials storage
     */
    private void handleRememberMe(String id, String phone, String password) {
        File file = new File("remember_me.dat");
        if (view.getRememberMeCheckBox().isSelected()) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                // Save the identifier (prefer ID, then phone) and password
                String identifier = id.isEmpty() ? phone : id;
                writer.println(identifier);
                writer.println(password);
            } catch (Exception ex) {
                System.out.println("Error saving credentials: " + ex);
            }
        } else {
            // If Remember Me is unchecked, delete the file to clear saved credentials
            if (file.exists()) {
                file.delete();
            }
        }
    }

    /**
     * Mock dashboard router which routes users based on their role
     * @param user the logged in User object
     */
    private void openDashboard(User user) {
        view.dispose(); // Close current Login window

        String role = user.getRole().toLowerCase();
        switch (role) {
            case "admin":
                JOptionPane.showMessageDialog(null, 
                        "Launching Admin Dashboard...\n(Note: Dashboard frame files will be integrated as you progress)", 
                        "Admin Dashboard", JOptionPane.INFORMATION_MESSAGE);
                break;
            case "doctor":
                JOptionPane.showMessageDialog(null, 
                        "Launching Doctor Dashboard...\n(Note: Dashboard frame files will be integrated as you progress)", 
                        "Doctor Dashboard", JOptionPane.INFORMATION_MESSAGE);
                break;
            case "receptionist":
                JOptionPane.showMessageDialog(null, 
                        "Launching Receptionist Dashboard...\n(Note: Dashboard frame files will be integrated as you progress)", 
                        "Receptionist Dashboard", JOptionPane.INFORMATION_MESSAGE);
                break;
            case "patient":
                JOptionPane.showMessageDialog(null, 
                        "Launching Patient Dashboard...\n(Note: Dashboard frame files will be integrated as you progress)", 
                        "Patient Dashboard", JOptionPane.INFORMATION_MESSAGE);
                break;
            default:
                JOptionPane.showMessageDialog(null, 
                        "Error: Unknown user role '" + role + "'.", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

    /**
     * Inner class implementing ActionListener to handle transitions to Forgot Password screen
     */
    private class ForgotPasswordLinkListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.dispose(); // Close the login view
            ForgotPassword forgotPassFrame = new ForgotPassword();
            forgotPassFrame.setVisible(true); // Open forgot password view
        }
    }

    /**
     * Inner class implementing ActionListener to handle transitions to Sign Up screen
     */
    private class SignUpLinkListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.dispose(); // Close the login view
            SignUp signUpFrame = new SignUp();
            signUpFrame.setVisible(true); // Open sign up view
        }
    }
}
