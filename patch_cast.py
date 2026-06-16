import re

with open("src/view/DoctorPanel.java", "r") as f:
    content = f.read()

content = content.replace("jDateChooserFollowUp = (com.toedter.calendar.JDateChooser) dateChooserClass.getDeclaredConstructor().newInstance();",
                          "jDateChooserFollowUp = (javax.swing.JComponent) dateChooserClass.getDeclaredConstructor().newInstance();")

with open("src/view/DoctorPanel.java", "w") as f:
    f.write(content)
print("Patched cast")
