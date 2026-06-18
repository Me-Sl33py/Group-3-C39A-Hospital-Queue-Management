package view;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class AppointmentConfirmationView extends JPanel {
    private JTextField txtSearch;
    private JButton btnSearch;
    private JTable tblAppointments;
    private DefaultTableModel tableModel;
    
    // Details panel components
    private JLabel lblDetailsTitle;
    private JLabel lblPatientId;
    private JLabel lblFullName;
    private JLabel lblDoctor;
    private JLabel lblAppointmentDate;
    private JLabel lblStatus;
    
    private JButton btnConfirmArrival;
    private JButton btnCancelAppointment;

    public AppointmentConfirmationView() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(20, 20));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Top Search Panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        searchPanel.setBackground(Color.WHITE);
        
        JLabel lblTitle = new JLabel("📅 Appointment Confirmation");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblTitle.setForeground(new Color(51, 51, 51));
        
        txtSearch = new JTextField(25);
        txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtSearch.setPreferredSize(new Dimension(300, 35));
        txtSearch.putClientProperty("JTextField.placeholderText", "Enter Patient Name or ID...");
        
        btnSearch = new JButton("Search Patient");
        btnSearch.setBackground(new Color(30, 100, 180));
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnSearch.setPreferredSize(new Dimension(150, 35));
        
        searchPanel.add(lblTitle);
        searchPanel.add(Box.createHorizontalStrut(50));
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);

        // Center Table Panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setBorder(BorderFactory.createTitledBorder("Pending Appointments"));
        
        String[] columns = {"Appointment ID", "Patient ID", "Patient Name", "Doctor", "Department", "Date", "Time", "Status"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblAppointments = new JTable(tableModel);
        tblAppointments.setRowHeight(30);
        tblAppointments.setFont(new Font("Tahoma", Font.PLAIN, 13));
        tblAppointments.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
        
        JScrollPane scrollPane = new JScrollPane(tblAppointments);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Bottom Details Panel
        JPanel bottomPanel = new JPanel(new BorderLayout(20, 20));
        bottomPanel.setBackground(Color.WHITE);
        
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(new Color(245, 245, 245));
        detailsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        
        lblDetailsTitle = new JLabel("Selected Patient Details");
        lblDetailsTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblDetailsTitle.setForeground(new Color(30, 100, 180));
        
        lblPatientId = new JLabel("Patient ID:  -- ");
        lblFullName = new JLabel("Full Name:   -- ");
        lblDoctor = new JLabel("Doctor:      -- ");
        lblAppointmentDate = new JLabel("Appointment: -- ");
        lblStatus = new JLabel("Status:      -- ");
        
        Font detailsFont = new Font("Tahoma", Font.PLAIN, 14);
        lblPatientId.setFont(detailsFont);
        lblFullName.setFont(detailsFont);
        lblDoctor.setFont(detailsFont);
        lblAppointmentDate.setFont(detailsFont);
        lblStatus.setFont(detailsFont);
        
        detailsPanel.add(lblDetailsTitle);
        detailsPanel.add(Box.createVerticalStrut(10));
        detailsPanel.add(lblPatientId);
        detailsPanel.add(Box.createVerticalStrut(5));
        detailsPanel.add(lblFullName);
        detailsPanel.add(Box.createVerticalStrut(5));
        detailsPanel.add(lblDoctor);
        detailsPanel.add(Box.createVerticalStrut(5));
        detailsPanel.add(lblAppointmentDate);
        detailsPanel.add(Box.createVerticalStrut(5));
        detailsPanel.add(lblStatus);
        
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        actionPanel.setBackground(Color.WHITE);
        
        btnConfirmArrival = new JButton("Confirm Arrival");
        btnConfirmArrival.setBackground(new Color(34, 139, 34)); // Green
        btnConfirmArrival.setForeground(Color.WHITE);
        btnConfirmArrival.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnConfirmArrival.setPreferredSize(new Dimension(180, 40));
        
        btnCancelAppointment = new JButton("Cancel Appointment");
        btnCancelAppointment.setBackground(new Color(220, 53, 69)); // Red
        btnCancelAppointment.setForeground(Color.WHITE);
        btnCancelAppointment.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnCancelAppointment.setPreferredSize(new Dimension(180, 40));
        
        actionPanel.add(btnConfirmArrival);
        actionPanel.add(btnCancelAppointment);
        
        bottomPanel.add(detailsPanel, BorderLayout.CENTER);
        bottomPanel.add(actionPanel, BorderLayout.SOUTH);

        add(searchPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Getters for controller access
    public JTextField getTxtSearch() { return txtSearch; }
    public JButton getBtnSearch() { return btnSearch; }
    public JTable getTblAppointments() { return tblAppointments; }
    public DefaultTableModel getTableModel() { return tableModel; }
    
    public JLabel getLblPatientId() { return lblPatientId; }
    public JLabel getLblFullName() { return lblFullName; }
    public JLabel getLblDoctor() { return lblDoctor; }
    public JLabel getLblAppointmentDate() { return lblAppointmentDate; }
    public JLabel getLblStatus() { return lblStatus; }
    
    public JButton getBtnConfirmArrival() { return btnConfirmArrival; }
    public JButton getBtnCancelAppointment() { return btnCancelAppointment; }
}
