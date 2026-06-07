import re

# Update .form file
f = open('src/view/ReceptionistAccountSettingsView.form', 'r', encoding='utf-8')
content = f.read()
f.close()

# Remove cardEmail
content = re.sub(r'<Container class="javax\.swing\.JPanel" name="cardEmail".*?</Container>\s*(?=<Container class="javax\.swing\.JPanel" name="cardJoinDate")', '', content, flags=re.DOTALL)

# Remove lblEa
content = re.sub(r'<Component class="javax\.swing\.JLabel" name="lblEa".*?</Component>\s*', '', content, flags=re.DOTALL)

# Remove txtEmail
content = re.sub(r'<Component class="javax\.swing\.JTextField" name="txtEmail".*?</Component>\s*', '', content, flags=re.DOTALL)

# Update gridY for lblCp, lblNp, lblCnp from 5 to 3
content = re.sub(r'(<Component class="javax\.swing\.JLabel" name="lblCp".*?<GridBagConstraints gridX="0" gridY=")5(")', r'\g<1>3\g<2>', content, flags=re.DOTALL)
content = re.sub(r'(<Component class="javax\.swing\.JLabel" name="lblNp".*?<GridBagConstraints gridX="1" gridY=")5(")', r'\g<1>3\g<2>', content, flags=re.DOTALL)
content = re.sub(r'(<Component class="javax\.swing\.JLabel" name="lblCnp".*?<GridBagConstraints gridX="2" gridY=")5(")', r'\g<1>3\g<2>', content, flags=re.DOTALL)

# Update gridY for txtCurrentPwd, txtNewPwd, txtConfirmPwd from 6 to 4
content = re.sub(r'(<Component class="javax\.swing\.JPasswordField" name="txtCurrentPwd".*?<GridBagConstraints gridX="0" gridY=")6(")', r'\g<1>4\g<2>', content, flags=re.DOTALL)
content = re.sub(r'(<Component class="javax\.swing\.JPasswordField" name="txtNewPwd".*?<GridBagConstraints gridX="1" gridY=")6(")', r'\g<1>4\g<2>', content, flags=re.DOTALL)
content = re.sub(r'(<Component class="javax\.swing\.JPasswordField" name="txtConfirmPwd".*?<GridBagConstraints gridX="2" gridY=")6(")', r'\g<1>4\g<2>', content, flags=re.DOTALL)

open('src/view/ReceptionistAccountSettingsView.form', 'w', encoding='utf-8').write(content)

print("Form modified successfully!")
