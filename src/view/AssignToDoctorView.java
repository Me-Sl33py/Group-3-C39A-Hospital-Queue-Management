package view;

import javax.swing.*;
import java.awt.*;

public class AssignToDoctorView extends javax.swing.JPanel {

    public AssignToDoctorView() {
        initComponents();
        btnAssignPatient.setOpaque(true);
        btnAssignPatient.setBorderPainted(false);
        initCustomComponents();
    }

    private javax.swing.JLabel lblBloodGroupLabel;
    private javax.swing.JLabel lblBloodGroupValue;

    private void initCustomComponents() {
        java.awt.GridBagConstraints gridBagConstraints;
        lblBloodGroupLabel = new javax.swing.JLabel();
        lblBloodGroupValue = new javax.swing.JLabel();
        
        lblDeptLabel = new javax.swing.JLabel();
        lblDeptValue = new javax.swing.JLabel();
        
        lblBloodGroupLabel.setFont(new java.awt.Font("Segoe UI", 1, 10));
        lblBloodGroupLabel.setForeground(new java.awt.Color(128, 128, 128));
        lblBloodGroupLabel.setText("BLOOD GROUP");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 2, 10);
        pnlPatientDetails.add(lblBloodGroupLabel, gridBagConstraints);

        lblBloodGroupValue.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lblBloodGroupValue.setText("Not Specified");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 15, 10);
        pnlPatientDetails.add(lblBloodGroupValue, gridBagConstraints);

        lblDeptLabel.setFont(new java.awt.Font("Segoe UI", 1, 10));
        lblDeptLabel.setForeground(new java.awt.Color(128, 128, 128));
        lblDeptLabel.setText("DEPARTMENT");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 2, 20);
        pnlPatientDetails.add(lblDeptLabel, gridBagConstraints);

        lblDeptValue.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lblDeptValue.setText("Unknown");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 15, 20);
        pnlPatientDetails.add(lblDeptValue, gridBagConstraints);

        java.awt.GridBagLayout layout = (java.awt.GridBagLayout) pnlPatientDetails.getLayout();
        gridBagConstraints = layout.getConstraints(lblReasonLabel);
        gridBagConstraints.gridy = 7;
        layout.setConstraints(lblReasonLabel, gridBagConstraints);

        gridBagConstraints = layout.getConstraints(spReason);
        gridBagConstraints.gridy = 8;
        layout.setConstraints(spReason, gridBagConstraints);
    }


    public JComboBox<String> getCbDoctors() { return cbDoctors; }
    public JButton getBtnAssignPatient() { return btnAssignPatient; }

    public JPanel getMainPanel() { return mainPanel; }
    public JLabel getLblTokenValue() { return lblTokenValue; }
    public JLabel getLblFNameValue() { return lblFNameValue; }
    public JLabel getLblPIDValue() { return lblPIDValue; }
    public JLabel getLblGenValue() { return lblGenValue; }
    public JLabel getLblContactValue() { return lblContactValue; }
    public JLabel getLblBloodGroupValue() { return lblBloodGroupValue; }
    public JLabel getLblDeptValue() { return lblDeptValue; }
    public JTextArea getTaReason() { return taReason; }
    public JPanel getPnlWLGrid() { return pnlWLGrid; }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        mainPanel = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        lblHeaderTitle = new javax.swing.JLabel();
        lblHeaderSubtitle = new javax.swing.JLabel();
        bodyScroll = new javax.swing.JScrollPane();
        bodyPanel = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        pnlToken = new javax.swing.JPanel();
        lblTokenText = new javax.swing.JLabel();
        lblTokenValue = new javax.swing.JLabel();
        lblDesc = new javax.swing.JLabel();
        pnlCards = new javax.swing.JPanel();
        pnlPatientDetails = new javax.swing.JPanel();
        lblPDTitle = new javax.swing.JLabel();
        lblPriority = new javax.swing.JLabel();
        lblFNameLabel = new javax.swing.JLabel();
        lblFNameValue = new javax.swing.JLabel();
        lblPIDLabel = new javax.swing.JLabel();
        lblPIDValue = new javax.swing.JLabel();
        lblGenLabel = new javax.swing.JLabel();
        lblGenValue = new javax.swing.JLabel();
        lblContactLabel = new javax.swing.JLabel();
        lblContactValue = new javax.swing.JLabel();
        lblReasonLabel = new javax.swing.JLabel();
        spReason = new javax.swing.JScrollPane();
        taReason = new javax.swing.JTextArea();
        pnlSelection = new javax.swing.JPanel();
        lblSelectionTitle = new javax.swing.JLabel();
        lblAssignToLabel = new javax.swing.JLabel();
        cbDoctors = new javax.swing.JComboBox();
        lblRecDoc = new javax.swing.JLabel();

        btnAssignPatient = new javax.swing.JButton();
        pnlWorkload = new javax.swing.JPanel();
        lblWLTitle = new javax.swing.JLabel();
        pnlWLGrid = new javax.swing.JPanel();
        pnlDoc1 = new javax.swing.JPanel();
        pnlD1Top = new javax.swing.JPanel();
        lblD1Name = new javax.swing.JLabel();
        lblD1Dept = new javax.swing.JLabel();
        pnlD1Bot = new javax.swing.JPanel();
        pbD1 = new javax.swing.JProgressBar();
        lblD1Stat = new javax.swing.JLabel();
        pnlDoc2 = new javax.swing.JPanel();
        pnlD2Top = new javax.swing.JPanel();
        lblD2Name = new javax.swing.JLabel();
        lblD2Dept = new javax.swing.JLabel();
        pnlD2Bot = new javax.swing.JPanel();
        pbD2 = new javax.swing.JProgressBar();
        lblD2Stat = new javax.swing.JLabel();
        pnlDoc3 = new javax.swing.JPanel();
        pnlD3Top = new javax.swing.JPanel();
        lblD3Name = new javax.swing.JLabel();
        lblD3Dept = new javax.swing.JLabel();
        pnlD3Bot = new javax.swing.JPanel();
        lblD3Stat = new javax.swing.JLabel();
        pnlDoc4 = new javax.swing.JPanel();
        pnlD4Top = new javax.swing.JPanel();
        lblD4Name = new javax.swing.JLabel();
        lblD4Dept = new javax.swing.JLabel();
        pnlD4Bot = new javax.swing.JPanel();
        pbD4 = new javax.swing.JProgressBar();
        lblD4Stat = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        mainPanel.setBackground(new java.awt.Color(249, 250, 251));
        mainPanel.setLayout(new java.awt.BorderLayout());

        headerPanel.setBackground(new java.awt.Color(255, 255, 255));
        headerPanel.setPreferredSize(new java.awt.Dimension(800, 70));
        headerPanel.setLayout(new java.awt.GridBagLayout());

        lblHeaderTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblHeaderTitle.setForeground(new java.awt.Color(80, 80, 80));
        lblHeaderTitle.setText("Reception Dashboard");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 5, 10);
        headerPanel.add(lblHeaderTitle, gridBagConstraints);

        lblHeaderSubtitle.setText("Sarah Jenkins - Senior Receptionist");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 10, 10);
        headerPanel.add(lblHeaderSubtitle, gridBagConstraints);

        mainPanel.add(headerPanel, java.awt.BorderLayout.NORTH);

        bodyPanel.setBackground(new java.awt.Color(249, 250, 251));
        bodyPanel.setLayout(new java.awt.GridBagLayout());

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        lblTitle.setText("<html>Assign to<br>Doctor</html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 5, 20);
        bodyPanel.add(lblTitle, gridBagConstraints);

        pnlToken.setBackground(new java.awt.Color(5, 150, 105));
        pnlToken.setLayout(new java.awt.GridBagLayout());

        lblTokenText.setForeground(new java.awt.Color(255, 255, 255));
        lblTokenText.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblTokenText.setText("CURRENT TOKEN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        pnlToken.add(lblTokenText, gridBagConstraints);

        lblTokenValue.setForeground(new java.awt.Color(255, 255, 255));
        lblTokenValue.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        lblTokenValue.setText("A-242");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        pnlToken.add(lblTokenValue, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 20);
        bodyPanel.add(pnlToken, gridBagConstraints);

        lblDesc.setForeground(new java.awt.Color(128, 128, 128));
        lblDesc.setText("Review patient information and allocate to an available specialist.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 20, 20);
        bodyPanel.add(lblDesc, gridBagConstraints);

        pnlCards.setBackground(new java.awt.Color(249, 250, 251));
        pnlCards.setLayout(new java.awt.GridBagLayout());

        pnlPatientDetails.setBackground(new java.awt.Color(255, 255, 255));
        pnlPatientDetails.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(230, 230, 230), 1, true));
        pnlPatientDetails.setLayout(new java.awt.GridBagLayout());

        lblPDTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblPDTitle.setText("Patient Details");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 10, 10);
        pnlPatientDetails.add(lblPDTitle, gridBagConstraints);

        lblPriority.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblPriority.setForeground(new java.awt.Color(5, 150, 105));
        lblPriority.setText("PRIORITY: NORMAL");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(20, 10, 10, 20);
        pnlPatientDetails.add(lblPriority, gridBagConstraints);

        lblFNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblFNameLabel.setForeground(new java.awt.Color(128, 128, 128));
        lblFNameLabel.setText("FULL NAME");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 2, 10);
        pnlPatientDetails.add(lblFNameLabel, gridBagConstraints);

        lblFNameValue.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblFNameValue.setText("Jonathan Miller");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 15, 10);
        pnlPatientDetails.add(lblFNameValue, gridBagConstraints);

        lblPIDLabel.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblPIDLabel.setForeground(new java.awt.Color(128, 128, 128));
        lblPIDLabel.setText("PATIENT ID");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 2, 20);
        pnlPatientDetails.add(lblPIDLabel, gridBagConstraints);

        lblPIDValue.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPIDValue.setText("HSP-992384-B");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 15, 20);
        pnlPatientDetails.add(lblPIDValue, gridBagConstraints);

        lblGenLabel.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblGenLabel.setForeground(new java.awt.Color(128, 128, 128));
        lblGenLabel.setText("GENDER / AGE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 2, 10);
        pnlPatientDetails.add(lblGenLabel, gridBagConstraints);

        lblGenValue.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblGenValue.setText("Male, 34 Years");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 15, 10);
        pnlPatientDetails.add(lblGenValue, gridBagConstraints);

        lblContactLabel.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblContactLabel.setForeground(new java.awt.Color(128, 128, 128));
        lblContactLabel.setText("CONTACT");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 2, 20);
        pnlPatientDetails.add(lblContactLabel, gridBagConstraints);

        lblContactValue.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblContactValue.setText("+1 (555) 012-3456");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 15, 20);
        pnlPatientDetails.add(lblContactValue, gridBagConstraints);

        lblReasonLabel.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblReasonLabel.setForeground(new java.awt.Color(128, 128, 128));
        lblReasonLabel.setText("REASON FOR VISIT");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 5, 20);
        pnlPatientDetails.add(lblReasonLabel, gridBagConstraints);

        spReason.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        taReason.setBackground(new java.awt.Color(249, 250, 251));
        taReason.setColumns(20);
        taReason.setFont(new java.awt.Font("Segoe UI", 2, 13)); // NOI18N
        taReason.setForeground(new java.awt.Color(80, 80, 80));
        taReason.setLineWrap(true);
        taReason.setRows(3);
        taReason.setText("“Patient reports persistent lower back pain for the last 3 days, radiating to the left leg. No history of recent injury. Rated pain as 6/10.”");
        taReason.setWrapStyleWord(true);
        taReason.setEditable(false);
        spReason.setViewportView(taReason);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 20, 20);
        pnlPatientDetails.add(spReason, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.7;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        pnlCards.add(pnlPatientDetails, gridBagConstraints);

        pnlSelection.setBackground(new java.awt.Color(255, 255, 255));
        pnlSelection.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(230, 230, 230), 1, true));
        pnlSelection.setLayout(new java.awt.GridBagLayout());

        lblSelectionTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblSelectionTitle.setForeground(new java.awt.Color(5, 150, 105));
        lblSelectionTitle.setText("Selection");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 20);
        pnlSelection.add(lblSelectionTitle, gridBagConstraints);

        lblAssignToLabel.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblAssignToLabel.setForeground(new java.awt.Color(128, 128, 128));
        lblAssignToLabel.setText("ASSIGN TO DOCTOR");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 5, 20);
        pnlSelection.add(lblAssignToLabel, gridBagConstraints);

        cbDoctors.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select an available doctor", "Dr. Marcus Chen", "Dr. E. Rodriguez", "Dr. K. Patel" }));
        cbDoctors.setPreferredSize(new java.awt.Dimension(200, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 5, 20);
        pnlSelection.add(cbDoctors, gridBagConstraints);

        lblRecDoc.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblRecDoc.setForeground(new java.awt.Color(128, 128, 128));
        lblRecDoc.setText("Recommended based on symptoms: Dr. Marcus Chen");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 20, 20);
        pnlSelection.add(lblRecDoc, gridBagConstraints);



        btnAssignPatient.setBackground(new java.awt.Color(5, 150, 105));
        btnAssignPatient.setForeground(new java.awt.Color(255, 255, 255));
        btnAssignPatient.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAssignPatient.setText("Assign Patient");
        btnAssignPatient.setFocusPainted(false);
        btnAssignPatient.setPreferredSize(new java.awt.Dimension(125, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 20, 20);
        pnlSelection.add(btnAssignPatient, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 0);
        pnlCards.add(pnlSelection, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.6;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 10, 20);
        bodyPanel.add(pnlCards, gridBagConstraints);

        pnlWorkload.setBackground(new java.awt.Color(255, 255, 255));
        pnlWorkload.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(230, 230, 230), 1, true));
        pnlWorkload.setLayout(new java.awt.BorderLayout());

        lblWLTitle.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblWLTitle.setText("Real-time Doctor Workload");
        lblWLTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 0, 20));
        pnlWorkload.add(lblWLTitle, java.awt.BorderLayout.NORTH);

        pnlWLGrid.setBackground(new java.awt.Color(255, 255, 255));
        pnlWLGrid.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 20, 20, 20));
        pnlWLGrid.setLayout(new java.awt.GridLayout(1, 4, 15, 0));

        pnlDoc1.setBackground(new java.awt.Color(249, 250, 251));
        pnlDoc1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlDoc1.setLayout(new java.awt.BorderLayout());

        pnlD1Top.setBackground(new java.awt.Color(249, 250, 251));
        pnlD1Top.setLayout(new java.awt.GridLayout(2, 1));

        lblD1Name.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblD1Name.setText("Dr. E. Rodriguez");
        pnlD1Top.add(lblD1Name);

        lblD1Dept.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblD1Dept.setForeground(new java.awt.Color(128, 128, 128));
        lblD1Dept.setText("Cardiology");
        pnlD1Top.add(lblD1Dept);

        pnlDoc1.add(pnlD1Top, java.awt.BorderLayout.NORTH);

        pnlD1Bot.setBackground(new java.awt.Color(249, 250, 251));
        pnlD1Bot.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 0, 0, 0));
        pnlD1Bot.setLayout(new java.awt.BorderLayout());

        pbD1.setForeground(new java.awt.Color(16, 185, 129));
        pbD1.setValue(30);
        pbD1.setPreferredSize(new java.awt.Dimension(100, 5));
        pnlD1Bot.add(pbD1, java.awt.BorderLayout.CENTER);

        lblD1Stat.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblD1Stat.setForeground(new java.awt.Color(16, 185, 129));
        lblD1Stat.setText("LOW");
        pnlD1Bot.add(lblD1Stat, java.awt.BorderLayout.EAST);

        pnlDoc1.add(pnlD1Bot, java.awt.BorderLayout.SOUTH);

        pnlWLGrid.add(pnlDoc1);

        pnlDoc2.setBackground(new java.awt.Color(249, 250, 251));
        pnlDoc2.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlDoc2.setLayout(new java.awt.BorderLayout());

        pnlD2Top.setBackground(new java.awt.Color(249, 250, 251));
        pnlD2Top.setLayout(new java.awt.GridLayout(2, 1));

        lblD2Name.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblD2Name.setText("Dr. M. Chen");
        pnlD2Top.add(lblD2Name);

        lblD2Dept.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblD2Dept.setForeground(new java.awt.Color(128, 128, 128));
        lblD2Dept.setText("Orthopedics");
        pnlD2Top.add(lblD2Dept);

        pnlDoc2.add(pnlD2Top, java.awt.BorderLayout.NORTH);

        pnlD2Bot.setBackground(new java.awt.Color(249, 250, 251));
        pnlD2Bot.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 0, 0, 0));
        pnlD2Bot.setLayout(new java.awt.BorderLayout());

        pbD2.setForeground(new java.awt.Color(245, 158, 11));
        pbD2.setValue(70);
        pbD2.setPreferredSize(new java.awt.Dimension(100, 5));
        pnlD2Bot.add(pbD2, java.awt.BorderLayout.CENTER);

        lblD2Stat.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblD2Stat.setForeground(new java.awt.Color(245, 158, 11));
        lblD2Stat.setText("MED");
        pnlD2Bot.add(lblD2Stat, java.awt.BorderLayout.EAST);

        pnlDoc2.add(pnlD2Bot, java.awt.BorderLayout.SOUTH);

        pnlWLGrid.add(pnlDoc2);

        pnlDoc3.setBackground(new java.awt.Color(249, 250, 251));
        pnlDoc3.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlDoc3.setLayout(new java.awt.BorderLayout());

        pnlD3Top.setBackground(new java.awt.Color(249, 250, 251));
        pnlD3Top.setLayout(new java.awt.GridLayout(2, 1));

        lblD3Name.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblD3Name.setText("Dr. S. Thompson");
        pnlD3Top.add(lblD3Name);

        lblD3Dept.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblD3Dept.setForeground(new java.awt.Color(128, 128, 128));
        lblD3Dept.setText("General Med");
        pnlD3Top.add(lblD3Dept);

        pnlDoc3.add(pnlD3Top, java.awt.BorderLayout.NORTH);

        pnlD3Bot.setBackground(new java.awt.Color(249, 250, 251));
        pnlD3Bot.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 0, 0, 0));
        pnlD3Bot.setLayout(new java.awt.BorderLayout());

        lblD3Stat.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblD3Stat.setForeground(new java.awt.Color(128, 128, 128));
        lblD3Stat.setText("ON BREAK");
        lblD3Stat.setBackground(new java.awt.Color(230, 230, 230));
        lblD3Stat.setOpaque(true);
        lblD3Stat.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 5, 2, 5));
        pnlD3Bot.add(lblD3Stat, java.awt.BorderLayout.WEST);

        pnlDoc3.add(pnlD3Bot, java.awt.BorderLayout.SOUTH);

        pnlWLGrid.add(pnlDoc3);

        pnlDoc4.setBackground(new java.awt.Color(249, 250, 251));
        pnlDoc4.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlDoc4.setLayout(new java.awt.BorderLayout());

        pnlD4Top.setBackground(new java.awt.Color(249, 250, 251));
        pnlD4Top.setLayout(new java.awt.GridLayout(2, 1));

        lblD4Name.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblD4Name.setText("Dr. K. Patel");
        pnlD4Top.add(lblD4Name);

        lblD4Dept.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblD4Dept.setForeground(new java.awt.Color(128, 128, 128));
        lblD4Dept.setText("Neurology");
        pnlD4Top.add(lblD4Dept);

        pnlDoc4.add(pnlD4Top, java.awt.BorderLayout.NORTH);

        pnlD4Bot.setBackground(new java.awt.Color(249, 250, 251));
        pnlD4Bot.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 0, 0, 0));
        pnlD4Bot.setLayout(new java.awt.BorderLayout());

        pbD4.setForeground(new java.awt.Color(16, 185, 129));
        pbD4.setValue(20);
        pbD4.setPreferredSize(new java.awt.Dimension(100, 5));
        pnlD4Bot.add(pbD4, java.awt.BorderLayout.CENTER);

        lblD4Stat.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblD4Stat.setForeground(new java.awt.Color(16, 185, 129));
        lblD4Stat.setText("LOW");
        pnlD4Bot.add(lblD4Stat, java.awt.BorderLayout.EAST);

        pnlDoc4.add(pnlD4Bot, java.awt.BorderLayout.SOUTH);

        pnlWLGrid.add(pnlDoc4);

        pnlWorkload.add(pnlWLGrid, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.4;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 20, 20);
        bodyPanel.add(pnlWorkload, gridBagConstraints);

        bodyScroll.setViewportView(bodyPanel);

        mainPanel.add(bodyScroll, java.awt.BorderLayout.CENTER);

        add(mainPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JScrollPane bodyScroll;
    private javax.swing.JButton btnAssignPatient;

    private javax.swing.JComboBox cbDoctors;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel lblAssignToLabel;
    private javax.swing.JLabel lblContactLabel;
    private javax.swing.JLabel lblContactValue;
    private javax.swing.JLabel lblDeptLabel;
    private javax.swing.JLabel lblDeptValue;
    private javax.swing.JLabel lblD1Dept;
    private javax.swing.JLabel lblD1Name;
    private javax.swing.JLabel lblD1Stat;
    private javax.swing.JLabel lblD2Dept;
    private javax.swing.JLabel lblD2Name;
    private javax.swing.JLabel lblD2Stat;
    private javax.swing.JLabel lblD3Dept;
    private javax.swing.JLabel lblD3Name;
    private javax.swing.JLabel lblD3Stat;
    private javax.swing.JLabel lblD4Dept;
    private javax.swing.JLabel lblD4Name;
    private javax.swing.JLabel lblD4Stat;
    private javax.swing.JLabel lblDesc;
    private javax.swing.JLabel lblFNameLabel;
    private javax.swing.JLabel lblFNameValue;
    private javax.swing.JLabel lblGenLabel;
    private javax.swing.JLabel lblGenValue;
    private javax.swing.JLabel lblHeaderSubtitle;
    private javax.swing.JLabel lblHeaderTitle;
    private javax.swing.JLabel lblPDTitle;
    private javax.swing.JLabel lblPIDLabel;
    private javax.swing.JLabel lblPIDValue;
    private javax.swing.JLabel lblPriority;
    private javax.swing.JLabel lblReasonLabel;
    private javax.swing.JLabel lblRecDoc;

    private javax.swing.JLabel lblSelectionTitle;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTokenText;
    private javax.swing.JLabel lblTokenValue;
    private javax.swing.JLabel lblWLTitle;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JProgressBar pbD1;
    private javax.swing.JProgressBar pbD2;
    private javax.swing.JProgressBar pbD4;
    private javax.swing.JPanel pnlCards;
    private javax.swing.JPanel pnlD1Bot;
    private javax.swing.JPanel pnlD1Top;
    private javax.swing.JPanel pnlD2Bot;
    private javax.swing.JPanel pnlD2Top;
    private javax.swing.JPanel pnlD3Bot;
    private javax.swing.JPanel pnlD3Top;
    private javax.swing.JPanel pnlD4Bot;
    private javax.swing.JPanel pnlD4Top;
    private javax.swing.JPanel pnlDoc1;
    private javax.swing.JPanel pnlDoc2;
    private javax.swing.JPanel pnlDoc3;
    private javax.swing.JPanel pnlDoc4;
    private javax.swing.JPanel pnlPatientDetails;

    private javax.swing.JPanel pnlSelection;
    private javax.swing.JPanel pnlToken;
    private javax.swing.JPanel pnlWLGrid;
    private javax.swing.JPanel pnlWorkload;
    private javax.swing.JScrollPane spReason;
    private javax.swing.JTextArea taReason;
    // End of variables declaration//GEN-END:variables
}
