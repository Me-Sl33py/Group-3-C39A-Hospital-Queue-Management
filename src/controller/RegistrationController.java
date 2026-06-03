package controller;

import dao.PatientDao;
import view.SignUp;
import view.UserLogin;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * RegistrationController — handles all button clicks and business logic
 * for the SignUp (registration) screen.
 *
 * Responsibilities:
 *   - Gets input values from the SignUp view using its getter methods
 *   - Validates all fields (empty check, password match, digits, etc.)
 *   - Calls PatientDao to insert into 'users' then 'patients' tables
 *   - Shows success or error messages to the user
 *   - Navigates to UserLogin frame after success
 *
 * Architecture Rule: No SQL in this class. No UI components created here.
 *   Controller only reads from View and writes through DAO.
 *
 * @author Group 3 C39A
 */
public class RegistrationController {

    // Reference to the SignUp view — used to call getter methods
    private SignUp view;

    // Reference to the PatientDao — used to perform all DB operations
    private PatientDao patientDao;

    // ==================== Theme Colors ====================
    // (Moved from SignUp view — all styling logic belongs in the controller)
    private static final Color PRIMARY   = new Color(21, 101, 192);   // deep blue
    private static final Color FIELD_BG  = new Color(248, 249, 250);  // light grey input bg
    private static final Color DARK_TEXT = new Color(33, 33, 33);     // near-black
    private static final Color GRAY_TEXT = new Color(120, 120, 120);  // muted label color
    private static final Color BORDER    = new Color(205, 210, 218);  // input border color

    // ==================== Constructor ====================

    /**
     * Constructor — connects the controller to the view and sets up button listeners.
     *
     * @param view the SignUp JFrame that this controller manages
     */
    public RegistrationController(SignUp view) {
        this.view = view;
        this.patientDao = new PatientDao();

        // Set up styles (borders, colours, images) — moved from SignUp view
        setupStyles();

        // Set up placeholder hint text in all fields — moved from SignUp view
        setupPlaceholders();

        // Set up auto-capitalize on full name and location fields — moved from SignUp view
        setupAutoCapitalize();

        // Attach listeners to buttons on the view
        // (Removed duplicate listeners: The View's auto-generated action stubs already call handleRegister(), handleBack(), and handleLoginLink())

        // Eye icon (show/hide) buttons for password fields
        this.view.getShowPasswordLabel().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                togglePasswordVisibility();  // Toggle password field
            }
        });

        this.view.getShowConfirmPasswordLabel().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                toggleConfirmPasswordVisibility();  // Toggle confirm password field
            }
        });

        this.view.getHidePasswordLabel().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                togglePasswordVisibility();  // Same toggle
            }
        });

        this.view.getHideConfirmPasswordLabel().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                toggleConfirmPasswordVisibility();  // Same toggle
            }
        });
    }

    // ==================== Style, Placeholder & AutoCapitalize Methods ====================
    // (These were moved here from SignUp view to keep all logic in the controller)

    /**
     * Applies visual styles to the SignUp form fields and buttons.
     * Sets borders, background colors, hand cursors, loads images and eye icons.
     * Was originally applyStyles() in SignUp view.
     */
    private void setupStyles() {
        // Thin border + inner padding for all text fields
        javax.swing.border.Border fieldBorder = BorderFactory.createCompoundBorder(
                new javax.swing.border.LineBorder(BORDER, 1),
                BorderFactory.createEmptyBorder(2, 8, 2, 8)
        );
        view.getFullNameField().setBorder(fieldBorder);         view.getFullNameField().setBackground(FIELD_BG);
        view.getDobField().setBorder(fieldBorder);              view.getDobField().setBackground(FIELD_BG);
        view.getPhoneField().setBorder(fieldBorder);            view.getPhoneField().setBackground(FIELD_BG);
        view.getLocationField().setBorder(fieldBorder);         view.getLocationField().setBackground(FIELD_BG);
        view.getPasswordField().setBorder(fieldBorder);         view.getPasswordField().setBackground(FIELD_BG);
        view.getConfirmPasswordField().setBorder(fieldBorder);  view.getConfirmPasswordField().setBackground(FIELD_BG);

        // Back button — outlined style (blue border on white background)
        view.getBackButton().setBorder(new javax.swing.border.LineBorder(PRIMARY, 1));

        // Hand cursor for all clickable elements
        Cursor hand = new Cursor(Cursor.HAND_CURSOR);
        view.getSignUpButton().setCursor(hand);
        view.getBackButton().setCursor(hand);
        view.getLoginLinkButton().setCursor(hand);
        view.getShowPasswordLabel().setCursor(hand);
        view.getHidePasswordLabel().setCursor(hand);
        view.getShowConfirmPasswordLabel().setCursor(hand);
        view.getHideConfirmPasswordLabel().setCursor(hand);

        // Load eye icons (show = open eye, hide = closed eye)
        ImageIcon showIcon = safeLoadIcon("/images/show password.png");
        ImageIcon hideIcon = safeLoadIcon("/images/hide password.png");
        view.getShowPasswordLabel().setIcon(showIcon);
        view.getHidePasswordLabel().setIcon(hideIcon);
        view.getShowConfirmPasswordLabel().setIcon(showIcon);
        view.getHideConfirmPasswordLabel().setIcon(hideIcon);
    }

    /**
     * Sets grey placeholder hint text in all SignUp text fields.
     * When user clicks a field the placeholder disappears.
     * When user leaves the field empty the placeholder reappears.
     * Was originally addPlaceholders() / attachPlaceholder() in SignUp view.
     */
    private void setupPlaceholders() {
        // NOTE: dobField is now a JDateChooser — it manages its own display.
        //       We do NOT attach a placeholder to it.
        attachPlaceholder(view.getFullNameField(),        "Enter full name");
        attachPlaceholder(view.getPhoneField(),           "Enter phone number");
        attachPlaceholder(view.getLocationField(),        "Enter location / address");
        attachPlaceholder(view.getPasswordField(),        "Create password");
        attachPlaceholder(view.getConfirmPasswordField(), "Confirm password");
    }

    /**
     * Helper: attaches focus-driven placeholder text to one JTextField.
     * Works with both JTextField and JPasswordField.
     */
    private void attachPlaceholder(javax.swing.JTextField field, String placeholder) {
        field.setText(placeholder);
        field.setForeground(GRAY_TEXT);
        if (field instanceof javax.swing.JPasswordField) {
            ((javax.swing.JPasswordField) field).setEchoChar((char) 0); // show plain text for placeholder
        }
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                // Clear placeholder when user clicks in
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(DARK_TEXT);
                    if (field instanceof javax.swing.JPasswordField) {
                        ((javax.swing.JPasswordField) field).setEchoChar('●'); // mask real input
                    }
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                // Restore placeholder when user leaves field empty
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(GRAY_TEXT);
                    if (field instanceof javax.swing.JPasswordField) {
                        ((javax.swing.JPasswordField) field).setEchoChar((char) 0);
                    }
                }
            }
        });
    }

    /**
     * Adds focus listeners to fullName and location fields so that when the user
     * clicks away, the text is automatically capitalized (e.g. "john doe" → "John Doe").
     * Was originally addAutoCapitalizeListeners() in SignUp view.
     */
    private void setupAutoCapitalize() {
        view.getFullNameField().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String text = view.getFullNameField().getText();
                // Only capitalize if it is not empty and not the placeholder
                if (!text.isEmpty() && !text.equals("Enter full name")) {
                    view.getFullNameField().setText(capitalizeWords(text));
                }
            }
        });

        view.getLocationField().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String text = view.getLocationField().getText();
                // Only capitalize if it is not empty and not the placeholder
                if (!text.isEmpty() && !text.equals("Enter location / address")) {
                    view.getLocationField().setText(capitalizeWords(text));
                }
            }
        });
    }

    /**
     * Safely loads an ImageIcon from a resource path.
     * Returns null (no crash) if the image file is missing.
     * Was originally safeLoadIcon() in SignUp view.
     *
     * @param path the resource path (e.g. "/images/show password.png")
     * @return the ImageIcon, or null if not found
     */
    private ImageIcon safeLoadIcon(String path) {
        try {
            java.net.URL url = getClass().getResource(path);
            if (url != null) return new ImageIcon(url);
        } catch (Exception ignored) {}
        return null;
    }

    // ==================== Eye Icon Toggle Methods ====================

    /**
     * Toggles visibility of the password field.
     * If it's hidden (shows dots •••), make it visible (show real text).
     * If it's visible (shows text), hide it again (show dots •••).
     *
     * We use JTextField's setEchoChar() method.
     * EchoChar = 0     → visible (no masking character)
     * EchoChar = '●'   → hidden (shows ● for each character typed)
     */
    public void togglePasswordVisibility() {
        javax.swing.JTextField passField = view.getPasswordField();

        // Check the current echo character
        if (passField instanceof javax.swing.JPasswordField) {
            javax.swing.JPasswordField pf = (javax.swing.JPasswordField) passField;
            if (pf.getEchoChar() == 0) {
                // Currently visible — hide it
                pf.setEchoChar('●');
            } else {
                // Currently hidden — show it
                pf.setEchoChar((char) 0);
            }
        } else {
            // It's a plain JTextField — no echo char to toggle
            // The view uses plain JTextFields so we simulate by changing foreground
            // (This branch applies if the form uses JTextField not JPasswordField)
            System.out.println("[RegistrationController] Password field is a plain JTextField — toggle not needed.");
        }
    }

    /**
     * Toggles visibility of the confirm password field.
     * Same logic as togglePasswordVisibility().
     */
    public void toggleConfirmPasswordVisibility() {
        javax.swing.JTextField confirmField = view.getConfirmPasswordField();

        if (confirmField instanceof javax.swing.JPasswordField) {
            javax.swing.JPasswordField pf = (javax.swing.JPasswordField) confirmField;
            if (pf.getEchoChar() == 0) {
                pf.setEchoChar('●');
            } else {
                pf.setEchoChar((char) 0);
            }
        } else {
            System.out.println("[RegistrationController] Confirm password field is a plain JTextField — toggle not needed.");
        }
    }

    // ==================== handleRegister() ====================

    /**
     * Main registration method — called when the Sign Up button is clicked.
     *
     * Steps:
     *   1. Get all values from the view using getter methods
     *   2. Validate: no empty fields
     *   3. Validate: password matches confirm password
     *   4. Validate: contact number is exactly 10 digits
     *   5. Validate: age is a valid number between 0-120
     *   6. Check: username is not already taken
     *   7. Insert into 'users' table — get the new user_id
     *   8. Generate next patient ID (P-001, P-002, ...)
     *   9. Insert into 'patients' table using the user_id
     *  10. Show success message and open UserLogin
     */
    public void handleRegister() {

        // ---- Step 1: Get all values from the view ----
        // getCleanInput() removes placeholder text and trims whitespace
        String fullName      = capitalizeWords(getCleanInput(view.getFullNameField(), "Enter full name"));
        String password      = getCleanInput(view.getPasswordField(), "Create password");
        String confirmPwd    = getCleanInput(view.getConfirmPasswordField(), "Confirm password");
        String gender        = view.getGenderComboBox().getSelectedItem().toString(); // from JComboBox
        String contactNumber = getCleanInput(view.getPhoneField(), "Enter phone number");
        String address       = capitalizeWords(getCleanInput(view.getLocationField(), "Enter location / address"));

        // ---- Step 1b: Get date from JDateChooser ----
        // getDate() returns null if the user has not picked a date yet
        java.util.Date selectedDate = view.getDobField().getDate();

        if (selectedDate == null) {
            // No date selected — show a warning and stop
            JOptionPane.showMessageDialog(view,
                "please select your date of birth",
                "missing field", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Convert java.util.Date  →  java.sql.Date  (needed for JDBC / database insert)
        java.sql.Date dob = new java.sql.Date(selectedDate.getTime());

        // Auto-calculate age from the selected date
        java.time.LocalDate birthDate = dob.toLocalDate();
        java.time.LocalDate today     = java.time.LocalDate.now();
        int age = java.time.Period.between(birthDate, today).getYears();

        // Validate age is a sensible value (0–120). Age 0 means infant (< 1 year old) or testing with today's date
        if (age < 0 || age > 120) {
            JOptionPane.showMessageDialog(view,
                "please enter a valid date of birth",
                "invalid date", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // ---- Validations ----
        // full name check
        if (fullName.isEmpty()) {
            JOptionPane.showMessageDialog(view,
                "please enter your full name",
                "missing field", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // gender check
        if (gender.equals("Select Gender")) {
            JOptionPane.showMessageDialog(view,
                "please select your gender",
                "missing field", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // phone number check
        if (contactNumber.isEmpty()) {
            JOptionPane.showMessageDialog(view,
                "please enter your phone number",
                "missing field", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // phone number length check
        if (contactNumber.length() != 10) {
            JOptionPane.showMessageDialog(view,
                "phone number must be exactly 10 digits",
                "invalid input", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // phone number digits only check
        if (!contactNumber.matches("\\d+")) {
            JOptionPane.showMessageDialog(view,
                "phone number must contain numbers only",
                "invalid input", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // location check
        if (address.isEmpty()) {
            JOptionPane.showMessageDialog(view,
                "please enter your location",
                "missing field", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // password check
        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(view,
                "please create a password",
                "missing field", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // password length check
        if (password.length() < 6) {
            JOptionPane.showMessageDialog(view,
                "password must be at least 6 characters",
                "invalid input", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // confirm password check
        if (confirmPwd.isEmpty()) {
            JOptionPane.showMessageDialog(view,
                "please confirm your password",
                "missing field", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // password match check
        if (!password.equals(confirmPwd)) {
            JOptionPane.showMessageDialog(view,
                "passwords do not match please try again",
                "invalid input", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // NOTE: DOB validation and age calculation are now done in Step 1b above
        //       (using JDateChooser). This block is no longer needed.

        // ---- Step 6: Generate Username from Full Name ----
        String username = patientDao.generateUsername(fullName);

        // ---- Step 7: Insert into 'users' table ----
        // insertUser() returns the auto-generated user_id, or -1 if it failed
        int userId = patientDao.insertUser(username, password, "patient");

        if (userId == -1) {
            // DB insert failed
            JOptionPane.showMessageDialog(view,
                "Could not create your account. Please check your database connection and try again.",
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // ---- Step 8: Generate the next patient ID (e.g., P-001) ----
        String patientId = patientDao.generatePatientId();

        // ---- Step 9: Insert into 'patients' table ----
        // Pass BOTH dob (java.sql.Date) and age (int) — DAO stores both in DB
        boolean patientSaved = patientDao.insertPatient(
            patientId,      // auto-generated, e.g., "P-001"
            userId,         // returned from insertUser()
            fullName,
            dob,            // java.sql.Date from JDateChooser
            age,            // auto-calculated from dob
            gender,
            contactNumber,
            address
        );

        if (patientSaved) {
            // ---- Step 10: Show custom success popup and navigate ----
            showRegistrationSuccessPopup(fullName, username, userId);
        } else {
            // Patient insert failed — user was already inserted in users table
            JOptionPane.showMessageDialog(view,
                "Database error: patient details could not be saved.",
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    // ==================== Helper Method ====================

    private void showRegistrationSuccessPopup(String fullName, String username, int userId) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Registration Successful");
        dialog.setPreferredSize(new Dimension(420, 320));
        dialog.setModal(true);
        dialog.setLayout(new BorderLayout());
        dialog.setResizable(false);
        dialog.getContentPane().setBackground(Color.WHITE);

        // top panel with green checkmark
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 5, 0));
        JLabel iconLabel = new JLabel("✓");
        iconLabel.setFont(new Font("Segoe UI", Font.BOLD, 40));
        iconLabel.setForeground(new Color(40, 167, 69)); // green circle/checkmark color
        topPanel.add(iconLabel);

        // center panel with message
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        JLabel titleLabel = new JLabel("Registration Successful");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel welcomeLabel = new JLabel("Welcome " + fullName + "! Your account has been created.");
        welcomeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // username in highlighted box
        JPanel userBox = new JPanel();
        userBox.setBackground(new Color(230, 242, 255)); // light blue background
        userBox.setBorder(BorderFactory.createLineBorder(new Color(180, 215, 255), 1));
        userBox.setMaximumSize(new Dimension(320, 40));
        JLabel userLabel = new JLabel("Your Username: " + username);
        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        userLabel.setForeground(new Color(33, 97, 172));
        userBox.add(javax.swing.Box.createVerticalStrut(5)); // small internal padding
        userBox.add(userLabel);
        userBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel noteLabel = new JLabel("Please save your username. You will need it to login.");
        noteLabel.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        noteLabel.setForeground(Color.GRAY);
        noteLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(titleLabel);
        centerPanel.add(javax.swing.Box.createVerticalStrut(15));
        centerPanel.add(welcomeLabel);
        centerPanel.add(javax.swing.Box.createVerticalStrut(15));
        centerPanel.add(userBox);
        centerPanel.add(javax.swing.Box.createVerticalStrut(15));
        centerPanel.add(noteLabel);

        // bottom panel with ok button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        JButton okButton = new JButton("OK");
        okButton.setBackground(new Color(33, 97, 172));
        okButton.setForeground(Color.WHITE);
        okButton.setFocusPainted(false);
        okButton.setPreferredSize(new Dimension(120, 35));
        okButton.addActionListener(e -> {
            dialog.dispose();
            view.dispose();
            view.SecurityQuestions sqFrame = new view.SecurityQuestions(userId);
            sqFrame.setUsername(username);
            sqFrame.setVisible(true);
        });
        bottomPanel.add(okButton);

        dialog.add(topPanel, BorderLayout.NORTH);
        dialog.add(centerPanel, BorderLayout.CENTER);
        dialog.add(bottomPanel, BorderLayout.SOUTH);

        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    private String capitalizeWords(String text) {
        if (text == null || text.trim().isEmpty()) {
            return text;
        }
        String[] words = text.trim().split("\\s+");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)))
                      .append(word.substring(1).toLowerCase())
                      .append(" ");
            }
        }
        return result.toString().trim();
    }

    /**
     * Reads text from a JTextField and cleans it up.
     * If the field still shows placeholder text, returns empty string instead.
     * Also trims leading/trailing whitespace.
     *
     * @param field       the JTextField to read from
     * @param placeholder the placeholder text that was set on this field
     * @return the clean user input, or "" if field is empty or has placeholder
     */
    private String getCleanInput(javax.swing.JTextField field, String placeholder) {
        String text = field.getText().trim();
        // If the text equals the placeholder, treat it as empty
        if (text.equalsIgnoreCase(placeholder)) {
            return "";
        }
        return text;
    }

    // ==================== Inner Listener Classes ====================

    /**
     * Handles Sign Up button click — triggers the registration process.
     */
    private class SignUpButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            handleRegister(); // delegate to the main handleRegister() method
        }
    }

    /**
     * Handles Back button click — returns to login screen.
     */
    public void handleBack() {
        view.dispose();                         // close the SignUp window
        new UserLogin().setVisible(true);       // open Login window
    }

    /**
     * Handles Login link button click — same as Back.
     */
    public void handleLoginLink() {
        view.dispose();
        new UserLogin().setVisible(true);
    }

    /**
     * Handles Back and Login link button clicks — returns to login screen.
     */
    private class BackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            handleBack(); // delegate to public handleBack()
        }
    }
}
