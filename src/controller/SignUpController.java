package controller;

import dao.UserDAO;
import model.User;
import model.Patient;
import view.SignUp;
import view.UserLogin;
import view.SecurityQuestions;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Period;

/**
 * Controller class for the SignUp view.
 * Handles patient registration, age calculation, patient ID auto-generation,
 * and handles DB insertion into users first, then patients table.
 * Follows MVC clean architecture guidelines.
 */
public class SignUpController {

    // References to the View and DAO layers
    private SignUp view;
    private UserDAO userDAO;

    /**
     * Constructor - registers action listeners and initializes DAO
     * @param view the SignUp view JFrame
     */
    public SignUpController(SignUp view) {
        this.view = view;
        this.userDAO = new UserDAO();

        // Register action listeners for buttons on the view
        this.view.getSignUpButton().addActionListener(new SignUpButtonListener());
        this.view.getBackButton().addActionListener(new BackButtonListener());
        this.view.getLoginLinkButton().addActionListener(new BackButtonListener());
    }

    /**
     * Helper method to filter out default placeholder text
     */
    private String getCleanInput(javax.swing.JTextField field, String placeholder) {
        String text = field.getText().trim();
        if (text.equalsIgnoreCase(placeholder)) {
            return "";
        }
        return text;
    }

    /**
     * Inner class implementing ActionListener to handle the Sign Up button click
     */
    private class SignUpButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Retrieve all inputs from view
            String fullName = getCleanInput(view.getFullNameField(), "Enter full name");
            String dobStr = getCleanInput(view.getDobField(), "YYYY-MM-DD");
            String phone = getCleanInput(view.getPhoneField(), "Enter phone number");
            String location = getCleanInput(view.getLocationField(), "Enter location");
            String password = getCleanInput(view.getPasswordField(), "Create password");
            String confirmPassword = getCleanInput(view.getConfirmPasswordField(), "Confirm password");

            // 1. Validation: check for empty fields
            if (fullName.isEmpty() || dobStr.isEmpty() || phone.isEmpty() || 
                location.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(view, 
                        "All fields marked are required. Please fill them up.", 
                        "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 2. Validation: match passwords
            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(view, 
                        "Passwords do not match. Please try again.", 
                        "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 3. Validation & Parsing: Date of Birth and Age Calculation
            int age = 0;
            try {
                LocalDate dob = LocalDate.parse(dobStr);
                LocalDate today = LocalDate.now();
                if (dob.isAfter(today)) {
                    JOptionPane.showMessageDialog(view, 
                            "Date of birth cannot be in the future.", 
                            "Validation Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                age = Period.between(dob, today).getYears();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, 
                        "Please enter Date of Birth in YYYY-MM-DD format.", 
                        "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 4. Validation: Check if the phone number (username) already exists in users
            if (userDAO.isUsernameExists(phone)) {
                JOptionPane.showMessageDialog(view, 
                        "An account with this phone number is already registered.", 
                        "Duplicate Account", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 5. Create Model Objects & Database insertion order
            
            // First: Register in users table and get auto-generated userId
            // We use the phone number as their login username
            int userId = userDAO.registerUser(phone, password, "patient");

            if (userId != -1) {
                // Second: Generate sequential Patient ID (e.g. P-001, P-002)
                String patientId = userDAO.generatePatientId();

                // Third: Default gender to "other" since it's not present in the signup form
                String gender = "other";

                // Register in patients table using retrieved userId
                boolean patientSuccess = userDAO.registerPatient(
                        patientId, userId, fullName, age, gender, phone, location
                );

                if (patientSuccess) {
                    // Show success message as required
                    JOptionPane.showMessageDialog(view, 
                            "Registration Successful!\n" +
                            "Patient Name: " + fullName + "\n" +
                            "Assigned Patient ID: " + patientId + "\n" +
                            "Username: " + phone + "\n\n" +
                            "Please set up your security questions to secure your account.", 
                            "Success", JOptionPane.INFORMATION_MESSAGE);

                    // Close SignUp view and route to Security Questions view with generated userId
                    view.dispose();
                    SecurityQuestions securityQuestionsFrame = new SecurityQuestions(userId);
                    securityQuestionsFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(view, 
                            "Failed to register patient details. Please contact support.", 
                            "Database Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(view, 
                        "Failed to create user account. Please try again.", 
                        "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Inner class implementing ActionListener to handle the Back/Login navigation
     */
    private class BackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.dispose(); // Close current Sign Up window
            UserLogin loginFrame = new UserLogin();
            loginFrame.setVisible(true); // Return to Login window
        }
    }
}
