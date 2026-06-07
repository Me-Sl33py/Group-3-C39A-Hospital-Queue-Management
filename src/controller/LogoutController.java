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
        panel.getLogoutButton().addActionListener(e -> parentFrame.dispose());
        panel.getCancelButton().addActionListener(e -> cardLayout.show(contentPanel, "home"));
    }
}