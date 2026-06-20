package view;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class AppointmentConfirmationView extends JPanel {
    private DefaultTableModel tableModel;

    public AppointmentConfirmationView() {
        initComponents();
        
        // Custom initialization for table model
        String[] columns = {"Appointment ID", "Patient ID", "Patient Name", "Doctor", "Department", "Date", "Time", "Status"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblAppointments.setModel(tableModel);
        tblAppointments.setRowHeight(30);
        tblAppointments.setFont(new Font("Tahoma", Font.PLAIN, 13));
        tblAppointments.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
        
        // Apply manual styling to restore the handwritten appearance while keeping Design tab functional
        txtSearch.putClientProperty("JTextField.placeholderText", "Enter Patient Name or ID...");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblDetailsTitle = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAppointments = new javax.swing.JTable();
        lblPatientId = new javax.swing.JLabel();
        lblFullName = new javax.swing.JLabel();
        lblDoctor = new javax.swing.JLabel();
        lblAppointmentDate = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        btnConfirmArrival = new javax.swing.JButton();
        btnCancelAppointment = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(null);

        lblDetailsTitle.setText("📅 Appointment Confirmation");
        lblDetailsTitle.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        add(lblDetailsTitle);
        lblDetailsTitle.setBounds(30, 30, 350, 30);
        add(txtSearch);
        txtSearch.setBounds(390, 30, 340, 30);

        tblAppointments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Appointment ID", "Patient ID", "Patient Name", "Doctor", "Department", "Date", "Time", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblAppointments);

        add(jScrollPane1);
        jScrollPane1.setBounds(30, 80, 1000, 450);

        lblPatientId.setText("Patient ID: --");
        lblPatientId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(lblPatientId);
        lblPatientId.setBounds(30, 550, 200, 17);

        lblFullName.setText("Full Name: --");
        lblFullName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(lblFullName);
        lblFullName.setBounds(30, 580, 200, 17);

        lblDoctor.setText("Doctor: --");
        lblDoctor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(lblDoctor);
        lblDoctor.setBounds(30, 610, 200, 17);

        lblAppointmentDate.setText("Appointment: --");
        lblAppointmentDate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(lblAppointmentDate);
        lblAppointmentDate.setBounds(250, 550, 200, 17);

        lblStatus.setText("Status: --");
        lblStatus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(lblStatus);
        lblStatus.setBounds(250, 580, 200, 17);

        btnConfirmArrival.setText("Confirm Arrival");
        btnConfirmArrival.setBackground(new java.awt.Color(34, 139, 34));
        btnConfirmArrival.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirmArrival.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add(btnConfirmArrival);
        btnConfirmArrival.setBounds(440, 560, 140, 40);

        btnCancelAppointment.setText("Cancel Appointment");
        btnCancelAppointment.setBackground(new java.awt.Color(220, 53, 69));
        btnCancelAppointment.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelAppointment.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add(btnCancelAppointment);
        btnCancelAppointment.setBounds(590, 560, 160, 40);
    }// </editor-fold>//GEN-END:initComponents

    // Getters for controller access
    public JTextField getTxtSearch() { return txtSearch; }
    public JTable getTblAppointments() { return tblAppointments; }
    public DefaultTableModel getTableModel() { return tableModel; }
    
    public JLabel getLblPatientId() { return lblPatientId; }
    public JLabel getLblFullName() { return lblFullName; }
    public JLabel getLblDoctor() { return lblDoctor; }
    public JLabel getLblAppointmentDate() { return lblAppointmentDate; }
    public JLabel getLblStatus() { return lblStatus; }
    
    public JButton getBtnConfirmArrival() { return btnConfirmArrival; }
    public JButton getBtnCancelAppointment() { return btnCancelAppointment; }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelAppointment;
    private javax.swing.JButton btnConfirmArrival;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAppointmentDate;
    private javax.swing.JLabel lblDetailsTitle;
    private javax.swing.JLabel lblDoctor;
    private javax.swing.JLabel lblFullName;
    private javax.swing.JLabel lblPatientId;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JTable tblAppointments;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
