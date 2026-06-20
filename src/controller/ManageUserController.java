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
            new String[]{"User ID", "Full Name", "Phone", "Gender", "Blood Group", "Date of Birth", "Age", "Role", "Status"}, 0
        ) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        panel.getTblUsers().setModel(model);
    }

    private void initListeners() {
        panel.getTblUsers().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) onRowSelected();
        });

        panel.getTxtSearch().getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { triggerSearch(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { triggerSearch(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { triggerSearch(); }
        });
        
        panel.getCmbFilterRole().addActionListener(e -> triggerSearch());
        panel.getCmbFilterStatus().addActionListener(e -> triggerSearch());
        panel.getCmbFilterAge().addActionListener(e -> triggerSearch());

        panel.getBtnUpdateUser().addActionListener(e -> updateUser());

        panel.getBtnDeactivateUser().addActionListener(e -> toggleUserStatus());

        panel.getBtnRefresh().addActionListener(e -> {
            panel.getTxtSearch().setText("");
            panel.getCmbFilterRole().setSelectedIndex(0);
            panel.getCmbFilterStatus().setSelectedIndex(0);
            panel.getCmbFilterAge().setSelectedIndex(0);
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
        setCombo(panel.getCmbGender(), table.getValueAt(row, 3));
        setCombo(panel.getCmbBloodGroup(), table.getValueAt(row, 4));
        Object dobVal = table.getValueAt(row, 5);
        if (dobVal != null && !dobVal.toString().trim().isEmpty()) {
            try {
                java.util.Date d = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(dobVal.toString());
                panel.getDobChooser().setDate(d);
            } catch (Exception ex) {
                panel.getDobChooser().setDate(null);
            }
        } else {
            panel.getDobChooser().setDate(null);
        }
        
        // Age is index 6, but we don't have a field for it in the Selected User Details panel.
        
        setCombo(panel.getCmbRole(),   table.getValueAt(row, 7));

        Object statusVal = table.getValueAt(row, 8);
        if (statusVal != null) {
            String s = statusVal.toString();
            if (s.equalsIgnoreCase("deactive")) s = "Deactive";
            setCombo(panel.getCmbStatus(), s);
            boolean isDeactive = s.equalsIgnoreCase("Deactive");
            panel.getBtnDeactivateUser().setText(isDeactive ? "Activate User" : "Deactivate User");
            panel.getBtnDeactivateUser().setBackground(isDeactive
                ? new java.awt.Color(40, 167, 69)
                : new java.awt.Color(220, 53, 69));
        }
    }

    private void updateUser() {
    if (selectedUserId == -1) {
        JOptionPane.showMessageDialog(parentFrame, "Please select a user first.",
            "No Selection", JOptionPane.WARNING_MESSAGE); return;
    }
    String fullName = panel.getTxtFullName().getText().trim();
    java.util.Date d = panel.getDobChooser().getDate();
    String dob = (d != null) ? new java.text.SimpleDateFormat("yyyy-MM-dd").format(d) : "";
    String status  = panel.getCmbStatus().getSelectedItem().toString().toLowerCase();
    String gender  = panel.getCmbGender().getSelectedItem().toString().toLowerCase();
    String phone   = panel.getTxtPhone().getText().trim();
    String bloodGroup = panel.getCmbBloodGroup().getSelectedItem().toString();
    String newPass = panel.getTxtChangePassword().getText().trim();

    if (!newPass.isEmpty() && newPass.length() < 6) {
        JOptionPane.showMessageDialog(parentFrame, "New password must be at least 6 characters long.", "Validation Error", JOptionPane.WARNING_MESSAGE);
        return;
    }

    boolean ok = dao.updateUser(selectedUserId, fullName, dob, status, gender, phone, bloodGroup);

    if (ok) {
        new dao.NotificationDAO().addNotification("Profile Updated", "Account is updated for User ID " + selectedUserId + " by Admin Admin");
    }

    if (ok && !newPass.isEmpty()) {
        boolean passOk = dao.changePassword(selectedUserId, newPass);
        if (passOk) {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dt = sdf.format(new java.util.Date());
            new dao.NotificationDAO().addNotification("Password Reset", "Password is changed for User ID " + selectedUserId + " by Admin Admin");
        }
        JOptionPane.showMessageDialog(parentFrame,
            passOk ? "Password changed successfully!" : "Failed to change password.",
            passOk ? "Success" : "Error",
            passOk ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
        panel.getTxtChangePassword().setText("");
    }

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
        boolean isActive = !status.equalsIgnoreCase("deactive");
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
            if (ok) {
                new dao.NotificationDAO().addNotification("Status Updated", "Account status is changed to " + (isActive ? "Deactive" : "Active") + " for User ID " + selectedUserId + " by Admin Admin");
                loadAllUsers(); 
                clearFields(); 
                resetDeactivateButton(); 
            }
        }
    }

    private void triggerSearch() {
        String keyword = panel.getTxtSearch().getText().trim();
        String roleFilter = panel.getCmbFilterRole().getSelectedItem().toString();
        String statusFilter = panel.getCmbFilterStatus().getSelectedItem().toString();
        String ageFilter = panel.getCmbFilterAge().getSelectedItem().toString();
        searchUsers(keyword, roleFilter, statusFilter, ageFilter);
    }

    private void searchUsers(String keyword, String roleFilter, String statusFilter, String ageFilter) {
        List<String[]> users = dao.searchUsers(keyword, roleFilter, statusFilter, ageFilter);
        DefaultTableModel model = (DefaultTableModel) panel.getTblUsers().getModel();
        model.setRowCount(0);
        for (String[] row : users) model.addRow(row);
    }

    public void loadAllUsers() { searchUsers("", "All", "All", "All"); }

   private void clearFields() {
    selectedUserId = -1;
    panel.getTxtFullName().setText("");
    panel.getTxtPhone().setText("");
    panel.getDobChooser().setDate(null);
    panel.getTxtChangePassword().setText(""); // ← add this
    panel.getCmbRole().setSelectedIndex(0);
    panel.getCmbStatus().setSelectedIndex(0);
    panel.getCmbGender().setSelectedIndex(0);
    panel.getCmbBloodGroup().setSelectedIndex(0);
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
