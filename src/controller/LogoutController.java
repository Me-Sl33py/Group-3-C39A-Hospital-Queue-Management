package controller;

import view.LogoutPanel;
import view.UserLogin;
import javax.swing.*;
import java.awt.*;

public class LogoutController {
    private LogoutPanel panel;
    private JFrame parentFrame;
    private CardLayout cardLayout;
    private JPanel contentPanel;

    public LogoutController(LogoutPanel panel, JFrame parentFrame,
                            CardLayout cardLayout, JPanel contentPanel) {
        this.panel        = panel;
        this.parentFrame  = parentFrame;
        this.cardLayout   = cardLayout;
        this.contentPanel = contentPanel;
        if (panel != null) initListeners();
    }

    public void handleLogout() {
        int confirm = JOptionPane.showConfirmDialog(parentFrame,
            "Are you sure you want to log out?",
            "Confirm Logout",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(parentFrame, "Logged out successfully!",
                "Logout", JOptionPane.INFORMATION_MESSAGE);
            navigateToLogin();
        }
    }

    private void initListeners() {
        panel.getLogoutButton().addActionListener(e -> handleLogout());
        panel.getCancelButton().addActionListener(e -> cardLayout.show(contentPanel, "home"));
    }

    private void navigateToLogin() {
        SwingUtilities.invokeLater(() -> {
            UserLogin loginFrame = new UserLogin();
            loginFrame.setLocationRelativeTo(null);
            loginFrame.setVisible(true);
            parentFrame.dispose();
        });
    }
}