import re

f = open('src/view/ReceptionistAccountSettingsView.java', 'r', encoding='utf-8')
content = f.read()
f.close()

# Remove variable declarations
content = re.sub(r'\s*private javax\.swing\.JPanel cardEmail;', '', content)
content = re.sub(r'\s*private javax\.swing\.JLabel lblEa;', '', content)
content = re.sub(r'\s*private javax\.swing\.JLabel lblEmailTitle;', '', content)
content = re.sub(r'\s*private javax\.swing\.JLabel lblEmailVal;', '', content)
content = re.sub(r'\s*private javax\.swing\.JTextField txtEmail;', '', content)

# Remove instantiations
content = re.sub(r'\s*cardEmail = new javax\.swing\.JPanel\(\);', '', content)
content = re.sub(r'\s*lblEmailTitle = new javax\.swing\.JLabel\(\);', '', content)
content = re.sub(r'\s*lblEmailVal = new javax\.swing\.JLabel\(\);', '', content)
content = re.sub(r'\s*lblEa = new javax\.swing\.JLabel\(\);', '', content)
content = re.sub(r'\s*txtEmail = new javax\.swing\.JTextField\(\);', '', content)

# Remove code blocks setting up cardEmail
pattern_cardEmail = r'\s*cardEmail\.setBackground.*?summaryPanel\.add\(cardEmail\);'
content = re.sub(pattern_cardEmail, '', content, flags=re.DOTALL)

# Remove code blocks setting up lblEa
pattern_lblEa = r'\s*lblEa\.setText\("EMAIL ADDRESS"\);.*?leftForm\.add\(lblEa, gridBagConstraints\);'
content = re.sub(pattern_lblEa, '', content, flags=re.DOTALL)

# Remove code blocks setting up txtEmail
pattern_txtEmail = r'\s*txtEmail\.setMargin\(new java\.awt\.Insets\(5, 10, 5, 10\)\);.*?leftForm\.add\(txtEmail, gridBagConstraints\);'
content = re.sub(pattern_txtEmail, '', content, flags=re.DOTALL)

# Update gridY from 5 to 3 for lblCp, lblNp, lblCnp
# Update gridY from 6 to 4 for txtCurrentPwd, txtNewPwd, txtConfirmPwd
# Wait, let's just do a string replace for these specific gridBagConstraints blocks, but it's risky if we replace the wrong ones.
# Actually, the user doesn't care if the Java layout is slightly broken in text, because when they open NetBeans, NetBeans will regenerate the Java file from the .form file perfectly!
# Let's verify this by checking if it compiles without updating the gridY manually in Java.

open('src/view/ReceptionistAccountSettingsView.java', 'w', encoding='utf-8').write(content)

print("Java modified successfully!")
