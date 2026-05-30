package controller;

import dao.SecurityQuestionsDao;
import view.SecurityQuestions;
import view.UserLogin;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SecurityQuestionsController {

    private SecurityQuestions view;
    private SecurityQuestionsDao dao;
    private int userId;
    private String username;

    public SecurityQuestionsController(SecurityQuestions view, int userId) {
        this.view = view;
        this.userId = userId;
        this.dao = new SecurityQuestionsDao();

        this.view.getSaveAnswersButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void handleSubmit() {
        String a1 = view.getQ1AnswerField().getText().trim();
        String a2 = view.getQ2AnswerField().getText().trim();
        String a3 = view.getQ3AnswerField().getText().trim();
        String a4 = view.getQ4AnswerField().getText().trim();
        String a5 = view.getQ5AnswerField().getText().trim();

        if (a1.isEmpty() || a2.isEmpty() || a3.isEmpty() || a4.isEmpty() || a5.isEmpty()) {
            JOptionPane.showMessageDialog(view,
                "please answer all 5 security questions",
                "missing field", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Hardcoded questions matching the labels in the view
        String q1 = "Your Favourite food?";
        String q2 = "Your First pet's name?";
        String q3 = "Your Favourite game?";
        String q4 = "Your Best Friend's name?";
        String q5 = "Your Favourite Place to visit?";

        boolean success = dao.insertSecurityQuestions(userId, q1, a1, q2, a2, q3, a3, q4, a4, q5, a5);

        if (success) {
            JOptionPane.showMessageDialog(view,
                "account setup complete! your username is " + username + " please login",
                "Setup Complete", JOptionPane.INFORMATION_MESSAGE);
            view.dispose();
            UserLogin loginFrame = new UserLogin();
            loginFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(view,
                "Failed to save security questions.",
                "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
