import re

with open("src/view/DoctorPanel.java", "r") as f:
    content = f.read()

# 1. Fix text entry box size in buildPatientInfoForm
old_btnSearch = """        btnSearchPatient = new javax.swing.JButton("Search");"""
new_btnSearch = """        jTextField1.setPreferredSize(new java.awt.Dimension(150, 30));
        jTextField2.setPreferredSize(new java.awt.Dimension(250, 30));
        
        btnSearchPatient = new javax.swing.JButton("Search");"""
content = content.replace(old_btnSearch, new_btnSearch)

# 2. Change title to Add Medical Record and align left
old_title = """        javax.swing.JLabel title = new javax.swing.JLabel("Clinical Documentation");
        title.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        title.setForeground(new java.awt.Color(30, 41, 59));
        panelDoc1.add(title, gbc);"""

new_title = """        javax.swing.JLabel title = new javax.swing.JLabel("Add Medical Record");
        title.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        title.setForeground(new java.awt.Color(30, 41, 59));
        
        // Add weightx to force left alignment of the whole GridBag block
        gbc.weightx = 1.0;
        panelDoc1.add(title, gbc);
        gbc.weightx = 0.0;"""
content = content.replace(old_title, new_title)

# 3. Remove Follow Up Date
old_followup = """        // Follow Up
        gbc.gridy++; gbc.gridwidth = 1;
        javax.swing.JLabel lblFollow = new javax.swing.JLabel("Follow Up Date:");
        lblFollow.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        panelDoc1.add(lblFollow, gbc);
        
        gbc.gridx = 1; gbc.gridwidth = 3;
        try {
            Class<?> dateChooserClass = Class.forName("com.toedter.calendar.JDateChooser");
            jDateChooserFollowUp = (javax.swing.JComponent) dateChooserClass.getDeclaredConstructor().newInstance();
            panelDoc1.add(jDateChooserFollowUp, gbc);
        } catch (Exception e) {
            System.err.println("JDateChooser not found on classpath at runtime. Using standard text field fallback.");
            panelDoc1.add(new javax.swing.JTextField("YYYY-MM-DD", 10), gbc);
        }"""
new_followup = """        // Follow Up date removed as requested"""
content = content.replace(old_followup, new_followup)

# 4. Add pop up if patient doesn't exist
old_action = """    private void btnSearchPatientActionPerformed(java.awt.event.ActionEvent evt) {
        String id = jTextField1.getText().trim();
        String name = jTextField2.getText().trim();
        
        if (!id.isEmpty()) {
            loadMedicalHistory(id, name.isEmpty() ? "Unknown" : name);
        } else if (!name.isEmpty()) {
            loadMedicalHistory("P-???", name);
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Please enter Patient ID or Name to search.");
        }
    }"""
new_action = """    private void btnSearchPatientActionPerformed(java.awt.event.ActionEvent evt) {
        String id = jTextField1.getText().trim();
        String name = jTextField2.getText().trim();
        
        if (!name.isEmpty() && id.isEmpty()) {
            dao.PatientDao pDao = new dao.PatientDao();
            java.util.List<model.Patient> all = pDao.getAllPatients();
            model.Patient found = null;
            for (model.Patient p : all) {
                if (p.getFullName().equalsIgnoreCase(name)) {
                    found = p; break;
                }
            }
            if (found == null) {
                javax.swing.JOptionPane.showMessageDialog(this, "Patient doesn't exist!");
                return;
            }
            id = found.getPatientId();
            name = found.getFullName();
            jTextField1.setText(id);
        } else if (!id.isEmpty()) {
            dao.PatientDao pDao = new dao.PatientDao();
            model.Patient p = pDao.getPatientById(id);
            if (p == null) {
                javax.swing.JOptionPane.showMessageDialog(this, "Patient doesn't exist!");
                return;
            }
            name = p.getFullName();
            jTextField2.setText(name);
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Please enter Patient ID or Name to search.");
            return;
        }
        loadMedicalHistory(id, name);
    }"""
content = content.replace(old_action, new_action)

with open("src/view/DoctorPanel.java", "w") as f:
    f.write(content)

print("UI fixes applied successfully")
