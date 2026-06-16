import re

with open("src/view/DoctorPanel.java", "r") as f:
    content = f.read()

# Fix the import error by using JComponent
content = content.replace("private com.toedter.calendar.JDateChooser jDateChooserFollowUp;",
                          "private javax.swing.JComponent jDateChooserFollowUp;")

method_code = """
    private javax.swing.JButton btnSearchPatient;
    
    private void buildPatientInfoForm() {
        panelPatientInfo.removeAll();
        panelPatientInfo.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 15, 15));
        
        javax.swing.JLabel lblId = new javax.swing.JLabel("Patient ID:");
        lblId.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        lblId.setForeground(new java.awt.Color(30, 41, 59));
        
        javax.swing.JLabel lblName = new javax.swing.JLabel("Patient Name:");
        lblName.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        lblName.setForeground(new java.awt.Color(30, 41, 59));
        
        btnSearchPatient = new javax.swing.JButton("Search");
        btnSearchPatient.setBackground(new java.awt.Color(0, 102, 255));
        btnSearchPatient.setForeground(java.awt.Color.WHITE);
        btnSearchPatient.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        btnSearchPatient.addActionListener(this::btnSearchPatientActionPerformed);
        
        panelPatientInfo.add(lblId);
        panelPatientInfo.add(jTextField1);
        panelPatientInfo.add(lblName);
        panelPatientInfo.add(jTextField2);
        panelPatientInfo.add(btnSearchPatient);
        
        panelPatientInfo.revalidate();
        panelPatientInfo.repaint();
    }
    
    private void btnSearchPatientActionPerformed(java.awt.event.ActionEvent evt) {
        String id = jTextField1.getText().trim();
        String name = jTextField2.getText().trim();
        
        if (!id.isEmpty()) {
            loadMedicalHistory(id, name.isEmpty() ? "Unknown" : name);
        } else if (!name.isEmpty()) {
            loadMedicalHistory("P-???", name);
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Please enter Patient ID or Name to search.");
        }
    }
"""

if "private void buildPatientInfoForm()" not in content:
    last_brace_idx = content.rfind("}")
    content = content[:last_brace_idx] + method_code + "\n}\n"

with open("src/view/DoctorPanel.java", "w") as f:
    f.write(content)

print("Patched DoctorPanel.java methods and types")
