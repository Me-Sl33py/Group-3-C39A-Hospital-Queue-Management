package controller;

import dao.UserDAO;
import dao.SecurityQuestionDAO;
import view.ForgotPassword;
import view.UserLogin;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller class for the ForgotPassword view.
 * Handles checking user existence based on name, phone, DOB, location,
 * verifying security answers, and resetting their password.
 * Follows MVC clean architecture guidelines.
 */
public class ForgotPasswordController {

    // References to the View, DAO layers, and state variables
    private ForgotPassword view;
    private UserDAO userDAO;
    private SecurityQuestionDAO securityDAO;
    private int verifiedUserId = -1; // user_id of the verified patient (-1 if not yet verified)

    /**
     * Constructor - links the controller to the view, initializes DAOs, and registers listeners
     * @param view the ForgotPassword view JFrame
     */
    public ForgotPasswordController(ForgotPassword view) {
        this.view = view;
        this.userDAO = new UserDAO();
        this.securityDAO = new SecurityQuestionDAO();

        // Register action listeners for buttons on the view
        this.view.getSearchUserButton().addActionListener(new SearchUserButtonListener());
        this.view.getSaveAnswersButton().addActionListener(new VerifyAndResetButtonListener());
        this.view.getCancelAnswersButton().addActionListener(new CancelButtonListener());
    }

    // ==================== Public Handler Methods ====================
    // These are called by ForgotPassword view's action performed stubs.
    // All logic stays in this controller, view just delegates.

    /**
     * Called when the Search button is clicked in the view.
     * Verifies the patient's identity using their name, phone, DOB, and location.
     */
    public void handleSearchUser() {
        // Retrieve verification inputs from view
        String fullName = view.getFullNameField().getText().trim();
        String phone    = view.getPhoneField().getText().trim();
        String location = view.getLocationField().getText().trim();

        // Retrieve date from JDateChooser
        java.util.Date selectedDate = view.getDobField().getDate();

        // Simple validation: check required fields
        if (fullName.isEmpty() || phone.isEmpty() || selectedDate == null) {
            javax.swing.JOptionPane.showMessageDialog(view,
                    "Full Name, Phone Number, and DOB (Date of Birth) are required for identity verification.",
                    "Validation Error", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Convert java.util.Date to java.sql.Date
        java.sql.Date dob = new java.sql.Date(selectedDate.getTime());

        // Call DAO to search if user exists with matching patient details
        int userId = userDAO.searchUserForReset(fullName, phone, dob, location);

        if (userId != -1) {
            // If verified, save user_id in the instance variable
            verifiedUserId = userId;
            javax.swing.JOptionPane.showMessageDialog(view,
                    "Clinical Identity Verified successfully!\n" +
                    "Please answer the security questions below to proceed with resetting your password.",
                    "Identity Verified", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        } else {
            // If verification failed, reset verifiedUserId to -1
            verifiedUserId = -1;
            javax.swing.JOptionPane.showMessageDialog(view,
                    "Identity verification failed. No patient record matches the provided details.",
                    "Verification Failed", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Called when the Verify and Reset button is clicked in the view.
     * Checks security answers and resets the password if they pass.
     */
    public void handleVerifyAndReset() {
        // Check if identity has been verified first
        if (verifiedUserId == -1) {
            javax.swing.JOptionPane.showMessageDialog(view,
                    "Please verify your identity by searching your patient details first.",
                    "Verification Required", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Retrieve the answers entered by the user
        String a1 = view.getQ1AnswerField().getText().trim();
        String a2 = view.getQ2AnswerField().getText().trim();
        String a3 = view.getQ3AnswerField().getText().trim();
        String a4 = view.getQ4AnswerField().getText().trim();
        String a5 = view.getQ5AnswerField().getText().trim();

        String[] enteredAnswers = { a1, a2, a3, a4, a5 };

        // Call SecurityQuestionDAO to verify at least 3 answers match
        boolean answersPassed = securityDAO.verifySecurityAnswers(verifiedUserId, enteredAnswers);

        if (answersPassed) {
            // Prompt user to enter a new password
            String newPassword = javax.swing.JOptionPane.showInputDialog(view,
                    "Security verification passed!\nEnter your new password:",
                    "Reset Password", javax.swing.JOptionPane.PLAIN_MESSAGE);

            if (newPassword == null) {
                return; // User canceled the input prompt
            }
            newPassword = newPassword.trim();
            if (newPassword.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(view,
                        "Password cannot be empty.",
                        "Validation Error", javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Update the user's password in the users table via UserDAO
            boolean updateSuccess = userDAO.updatePassword(verifiedUserId, newPassword);

            if (updateSuccess) {
                javax.swing.JOptionPane.showMessageDialog(view,
                        "Your password has been reset successfully!",
                        "Success", javax.swing.JOptionPane.INFORMATION_MESSAGE);

                // Redirect back to login screen
                view.dispose();
                new view.UserLogin().setVisible(true);
            } else {
                javax.swing.JOptionPane.showMessageDialog(view,
                        "Failed to update password in database. Please try again.",
                        "Database Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(view,
                    "Security answers incorrect. Please check your answers and try again.",
                    "Verification Failed", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Called when the Cancel button is clicked in the view.
     * Closes the ForgotPassword window and returns to the login screen.
     */
    public void handleCancel() {
        view.dispose(); // Close current Forgot Password window
        new view.UserLogin().setVisible(true); // Return to login screen
    }

    /**
     * Inner class implementing ActionListener to handle identity verification search
     */
    private class SearchUserButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Retrieve verification inputs from view
            String fullName = view.getFullNameField().getText().trim();
            String phone = view.getPhoneField().getText().trim();
            String location = view.getLocationField().getText().trim();

            // Retrieve date from JDateChooser
            java.util.Date selectedDate = view.getDobField().getDate();

            // Simple validation: check required fields
            if (fullName.isEmpty() || phone.isEmpty() || selectedDate == null) {
                JOptionPane.showMessageDialog(view, 
                        "Full Name, Phone Number, and DOB (Date of Birth) are required for identity verification.", 
                        "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Convert java.util.Date to java.sql.Date
            java.sql.Date dob = new java.sql.Date(selectedDate.getTime());

            // Call DAO to search if user exists with matching patient details
            int userId = userDAO.searchUserForReset(fullName, phone, dob, location);

            if (userId != -1) {
                // If verified, save user_id in the instance variable
                verifiedUserId = userId;
                JOptionPane.showMessageDialog(view, 
                        "Clinical Identity Verified successfully!\n" +
                        "Please answer the security questions below to proceed with resetting your password.", 
                        "Identity Verified", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // If verification failed, reset verifiedUserId to -1
                verifiedUserId = -1;
                JOptionPane.showMessageDialog(view, 
                        "Identity verification failed. No patient record matches the provided details.", 
                        "Verification Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Inner class implementing ActionListener to verify security questions and prompt for password reset
     */
    private class VerifyAndResetButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Check if identity has been verified first
            if (verifiedUserId == -1) {
                JOptionPane.showMessageDialog(view, 
                        "Please verify your identity by searching your patient details first.", 
                        "Verification Required", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Retrieve the answers entered by the user
            String a1 = view.getQ1AnswerField().getText().trim();
            String a2 = view.getQ2AnswerField().getText().trim();
            String a3 = view.getQ3AnswerField().getText().trim();
            String a4 = view.getQ4AnswerField().getText().trim();
            String a5 = view.getQ5AnswerField().getText().trim();

            String[] enteredAnswers = { a1, a2, a3, a4, a5 };

            // Call SecurityQuestionDAO to verify at least 3 answers match
            boolean answersPassed = securityDAO.verifySecurityAnswers(verifiedUserId, enteredAnswers);

            if (answersPassed) {
                // Prompt user to enter a new password
                String newPassword = JOptionPane.showInputDialog(view, 
                        "Security verification passed!\nEnter your new password:", 
                        "Reset Password", JOptionPane.PLAIN_MESSAGE);

                // Simple validation on the new password
                if (newPassword == null) {
                    return; // User canceled the input prompt
                }
                newPassword = newPassword.trim();
                if (newPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(view, 
                            "Password cannot be empty.", 
                            "Validation Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Update the user's password in the users table via UserDAO
                boolean updateSuccess = userDAO.updatePassword(verifiedUserId, newPassword);

                if (updateSuccess) {
                    JOptionPane.showMessageDialog(view, 
                            "Your password has been reset successfully!", 
                            "Success", JOptionPane.INFORMATION_MESSAGE);

                    // Redirect back to login screen
                    view.dispose();
                    UserLogin loginFrame = new UserLogin();
                    loginFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(view, 
                            "Failed to update password in database. Please try again.", 
                            "Database Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(view, 
                        "Security answers incorrect. Please check your answers and try again.", 
                        "Verification Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Inner class implementing ActionListener to handle cancel/return navigation
     */
    private class CancelButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.dispose(); // Close current Forgot Password window
            UserLogin loginFrame = new UserLogin();
            loginFrame.setVisible(true); // Return to login screen
        }
    }
}
