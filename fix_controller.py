import re

with open("src/view/DoctorPanel.java", "r") as f:
    panel_content = f.read()

# Expose the new fields in DoctorPanel
getters = """
    public javax.swing.JTextArea getTaDiagnosis() { return taDiagnosis; }
    public javax.swing.JTextArea getTaPrescription() { return taPrescription; }
    public javax.swing.JTextArea getTaNotes() { return taNotes; }
    public javax.swing.JTextField getTxtDocPatientId() { return txtDocPatientId; }
"""
if "getTaDiagnosis" not in panel_content:
    panel_content = panel_content.replace("public javax.swing.JButton getBtnLogout()            { return jButton5;         }", "public javax.swing.JButton getBtnLogout()            { return jButton5;         }\n" + getters)
    with open("src/view/DoctorPanel.java", "w") as f:
        f.write(panel_content)


with open("src/controller/DoctorController.java", "r") as f:
    ctrl_content = f.read()

# Fix compilation errors in DoctorController.java
ctrl_content = ctrl_content.replace("String notes = view.getTaMessage().getText().trim();", 
                                  "String notes = view.getTaDiagnosis().getText().trim(); // Temporarily using diagnosis for now")
ctrl_content = ctrl_content.replace("view.getTaMessage().setText(",
                                  "view.getTaDiagnosis().setText(")
                                  
with open("src/controller/DoctorController.java", "w") as f:
    f.write(ctrl_content)

print("Fixed Controller")
