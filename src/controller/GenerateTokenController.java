package controller;

import view.GenerateTokenView;
import view.DashboardView;
import view.RegisterWalkinView;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Controller class for the Generate Token Screen.
 * Implements MVC logic for form validation, table updates, and screen navigation.
 */
public class GenerateTokenController {
    private final GenerateTokenView view;
    private String patientName = "Mr. Alexander Thompson";
    private String patientID = "Patient ID: #HP-2024-8891";
    private String ageGen = "34 Years / Male";
    private String contact = "+1 (555) 012-3456";
    private String bloodGroup = "O Positive (O+)";
    private String regDate = "Oct 24, 2023 | 09:15 AM";

    public GenerateTokenController(GenerateTokenView view) {
        this.view = view;
        if (view.getCbDepartment() != null) {
            view.getCbDepartment().setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose Department", "Cardiology", "Dermatology", "Pediatrics", "General Medicine" }));
        }
        initEventHandlers();
        loadInitialData();
    }

    public GenerateTokenController(GenerateTokenView view, String name, String dob, String gender, String phone) {
        this.view = view;
        this.patientName = name;
        this.patientID = "Patient ID: #HP-2026-" + (1000 + (int)(Math.random() * 9000));
        this.ageGen = dob + " / " + gender;
        this.contact = phone;
        this.bloodGroup = "O Positive (O+)";
        this.regDate = new java.text.SimpleDateFormat("MMM dd, yyyy | hh:mm a").format(new java.util.Date());
        if (view.getCbDepartment() != null) {
            view.getCbDepartment().setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose Department", "Cardiology", "Dermatology", "Pediatrics", "General Medicine" }));
        }
        initEventHandlers();
        loadInitialData();
    }

    private void initEventHandlers() {
        // Navigation: Go to Dashboard/Waitlist
        view.getBtnManageWaitlist().addActionListener(e -> {
            view.dispose();
            DashboardView dashboard = new DashboardView();
            new DashboardController(dashboard);
            dashboard.setVisible(true);
        });

        // Navigation other sidebar items
        view.getBtnRegisterWalkin().addActionListener(e -> {
            view.dispose();
            RegisterWalkinView walkinView = new RegisterWalkinView();
            new RegisterWalkinController(walkinView);
            walkinView.setVisible(true);
        });

        view.getBtnAssignDoctor().addActionListener(e -> {
            JOptionPane.showMessageDialog(view, "Assign to Doctor Action Triggered", "Hospicare", JOptionPane.INFORMATION_MESSAGE);
        });

        view.getBtnLogout().addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(view, "Are you sure you want to log out?", "Logout", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                view.dispose();
                System.exit(0);
            }
        });

        // Dynamic combobox selection to update estimated wait times
        view.getCbDepartment().addActionListener(e -> {
            updateEstimatedWaitTime();
        });

        // Generate Token Submit button action
        view.getBtnGenerateTokenSubmit().addActionListener(e -> {
            generateToken();
        });
    }

    private void loadInitialData() {
        // Default patient information shown on mockup
        view.getLblPatientName().setText(patientName);
        view.getLblPatientID().setText(patientID);
        view.getLblAgeGenVal().setText(ageGen);
        view.getLblContactVal().setText(contact);
        view.getLblBloodVal().setText(bloodGroup);
        view.getLblRegDateVal().setText(regDate);

        // Vitals
        view.getLblBPVal().setText("120/80");
        view.getLblTempVal().setText("98.6°F");
        view.getLblSpo2Val().setText("99%");

        // Default combo box and tip
        view.getCbDepartment().setSelectedIndex(0);
        view.getLblTipText().setText("<html>Please select a department to see estimated waiting times.</html>");
    }

    private void updateEstimatedWaitTime() {
        String dept = (String) view.getCbDepartment().getSelectedItem();
        if (dept == null || dept.equals("Choose Department")) {
            view.getLblTipText().setText("<html>Please select a department to see estimated waiting times.</html>");
            return;
        }

        switch (dept) {
            case "General Medicine":
                view.getLblTipText().setText("<html>Estimated waiting time for <b>General Medicine</b> is currently <b>12 minutes</b> with 4 patients ahead in queue.</html>");
                break;
            case "Cardiology":
                view.getLblTipText().setText("<html>Estimated waiting time for <b>Cardiology</b> is currently <b>35 minutes</b> with 8 patients ahead in queue.</html>");
                break;
            case "Dermatology":
                view.getLblTipText().setText("<html>Estimated waiting time for <b>Dermatology</b> is currently <b>18 minutes</b> with 3 patients ahead in queue.</html>");
                break;
            case "Pediatrics":
                view.getLblTipText().setText("<html>Estimated waiting time for <b>Pediatrics</b> is currently <b>8 minutes</b> with 1 patient ahead in queue.</html>");
                break;
            default:
                view.getLblTipText().setText("<html>Estimated waiting time is currently unavailable for this department.</html>");
        }
    }

    private void generateToken() {
        String dept = (String) view.getCbDepartment().getSelectedItem();
        if (dept == null || dept.equals("Choose Department")) {
            JOptionPane.showMessageDialog(view, "Please select a department first.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 1. Determine prefix based on department
        String prefix = "GM";
        if (dept.equals("Cardiology")) prefix = "CD";
        else if (dept.equals("Dermatology")) prefix = "DM";
        else if (dept.equals("Pediatrics")) prefix = "PD";

        // 2. Generate random ticket number
        int randomNum = 100 + (int)(Math.random() * 900);
        String token = "#" + prefix + "-" + randomNum;
        String patientName = view.getLblPatientName().getText();
        String timeStr = new SimpleDateFormat("hh:mm a").format(new Date());

        // 3. Add to live queue table
        DefaultTableModel model = (DefaultTableModel) view.getTblLiveQueue().getModel();
        model.insertRow(0, new Object[]{token, patientName, dept, "Waiting", timeStr});

        JOptionPane.showMessageDialog(view, "Token generated successfully!\nToken Number: " + token, "Token Generated", JOptionPane.INFORMATION_MESSAGE);
    }
}
