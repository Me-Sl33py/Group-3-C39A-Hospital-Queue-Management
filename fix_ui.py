import re

with open("src/view/DoctorPanel.java", "r") as f:
    content = f.read()

# Make sure we don't duplicate
if "buildClinicalDocForm()" in content:
    print("Already injected")
    exit(0)

# Add the method call to the constructor
constructor_match = re.search(r"public DoctorPanel\(\) \{.*?(?=jTabbedPane1)", content, re.DOTALL)
if constructor_match:
    insertion_point = constructor_match.end()
    content = content[:insertion_point] + "        buildClinicalDocForm();\n        " + content[insertion_point:]

# Build the method
method_code = """
    private void buildClinicalDocForm() {
        panelDoc1.removeAll();
        panelDoc1.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(5, 5, 5, 5);
        gbc.anchor = java.awt.GridBagConstraints.WEST;

        // Title
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 4;
        javax.swing.JLabel title = new javax.swing.JLabel("Clinical Documentation");
        title.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        title.setForeground(new java.awt.Color(30, 41, 59));
        panelDoc1.add(title, gbc);

        // Separator
        gbc.gridy++;
        panelDoc1.add(new javax.swing.JSeparator(), gbc);

        // Row 1: Patient ID and Name
        gbc.gridy++; gbc.gridwidth = 1;
        
        gbc.gridx = 0;
        javax.swing.JLabel lblId = new javax.swing.JLabel("Patient ID:");
        lblId.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        panelDoc1.add(lblId, gbc);
        
        gbc.gridx = 1;
        txtDocPatientId = new javax.swing.JTextField(10);
        txtDocPatientId.setEditable(false);
        panelDoc1.add(txtDocPatientId, gbc);

        gbc.gridx = 2;
        javax.swing.JLabel lblName = new javax.swing.JLabel("Patient Name:");
        lblName.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        panelDoc1.add(lblName, gbc);

        gbc.gridx = 3;
        txtDocPatientName = new javax.swing.JTextField(20);
        txtDocPatientName.setEditable(false);
        panelDoc1.add(txtDocPatientName, gbc);

        // Row 2: Date and Time
        gbc.gridy++;
        
        gbc.gridx = 0;
        javax.swing.JLabel lblDate = new javax.swing.JLabel("Appointment Date:");
        lblDate.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        panelDoc1.add(lblDate, gbc);

        gbc.gridx = 1;
        txtDocApptDate = new javax.swing.JTextField(10);
        txtDocApptDate.setEditable(false);
        panelDoc1.add(txtDocApptDate, gbc);

        gbc.gridx = 2;
        javax.swing.JLabel lblTime = new javax.swing.JLabel("Appointment Time:");
        lblTime.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        panelDoc1.add(lblTime, gbc);

        gbc.gridx = 3;
        txtDocApptTime = new javax.swing.JTextField(10);
        txtDocApptTime.setEditable(false);
        panelDoc1.add(txtDocApptTime, gbc);

        // Text Areas
        gbc.gridwidth = 4; gbc.gridx = 0;
        
        // Diagnosis
        gbc.gridy++;
        javax.swing.JLabel lblDiag = new javax.swing.JLabel("Diagnosis:");
        lblDiag.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        panelDoc1.add(lblDiag, gbc);
        
        gbc.gridy++;
        taDiagnosis = new javax.swing.JTextArea(3, 20);
        panelDoc1.add(new javax.swing.JScrollPane(taDiagnosis), gbc);

        // Prescription
        gbc.gridy++;
        javax.swing.JLabel lblPresc = new javax.swing.JLabel("Prescription:");
        lblPresc.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        panelDoc1.add(lblPresc, gbc);
        
        gbc.gridy++;
        taPrescription = new javax.swing.JTextArea(3, 20);
        panelDoc1.add(new javax.swing.JScrollPane(taPrescription), gbc);

        // Notes
        gbc.gridy++;
        javax.swing.JLabel lblNotes = new javax.swing.JLabel("Notes (Optional):");
        lblNotes.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        panelDoc1.add(lblNotes, gbc);
        
        gbc.gridy++;
        taNotes = new javax.swing.JTextArea(3, 20);
        panelDoc1.add(new javax.swing.JScrollPane(taNotes), gbc);

        // Follow Up
        gbc.gridy++; gbc.gridwidth = 1;
        javax.swing.JLabel lblFollow = new javax.swing.JLabel("Follow Up Date:");
        lblFollow.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        panelDoc1.add(lblFollow, gbc);
        
        gbc.gridx = 1; gbc.gridwidth = 3;
        try {
            Class<?> dateChooserClass = Class.forName("com.toedter.calendar.JDateChooser");
            jDateChooserFollowUp = (com.toedter.calendar.JDateChooser) dateChooserClass.getDeclaredConstructor().newInstance();
            panelDoc1.add(jDateChooserFollowUp, gbc);
        } catch (Exception e) {
            System.err.println("JDateChooser not found on classpath at runtime. Using standard text field fallback.");
            panelDoc1.add(new javax.swing.JTextField("YYYY-MM-DD", 10), gbc);
        }

        // Separator
        gbc.gridy++; gbc.gridx = 0; gbc.gridwidth = 4;
        panelDoc1.add(new javax.swing.JSeparator(), gbc);

        // Buttons
        gbc.gridy++;
        javax.swing.JPanel btnPanel = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        btnCancel1 = new javax.swing.JButton("Cancel");
        btnSubmit1 = new javax.swing.JButton("Submit Record");
        btnSubmit1.setBackground(new java.awt.Color(0, 102, 255));
        btnSubmit1.setForeground(java.awt.Color.WHITE);
        btnPanel.add(btnCancel1);
        btnPanel.add(btnSubmit1);
        panelDoc1.add(btnPanel, gbc);
        
        panelDoc1.revalidate();
        panelDoc1.repaint();
    }
"""

last_brace_idx = content.rfind("}")
content = content[:last_brace_idx] + method_code + "\n}\n"

with open("src/view/DoctorPanel.java", "w") as f:
    f.write(content)

print("Injected successfully")
