package controller;

import dao.UserDAO;
import view.ManageuserPanel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ManageUserController {

    private ManageuserPanel panel;
    private UserDAO dao;
    private JFrame parentFrame;
    private int selectedUserId = -1;

    public ManageUserController(ManageuserPanel panel, JFrame parentFrame) {
        this.panel       = panel;
        this.dao         = new UserDAO();
        this.parentFrame = parentFrame;
        setupTable();
        initListeners();
        loadAllUsers();
    }

    private void setupTable() {
        DefaultTableModel model = new DefaultTableModel(
            new String[]{"User ID", "Full Name", "Phone", "Gender", "Date of Birth", "Role", "Status"}, 0
        ) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        panel.getTblUsers().setModel(model);
    }

    private void initListeners() {
        panel.getTblUsers().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) onRowSelected();
        });

        panel.getBtnSearch().addActionListener(e ->
            searchUsers(panel.getTxtSearch().getText().trim()));

        panel.getBtnUpdateUser().addActionListener(e -> updateUser());

        panel.getBtnDeactivateUser().addActionListener(e -> toggleUserStatus());

        panel.getBtnRefresh().addActionListener(e -> {
            panel.getTxtSearch().setText("");
            loadAllUsers();
            clearFields();
            resetDeactivateButton();
        });
    }

    private void onRowSelected() {
        JTable table = panel.getTblUsers();
        int row = table.getSelectedRow();
        if (row < 0 || row >= table.getRowCount()) return;
        Object idVal = table.getValueAt(row, 0);
        if (idVal == null) return;

        selectedUserId = Integer.parseInt(idVal.toString());

        setValue(panel.getTxtFullName(), table.getValueAt(row, 1));
        setValue(panel.getTxtPhone(),    table.getValueAt(row, 2));
       setValue(panel.getTxtDob(), table.getValueAt(row, 4));

        setCombo(panel.getCmbRole(),   table.getValueAt(row, 5));
        setCombo(panel.getCmbGender(), table.getValueAt(row, 3));

        Object statusVal = table.getValueAt(row, 6);
        if (statusVal != null) {
            setCombo(panel.getCmbStatus(), statusVal);
            boolean isInactive = statusVal.toString().equalsIgnoreCase("inactive");
            panel.getBtnDeactivateUser().setText(isInactive ? "Activate User" : "Deactivate User");
            panel.getBtnDeactivateUser().setBackground(isInactive
                ? new java.awt.Color(40, 167, 69)
                : new java.awt.Color(220, 53, 69));
        }
    }

    private void updateUser() {
        if (selectedUserId == -1) {
            JOptionPane.showMessageDialog(parentFrame, "Please select a user first.",
                "No Selection", JOptionPane.WARNING_MESSAGE); return;
        }
        String status = panel.getCmbStatus().getSelectedItem().toString().toLowerCase();
        String gender = panel.getCmbGender().getSelectedItem().toString().toLowerCase();
        String phone  = panel.getTxtPhone().getText().trim();

        boolean ok = dao.updateUser(selectedUserId, status, gender, phone);
        JOptionPane.showMessageDialog(parentFrame,
            ok ? "User updated successfully!" : "Failed to update user.",
            ok ? "Success" : "Error",
            ok ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
        if (ok) { loadAllUsers(); clearFields(); }
    }

    private void toggleUserStatus() {
        if (selectedUserId == -1) {
            JOptionPane.showMessageDialog(parentFrame, "Please select a user first.",
                "No Selection", JOptionPane.WARNING_MESSAGE); return;
        }
        String status   = panel.getCmbStatus().getSelectedItem().toString();
        boolean isActive = status.equalsIgnoreCase("active");
        String action   = isActive ? "deactivate" : "activate";

        int confirm = JOptionPane.showConfirmDialog(parentFrame,
            "Are you sure you want to " + action + " this user?",
            "Confirm", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean ok = isActive
                ? dao.deactivateUser(selectedUserId)
                : dao.activateUser(selectedUserId);
            JOptionPane.showMessageDialog(parentFrame,
                ok ? "User " + action + "d!" : "Failed.",
                ok ? "Success" : "Error",
                ok ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
            if (ok) { loadAllUsers(); clearFields(); resetDeactivateButton(); }
        }
    }

    private void searchUsers(String keyword) {
        List<String[]> users = dao.searchUsers(keyword);
        DefaultTableModel model = (DefaultTableModel) panel.getTblUsers().getModel();
        model.setRowCount(0);
        for (String[] row : users) model.addRow(row);
        if (users.isEmpty())
            JOptionPane.showMessageDialog(parentFrame, "No users found.",
                "Search", JOptionPane.INFORMATION_MESSAGE);
    }

    public void loadAllUsers() { searchUsers(""); }

    private void clearFields() {
        selectedUserId = -1;
        panel.getTxtFullName().setText("");
        panel.getTxtPhone().setText("");
        panel.getTxtDob().setText("");
        panel.getCmbRole().setSelectedIndex(0);
        panel.getCmbStatus().setSelectedIndex(0);
        panel.getCmbGender().setSelectedIndex(0);
        panel.getTblUsers().clearSelection();
    }

    private void resetDeactivateButton() {
        panel.getBtnDeactivateUser().setText("Deactivate User");
        panel.getBtnDeactivateUser().setBackground(new java.awt.Color(220, 53, 69));
    }

    private void setValue(JTextField field, Object val) {
        field.setText(val != null ? val.toString() : "");
    }

    private void setCombo(JComboBox<String> cmb, Object val) {
        if (val == null) return;
        String v = val.toString();
        for (int i = 0; i < cmb.getItemCount(); i++)
            if (cmb.getItemAt(i).equalsIgnoreCase(v)) { cmb.setSelectedIndex(i); break; }
    }
}