package controller;

import dao.SecurityQuestionsDao;
import view.SecurityQuestions;
import view.UserLogin;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.Component;
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
            showSetupCompletePopup(username);
        } else {
            JOptionPane.showMessageDialog(view,
                "Failed to save security questions.",
                "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showSetupCompletePopup(String username) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Setup Complete");
        dialog.setPreferredSize(new Dimension(420, 320));
        dialog.setModal(true);
        dialog.setLayout(new BorderLayout());
        dialog.setResizable(false);
        dialog.getContentPane().setBackground(Color.WHITE);

        // top panel with lock icon
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 5, 0));
        JLabel iconLabel = new JLabel("🔒");
        iconLabel.setFont(new Font("Segoe UI", Font.PLAIN, 36));
        iconLabel.setForeground(new Color(33, 97, 172));
        topPanel.add(iconLabel);

        // center panel with message
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        JLabel titleLabel = new JLabel("Account Setup Complete");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel msgLabel = new JLabel("Your security questions have been saved successfully.");
        msgLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        msgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel msgLabel2 = new JLabel("You can now login with your username.");
        msgLabel2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        msgLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);

        // username in highlighted box
        JPanel userBox = new JPanel();
        userBox.setBackground(new Color(230, 242, 255)); // light blue background
        userBox.setBorder(BorderFactory.createLineBorder(new Color(180, 215, 255), 1));
        userBox.setMaximumSize(new Dimension(320, 40));
        JLabel userLabel = new JLabel("Username: " + username);
        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        userLabel.setForeground(new Color(33, 97, 172));
        userBox.add(javax.swing.Box.createVerticalStrut(5)); // small internal padding
        userBox.add(userLabel);
        userBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(titleLabel);
        centerPanel.add(javax.swing.Box.createVerticalStrut(15));
        centerPanel.add(msgLabel);
        centerPanel.add(javax.swing.Box.createVerticalStrut(5));
        centerPanel.add(msgLabel2);
        centerPanel.add(javax.swing.Box.createVerticalStrut(15));
        centerPanel.add(userBox);

        // bottom panel with login now button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        JButton loginButton = new JButton("Login Now");
        loginButton.setBackground(new Color(33, 97, 172));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setPreferredSize(new Dimension(140, 35));
        loginButton.addActionListener(e -> {
            dialog.dispose();
            view.dispose();
            new UserLogin().setVisible(true);
        });
        bottomPanel.add(loginButton);

        dialog.add(topPanel, BorderLayout.NORTH);
        dialog.add(centerPanel, BorderLayout.CENTER);
        dialog.add(bottomPanel, BorderLayout.SOUTH);

        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
