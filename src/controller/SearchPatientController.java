/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.SearchPatientDAO;
import view.SearchPatientView;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class SearchPatientController {

    private final SearchPatientView view;
    private final SearchPatientDAO  searchDAO;

    public SearchPatientController(SearchPatientView view) {
        this.view      = view;
        this.searchDAO = new SearchPatientDAO();

        attachListeners();
        loadAllPatients(); // show full table on startup
    }

    private void attachListeners() {

        // Search button
        view.getBtnSearch().addActionListener(e -> searchPatients());

        // Enter key in search field
        view.getTxtSearch().addActionListener(e -> searchPatients());

        // Clear button — reloads full table
        view.getBtnClear().addActionListener(e -> {
            view.getTxtSearch().setText("");
            loadAllPatients();
        });
    }

    // Load full patients table on startup
    private void loadAllPatients() {
        List<Object[]> rows = searchDAO.getAllPatients();
        populateTable(rows);
    }

    // Filter table by search keyword
    private void searchPatients() {
        String keyword = view.getTxtSearch().getText().trim();

        if (keyword.isEmpty()) {
            loadAllPatients();
            return;
        }

        List<Object[]> results = searchDAO.searchPatients(keyword);
        populateTable(results);
    }

    // Fill the table
    private void populateTable(List<Object[]> rows) {
        DefaultTableModel model = (DefaultTableModel) view.getTblResults().getModel();
        model.setRowCount(0);
        for (Object[] row : rows) {
            model.addRow(row);
        }
    }

    // Run standalone
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            SearchPatientView view = new SearchPatientView();
            new SearchPatientController(view);
            view.setVisible(true);
        });
    }
}