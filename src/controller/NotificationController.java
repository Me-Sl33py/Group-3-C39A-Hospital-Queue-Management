package controller;

import dao.NotificationDAO;
import view.NotificationPanel;
import javax.swing.JLabel;
import java.util.List;

public class NotificationController {

    private NotificationPanel panel;
    private NotificationDAO dao;

    public NotificationController(NotificationPanel panel) {
        this.panel = panel;
        this.dao   = new NotificationDAO();
    }

    public void loadAll() {
        JLabel[] msgLabels = {
            panel.getJLabel2(), panel.getJLabel5(), panel.getJLabel8()
        };
        JLabel[] timeLabels = {
            panel.getJLabel3(), panel.getJLabel6(), panel.getJLabel9()
        };

        List<String[]> rows = dao.getRecentNotifications();
        int i = 0;
        for (String[] row : rows) {
            if (i >= 3) break;
            msgLabels[i].setText(row[1]);
            timeLabels[i].setText(row[2]);
            i++;
        }
        // Clear remaining slots
        for (; i < 3; i++) {
            msgLabels[i].setText("");
            timeLabels[i].setText("");
        }
    }
}