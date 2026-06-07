package controller;

import dao.DashboardDAO;
import view.DashboardPanel;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class DashboardController {

    private DashboardPanel panel;
    private DashboardDAO dao;

    public DashboardController(DashboardPanel panel) {
        this.panel = panel;
        this.dao   = new DashboardDAO();
    }

    public void loadAll() {
        loadCards();
        loadTable();
    }

    private void loadCards() {
        panel.getCard1NumberLabel().setText(String.valueOf(dao.getPatientCount()));
        panel.getCard2NumberLabel().setText(String.valueOf(dao.getDoctorCount()));
        panel.getCard3NumberLabel().setText(String.valueOf(dao.getAppointmentCount()));
        panel.getCard4NumberLabel().setText(String.valueOf(dao.getReceptionistCount()));
    }

    private void loadTable() {
        DefaultTableModel model = new DefaultTableModel(
            new String[]{"ID", "User", "Enrolled", "Status", "Gender", "Role"}, 0
        ) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        List<String[]> rows = dao.getRecentUsers();
        for (String[] row : rows) model.addRow(row);
        panel.getDashboardTable().setModel(model);
    }
}