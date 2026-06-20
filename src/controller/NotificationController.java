package controller;

import dao.NotificationDAO;
import view.NotificationPanel;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class NotificationController {

    private NotificationPanel panel;
    private NotificationDAO dao;

    public NotificationController(NotificationPanel panel) {
        this.panel = panel;
        this.dao   = new NotificationDAO();
    }

    public void loadAll() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Notification Details", "Date/Time"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table read-only
            }
        };

        List<String[]> rows = dao.getRecentNotifications();
        for (String[] row : rows) {
            model.addRow(new Object[]{row[0], row[1], row[2]});
        }
        
        panel.getNotificationTable().setModel(model);
        
        // Optional: resize columns
        panel.getNotificationTable().getColumnModel().getColumn(0).setPreferredWidth(50);
        panel.getNotificationTable().getColumnModel().getColumn(0).setMaxWidth(80);
        panel.getNotificationTable().getColumnModel().getColumn(2).setPreferredWidth(150);
        panel.getNotificationTable().getColumnModel().getColumn(2).setMaxWidth(200);
    }
}