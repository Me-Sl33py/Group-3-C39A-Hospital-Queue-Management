package controller;

import dao.ReportDAO;
import view.ReportPanel;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ReportController {

    private ReportPanel panel;
    private ReportDAO dao;

    public ReportController(ReportPanel panel) {
        this.panel = panel;
        this.dao   = new ReportDAO();
    }

    public void loadAll() {
        loadCards();
        loadTable();
    }

    private void loadCards() {
        panel.getRcard1NumberLabel().setText(String.valueOf(dao.getMonthlyVisitCount()));
        panel.getRcard2NumberLabel().setText(String.valueOf(dao.getTotalDoctorCount()));
        panel.getRcard3NumberLabel().setText(String.valueOf(dao.getActiveDoctorCount()));
        panel.getRcard4NumberLabel().setText(String.valueOf(dao.getTotalPatientCount()));
    }

    private void loadTable() {
        DefaultTableModel model = (DefaultTableModel) panel.getJTable1().getModel();
        model.setRowCount(0);
        List<String[]> rows = dao.getMonthlyAppointments();
        for (String[] row : rows) model.addRow(row);
    }
}