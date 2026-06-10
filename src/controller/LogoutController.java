package controller;

import view.LogoutPanel;
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
        initListeners();
    }

    private void initListeners() {
        panel.getLogoutButton().addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(parentFrame, 
                "Are you sure you want to log out?", 
                "Confirm Logout", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE);
                
            if (confirm == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(parentFrame, "Logged out successfully!", "Logout", JOptionPane.INFORMATION_MESSAGE);
                parentFrame.dispose();
            }
        });
        panel.getCancelButton().addActionListener(e -> cardLayout.show(contentPanel, "home"));
    }
}