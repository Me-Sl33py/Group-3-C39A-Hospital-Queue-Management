package controller;
import view.*;
import view.admin;
import dao.AdminDAO;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class AdminController {
    private admin adminFrame;
    private AdminDAO adminDAO;
    private java.awt.CardLayout cardLayout;
    private javax.swing.JPanel contentPanel;

    private ManageuserPanel manageUserPanel;
    private CreateuserPanel createUserPanel;
    private int selectedUserId = -1;

    public AdminController(admin adminFrame) {
        this.adminFrame   = adminFrame;
        this.adminDAO     = new AdminDAO();
        this.cardLayout   = adminFrame.getCardLayout();
        this.contentPanel = adminFrame.getContentPanel();
        loadPanels();
        initNavigation();
    }

    private void loadPanels() {
        manageUserPanel = new ManageuserPanel();
        createUserPanel = new CreateuserPanel();

        contentPanel.add(new HomePanel(),         "home");
        contentPanel.add(new DashboardPanel(),    "dashboard");
        contentPanel.add(new SchedulePanel(),     "schedule");
        contentPanel.add(new ReportPanel(),       "report");
        contentPanel.add(new NotificationPanel(), "notification");
        contentPanel.add(createUserPanel,         "createuser");
        contentPanel.add(manageUserPanel,         "manageuser");
        contentPanel.add(new LogoutPanel(),       "logout");
        cardLayout.show(contentPanel, "home");

        setupCreateUserListeners();
        setupManageUserTable();
        setupManageUserListeners();
    }

    // ── CREATE USER ──────────────────────────────────────────────

    private void setupCreateUserListeners() {

        createUserPanel.getSaveButton().addActionListener(e -> {
            String fullName = createUserPanel.getNameField().getText().trim();
            String phone    = createUserPanel.getNameField1().getText().trim();
            String gender   = createUserPanel.getGenderCombobox()
                                .getSelectedItem().toString().trim();
            String email    = createUserPanel.getEmailField().getText().trim();
            String role     = createUserPanel.getRoleComboBox()
                                .getSelectedItem().toString().trim();
            String password = new String(
                createUserPanel.getPasswordField().getPassword()).trim();

            if (fullName.isEmpty() || phone.isEmpty() ||
                email.isEmpty()    || password.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(adminFrame,
                    "Please fill in all fields.", "Validation Error",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }

            boolean success = adminDAO.createUser(
                fullName, phone, gender, email, role, password);

            if (success) {
                String message =
                    "✅ User Created Successfully!\n" +
                    "─────────────────────────────\n" +
                    "Full Name : " + fullName  + "\n" +
                    "Phone     : " + phone     + "\n" +
                    "Gender    : " + gender    + "\n" +
                    "Email     : " + email     + "\n" +
                    "Role      : " + role      + "\n" +
                    "Password  : " + password  + "\n" +
                    "Status    : Active";
                javax.swing.JOptionPane.showMessageDialog(adminFrame,
                    message, "User Created",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
                clearCreateForm();
            } else {
                javax.swing.JOptionPane.showMessageDialog(adminFrame,
                    "Failed to create user.\nEmail may already exist.",
                    "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        });

        createUserPanel.getClearButton().addActionListener(e -> clearCreateForm());
    }

    private void clearCreateForm() {
        createUserPanel.getNameField().setText("");
        createUserPanel.getNameField1().setText("");
        createUserPanel.getEmailField().setText("");
        createUserPanel.getPasswordField().setText("");
        createUserPanel.getGenderCombobox().setSelectedIndex(0);
        createUserPanel.getRoleComboBox().setSelectedIndex(0);
    }

    // ── MANAGE USER ──────────────────────────────────────────────

    private void setupManageUserTable() {
        DefaultTableModel model = new DefaultTableModel(
            new String[]{"User ID", "Full Name", "Phone",
                         "Gender", "Email", "Role", "Status"}, 0
        ) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        manageUserPanel.getTblUsers().setModel(model);
        loadAllUsers();
    }

    private void setupManageUserListeners() {
        javax.swing.JTable table = manageUserPanel.getTblUsers();

        // Row click → fill fields
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    selectedUserId = Integer.parseInt(
                        table.getValueAt(row, 0).toString());
                    manageUserPanel.getTxtFullName()
                        .setText(table.getValueAt(row, 1).toString());
                    manageUserPanel.getTxtPhone()
                        .setText(table.getValueAt(row, 2).toString());
                    manageUserPanel.getTxtEmail()
                        .setText(table.getValueAt(row, 4).toString());

                    // Role
                    String role = table.getValueAt(row, 5).toString();
                    javax.swing.JComboBox<String> cmbRole =
                        manageUserPanel.getCmbRole();
                    for (int i = 0; i < cmbRole.getItemCount(); i++) {
                        if (cmbRole.getItemAt(i).equalsIgnoreCase(role)) {
                            cmbRole.setSelectedIndex(i); break;
                        }
                    }

                    // Status
                    String status = table.getValueAt(row, 6).toString();
                    javax.swing.JComboBox<String> cmbStatus =
                        manageUserPanel.getCmbStatus();
                    for (int i = 0; i < cmbStatus.getItemCount(); i++) {
                        if (cmbStatus.getItemAt(i).equalsIgnoreCase(status)) {
                            cmbStatus.setSelectedIndex(i); break;
                        }
                    }

                    // Gender
                    String gender = table.getValueAt(row, 3).toString();
                    javax.swing.JComboBox<String> cmbGender =
                        manageUserPanel.getCmbGender();
                    for (int i = 0; i < cmbGender.getItemCount(); i++) {
                        if (cmbGender.getItemAt(i).equalsIgnoreCase(gender)) {
                            cmbGender.setSelectedIndex(i); break;
                        }
                    }

                    // Toggle deactivate/activate button
                    if (status.equalsIgnoreCase("inactive")) {
                        manageUserPanel.getBtnDeactivateUser()
                            .setText("Activate User");
                        manageUserPanel.getBtnDeactivateUser()
                            .setBackground(new java.awt.Color(40, 167, 69));
                    } else {
                        manageUserPanel.getBtnDeactivateUser()
                            .setText("Deactivate User");
                        manageUserPanel.getBtnDeactivateUser()
                            .setBackground(new java.awt.Color(220, 53, 69));
                    }
                }
            }
        });

        // Search
        manageUserPanel.getBtnSearch().addActionListener(e ->
            searchUsers(manageUserPanel.getTxtSearch().getText().trim()));

        // Update User
        manageUserPanel.getBtnUpdateUser().addActionListener(e -> {
            if (selectedUserId == -1) {
                javax.swing.JOptionPane.showMessageDialog(adminFrame,
                    "Please select a user first.", "No Selection",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }
        String newStatus = manageUserPanel.getCmbStatus()
    .getSelectedItem().toString().toLowerCase();
String newGender = manageUserPanel.getCmbGender()
    .getSelectedItem().toString().toLowerCase();
String newPhone  = manageUserPanel.getTxtPhone()
    .getText().trim();
boolean ok = adminDAO.updateUser(selectedUserId, newStatus, newGender, newPhone);
            javax.swing.JOptionPane.showMessageDialog(adminFrame,
                ok ? "User updated successfully!" : "Failed to update user.",
                ok ? "Success" : "Error",
                ok ? javax.swing.JOptionPane.INFORMATION_MESSAGE
                   : javax.swing.JOptionPane.ERROR_MESSAGE);
            if (ok) { loadAllUsers(); clearManageFields(); }
        });

        // Deactivate / Activate
        manageUserPanel.getBtnDeactivateUser().addActionListener(e -> {
            if (selectedUserId == -1) {
                javax.swing.JOptionPane.showMessageDialog(adminFrame,
                    "Please select a user first.", "No Selection",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }
            String status = manageUserPanel.getCmbStatus()
                .getSelectedItem().toString();
            boolean isActive = status.equalsIgnoreCase("active");
            String action = isActive ? "deactivate" : "activate";

            int confirm = javax.swing.JOptionPane.showConfirmDialog(adminFrame,
                "Are you sure you want to " + action + " this user?",
                "Confirm", javax.swing.JOptionPane.YES_NO_OPTION);

            if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                boolean ok = isActive
                    ? adminDAO.deactivateUser(selectedUserId)
                    : adminDAO.activateUser(selectedUserId);
                javax.swing.JOptionPane.showMessageDialog(adminFrame,
                    ok ? "User " + action + "d!" : "Failed.",
                    ok ? "Success" : "Error",
                    ok ? javax.swing.JOptionPane.INFORMATION_MESSAGE
                       : javax.swing.JOptionPane.ERROR_MESSAGE);
                if (ok) { loadAllUsers(); clearManageFields(); }
            }
        });

        // Refresh
        manageUserPanel.getBtnRefresh().addActionListener(e -> {
            manageUserPanel.getTxtSearch().setText("");
            loadAllUsers();
            clearManageFields();
            manageUserPanel.getBtnDeactivateUser().setText("Deactivate User");
            manageUserPanel.getBtnDeactivateUser()
                .setBackground(new java.awt.Color(220, 53, 69));
        });
    }

    private void searchUsers(String keyword) {
        List<String[]> users = adminDAO.searchUsers(keyword);
        DefaultTableModel model =
            (DefaultTableModel) manageUserPanel.getTblUsers().getModel();
        model.setRowCount(0);
        for (String[] row : users) model.addRow(row);
        if (users.isEmpty())
            javax.swing.JOptionPane.showMessageDialog(adminFrame,
                "No users found.", "Search",
                javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }

    private void loadAllUsers() { searchUsers(""); }

    private void clearManageFields() {
        selectedUserId = -1;
        manageUserPanel.getTxtFullName().setText("");
        manageUserPanel.getTxtPhone().setText("");
        manageUserPanel.getTxtEmail().setText("");
        manageUserPanel.getCmbRole().setSelectedIndex(0);
        manageUserPanel.getCmbStatus().setSelectedIndex(0);
        manageUserPanel.getCmbGender().setSelectedIndex(0);
        manageUserPanel.getTblUsers().clearSelection();
    }

    // ── NAVIGATION ───────────────────────────────────────────────

    private void initNavigation() {
        adminFrame.getBtnHome()
            .addActionListener(e -> cardLayout.show(contentPanel, "home"));
        adminFrame.getBtnDashboard()
            .addActionListener(e -> cardLayout.show(contentPanel, "dashboard"));
        adminFrame.getBtnSchedule()
            .addActionListener(e -> cardLayout.show(contentPanel, "schedule"));
        adminFrame.getBtnReport()
            .addActionListener(e -> cardLayout.show(contentPanel, "report"));
        adminFrame.getBtnNotification()
            .addActionListener(e -> cardLayout.show(contentPanel, "notification"));
        adminFrame.getBtnCreateUser()
            .addActionListener(e -> cardLayout.show(contentPanel, "createuser"));
        adminFrame.getBtnManageUser()
            .addActionListener(e -> cardLayout.show(contentPanel, "manageuser"));
        adminFrame.getBtnLogout()
            .addActionListener(e -> handleLogout());
    }

    private void handleLogout() {
        int ok = javax.swing.JOptionPane.showConfirmDialog(
            adminFrame, "Are you sure you want to logout?",
            "Logout", javax.swing.JOptionPane.YES_NO_OPTION);
        if (ok == javax.swing.JOptionPane.YES_OPTION) {
            adminFrame.dispose();
        }
    }

    public AdminDAO getAdminDAO() { return adminDAO; }
}