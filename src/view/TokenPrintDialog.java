package view;

import javax.swing.JOptionPane;

public class TokenPrintDialog extends javax.swing.JDialog {

    public TokenPrintDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public TokenPrintDialog(java.awt.Frame parent, String tokenNumber, String patientName, String department, String doctor) {
        super(parent, true);
        initComponents();
        lblToken.setText(tokenNumber);
        lblPatient.setText("Patient: " + patientName);
        lblDepartment.setText("Dept: " + department);
        lblDoctor.setText("Doctor: " + doctor);
        this.setLocationRelativeTo(parent);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        lblToken = new javax.swing.JLabel();
        lblPatient = new javax.swing.JLabel();
        lblDepartment = new javax.swing.JLabel();
        lblDoctor = new javax.swing.JLabel();
        btnPrint = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Print Token");
        setPreferredSize(new java.awt.Dimension(350, 450));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("HOSPICARE");
        getContentPane().add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 350, 30));

        lblToken.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblToken.setForeground(new java.awt.Color(5, 150, 105));
        lblToken.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblToken.setText("1");
        getContentPane().add(lblToken, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 350, 60));

        lblPatient.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPatient.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPatient.setText("Patient: Name");
        getContentPane().add(lblPatient, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 350, 20));

        lblDepartment.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDepartment.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDepartment.setText("Dept: Department");
        getContentPane().add(lblDepartment, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 350, 20));

        lblDoctor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDoctor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDoctor.setText("Doctor: Doctor");
        getContentPane().add(lblDoctor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 350, 20));

        btnPrint.setText("Print Token");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });
        getContentPane().add(btnPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, 100, 30));

        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });
        getContentPane().add(btnClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 320, 80, 30));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        JOptionPane.showMessageDialog(this, "Sending to printer...", "Print", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }//GEN-LAST:event_btnPrintActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnPrint;
    private javax.swing.JLabel lblDepartment;
    private javax.swing.JLabel lblDoctor;
    private javax.swing.JLabel lblPatient;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblToken;
    // End of variables declaration//GEN-END:variables
}
