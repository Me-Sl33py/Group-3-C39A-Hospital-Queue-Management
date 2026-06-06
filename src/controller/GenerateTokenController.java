package controller;

import view.GenerateTokenView;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.List;
import java.sql.Timestamp;

public class GenerateTokenController {
    private final GenerateTokenView view;
    private final view.WithTabbedPane mainFrame;
    private String currentPatientId = null;
    private String patientName = "Mr. Alexander Thompson";
    private String patientID = "Patient ID: #HP-2024-8891";
    private String ageGen = "34 Years / Male";
    private String contact = "+1 (555) 012-3456";
    private String bloodGroup = "O Positive (O+)";
    private String regDate = "Oct 24, 2023 | 09:15 AM";
    private List<model.Department> departments;

    public GenerateTokenController(GenerateTokenView view, view.WithTabbedPane mainFrame) {
        this.view = view;
        this.mainFrame = mainFrame;
        if (view.getCbDepartment() != null) {
            dao.DepartmentDAO deptDAO = new dao.DepartmentDAO();
            departments = deptDAO.getAllDepartments();
            DefaultComboBoxModel<model.Department> model = new DefaultComboBoxModel<>();
            model.addElement(new model.Department(-1, "Choose Department", ""));
            if (departments != null) {
                for (model.Department d : departments) {
                    model.addElement(d);
                }
            }
            view.getCbDepartment().setModel((DefaultComboBoxModel) model);
        }
        initEventHandlers();
        
        if (this.currentPatientId == null) {
            dao.PatientDAO pDAO = new dao.PatientDAO();
            model.Patient latest = pDAO.getLatestPatient();
            if (latest != null) {
                this.currentPatientId = latest.getPatientId();
                this.patientName = latest.getFullName();
                this.patientID = "Patient ID: " + latest.getPatientId();
                this.ageGen = latest.getAge() + " Years / " + latest.getGender();
                this.contact = latest.getContactNumber();
                this.bloodGroup = "Not Specified";
                this.regDate = new SimpleDateFormat("MMM dd, yyyy | hh:mm a").format(new java.util.Date());
            }
        }
        
        loadInitialData();
    }

    public void updatePatientDetails(String patientId, String name, String dob, String gender, String phone) {
        this.currentPatientId = patientId;
        this.patientName = name;
        this.patientID = "Patient ID: " + patientId;
        this.ageGen = dob + " Years / " + gender;
        this.contact = phone;
        this.bloodGroup = "Not Specified";
        this.regDate = new SimpleDateFormat("MMM dd, yyyy | hh:mm a").format(new java.util.Date());
        loadInitialData();
    }

    private void initEventHandlers() {
        view.getCbDepartment().addActionListener(e -> updateEstimatedWaitTime());
        view.getBtnGenerateTokenSubmit().addActionListener(e -> generateToken());
    }

    private void loadInitialData() {
        view.getLblPatientName().setText(patientName);
        view.getLblPatientID().setText(patientID);
        view.getLblAgeGenVal().setText(ageGen);
        view.getLblContactVal().setText(contact);
        view.getLblBloodVal().setText(bloodGroup);
        view.getLblRegDateVal().setText(regDate);

        view.getCbDepartment().setSelectedIndex(0);
        view.getLblTipText().setText("<html>Please select a department to see estimated waiting times.</html>");
        
        refreshLiveQueue();
    }

    public void refreshLiveQueue() {
        dao.TokenDAO tokenDAO = new dao.TokenDAO();
        List<model.Token> liveTokens = tokenDAO.getAllWaitingTokens();
        DefaultTableModel model = (DefaultTableModel) view.getTblLiveQueue().getModel();
        model.setRowCount(0);
        if (liveTokens != null) {
            for (model.Token t : liveTokens) {
                String timeStr = "";
                if (t.getCreatedAt() != null) {
                    timeStr = new SimpleDateFormat("hh:mm a").format(t.getCreatedAt());
                }
                model.addRow(new Object[]{t.getTokenNumber(), t.getPatientName(), "N/A", t.getStatus(), timeStr});
            }
        }
    }

    private void updateEstimatedWaitTime() {
        Object selected = view.getCbDepartment().getSelectedItem();
        if (selected == null || !(selected instanceof model.Department)) return;
        model.Department dept = (model.Department) selected;

        if (dept.getDepartmentId() == -1) {
            view.getLblTipText().setText("<html>Please select a department to see estimated waiting times.</html>");
            return;
        }

        // Just an estimate since queue table doesn't have department_id
        int waitingCount = new dao.TokenDAO().countTotalWaiting();
        int estimatedMins = waitingCount * 12;

        view.getLblTipText().setText("<html>Estimated waiting time for <b>" + dept.getDepartmentName() + "</b> is currently <b>" + estimatedMins + " minutes</b> with " + waitingCount + " patients ahead in queue.</html>");
    }

    private void generateToken() {
        Object selected = view.getCbDepartment().getSelectedItem();
        if (selected == null || !(selected instanceof model.Department)) return;
        model.Department dept = (model.Department) selected;

        if (dept.getDepartmentId() == -1) {
            JOptionPane.showMessageDialog(view, "Please select a department first.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (currentPatientId == null) {
            JOptionPane.showMessageDialog(view, "Please register a patient first.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int tokenNum = 100 + (int)(Math.random() * 900);
        
        dao.DoctorDAO doctorDAO = new dao.DoctorDAO();
        java.util.List<model.Doctor> doctors = doctorDAO.getDoctorsByDepartment(dept.getDepartmentId());
        String assignedDoctorId = "";
        if (doctors != null && !doctors.isEmpty()) {
            assignedDoctorId = doctors.get(0).getDoctorId();
        } else {
            JOptionPane.showMessageDialog(view, "No doctors available in this department to assign.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        model.Token token = new model.Token(-1, tokenNum, currentPatientId, assignedDoctorId, "Waiting", null);

        dao.TokenDAO tokenDAO = new dao.TokenDAO();
        int queueId = tokenDAO.createToken(token);

        if (queueId != -1) {
            refreshLiveQueue();
            JOptionPane.showMessageDialog(view, "Token generated successfully!\nToken Number: " + tokenNum, "Token Generated", JOptionPane.INFORMATION_MESSAGE);
            if (mainFrame.getAssignToDoctorController() != null) {
                mainFrame.getAssignToDoctorController().refreshData();
            }
            if (mainFrame.getDashboardController() != null) {
                mainFrame.getDashboardController().refreshData();
            }
            if (mainFrame.getRegisterWalkinController() != null) {
                mainFrame.getRegisterWalkinController().refreshData();
            }
        } else {
            JOptionPane.showMessageDialog(view, "Database Error: Could not create token.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
