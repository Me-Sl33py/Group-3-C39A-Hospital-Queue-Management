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

    // Add this:
    panel.getSearchButton().addActionListener(e -> {
        String keyword = panel.getSearchField().getText().trim();
        loadTable(keyword);
    });
}

   public void loadAll() {
    loadCards();
    loadTable(""); 
}
    private void loadCards() {
    panel.getRcard1NumberLabel().setText(String.valueOf(dao.getMonthlyVisitCount()));
    panel.getRcard2NumberLabel().setText(String.valueOf(dao.getTotalDoctorCount()));
    panel.getRcard3NumberLabel().setText(String.valueOf(dao.getTotalAppointmentCount()));
    panel.getRcard4NumberLabel().setText(String.valueOf(dao.getTotalDepartmentCount()));
}

  private void loadTable(String keyword) {
    DefaultTableModel model = (DefaultTableModel) panel.getJTable1().getModel();
    model.setRowCount(0);
    List<String[]> rows = keyword.isEmpty() 
        ? dao.getMonthlyAppointments() 
        : dao.searchAppointments(keyword);
    for (String[] row : rows) model.addRow(row);
}
    }
