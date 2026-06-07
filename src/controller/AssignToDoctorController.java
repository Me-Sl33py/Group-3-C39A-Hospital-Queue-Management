package controller;

import view.AssignToDoctorView;
import javax.swing.*;
import java.util.List;

public class AssignToDoctorController {
    private final AssignToDoctorView view;
    private final view.WithTabbedPane mainFrame;
    private model.Token currentToken = null;
    private List<model.Doctor> doctors;

    public AssignToDoctorController(AssignToDoctorView view, view.WithTabbedPane mainFrame) {
        this.view = view;
        this.mainFrame = mainFrame;
        initEventHandlers();
        refreshData();
    }

    private void initEventHandlers() {
        view.getBtnAssignPatient().addActionListener(e -> assignPatient());
    }

    public void refreshData() {
        loadNextToken();
        loadDoctors();
        loadWorkloadCards();
    }

    private void loadNextToken() {
        dao.TokenDAO tokenDAO = new dao.TokenDAO();
        currentToken = tokenDAO.getNextUnassignedToken();

        if (currentToken != null) {
            view.getLblTokenValue().setText(String.valueOf(currentToken.getTokenNumber()));
            view.getLblFNameValue().setText(currentToken.getPatientName() != null ? currentToken.getPatientName() : "Unknown");
            view.getLblPIDValue().setText("Patient ID: " + currentToken.getPatientId());
            
            dao.PatientDAO pDAO = new dao.PatientDAO();
            model.Patient p = pDAO.getPatientById(currentToken.getPatientId());
            if (p != null) {
                String capGender = p.getGender() != null && !p.getGender().isEmpty() ? p.getGender().substring(0, 1).toUpperCase() + p.getGender().substring(1).toLowerCase() : "";
                view.getLblGenValue().setText(capGender + ", " + p.getAge() + " Years");
                view.getLblContactValue().setText(p.getContactNumber() != null ? p.getContactNumber() : "N/A");
                view.getLblBloodGroupValue().setText(p.getBloodGroup() != null && !p.getBloodGroup().isEmpty() ? p.getBloodGroup() : "Not Specified");
                view.getTaReason().setText(p.getReason() != null ? p.getReason() : "No reason specified.");
            }

            view.getBtnAssignPatient().setEnabled(true);
        } else {
            view.getLblTokenValue().setText("--");
            view.getLblFNameValue().setText("No waiting patients");
            view.getLblPIDValue().setText("Patient ID: N/A");
            view.getLblGenValue().setText("N/A");
            view.getLblContactValue().setText("N/A");
            view.getLblBloodGroupValue().setText("N/A");
            view.getTaReason().setText("");
            view.getBtnAssignPatient().setEnabled(false);
        }
    }

    private void loadDoctors() {
        dao.DoctorDAO doctorDAO = new dao.DoctorDAO();
        doctors = doctorDAO.getAllDoctors();
        
        DefaultComboBoxModel<model.Doctor> model = new DefaultComboBoxModel<>();
        if (doctors != null && !doctors.isEmpty()) {
            for (model.Doctor d : doctors) {
                model.addElement(d);
            }
            view.getCbDoctors().setModel((DefaultComboBoxModel) model);
            view.getBtnAssignPatient().setEnabled(true);
        } else {
            view.getBtnAssignPatient().setEnabled(false);
        }
    }

    private void loadWorkloadCards() {
        JPanel grid = view.getPnlWLGrid();
        grid.removeAll();

        if (doctors == null || doctors.isEmpty()) {
            grid.revalidate();
            grid.repaint();
            return;
        }

        grid.setLayout(new java.awt.GridLayout(1, Math.max(4, doctors.size()), 15, 0));
        
        dao.TokenDAO tokenDAO = new dao.TokenDAO();

        for (model.Doctor doctor : doctors) {
            int count = tokenDAO.countPatientsWaitingForDoctor(doctor.getDoctorId());
            
            JPanel pnlDoc = new JPanel(new java.awt.BorderLayout());
            pnlDoc.setBackground(new java.awt.Color(249, 250, 251));
            pnlDoc.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JPanel pnlTop = new JPanel(new java.awt.GridLayout(2, 1));
            pnlTop.setBackground(new java.awt.Color(249, 250, 251));

            JLabel lblName = new JLabel(doctor.getFullName());
            lblName.setFont(new java.awt.Font("Segoe UI", 1, 13));
            pnlTop.add(lblName);

            String deptStr = doctor.getSpecialization() != null ? doctor.getSpecialization() : "General";
            JLabel lblDept = new JLabel(deptStr);
            lblDept.setFont(new java.awt.Font("Segoe UI", 0, 11));
            lblDept.setForeground(new java.awt.Color(128, 128, 128));
            pnlTop.add(lblDept);

            pnlDoc.add(pnlTop, java.awt.BorderLayout.NORTH);

            JPanel pnlBot = new JPanel(new java.awt.BorderLayout());
            pnlBot.setBackground(new java.awt.Color(249, 250, 251));
            pnlBot.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

            JProgressBar pb = new JProgressBar();
            pb.setPreferredSize(new java.awt.Dimension(100, 5));
            
            JLabel lblStat = new JLabel();
            lblStat.setFont(new java.awt.Font("Segoe UI", 1, 10));
            
            int maxCapacity = 10;
            int percentage = (int) Math.min(100, ((double) count / maxCapacity) * 100);
            pb.setValue(percentage);

            if (count == 0) {
                pb.setForeground(new java.awt.Color(16, 185, 129)); // Green
                lblStat.setForeground(new java.awt.Color(16, 185, 129));
                lblStat.setText("NONE");
            } else if (count <= 3) {
                pb.setForeground(new java.awt.Color(16, 185, 129)); // Green
                lblStat.setForeground(new java.awt.Color(16, 185, 129));
                lblStat.setText("LOW");
            } else if (count <= 6) {
                pb.setForeground(new java.awt.Color(245, 158, 11)); // Orange/Medium
                lblStat.setForeground(new java.awt.Color(245, 158, 11));
                lblStat.setText("MED");
            } else {
                pb.setForeground(new java.awt.Color(239, 68, 68)); // Red/High
                lblStat.setForeground(new java.awt.Color(239, 68, 68));
                lblStat.setText("HIGH");
            }

            pnlBot.add(pb, java.awt.BorderLayout.CENTER);
            pnlBot.add(lblStat, java.awt.BorderLayout.EAST);

            pnlDoc.add(pnlBot, java.awt.BorderLayout.SOUTH);
            
            grid.add(pnlDoc);
        }
        
        // Fill empty spaces if less than 4 to maintain UI look
        int emptySlots = 4 - doctors.size();
        for (int i = 0; i < emptySlots; i++) {
            JPanel emptyPanel = new JPanel();
            emptyPanel.setOpaque(false);
            grid.add(emptyPanel);
        }

        grid.revalidate();
        grid.repaint();
    }

    private void assignPatient() {
        if (currentToken == null) {
            JOptionPane.showMessageDialog(view, "No patient to assign.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Object selected = view.getCbDoctors().getSelectedItem();
        if (selected == null || !(selected instanceof model.Doctor)) {
            JOptionPane.showMessageDialog(view, "Please select a valid doctor.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        model.Doctor doctor = (model.Doctor) selected;

        dao.TokenDAO tokenDAO = new dao.TokenDAO();
        boolean success = tokenDAO.assignDoctorToToken(currentToken.getQueueId(), doctor.getDoctorId());

        if (success) {
            JOptionPane.showMessageDialog(view, "Patient successfully assigned to " + doctor.getFullName(), "Success", JOptionPane.INFORMATION_MESSAGE);
            refreshData();
            if (mainFrame.getDashboardController() != null) {
                mainFrame.getDashboardController().refreshData();
            }
            if (mainFrame.getGenerateTokenController() != null) {
                mainFrame.getGenerateTokenController().refreshLiveQueue();
            }
            if (mainFrame.getRegisterWalkinController() != null) {
                mainFrame.getRegisterWalkinController().refreshData();
            }
        } else {
            JOptionPane.showMessageDialog(view, "Database Error: Could not assign patient.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
