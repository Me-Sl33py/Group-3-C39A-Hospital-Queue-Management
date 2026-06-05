/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.*;
import java.awt.*;

public class SearchPatientView extends javax.swing.JFrame {

    public SearchPatientView() {
        initComponents();
        setTitle("Search Patient");
        setLocationRelativeTo(null);
    }

    public JTextField getTxtSearch()  { return txtSearch;  }
    public JButton    getBtnSearch()  { return btnSearch;  }
    public JButton    getBtnClear()   { return btnClear;   }
    public JTable     getTblResults() { return tblResults; }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // ── Title ─────────────────────────────────────────────────────────────
        JLabel lblTitle = new JLabel("Patient Database");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));

        // ── Search bar ────────────────────────────────────────────────────────
        txtSearch = new JTextField();
        txtSearch.setPreferredSize(new Dimension(300, 35));
        txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        btnSearch = new JButton("Search");
        btnSearch.setBackground(new Color(37, 99, 235));
        btnSearch.setForeground(Color.BLACK);
        btnSearch.setFocusPainted(false);

        btnClear = new JButton("Clear");
        btnClear.setFocusPainted(false);

        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 5));
        topBar.add(new JLabel("Search:"));
        topBar.add(txtSearch);
        topBar.add(btnSearch);
        topBar.add(btnClear);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(lblTitle, BorderLayout.NORTH);
        topPanel.add(topBar,   BorderLayout.CENTER);

        // ── Table ─────────────────────────────────────────────────────────────
        tblResults = new JTable();
        tblResults.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"Patient ID", "Full Name", "Age",
                         "Gender", "Contact", "Address"}
        ) {
            public boolean isCellEditable(int r, int c) { return false; }
        });
        tblResults.setRowHeight(35);
        tblResults.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tblResults.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));

        JScrollPane scrollPane = new JScrollPane(tblResults);

        mainPanel.add(topPanel,    BorderLayout.NORTH);
        mainPanel.add(scrollPane,  BorderLayout.CENTER);

        setContentPane(mainPanel);
        setPreferredSize(new Dimension(850, 500));
        pack();
    }

    private JTextField txtSearch;
    private JButton    btnSearch;
    private JButton    btnClear;
    private JTable     tblResults;
}