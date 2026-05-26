package controller;

import dao.UserDAO;
import model.User;
import view.UserLogin;
import view.SignUp;
import view.ForgotPassword;
import javax.swing.JOptionPane;
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

    /**
     * Constructor - initializes view and DAO, and registers action listeners
     * @param view the Login view JFrame
     */
    public UserLoginController(UserLogin view) {
        this.view = view;
        this.userDAO = new UserDAO();

        // Register action listeners for buttons on the view
        this.view.getLoginButton().addActionListener(new LoginButtonListener());
        this.view.getForgotPasswordButton().addActionListener(new ForgotPasswordLinkListener());
        this.view.getSignUpButton().addActionListener(new SignUpLinkListener());
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
     * Inner class implementing ActionListener to handle the Login button click
     */
    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Retrieve values from the view, clean up any placeholder text
            String id = getCleanInput(view.getIdField(), "Enter your ID");
            String phone = getCleanInput(view.getPhoneField(), "Enter phone number");
            String password = getCleanInput(view.getPasswordField(), "Enter password");

            // Simple validation: check if ID/Phone and Password are empty
            if (id.isEmpty() && phone.isEmpty()) {
                JOptionPane.showMessageDialog(view, 
                        "Please enter either your ID (Username) or Phone Number.", 
                        "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (password.isEmpty()) {
                JOptionPane.showMessageDialog(view, 
                        "Please enter your password.", 
                        "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            User loggedInUser = null;

            // Attempt login: if ID is entered, try to login by ID first
            if (!id.isEmpty()) {
                loggedInUser = userDAO.loginById(id, password);
            } 
            // If ID login failed or wasn't provided, and Phone number is provided, try that
            if (loggedInUser == null && !phone.isEmpty()) {
                loggedInUser = userDAO.loginByPhone(phone, password);
            }

            // Verify if a user was successfully found matching credentials
            if (loggedInUser != null) {
                // Check if Remember Me is checked, and save/clear credentials
                handleRememberMe(id, phone, password);

                // Show success popup with role detail
                JOptionPane.showMessageDialog(view, 
                        "Login successful!\nWelcome back, " + loggedInUser.getUsername() + 
                        " (" + loggedInUser.getRole().toUpperCase() + ")", 
                        "Success", JOptionPane.INFORMATION_MESSAGE);

                // Open the corresponding dashboard depending on the user's role
                openDashboard(loggedInUser);
            } else {
                // If credentials did not match, show error message
                JOptionPane.showMessageDialog(view, 
                        "Invalid Username/Phone or Password. Please try again.", 
                        "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
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
