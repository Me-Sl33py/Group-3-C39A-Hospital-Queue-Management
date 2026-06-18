package dao;

import database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Patient;
/**
 * PatientDao — handles ALL database queries related to patient registration.
 *
 * Methods:
 *   - insertUser()            : inserts into 'users' table, returns generated user_id
 *   - insertPatient()         : inserts into 'patients' table using the user_id
 *   - checkUsernameExists()   : returns true if username is already taken
 *   - generatePatientId()     : auto-generates patient_id like P-001, P-002
 *
 * Architecture Rule: This class contains ONLY SQL code. No UI, no JOptionPane.
 *
 * @author Group 3 C39A
 */
public class PatientDao {

    // ==================== METHOD 1: insertUser ====================

    /**
     * Inserts a new row into the 'users' table.
     *
     * SQL: INSERT INTO users (username, password) VALUES (?, ?)
     *
     * @param username the patient's login username
     * @param password the patient's password
     * @param role     unused (kept for API compatibility)
     * @return the auto-generated user_id (int) if successful, or -1 if it failed
     */

    /**
     * Inserts a new row into the 'users' table.
     *
     * SQL: INSERT INTO users (username, password, role) VALUES (?, ?, ?)
     *
     * @param username the patient's login username (e.g., phone number or chosen username)
     * @param password the patient's password (plain text — hash it later for security)
     * @param role     the role string — should be "patient" for this screen
     * @return the auto-generated user_id (int) if successful, or -1 if it failed
     */
    public int insertUser(String username, String password, String role) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, username);
            ps.setString(2, password);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int newUserId = generatedKeys.getInt(1);
                        System.out.println("[PatientDao] insertUser: New user_id = " + newUserId);
                        return newUserId;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("[PatientDao] insertUser error: " + e.getMessage());
        }
        return -1;
    }

    /**
     * Inserts a new row into the 'patients' table.
     *
     * SQL: INSERT INTO patients
     *        (patient_id, user_id, full_name, dob, age, gender, contact_number, address)
     *      VALUES (?, ?, ?, ?, ?, ?, ?, ?)
     *
     * Call insertUser() FIRST to get the userId, then call this method.
     *
     * @param patientId     auto-generated ID like "P-001" (from generatePatientId())
     * @param userId        the user_id returned by insertUser()
     * @param fullName      patient's full name
     * @param dob           patient's date of birth as java.sql.Date (from JDateChooser)
     * @param age           patient's age in years (auto-calculated from dob)
     * @param gender        "male", "female", or "other"
     * @param contactNumber patient's 10-digit phone number
     * @param address       patient's home address
     * @return true if the insert was successful, false if it failed
     */
    public boolean insertPatient(String patientId, int userId, String fullName, String username,
                                 java.sql.Date dob, int age, String gender,
                                 String contactNumber, String address) {
        String sql = "INSERT INTO patients " +
                     "(patient_id, user_id, full_name, username, dob, age, " +
                     "gender, contact_number, address) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String profileSql = "INSERT INTO user_profiles (user_id, full_name, contact_number, dob, age, gender, role, address) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = MySqlConnection.getConnection()) {
            // Insert into patients table
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, patientId);
                ps.setInt   (2, userId);
                ps.setString(3, fullName);
                ps.setString(4, username);
                ps.setDate  (5, dob);
                ps.setInt   (6, age);
                ps.setString(7, gender);
                ps.setString(8, contactNumber);
                ps.setString(9, address);
                int rowsAffected = ps.executeUpdate();

                // Insert into user_profiles table
                try (PreparedStatement psProfile = conn.prepareStatement(profileSql)) {
                    psProfile.setInt   (1, userId);
                    psProfile.setString(2, fullName);
                    psProfile.setString(3, contactNumber);
                    psProfile.setDate  (4, dob);
                    psProfile.setInt   (5, age);
                    psProfile.setString(6, gender);
                    psProfile.setString(7, "patient");
                    psProfile.setString(8, address);
                    int profileRows = psProfile.executeUpdate();

                    if (rowsAffected > 0 && profileRows > 0) {
                        System.out.println("[PatientDao] insertPatient: Patient " + patientId + " registered.");
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("[PatientDao] insertPatient error: " + e.getMessage());
        }
        return false;
    }

    // ==================== METHOD 3: checkUsernameExists ====================

    /**
     * Checks if a username is already taken in the 'users' table.
     *
     * SQL: SELECT user_id FROM users WHERE username = ?
     *
     * Use this BEFORE calling insertUser() to avoid duplicate accounts.
     *
     * @param username the username to check (e.g., phone number)
     * @return true if the username already exists, false if it is available
     */
    public boolean checkUsernameExists(String username) {
        String sql = "SELECT user_id FROM users WHERE username = ?";
        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                boolean exists = rs.next();
                System.out.println("[PatientDao] checkUsernameExists '" + username + "': " + exists);
                return exists;
            }
        } catch (SQLException e) {
            System.out.println("[PatientDao] checkUsernameExists error: " + e.getMessage());
        }
        return false;
    }

    // ==================== METHOD 4: generateUsername ====================

    public String generateUsername(String fullName) {
        String firstWord = fullName.trim().split("\\s+")[0].toLowerCase();
        String sql = "SELECT COUNT(*) FROM users WHERE username LIKE ?";
        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, firstWord + "%");
            try (ResultSet rs = ps.executeQuery()) {
                int count = rs.next() ? rs.getInt(1) : 0;
                return firstWord + (count + 1);
            }
        } catch (SQLException e) {
            System.out.println("[PatientDao] generateUsername error: " + e.getMessage());
        }
        return firstWord + "1";
    }

    // ==================== METHOD 5: generatePatientId ====================

    /**
     * Auto-generates the next patient ID by looking at the last ID in the table.
     *
     * Example: if the last row has patient_id = "P-003", this returns "P-004".
     * If the table is empty, it returns "P-001".
     *
     * SQL: SELECT patient_id FROM patients ORDER BY patient_id DESC LIMIT 1
     *
     * @return the next patient ID string, e.g. "P-001", "P-002", "P-003"
     */
    public String generatePatientId() {
        String sql = "SELECT patient_id FROM patients ORDER BY patient_id DESC LIMIT 1";
        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                String lastId = rs.getString("patient_id");
                String[] parts = lastId.split("-");
                int lastNumber = Integer.parseInt(parts[1]);
                String nextId = String.format("P-%03d", lastNumber + 1);
                System.out.println("[PatientDao] generatePatientId: Next ID = " + nextId);
                return nextId;
            }
            System.out.println("[PatientDao] generatePatientId: Table empty, starting at P-001.");
        } catch (SQLException e) {
            System.out.println("[PatientDao] generatePatientId SQL error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("[PatientDao] generatePatientId: Could not parse last ID: " + e.getMessage());
        }
        return "P-001";
    }

    // =========================================================================
    // RESTORED DOCTOR PANEL METHODS
    // =========================================================================

    public void createTableIfNotExists() {
        // Table already exists in your team's schema — nothing to create
    }

    // Get all patients
    public List<Patient> getAllPatients() {
        List<Patient> list = new ArrayList<>();
        String sql = "SELECT patient_id, user_id, full_name, age, gender, " +
                     "contact_number, address FROM patients";
        try (Connection conn = database.MySqlConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            System.err.println("getAllPatients error: " + e.getMessage());
        }
        return list;
    }

    // Get patient by ID
    public Patient getPatientById(String patientId) {
        String sql = "SELECT patient_id, user_id, full_name, dob, age, gender, " +
                     "contact_number, address, blood_group FROM patients WHERE patient_id = ?";
        try (Connection conn = database.MySqlConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, patientId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        } catch (SQLException e) {
            System.err.println("getPatientById error: " + e.getMessage());
        }
        return null;
    }

    // Get next waiting patient from queue table
    public Patient getNextWaitingPatient() {
        String sql = "SELECT p.patient_id, p.user_id, p.full_name, p.dob, p.age, p.gender, " +
                     "p.contact_number, p.address FROM patients p " +
                     "JOIN queue q ON p.patient_id = q.patient_id " +
                     "WHERE q.status = 'waiting' " +
                     "ORDER BY q.token_number ASC LIMIT 1";
        try (Connection conn = database.MySqlConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) return mapRow(rs);
        } catch (SQLException e) {
            System.err.println("getNextWaitingPatient error: " + e.getMessage());
        }
        return null;
    }

    // Update queue status
    public boolean updateQueueStatus(String patientId, String newStatus) {
        String sql = "UPDATE queue SET status = ? WHERE patient_id = ? " +
                     "AND status != 'completed'";
        try (Connection conn = database.MySqlConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newStatus);
            ps.setString(2, patientId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("updateQueueStatus error: " + e.getMessage());
            return false;
        }
    }

    // Get all queue patients for a specific doctor
    public List<Object[]> getQueueByDoctor(String doctorId) {
        List<Object[]> list = new ArrayList<>();
        String sql = "SELECT q.token_number, p.full_name, q.status " +
                     "FROM queue q JOIN patients p ON q.patient_id = p.patient_id " +
                     "WHERE q.doctor_id = ? ORDER BY q.token_number ASC";
        try (Connection conn = database.MySqlConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, doctorId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Object[]{
                        rs.getInt("token_number"),
                        rs.getString("full_name"),
                        rs.getString("status"),
                        "View File"
                    });
                }
            }
        } catch (SQLException e) {
            System.err.println("getQueueByDoctor error: " + e.getMessage());
        }
        return list;
    }

    private Patient mapRow(ResultSet rs) throws SQLException {
        Patient p = new Patient(
            rs.getString("patient_id"),
            rs.getInt("user_id"),
            rs.getString("full_name"),
            rs.getInt("age"),
            rs.getString("gender"),
            rs.getString("contact_number"),
            rs.getString("address")
        );
        p.setDob(rs.getDate("dob"));
        try {
            p.setBloodGroup(rs.getString("blood_group"));
        } catch (SQLException e) {
            // blood_group might not be in the SELECT clause of some queries
        }
        return p;
    }

    public String getUsernameByUserId(int userId) {
        String sql = "SELECT username FROM users WHERE user_id = ?";
        try (Connection conn = database.MySqlConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getString("username");
            }
        } catch (SQLException e) {
            System.err.println("getUsernameByUserId error: " + e.getMessage());
        }
        return null;
    }

    public boolean validateCurrentPassword(int userId, String currentPassword) {
        String sql = "SELECT 1 FROM users WHERE user_id = ? AND password = ?";
        try (Connection conn = database.MySqlConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setString(2, currentPassword);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.err.println("validateCurrentPassword error: " + e.getMessage());
            return false;
        }
    }

    public boolean updatePatientProfile(String patientId, java.util.Date dob, int age, String phone, String address, String bloodGroup) {
        String sql = "UPDATE patients SET dob = ?, age = ?, contact_number = ?, address = ?, blood_group = ? WHERE patient_id = ?";
        try (Connection conn = database.MySqlConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            if (dob != null) ps.setDate(1, new java.sql.Date(dob.getTime()));
            else ps.setNull(1, java.sql.Types.DATE);
            ps.setInt(2, age);
            ps.setString(3, phone);
            ps.setString(4, address);
            ps.setString(5, bloodGroup);
            ps.setString(6, patientId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("updatePatientProfile error: " + e.getMessage());
            return false;
        }
    }

    public boolean updateUsernameAndPassword(int userId, String username, String password) {
        String sql = "UPDATE users SET username = ?, password = ? WHERE user_id = ?";
        try (Connection conn = database.MySqlConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setInt(3, userId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("updateUsernameAndPassword error: " + e.getMessage());
            return false;
        }
    }

    public boolean updateUsername(int userId, String username) {
        String sql = "UPDATE users SET username = ? WHERE user_id = ?";
        try (Connection conn = database.MySqlConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setInt(2, userId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("updateUsername error: " + e.getMessage());
            return false;
        }
    }
}
