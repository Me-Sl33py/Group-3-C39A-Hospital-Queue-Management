package controller;

import dao.UserDAO;
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
 * Also handles password show/hide eye icon toggle.
 * Follows MVC clean architecture guidelines.
 */
public class SignUpController {

    // References to the View and DAO layers
    private SignUp view;
    private UserDAO userDAO;

    // Track visibility state of each password field (false = hidden/dots, true = visible)
    private boolean passwordVisible        = false;
    private boolean confirmPasswordVisible = false;

    /**
     * Constructor - registers action listeners and initializes DAO
     * @param view the SignUp view JFrame
     */
    public SignUpController(SignUp view) {
        this.view = view;
        this.userDAO = new UserDAO();

        // Register action listeners for the Sign Up and navigation buttons
        this.view.getSignUpButton().addActionListener(new SignUpButtonListener());
        this.view.getBackButton().addActionListener(new BackButtonListener());
        this.view.getLoginLinkButton().addActionListener(new BackButtonListener());

        // ---- Eye icon toggle: show1 and hide1 (for password field) ----
        // When the user clicks the eye icon, toggle the password field visibility
        this.view.getShowPasswordLabel().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                togglePasswordField(); // show or hide the password text
            }
        });
        this.view.getHidePasswordLabel().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                togglePasswordField(); // same toggle
            }
        });

        // ---- Eye icon toggle: show2 and hide2 (for confirm password field) ----
        this.view.getShowConfirmPasswordLabel().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                toggleConfirmPasswordField();
            }
        });
        this.view.getHideConfirmPasswordLabel().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                toggleConfirmPasswordField();
            }
        });
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
     * Helper method for password fields
     */
    private String getCleanInput(javax.swing.JPasswordField field, String placeholder) {
        String text = new String(field.getPassword()).trim();
        if (text.equalsIgnoreCase(placeholder)) {
            return "";
        }
        return text;
    }

    // ==================== Eye Icon Toggle Methods ====================

    /**
     * Toggles the password field between visible text and hidden (dots).
     * passwordVisible = false means field currently shows ●●● (hidden)
     * passwordVisible = true  means field currently shows real text (visible)
     */
    private void togglePasswordField() {
        javax.swing.JPasswordField passField = view.getPasswordField();
        String currentText = new String(passField.getPassword());
        if (currentText.equals("Create password")) return; // Don't toggle if placeholder

        if (!passwordVisible) {
            // Currently hidden — show real text
            passField.setEchoChar((char) 0);
            view.getShowPasswordLabel().setVisible(false);
            view.getHidePasswordLabel().setVisible(true);
            passwordVisible = true;
        } else {
            // Currently visible — hide with dots
            passField.setEchoChar('●');
            view.getShowPasswordLabel().setVisible(true);
            view.getHidePasswordLabel().setVisible(false);
            passwordVisible = false;
        }
    }

    /**
     * Toggles the confirm password field between visible text and hidden (dots).
     */
    private void toggleConfirmPasswordField() {
        javax.swing.JPasswordField confirmField = view.getConfirmPasswordField();
        String currentText = new String(confirmField.getPassword());
        if (currentText.equals("Confirm password")) return;

        if (!confirmPasswordVisible) {
            // Currently hidden — show real text
            confirmField.setEchoChar((char) 0);
            view.getShowConfirmPasswordLabel().setVisible(false);
            view.getHideConfirmPasswordLabel().setVisible(true);
            confirmPasswordVisible = true;
        } else {
            // Currently visible — hide with dots
            confirmField.setEchoChar('●');
            view.getShowConfirmPasswordLabel().setVisible(true);
            view.getHideConfirmPasswordLabel().setVisible(false);
            confirmPasswordVisible = false;
        }
    }

    /**
     * Inner class implementing ActionListener to handle the Sign Up button click
     */
    private class SignUpButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Retrieve all inputs from view
            String fullName       = getCleanInput(view.getFullNameField(),        "Enter full name");
            String dobStr         = getCleanInput(view.getDobField(),             "YYYY-MM-DD");
            String phone          = getCleanInput(view.getPhoneField(),           "Enter phone number");
            String location       = getCleanInput(view.getLocationField(),        "Enter location");
            String password       = getCleanInput(view.getPasswordField(),        "Create password");
            String confirmPassword = getCleanInput(view.getConfirmPasswordField(), "Confirm password");

            // Read gender from the JComboBox
            String selectedGender = view.getGenderComboBox().getSelectedItem().toString();

            // 1. Validation: check for empty fields
            if (fullName.isEmpty() || dobStr.isEmpty() || phone.isEmpty() ||
                location.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(view,
                        "All fields are required. Please fill in every field.",
                        "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 2. Validation: gender must be selected (not left on "Select Gender")
            if (selectedGender.equals("Select Gender")) {
                JOptionPane.showMessageDialog(view,
                        "Please select your gender from the dropdown.",
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

                // Third: Map combo box gender value → database ENUM value
                // DB ENUM only accepts: 'male', 'female', 'other'
                // 'Others' and 'Prefer not to say' both map to 'other'
                String genderForDb;
                switch (selectedGender.toLowerCase()) {
                    case "male":
                        genderForDb = "male";
                        break;
                    case "female":
                        genderForDb = "female";
                        break;
                    default:
                        // 'Others' and 'Prefer not to say' both become 'other'
                        genderForDb = "other";
                        break;
                }

                // Register in patients table using retrieved userId
                boolean patientSuccess = userDAO.registerPatient(
                        patientId, userId, fullName, age, genderForDb, phone, location
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
