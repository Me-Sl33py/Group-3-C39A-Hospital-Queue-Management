package controller;

import dao.DoctorDAO;
import dao.DepartmentDAO;
import view.managedoctoranddepartment;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class DoctorDeptController {

    private managedoctoranddepartment panel;
    private DoctorDAO doctorDAO;
    private DepartmentDAO deptDAO;
    private JFrame parentFrame;
    private String selectedDoctorId = null;
    private int selectedDeptId = -1;

    public DoctorDeptController(managedoctoranddepartment panel, JFrame parentFrame) {
        this.panel       = panel;
        this.doctorDAO   = new DoctorDAO();
        this.deptDAO     = new DepartmentDAO();
        this.parentFrame = parentFrame;
        setupTables();
        initListeners();
        loadAllDoctors();
        loadAllDepartments();
    }

    private void setupTables() {
        DefaultTableModel doctorModel = new DefaultTableModel(
            new String[]{"Doctor ID", "Full Name", "Phone", "Specialty", "Department", "Availability", "Status"}, 0
        ) { public boolean isCellEditable(int r, int c) { return false; } };
        panel.getTblDoctors().setModel(doctorModel);

        DefaultTableModel deptModel = new DefaultTableModel(
            new String[]{"Department ID", "Department Name", "Head Doctor"}, 0
        ) { public boolean isCellEditable(int r, int c) { return false; } };
        panel.getTblDepartments().setModel(deptModel);
    }

    private void initListeners() {
        // Doctor listeners
        panel.getBtnSearchDoc().addActionListener(e -> triggerDoctorSearch());
        
        // Add document listener to search text field
        panel.getTxtSearchDoc().getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { triggerDoctorSearch(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { triggerDoctorSearch(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { triggerDoctorSearch(); }
        });

        // Add action listeners to filter combo boxes
        panel.getCmbFilterDepartment().addActionListener(e -> triggerDoctorSearch());
        panel.getCmbFilterAvailability().addActionListener(e -> triggerDoctorSearch());
        panel.getCmbFilterStatus().addActionListener(e -> triggerDoctorSearch());

        panel.getTblDoctors().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) onDoctorRowSelected();
        });

        panel.getBtnAddDoc().addActionListener(e -> addDoctor());
        panel.getBtnUpdateDoc().addActionListener(e -> updateDoctor());
        panel.getBtnRemoveDoc().addActionListener(e -> removeDoctor());
        panel.getBtnRefreshDoc().addActionListener(e -> {
            panel.getTxtSearchDoc().setText("");
            panel.getCmbFilterDepartment().setSelectedIndex(0);
            panel.getCmbFilterAvailability().setSelectedIndex(0);
            panel.getCmbFilterStatus().setSelectedIndex(0);
            loadAllDoctors();
            clearDocFields();
        });

        // Department listeners
        panel.getBtnSearchDept().addActionListener(e ->
            loadDepartments(panel.getTxtSearchDept().getText().trim()));

        panel.getTblDepartments().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) onDeptRowSelected();
        });

        panel.getBtnAddDept().addActionListener(e -> addDepartment());
        panel.getBtnUpdateDept().addActionListener(e -> updateDepartment());
        panel.getBtnRemoveDept().addActionListener(e -> removeDepartment());
        panel.getBtnRefreshDept().addActionListener(e -> {
            panel.getTxtSearchDept().setText("");
            loadAllDepartments();
            clearDeptFields();
        });
    }

    // ── DOCTOR ───────────────────────────────────────────────────

    private void onDoctorRowSelected() {
        JTable table = panel.getTblDoctors();
        int row = table.getSelectedRow();
        if (row < 0 || row >= table.getRowCount()) return;
        Object idVal = table.getValueAt(row, 0);
        if (idVal == null) return;

        selectedDoctorId = idVal.toString();
        setValue(panel.getTxtDocFullName(),   table.getValueAt(row, 1));
        setValue(panel.getTxtDocPhone(),      table.getValueAt(row, 2));
        setValue(panel.getTxtDocSpecialty(),  table.getValueAt(row, 3));
        setValue(panel.getTxtDocDepartment(), table.getValueAt(row, 4));
        setCombo(panel.getCmbDocAvailability(), table.getValueAt(row, 5));

        Object statusVal = table.getValueAt(row, 6);
        if (statusVal != null) {
            String s = statusVal.toString();
            if (s.equalsIgnoreCase("deactive")) s = "Deactive";
            setCombo(panel.getCmbDocStatus(), s);
        }
    }

    private void addDoctor() {
    String name     = panel.getTxtDocFullName().getText().trim();
    String phone    = panel.getTxtDocPhone().getText().trim();
    String spec     = panel.getTxtDocSpecialty().getText().trim();
    String deptName = panel.getTxtDocDepartment().getText().trim();
    String avail    = panel.getCmbDocAvailability().getSelectedItem().toString();

    if (name.isEmpty() || phone.isEmpty() || spec.isEmpty() || deptName.isEmpty()) {
        JOptionPane.showMessageDialog(parentFrame,
            "Please fill in all fields.",
            "Validation Error",
            JOptionPane.WARNING_MESSAGE);
        return;
    }

    int deptId = deptDAO.getDepartmentIdByName(deptName);

    if (deptId == -1) {
        JOptionPane.showMessageDialog(parentFrame,
            "Department not found.",
            "Error",
            JOptionPane.ERROR_MESSAGE);
        return;
    }

    boolean ok = doctorDAO.addDoctor(name, phone, spec, deptId, avail);

    JOptionPane.showMessageDialog(parentFrame,
        ok ? "Doctor added successfully!" : "Failed to add doctor.");

    if (ok) {
        loadAllDoctors();
        clearDocFields();
    }
}


    private void updateDoctor() {
        if (selectedDoctorId == null) {
            JOptionPane.showMessageDialog(parentFrame, "Please select a doctor first.",
                "No Selection", JOptionPane.WARNING_MESSAGE); return;
        }
        String name     = panel.getTxtDocFullName().getText().trim();
        String phone    = panel.getTxtDocPhone().getText().trim();
        String spec     = panel.getTxtDocSpecialty().getText().trim();
        String deptName = panel.getTxtDocDepartment().getText().trim();
        String avail    = panel.getCmbDocAvailability().getSelectedItem().toString();
        String status   = panel.getCmbDocStatus().getSelectedItem().toString().toLowerCase();

        int deptId = deptDAO.getDepartmentIdByName(deptName);
        if (deptId == -1) {
            JOptionPane.showMessageDialog(parentFrame, "Department not found.",
                "Error", JOptionPane.ERROR_MESSAGE); return;
        }
        boolean ok = doctorDAO.updateDoctor(selectedDoctorId, name, phone, spec, deptId, avail, status);
        JOptionPane.showMessageDialog(parentFrame,
            ok ? "Doctor updated!" : "Failed to update.",
            ok ? "Success" : "Error",
            ok ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
        if (ok) { loadAllDoctors(); clearDocFields(); }
    }

    private void removeDoctor() {
        if (selectedDoctorId == null) {
            JOptionPane.showMessageDialog(parentFrame, "Please select a doctor first.",
                "No Selection", JOptionPane.WARNING_MESSAGE); return;
        }
        int confirm = JOptionPane.showConfirmDialog(parentFrame,
            "Are you sure you want to deactivate this doctor?",
            "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            boolean ok = doctorDAO.deactivateDoctor(selectedDoctorId);
            JOptionPane.showMessageDialog(parentFrame,
                ok ? "Doctor deactivated!" : "Failed to deactivate.",
                ok ? "Success" : "Error",
                ok ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
            if (ok) { loadAllDoctors(); clearDocFields(); }
        }
    }

    public void triggerDoctorSearch() {
        String keyword = panel.getTxtSearchDoc().getText().trim();
        String deptFilter = panel.getCmbFilterDepartment().getSelectedItem().toString();
        String availFilter = panel.getCmbFilterAvailability().getSelectedItem().toString();
        String statusFilter = panel.getCmbFilterStatus().getSelectedItem().toString();

        List<String[]> doctors = doctorDAO.searchDoctors(keyword, deptFilter, availFilter, statusFilter);
        DefaultTableModel model = (DefaultTableModel) panel.getTblDoctors().getModel();
        model.setRowCount(0);
        for (String[] row : doctors) model.addRow(row);
    }

    private void loadDoctors(String keyword) {
        // Fallback for initial load
        List<String[]> doctors = doctorDAO.searchDoctors(keyword, "All", "All", "All");
        DefaultTableModel model = (DefaultTableModel) panel.getTblDoctors().getModel();
        model.setRowCount(0);
        for (String[] row : doctors) model.addRow(row);
    }

    public void loadAllDoctors() { loadDoctors(""); }

    private void clearDocFields() {
        selectedDoctorId = null;
        panel.getTxtDocFullName().setText("");
        panel.getTxtDocPhone().setText("");
        panel.getTxtDocSpecialty().setText("");
        panel.getTxtDocDepartment().setText("");
        panel.getCmbDocAvailability().setSelectedIndex(0);
        panel.getCmbDocStatus().setSelectedIndex(0);
        panel.getTblDoctors().clearSelection();
    }

    // ── DEPARTMENT ───────────────────────────────────────────────

    private void onDeptRowSelected() {
        JTable table = panel.getTblDepartments();
        int row = table.getSelectedRow();
        if (row < 0 || row >= table.getRowCount()) return;
        Object idVal = table.getValueAt(row, 0);
        if (idVal == null) return;

        selectedDeptId = Integer.parseInt(idVal.toString());
        setValue(panel.getTxtDeptName(),       table.getValueAt(row, 1));
        setValue(panel.getTxtDeptHeadDoctor(), table.getValueAt(row, 2));
    }

    private void addDepartment() {
        String name = panel.getTxtDeptName().getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(parentFrame, "Please enter a department name.",
                "Validation Error", JOptionPane.WARNING_MESSAGE); return;
        }
        boolean ok = deptDAO.addDepartment(name);
        JOptionPane.showMessageDialog(parentFrame,
            ok ? "Department added!" : "Failed to add department.",
            ok ? "Success" : "Error",
            ok ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
        if (ok) { loadAllDepartments(); clearDeptFields(); }
    }

    private void updateDepartment() {
        if (selectedDeptId == -1) {
            JOptionPane.showMessageDialog(parentFrame, "Please select a department first.",
                "No Selection", JOptionPane.WARNING_MESSAGE); return;
        }
        String name = panel.getTxtDeptName().getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(parentFrame, "Please enter a department name.",
                "Validation Error", JOptionPane.WARNING_MESSAGE); return;
        }
        boolean ok = deptDAO.updateDepartment(selectedDeptId, name);
        JOptionPane.showMessageDialog(parentFrame,
            ok ? "Department updated!" : "Failed to update.",
            ok ? "Success" : "Error",
            ok ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
        if (ok) { loadAllDepartments(); clearDeptFields(); }
    }

    private void removeDepartment() {
        if (selectedDeptId == -1) {
            JOptionPane.showMessageDialog(parentFrame, "Please select a department first.",
                "No Selection", JOptionPane.WARNING_MESSAGE); return;
        }
        int confirm = JOptionPane.showConfirmDialog(parentFrame,
            "Are you sure you want to deactivate this department?",
            "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            boolean ok = deptDAO.removeDepartment(selectedDeptId);
            JOptionPane.showMessageDialog(parentFrame,
                ok ? "Department deactivated!" : "Failed to deactivate.",
                ok ? "Success" : "Error",
                ok ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
            if (ok) { loadAllDepartments(); clearDeptFields(); }
        }
    }

    private void loadDepartments(String keyword) {
        List<String[]> depts = deptDAO.searchDepartments(keyword);
        DefaultTableModel model = (DefaultTableModel) panel.getTblDepartments().getModel();
        model.setRowCount(0);
        for (String[] row : depts) model.addRow(row);
    }

    public void loadAllDepartments() { loadDepartments(""); }

    private void clearDeptFields() {
        selectedDeptId = -1;
        panel.getTxtDeptName().setText("");
        panel.getTxtDeptHeadDoctor().setText("");
        panel.getTblDepartments().clearSelection();
    }

    // ── HELPERS ──────────────────────────────────────────────────

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