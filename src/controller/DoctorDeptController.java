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
            new String[]{"ID", "Full Name", "Phone", "Specialty", "Department", "Availability", "Status"}, 0
        ) { public boolean isCellEditable(int r, int c) { return false; } };
        panel.getTblDoctors().setModel(doctorModel);

        DefaultTableModel deptModel = new DefaultTableModel(
            new String[]{"ID", "Department Name", "Head Doctor"}, 0
        ) { public boolean isCellEditable(int r, int c) { return false; } };
        panel.getTblDepartments().setModel(deptModel);
    }

    private void initListeners() {
        // Doctor listeners
        panel.getBtnSearchDoc().addActionListener(e ->
            loadDoctors(panel.getTxtSearchDoc().getText().trim()));

        panel.getTblDoctors().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) onDoctorRowSelected();
        });

        panel.getBtnAddDoc().addActionListener(e -> addDoctor());
        panel.getBtnUpdateDoc().addActionListener(e -> updateDoctor());
        panel.getBtnRemoveDoc().addActionListener(e -> removeDoctor());
        panel.getBtnRefreshDoc().addActionListener(e -> {
            panel.getTxtSearchDoc().setText("");
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
        setCombo(panel.getCmbDocStatus(),       table.getValueAt(row, 6));
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
        String status   = panel.getCmbDocStatus().getSelectedItem().toString();

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
            "Are you sure you want to remove this doctor?",
            "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            boolean ok = doctorDAO.removeDoctor(selectedDoctorId);
            JOptionPane.showMessageDialog(parentFrame,
                ok ? "Doctor removed!" : "Failed to remove.",
                ok ? "Success" : "Error",
                ok ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
            if (ok) { loadAllDoctors(); clearDocFields(); }
        }
    }

    private void loadDoctors(String keyword) {
        List<String[]> doctors = doctorDAO.searchDoctors(keyword);
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
            "Are you sure? This will fail if doctors are assigned to this department.",
            "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            boolean ok = deptDAO.removeDepartment(selectedDeptId);
            JOptionPane.showMessageDialog(parentFrame,
                ok ? "Department removed!" : "Failed. Department may have doctors assigned.",
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