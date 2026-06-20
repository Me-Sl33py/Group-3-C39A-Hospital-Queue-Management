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
    private String regDate = "N/A";
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
            view.getCbDepartment().setModel(model);
        }
        
        if (view.getCbPatientSearch() != null) {
            refreshPatientList(null);
        } else {
            loadInitialData();
        }

        initEventHandlers();
        startAutoRefreshTimer();
    }

    public void updatePatientDetails(String patientId, String name, String dob, String gender, String phone, int appointmentId) {
        refreshPatientList(patientId);
    }

    public void refreshPatientList(String selectPatientId) {
        if (view.getCbPatientSearch() != null) {
            dao.PatientDAO pDAO = new dao.PatientDAO();
            List<model.Patient> patients = pDAO.getAllPatients();
            DefaultComboBoxModel<model.Patient> pModel = new DefaultComboBoxModel<>();
            model.Patient selectedPatient = null;
            if (patients != null) {
                for (model.Patient p : patients) {
                    pModel.addElement(p);
                    if (selectPatientId != null && p.getPatientId().equals(selectPatientId)) {
                        selectedPatient = p;
                    }
                }
            }
            
            java.awt.event.ActionListener[] listeners = view.getCbPatientSearch().getActionListeners();
            for (java.awt.event.ActionListener l : listeners) {
                view.getCbPatientSearch().removeActionListener(l);
            }
            
            view.getCbPatientSearch().setModel((DefaultComboBoxModel) pModel);
            if (selectedPatient != null) {
                view.getCbPatientSearch().setSelectedItem(selectedPatient);
            } else if (pModel.getSize() > 0) {
                view.getCbPatientSearch().setSelectedIndex(0);
            }
            
            for (java.awt.event.ActionListener l : listeners) {
                view.getCbPatientSearch().addActionListener(l);
            }
            
            onPatientSelected();
        }
    }

    private void initEventHandlers() {
        if (view.getCbPatientSearch() != null) {
            view.getCbPatientSearch().addActionListener(e -> onPatientSelected());
        }
        view.getCbDepartment().addActionListener(e -> {
            updateEstimatedWaitTime();
            loadDoctorsForSelectedDepartment();
        });
        view.getBtnGenerateTokenSubmit().addActionListener(e -> generateToken());
    }

    private void loadDoctorsForSelectedDepartment() {
        if (view.getCbDoctor() == null) return;
        
        DefaultComboBoxModel<model.Doctor> doctorModel = new DefaultComboBoxModel<>();
        view.getCbDoctor().setModel(doctorModel);
        
        Object selected = view.getCbDepartment().getSelectedItem();
        if (!(selected instanceof model.Department)) return;
        
        model.Department dept = (model.Department) selected;
        if (dept.getDepartmentId() == -1) return;
        
        dao.DoctorDAO doctorDAO = new dao.DoctorDAO();
        List<model.Doctor> doctors = doctorDAO.getDoctorsByDepartment(dept.getDepartmentId());
        
        if (doctors == null || doctors.isEmpty()) {
            view.getLblTipText().setText("<html>No doctors found for <b>" + dept.getDepartmentName() + "</b>.</html>");
            return;
        }
        
        for (model.Doctor d : doctors) {
            doctorModel.addElement(d);
        }
        view.getCbDoctor().setModel(doctorModel);
        view.getCbDoctor().setEnabled(true);
    }

    private void startAutoRefreshTimer() {
        Timer timer = new Timer(5000, e -> {
            if (view.isShowing()) {
                refreshLiveQueue();
            }
        });
        timer.start();
    }

    private void onPatientSelected() {
        if (view.getCbPatientSearch() == null) return;
        Object selected = view.getCbPatientSearch().getSelectedItem();
        if (selected == null || !(selected instanceof model.Patient)) return;
        model.Patient p = (model.Patient) selected;
        
        this.currentPatientId = p.getPatientId();
        this.patientName = p.getFullName();
        this.patientID = "Patient ID: " + p.getPatientId();
        
        String capGender = p.getGender();
        if (capGender != null && capGender.length() > 0) {
            capGender = capGender.substring(0, 1).toUpperCase() + capGender.substring(1).toLowerCase();
        }
        this.ageGen = p.getAge() + " Years / " + capGender;
        this.contact = p.getContactNumber();
        this.bloodGroup = p.getBloodGroup() != null && !p.getBloodGroup().isEmpty() ? p.getBloodGroup() : "Not Specified";
        if (p.getCreatedAt() != null) {
            this.regDate = new SimpleDateFormat("MMM dd, yyyy | hh:mm a").format(p.getCreatedAt());
        } else {
            this.regDate = new SimpleDateFormat("MMM dd, yyyy | hh:mm a").format(new java.util.Date());
        }
        
        loadInitialData();
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
        
        if (view.getCbDoctor() != null) {
            view.getCbDoctor().setModel(new DefaultComboBoxModel<>());
        }
        
        refreshLiveQueue();
    }

    public void refreshLiveQueue() {
        dao.TokenDAO tokenDAO = new dao.TokenDAO();
        List<model.Token> liveTokens = tokenDAO.getAllWaitingTokens();
        DefaultTableModel model = (DefaultTableModel) view.getTblLiveQueue().getModel();
        model.setRowCount(0);
        if (liveTokens != null) {
            int queuePosition = 1;
            for (model.Token t : liveTokens) {
                String timeStr = "";
                if (t.getCreatedAt() != null) {
                    timeStr = new SimpleDateFormat("hh:mm a").format(t.getCreatedAt());
                }
                String deptName = t.getDepartmentName() != null ? t.getDepartmentName() : "N/A";
                String doctorName = t.getDoctorName() != null ? t.getDoctorName() : "Any Available";
                String estWait = (queuePosition * 12) + " mins";
                model.addRow(new Object[]{t.getTokenNumber(), t.getPatientName(), deptName, doctorName, t.getStatus(), estWait, timeStr});
                queuePosition++;
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

        dao.AppointmentDAO apptDAO = new dao.AppointmentDAO();
        model.Appointment appointment = apptDAO.getLatestConfirmedAppointment(currentPatientId);
        
        Integer appointmentId = appointment != null ? appointment.getAppointmentId() : null;

        String doctorId = null;
        Object selectedDoctor = view.getCbDoctor() != null ? view.getCbDoctor().getSelectedItem() : null;
        if (selectedDoctor != null && selectedDoctor instanceof model.Doctor) {
            doctorId = ((model.Doctor) selectedDoctor).getDoctorId();
        }

        dao.TokenDAO tokenDAO = new dao.TokenDAO();
        int generatedTokenNumber = tokenDAO.createToken(appointmentId, currentPatientId, dept.getDepartmentId(), doctorId);

        if (generatedTokenNumber != -1) {
            refreshLiveQueue();
            String docName = (selectedDoctor != null) ? ((model.Doctor) selectedDoctor).getFullName() : "Any Available";
            String patName = patientName != null ? patientName : "Unknown Patient";
            
            view.TokenPrintDialog printDialog = new view.TokenPrintDialog(
                (JFrame) SwingUtilities.getWindowAncestor(view),
                String.valueOf(generatedTokenNumber),
                patName,
                dept.getDepartmentName(),
                docName
            );
            printDialog.setVisible(true);
            
            if (mainFrame.getDashboardController() != null) {
                mainFrame.getDashboardController().refreshData();
            }
            if (mainFrame.getRegisterWalkinController() != null) {
                mainFrame.getRegisterWalkinController().refreshData();
            }
        } else {
            JOptionPane.showMessageDialog(view, "Database Error: Could not create token.\n" + dao.TokenDAO.lastError, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
