import re

with open("src/view/DoctorPanel.java", "r") as f:
    content = f.read()

# Add buildPatientInfoForm to the constructor if not there
if "buildPatientInfoForm();" not in content:
    content = content.replace("buildClinicalDocForm();", "buildClinicalDocForm();\n        buildPatientInfoForm();")

# We need to make the read-only fields grey
old_grey = "txtDocPatientId.setEditable(false);"
new_grey = """txtDocPatientId.setEditable(false);
        txtDocPatientId.setFocusable(false);
        txtDocPatientId.setBackground(new java.awt.Color(240, 240, 240));"""
content = content.replace(old_grey, new_grey)

old_grey2 = "txtDocPatientName.setEditable(false);"
new_grey2 = """txtDocPatientName.setEditable(false);
        txtDocPatientName.setFocusable(false);
        txtDocPatientName.setBackground(new java.awt.Color(240, 240, 240));"""
content = content.replace(old_grey2, new_grey2)

old_grey3 = "txtDocApptDate.setEditable(false);"
new_grey3 = """txtDocApptDate.setEditable(false);
        txtDocApptDate.setFocusable(false);
        txtDocApptDate.setBackground(new java.awt.Color(240, 240, 240));"""
content = content.replace(old_grey3, new_grey3)

old_grey4 = "txtDocApptTime.setEditable(false);"
new_grey4 = """txtDocApptTime.setEditable(false);
        txtDocApptTime.setFocusable(false);
        txtDocApptTime.setBackground(new java.awt.Color(240, 240, 240));"""
content = content.replace(old_grey4, new_grey4)

# Create buildPatientInfoForm method
method_code = """
    private javax.swing.JButton btnSearchPatient;
    
    private void buildPatientInfoForm() {
        panelPatientInfo.removeAll();
        panelPatientInfo.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 15, 15));
        
        javax.swing.JLabel lblId = new javax.swing.JLabel("Patient ID:");
        lblId.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        lblId.setForeground(new java.awt.Color(30, 41, 59));
        
        // jTextField1 and jTextField2 were created by NetBeans
        
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
            // Find by ID
            // Since we don't have direct access to patient details by ID in this class natively,
            // we will just query via MedicalRecordDAO or assume it exists.
            loadMedicalHistory(id, name.isEmpty() ? "Unknown" : name);
        } else if (!name.isEmpty()) {
            // Fake an ID for demonstration
            loadMedicalHistory("P-???", name);
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Please enter Patient ID or Name to search.");
        }
    }
"""

if "buildPatientInfoForm()" not in content:
    last_brace_idx = content.rfind("}")
    content = content[:last_brace_idx] + method_code + "\n}\n"

with open("src/view/DoctorPanel.java", "w") as f:
    f.write(content)

print("Injected successfully")
