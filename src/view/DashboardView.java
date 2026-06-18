package view;

import javax.swing.*;
import java.awt.*;

/**
 * Hospicare Reception Dashboard View class.
 * This class is designed to be fully compatible with the NetBeans GUI Builder (Matisse).
 * Do NOT add business logic in this file; it is bound to the controller via getters.
 */
public class DashboardView extends javax.swing.JPanel {

    public DashboardView() {
        initComponents();
    }

    

    public JButton getBtnNewPatientReg() { return btnNewPatientReg; }
    public JButton getBtnGenEmergency() { return btnGenEmergency; }

    public JTable getTblWaitlist() { return tblWaitlist; }
    public JLabel getLblDate() { return lblDate; }
    public JLabel getLblTotalVal() { return lblTotalVal; }
    public JLabel getLblTokensVal() { return lblTokensVal; }
    public JLabel getLblWaitingVal() { return lblWaitingVal; }
    public JLabel getLblDoctorsVal() { return lblDoctorsVal; }

    public JPanel getMainPanel() { return mainPanel; }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        mainPanel = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        lblHeaderTitle = new javax.swing.JLabel();
        lblHeaderSubtitle = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        bodyScroll = new javax.swing.JScrollPane();
        bodyPanel = new javax.swing.JPanel();
        bannerPanel = new javax.swing.JPanel();
        lblGreeting = new javax.swing.JLabel();
        lblGreetingDesc = new javax.swing.JLabel();
        statsPanel = new javax.swing.JPanel();
        cardTotalPatients = new javax.swing.JPanel();
        lblTotalTitle = new javax.swing.JLabel();
        lblTotalVal = new javax.swing.JLabel();
        cardTokensGen = new javax.swing.JPanel();
        lblTokensTitle = new javax.swing.JLabel();
        lblTokensVal = new javax.swing.JLabel();
        cardWaiting = new javax.swing.JPanel();
        lblWaitingTitle = new javax.swing.JLabel();
        lblWaitingVal = new javax.swing.JLabel();
        cardDoctors = new javax.swing.JPanel();
        lblDoctorsTitle = new javax.swing.JLabel();
        lblDoctorsVal = new javax.swing.JLabel();
        bottomLeftPanel = new javax.swing.JPanel();
        tableHeaderArea = new javax.swing.JPanel();
        lblWaitlistTitle = new javax.swing.JLabel();
        tableScroll = new javax.swing.JScrollPane();
        tblWaitlist = new javax.swing.JTable();
        bottomRightPanel = new javax.swing.JPanel();
        quickActionsPanel = new javax.swing.JPanel();
        lblQuickActions = new javax.swing.JLabel();
        btnNewPatientReg = new javax.swing.JButton();
        btnGenEmergency = new javax.swing.JButton();



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

        lblHeaderSubtitle.setText("Reception");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 10, 10);
        headerPanel.add(lblHeaderSubtitle, gridBagConstraints);

        lblDate.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblDate.setText("Monday, Oct 23");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 20);
        headerPanel.add(lblDate, gridBagConstraints);

        mainPanel.add(headerPanel, java.awt.BorderLayout.NORTH);

        bodyPanel.setBackground(new java.awt.Color(249, 250, 251));
        bodyPanel.setLayout(new java.awt.GridBagLayout());

        bannerPanel.setBackground(new java.awt.Color(5, 150, 105));
        bannerPanel.setPreferredSize(new java.awt.Dimension(760, 90));
        bannerPanel.setLayout(new java.awt.GridBagLayout());

        lblGreeting.setForeground(new java.awt.Color(255, 255, 255));
        lblGreeting.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblGreeting.setText("Good morning, Sarah.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 20, 5, 20);
        bannerPanel.add(lblGreeting, gridBagConstraints);

        lblGreetingDesc.setForeground(new java.awt.Color(230, 230, 230));
        lblGreetingDesc.setText("The morning shift is picking up. You have 12 patients currently waiting and 4 doctors active on floor.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 15, 20);
        bannerPanel.add(lblGreetingDesc, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        bodyPanel.add(bannerPanel, gridBagConstraints);

        statsPanel.setOpaque(false);
        statsPanel.setLayout(new java.awt.GridLayout(1, 4, 15, 0));

        cardTotalPatients.setBackground(new java.awt.Color(255, 255, 255));
        cardTotalPatients.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(230, 230, 230), 1, true));
        cardTotalPatients.setLayout(new java.awt.GridBagLayout());

        lblTotalTitle.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblTotalTitle.setText("CONFIRMED TODAY");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 5, 15);
        cardTotalPatients.add(lblTotalTitle, gridBagConstraints);

        lblTotalVal.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTotalVal.setForeground(new java.awt.Color(80, 80, 80));
        lblTotalVal.setText("1,284");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 15, 15);
        cardTotalPatients.add(lblTotalVal, gridBagConstraints);

        statsPanel.add(cardTotalPatients);

        cardTokensGen.setBackground(new java.awt.Color(255, 255, 255));
        cardTokensGen.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(230, 230, 230), 1, true));
        cardTokensGen.setLayout(new java.awt.GridBagLayout());

        lblTokensTitle.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblTokensTitle.setText("IN CONSULTATION");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 5, 15);
        cardTokensGen.add(lblTokensTitle, gridBagConstraints);

        lblTokensVal.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTokensVal.setForeground(new java.awt.Color(80, 80, 80));
        lblTokensVal.setText("17");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 15, 15);
        cardTokensGen.add(lblTokensVal, gridBagConstraints);

        statsPanel.add(cardTokensGen);

        cardWaiting.setBackground(new java.awt.Color(5, 150, 105));
        cardWaiting.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(5, 150, 105), 1, true));
        cardWaiting.setLayout(new java.awt.GridBagLayout());

        lblWaitingTitle.setForeground(new java.awt.Color(230, 230, 230));
        lblWaitingTitle.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblWaitingTitle.setText("WAITING PATIENTS");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 5, 15);
        cardWaiting.add(lblWaitingTitle, gridBagConstraints);

        lblWaitingVal.setForeground(new java.awt.Color(255, 255, 255));
        lblWaitingVal.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblWaitingVal.setText("12");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 15, 15);
        cardWaiting.add(lblWaitingVal, gridBagConstraints);

        statsPanel.add(cardWaiting);

        cardDoctors.setBackground(new java.awt.Color(255, 255, 255));
        cardDoctors.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(230, 230, 230), 1, true));
        cardDoctors.setLayout(new java.awt.GridBagLayout());

        lblDoctorsTitle.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblDoctorsTitle.setText("DOCTORS AVAILABLE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 5, 15);
        cardDoctors.add(lblDoctorsTitle, gridBagConstraints);

        lblDoctorsVal.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblDoctorsVal.setForeground(new java.awt.Color(80, 80, 80));
        lblDoctorsVal.setText("08/10");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 15, 15);
        cardDoctors.add(lblDoctorsVal, gridBagConstraints);

        statsPanel.add(cardDoctors);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 15, 15);
        bodyPanel.add(statsPanel, gridBagConstraints);

        bottomLeftPanel.setBackground(new java.awt.Color(255, 255, 255));
        bottomLeftPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(230, 230, 230), 1, true));
        bottomLeftPanel.setLayout(new java.awt.BorderLayout());

        tableHeaderArea.setBackground(new java.awt.Color(255, 255, 255));
        tableHeaderArea.setPreferredSize(new java.awt.Dimension(400, 50));
        tableHeaderArea.setLayout(new java.awt.GridBagLayout());

        lblWaitlistTitle.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblWaitlistTitle.setForeground(new java.awt.Color(80, 80, 80));
        lblWaitlistTitle.setText("Active Waitlist");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 10);
        tableHeaderArea.add(lblWaitlistTitle, gridBagConstraints);

        bottomLeftPanel.add(tableHeaderArea, java.awt.BorderLayout.NORTH);

        tblWaitlist.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"#TK-402", "Robert Fox (Male, 45 yrs)", "Cardiology", "In Consultation", "[Call]"},
                {"#TK-405", "Esther Howard (Female, 28 yrs)", "Dermatology", "Waiting", "[Call]"},
                {"#TK-406", "Cameron Williamson (Male, 32 yrs)", "General Medicine", "Waiting", "[Call]"}
            },
            new String [] {
                "Token", "Patient Name", "Department", "Status", "Actions"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblWaitlist.setRowHeight(40);
        tblWaitlist.setShowGrid(false);
        tableScroll.setViewportView(tblWaitlist);

        bottomLeftPanel.add(tableScroll, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.7;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 15, 10);
        bodyPanel.add(bottomLeftPanel, gridBagConstraints);

        bottomRightPanel.setOpaque(false);
        bottomRightPanel.setLayout(new java.awt.GridBagLayout());

        quickActionsPanel.setBackground(new java.awt.Color(255, 255, 255));
        quickActionsPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(230, 230, 230), 1, true));
        quickActionsPanel.setLayout(new java.awt.GridBagLayout());

        lblQuickActions.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblQuickActions.setForeground(new java.awt.Color(80, 80, 80));
        lblQuickActions.setText("Quick Actions");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 10, 15);
        quickActionsPanel.add(lblQuickActions, gridBagConstraints);

        btnNewPatientReg.setForeground(new java.awt.Color(255, 255, 255));
        btnNewPatientReg.setBackground(new java.awt.Color(5, 150, 105));
        btnNewPatientReg.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnNewPatientReg.setText("New Patient Registration");
        btnNewPatientReg.setBorderPainted(false);
        btnNewPatientReg.setFocusPainted(false);
        btnNewPatientReg.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 15);
        quickActionsPanel.add(btnNewPatientReg, gridBagConstraints);

        btnGenEmergency.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnGenEmergency.setForeground(new java.awt.Color(80, 80, 80));
        btnGenEmergency.setText("Generate Emergency Token");
        btnGenEmergency.setFocusPainted(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 15, 15);
        quickActionsPanel.add(btnGenEmergency, gridBagConstraints);



        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        bottomRightPanel.add(quickActionsPanel, gridBagConstraints);



        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 15, 15);
        bodyPanel.add(bottomRightPanel, gridBagConstraints);

        bodyScroll.setViewportView(bodyPanel);

        mainPanel.add(bodyScroll, java.awt.BorderLayout.CENTER);

        add(mainPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnManageWaitlistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageWaitlistActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnManageWaitlistActionPerformed

    private void btnRegisterWalkinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterWalkinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegisterWalkinActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bannerPanel;
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JScrollPane bodyScroll;
    private javax.swing.JPanel bottomLeftPanel;
    private javax.swing.JPanel bottomRightPanel;

    private javax.swing.JButton btnGenEmergency;
    private javax.swing.JButton btnNewPatientReg;
    private javax.swing.JPanel cardDoctors;
    private javax.swing.JPanel cardTokensGen;
    private javax.swing.JPanel cardTotalPatients;
    private javax.swing.JPanel cardWaiting;

    private javax.swing.JPanel headerPanel;

    private javax.swing.JLabel lblDate;

    private javax.swing.JLabel lblDoctorsTitle;
    private javax.swing.JLabel lblDoctorsVal;
    private javax.swing.JLabel lblGreeting;
    private javax.swing.JLabel lblGreetingDesc;
    private javax.swing.JLabel lblHeaderSubtitle;
    private javax.swing.JLabel lblHeaderTitle;

    private javax.swing.JLabel lblQuickActions;
    private javax.swing.JLabel lblTokensTitle;
    private javax.swing.JLabel lblTokensVal;
    private javax.swing.JLabel lblTotalTitle;
    private javax.swing.JLabel lblTotalVal;
    private javax.swing.JLabel lblWaitingTitle;
    private javax.swing.JLabel lblWaitingVal;
    private javax.swing.JLabel lblWaitlistTitle;
    private javax.swing.JPanel mainPanel;

    private javax.swing.JPanel quickActionsPanel;
    private javax.swing.JPanel statsPanel;
    private javax.swing.JPanel tableHeaderArea;
    private javax.swing.JScrollPane tableScroll;
    private javax.swing.JTable tblWaitlist;
    // End of variables declaration//GEN-END:variables
}
