package controller;

import dao.ScheduleDAO;
import view.SchedulePanel;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ScheduleController {

    private SchedulePanel panel;
    private ScheduleDAO dao;

    public ScheduleController(SchedulePanel panel) {
        this.panel = panel;
        this.dao = new ScheduleDAO();
    }

    public void loadAll() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"Time", "Doctor Name", "Department", "Patient Name", "Status"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table read-only
            }
        };

        List<String[]> rows = dao.getTodaySchedule();
        for (String[] row : rows) {
            model.addRow(new Object[]{row[0], row[1], row[2], row[3]});
        }
        
        panel.getScheduleTable().setModel(model);
        
        // Resize columns
        panel.getScheduleTable().getColumnModel().getColumn(0).setPreferredWidth(100);
        panel.getScheduleTable().getColumnModel().getColumn(0).setMaxWidth(150);
        panel.getScheduleTable().getColumnModel().getColumn(3).setPreferredWidth(100);
        panel.getScheduleTable().getColumnModel().getColumn(3).setMaxWidth(150);
    }
}