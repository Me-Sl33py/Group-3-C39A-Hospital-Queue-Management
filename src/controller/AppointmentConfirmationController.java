package controller;

import view.AppointmentConfirmationView;
import dao.AppointmentDAO;
import model.Appointment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class AppointmentConfirmationController {
    private final AppointmentConfirmationView view;
    private final view.WithTabbedPane mainFrame;
    private final AppointmentDAO appointmentDAO;
    
    private List<Appointment> currentAppointments;
    private Appointment selectedAppointment;

    public AppointmentConfirmationController(AppointmentConfirmationView view, view.WithTabbedPane mainFrame) {
        this.view = view;
        this.mainFrame = mainFrame;
        this.appointmentDAO = new AppointmentDAO();
        
        initEventHandlers();
        loadPendingAppointments(""); // Load initially
    }

    private void initEventHandlers() {
        view.getTxtSearch().getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { search(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { search(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { search(); }
            private void search() {
                String keyword = view.getTxtSearch().getText();
                loadPendingAppointments(keyword);
            }
        });

        view.getTblAppointments().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.getTblAppointments().getSelectedRow();
                if (row >= 0 && row < currentAppointments.size()) {
                    selectedAppointment = currentAppointments.get(row);
                    updateDetailsPanel();
                }
            }
        });

        view.getBtnConfirmArrival().addActionListener(e -> handleConfirmArrival());
        view.getBtnCancelAppointment().addActionListener(e -> handleCancelAppointment());
    }

    public void loadPendingAppointments(String keyword) {
        currentAppointments = appointmentDAO.searchPendingAppointments(keyword);
        DefaultTableModel model = view.getTableModel();
        model.setRowCount(0);
        
        for (Appointment a : currentAppointments) {
            model.addRow(new Object[]{
                a.getAppointmentId(),
                a.getPatientId(),
                a.getPatientName() != null ? a.getPatientName() : "N/A",
                a.getDoctorName() != null ? a.getDoctorName() : "N/A",
                "Dept " + a.getDepartmentId(),
                a.getAppointmentDate(),
                a.getAppointmentTime() != null ? a.getAppointmentTime() : "N/A",
                a.getStatus()
            });
        }
        
        clearDetailsPanel();
    }

    private void updateDetailsPanel() {
        if (selectedAppointment == null) return;
        
        view.getLblPatientId().setText("Patient ID:  " + selectedAppointment.getPatientId());
        view.getLblFullName().setText("Full Name:   " + (selectedAppointment.getPatientName() != null ? selectedAppointment.getPatientName() : "N/A"));
        view.getLblDoctor().setText("Doctor:      " + (selectedAppointment.getDoctorName() != null ? selectedAppointment.getDoctorName() : "N/A"));
        
        String dateTimeStr = selectedAppointment.getAppointmentDate() + " " + (selectedAppointment.getAppointmentTime() != null ? selectedAppointment.getAppointmentTime() : "");
        view.getLblAppointmentDate().setText("Appointment: " + dateTimeStr);
        view.getLblStatus().setText("Status:      " + selectedAppointment.getStatus());
    }
    
    private void clearDetailsPanel() {
        selectedAppointment = null;
        view.getLblPatientId().setText("Patient ID:  --");
        view.getLblFullName().setText("Full Name:   --");
        view.getLblDoctor().setText("Doctor:      --");
        view.getLblAppointmentDate().setText("Appointment: --");
        view.getLblStatus().setText("Status:      --");
    }

    private void handleConfirmArrival() {
        if (selectedAppointment == null) {
            JOptionPane.showMessageDialog(view, "Please select an appointment from the table first.", "Selection Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(view, 
                "Are you sure you want to confirm arrival for this appointment?\nThis will assign a token and add the patient to the queue.", 
                "Confirm Arrival", JOptionPane.YES_NO_OPTION);
                
        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = appointmentDAO.confirmArrival(selectedAppointment.getAppointmentId());
            
            if (success) {
                dao.TokenDAO tokenDAO = new dao.TokenDAO();
                int tokenNum = tokenDAO.createToken(selectedAppointment.getAppointmentId(), selectedAppointment.getPatientId(), selectedAppointment.getDepartmentId(), selectedAppointment.getDoctorId());
                
                String docName = (selectedAppointment.getDoctorName() != null) ? selectedAppointment.getDoctorName() : "Any Available";
                String patName = (selectedAppointment.getPatientName() != null) ? selectedAppointment.getPatientName() : "Unknown Patient";
                String deptName = (selectedAppointment.getDepartmentName() != null) ? selectedAppointment.getDepartmentName() : "General";
                
                view.TokenPrintDialog printDialog = new view.TokenPrintDialog(
                    (JFrame) SwingUtilities.getWindowAncestor(view),
                    String.valueOf(tokenNum),
                    patName,
                    deptName,
                    docName
                );
                printDialog.setVisible(true);
                
                loadPendingAppointments(view.getTxtSearch().getText());
                
                // Refresh dashboards if applicable
                if (mainFrame.getDashboardController() != null) {
                    mainFrame.getDashboardController().refreshData();
                }
                if (mainFrame.getGenerateTokenController() != null) {
                    mainFrame.getGenerateTokenController().refreshLiveQueue();
                }
            } else {
                JOptionPane.showMessageDialog(view, "Error confirming arrival.", "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void handleCancelAppointment() {
        if (selectedAppointment == null) {
            JOptionPane.showMessageDialog(view, "Please select an appointment from the table first.", "Selection Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(view, 
                "Are you sure you want to cancel this appointment?", 
                "Cancel Appointment", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                
        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = appointmentDAO.cancelAppointment(
                selectedAppointment.getAppointmentId(),
                selectedAppointment.getPatientId()
            );
            
            if (success) {
                JOptionPane.showMessageDialog(view, "Appointment cancelled successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                loadPendingAppointments(view.getTxtSearch().getText());
            } else {
                JOptionPane.showMessageDialog(view, "Error cancelling appointment.", "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
