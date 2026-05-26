package controller;

import dao.SecurityQuestionDAO;
import view.SecurityQuestions;
import view.UserLogin;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller class for the SecurityQuestions view.
 * Handles saving security answers linked to a user's account for password recovery.
 * Follows MVC clean architecture guidelines.
 */
public class SecurityQuestionsController {

    // References to the View, DAO, and state variables
    private SecurityQuestions view;
    private SecurityQuestionDAO securityDAO;
    private int userId; // Target user_id these questions belong to

    /**
     * Constructor - links the controller to the view and registers action listeners
     * @param view the SecurityQuestions view JFrame
     * @param userId the user_id that owns these answers
     */
    public SecurityQuestionsController(SecurityQuestions view, int userId) {
        this.view = view;
        this.userId = userId;
        this.securityDAO = new SecurityQuestionDAO();

        // Register action listeners for buttons on the view
        this.view.getSaveAnswersButton().addActionListener(new SaveAnswersButtonListener());
        this.view.getCancelAnswersButton().addActionListener(new CancelButtonListener());
    }

    /**
     * Inner class implementing ActionListener to handle the Save Answers button click
     */
    private class SaveAnswersButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Retrieve all 5 answers from the view fields
            String a1 = view.getQ1AnswerField().getText().trim();
            String a2 = view.getQ2AnswerField().getText().trim();
            String a3 = view.getQ3AnswerField().getText().trim();
            String a4 = view.getQ4AnswerField().getText().trim();
            String a5 = view.getQ5AnswerField().getText().trim();

            // Store answers in an array
            String[] answers = { a1, a2, a3, a4, a5 };

            // Count how many answers are filled (not empty)
            int filledCount = 0;
            for (String ans : answers) {
                if (!ans.isEmpty()) {
                    filledCount++;
                }
            }

            // Validation: at least 3 security answers are mandatory
            if (filledCount < 3) {
                JOptionPane.showMessageDialog(view, 
                        "Security policy requires answering at least 3 questions.", 
                        "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Call DAO to save the answers to the security_questions table
            boolean success = securityDAO.saveSecurityQuestions(userId, answers);

            if (success) {
                JOptionPane.showMessageDialog(view, 
                        "Security Questions saved successfully!\n" +
                        "You can now log in using your account credentials.", 
                        "Success", JOptionPane.INFORMATION_MESSAGE);

                // Transition back to the login screen
                view.dispose();
                UserLogin loginFrame = new UserLogin();
                loginFrame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(view, 
                        "Failed to save security questions. Please try again.", 
                        "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Inner class implementing ActionListener to handle the Cancel button click
     */
    private class CancelButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int option = JOptionPane.showConfirmDialog(view, 
                    "Skipping security questions is not recommended.\n" +
                    "Without them, you will not be able to recover your password online.\n" +
                    "Are you sure you want to cancel?", 
                    "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (option == JOptionPane.YES_OPTION) {
                // Return to login screen without saving
                view.dispose();
                UserLogin loginFrame = new UserLogin();
                loginFrame.setVisible(true);
            }
        }
    }
}
