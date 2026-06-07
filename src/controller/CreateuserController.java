package controller;

import dao.UserDAO;
import view.CreateuserPanel;
import javax.swing.*;

public class CreateUserController {

    private CreateuserPanel panel;
    private UserDAO dao;
    private JFrame parentFrame;

    public CreateUserController(CreateuserPanel panel, JFrame parentFrame) {
        this.panel       = panel;
        this.dao         = new UserDAO();
        this.parentFrame = parentFrame;
        initListeners();
    }

    private void initListeners() {
        panel.getSaveButton().addActionListener(e -> saveUser());
        panel.getClearButton().addActionListener(e -> clearForm());
    }

    private void saveUser() {
        String fullName = panel.getNameField().getText().trim();
        String phone    = panel.getNameField1().getText().trim();
        String gender   = panel.getGenderCombobox().getSelectedItem().toString().trim();
        String email    = panel.getEmailField().getText().trim();
        String role     = panel.getRoleComboBox().getSelectedItem().toString().trim();
        String password = new String(panel.getPasswordField().getPassword()).trim();

        if (fullName.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(parentFrame,
                "Please fill in all fields.", "Validation Error",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean success = dao.createUser(fullName, phone, gender, email, role, password);

        if (success) {
            String message =
                "✅ User Created Successfully!\n" +
                "─────────────────────────────\n" +
                "Full Name : " + fullName  + "\n" +
                "Phone     : " + phone     + "\n" +
                "Gender    : " + gender    + "\n" +
                "Email     : " + email     + "\n" +
                "Role      : " + role      + "\n" +
                "Password  : " + password  + "\n" +
                "Status    : Active";
            JOptionPane.showMessageDialog(parentFrame, message, "User Created",
                JOptionPane.INFORMATION_MESSAGE);
            clearForm();
        } else {
            JOptionPane.showMessageDialog(parentFrame,
                "Failed to create user.\nEmail may already exist.",
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        panel.getNameField().setText("");
        panel.getNameField1().setText("");
        panel.getEmailField().setText("");
        panel.getPasswordField().setText("");
        panel.getGenderCombobox().setSelectedIndex(0);
        panel.getRoleComboBox().setSelectedIndex(0);
    }
}