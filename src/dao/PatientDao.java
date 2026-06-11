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

    // The database connection helper
    private MySqlConnection db;

    /**
     * Constructor — creates a MySqlConnection instance for this DAO to use.
     */
    public PatientDao() {
        this.db = new MySqlConnection();
    }

    // ==================== METHOD 1: insertUser ====================

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
        Connection conn = null;  // We declare conn outside so we can close it in finally
        try {
            // Step 1: Open the database connection
            conn = db.openConnection();

            if (conn == null) {
                System.out.println("[PatientDao] insertUser: Could not open DB connection.");
                return -1;
            }

            // Step 2: Write the SQL — RETURN_GENERATED_KEYS tells MySQL to give us the new user_id
            String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // Step 3: Set the values (? placeholders replaced in order)
            ps.setString(1, username);  // 1st ? = username
            ps.setString(2, password);  // 2nd ? = password
            ps.setString(3, role);      // 3rd ? = role

            // Step 4: Execute the INSERT
            int rowsAffected = ps.executeUpdate();

            // Step 5: If at least 1 row was inserted, get the auto-generated user_id
            if (rowsAffected > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int newUserId = generatedKeys.getInt(1); // get the first generated key
                    System.out.println("[PatientDao] insertUser: New user_id = " + newUserId);
                    return newUserId;
                }
            }

        } catch (SQLException e) {
            // Print the error — SQL error details are shown in console
            System.out.println("[PatientDao] insertUser error: " + e.getMessage());
        } finally {
            // Always close the connection — even if an error happened
            db.closeConnection(conn);
        }

        return -1; // Return -1 to signal that the insert failed
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
        Connection conn = null;
        try {
            // Step 1: Open the database connection
            conn = db.openConnection();

            if (conn == null) {
                System.out.println("[PatientDao] insertPatient: Could not open DB connection.");
                return false;
            }

            // Step 2: Write the SQL INSERT statement
            String sql = "INSERT INTO patients " +
                         "(patient_id, user_id, full_name, username, dob, age, " +
                         "gender, contact_number, address) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            // Step 3: Set the values (? placeholders in the same order as the columns above)
            ps.setString(1, patientId);     // 1st ? = patient_id   (e.g., "P-001")
            ps.setInt   (2, userId);        // 2nd ? = user_id      (foreign key)
            ps.setString(3, fullName);      // 3rd ? = full_name
            ps.setString(4, username);      // 4th ? = username
            ps.setDate  (5, dob);           // 5th ? = dob          (java.sql.Date from JDateChooser)
            ps.setInt   (6, age);           // 6th ? = age          (auto-calculated from dob)
            ps.setString(7, gender);        // 7th ? = gender
            ps.setString(8, contactNumber); // 8th ? = contact_number
            ps.setString(9, address);       // 9th ? = address

            // Step 4: Execute the INSERT into patients
            int rowsAffected = ps.executeUpdate();

            // Step 5: ALSO INSERT INTO user_profiles
            String profileSql = "INSERT INTO user_profiles (user_id, full_name, contact_number, dob, age, gender, role, address) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement psProfile = conn.prepareStatement(profileSql);
            psProfile.setInt(1, userId);
            psProfile.setString(2, fullName);
            psProfile.setString(3, contactNumber);
            psProfile.setDate(4, dob);
            psProfile.setInt(5, age);
            psProfile.setString(6, gender);
            psProfile.setString(7, "patient");
            psProfile.setString(8, address);
            int profileRows = psProfile.executeUpdate();

            // Step 6: Return true if both inserts were successful
            if (rowsAffected > 0 && profileRows > 0) {
                System.out.println("[PatientDao] insertPatient: Patient " + patientId + " registered in patients and user_profiles.");
                return true;
            }

        } catch (SQLException e) {
            System.out.println("[PatientDao] insertPatient error: " + e.getMessage());
        } finally {
            // Always close the connection
            db.closeConnection(conn);
        }

        return false; // Return false to signal failure
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
        Connection conn = null;
        try {
            // Step 1: Open connection
            conn = db.openConnection();

            if (conn == null) {
                System.out.println("[PatientDao] checkUsernameExists: Could not open DB connection.");
                return false; // Assume not exists if connection fails
            }

            // Step 2: Query the users table
            String sql = "SELECT user_id FROM users WHERE username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);

            // Step 3: Execute the query
            ResultSet rs = ps.executeQuery();

            // Step 4: If rs.next() is true, a row was found — username is taken
            boolean exists = rs.next();
            System.out.println("[PatientDao] checkUsernameExists '" + username + "': " + exists);
            return exists;

        } catch (SQLException e) {
            System.out.println("[PatientDao] checkUsernameExists error: " + e.getMessage());
        } finally {
            db.closeConnection(conn);
        }

        return false; // Default: assume not exists if there was an error
    }

    // ==================== METHOD 4: generateUsername ====================

    public String generateUsername(String fullName) {
        // get first word and make lowercase
        String firstWord = fullName.trim().split("\\s+")[0].toLowerCase();
        int count = 0;

        Connection conn = null;
        try {
            conn = db.openConnection();
            if (conn != null) {
                // count how many usernames start with firstword
                String countQuery = "SELECT count(*) FROM users WHERE username LIKE ?";
                PreparedStatement ps = conn.prepareStatement(countQuery);
                // pass firstWord + "%" as parameter
                ps.setString(1, firstWord + "%");
                
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    // count = result of query
                    count = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("[PatientDao] generateUsername error: " + e.getMessage());
        } finally {
            db.closeConnection(conn);
        }

        return firstWord + (count + 1);
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
        Connection conn = null;
        try {
            // Step 1: Open connection
            conn = db.openConnection();

            if (conn == null) {
                System.out.println("[PatientDao] generatePatientId: Could not open DB connection.");
                return "P-001"; // Fallback to first ID
            }

            // Step 2: Get the last patient_id (sorted Z-A, take top 1)
            String sql = "SELECT patient_id FROM patients ORDER BY patient_id DESC LIMIT 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Step 3: Extract the number from the last ID
                // Example: "P-007" -> split by "-" -> ["P", "007"] -> take index [1] -> 7
                String lastId = rs.getString("patient_id"); // e.g., "P-007"
                String[] parts = lastId.split("-");          // split into ["P", "007"]
                int lastNumber = Integer.parseInt(parts[1]); // parse "007" to integer 7

                // Step 4: Increment and format with leading zeros (P-001 format)
                String nextId = String.format("P-%03d", lastNumber + 1);
                System.out.println("[PatientDao] generatePatientId: Next ID = " + nextId);
                return nextId;
            }

            // If table is empty, start from P-001
            System.out.println("[PatientDao] generatePatientId: Table empty, starting at P-001.");
        } catch (SQLException e) {
            System.out.println("[PatientDao] generatePatientId SQL error: " + e.getMessage());
        } catch (NumberFormatException e) {
            // This happens if patient_id in DB doesn't follow "P-000" format
            System.out.println("[PatientDao] generatePatientId: Could not parse last ID: " + e.getMessage());
        } finally {
            db.closeConnection(conn);
        }

        return "P-001"; // Safe fallback
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
        try (Connection conn = new database.MySqlConnection().openConnection();
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
        try (Connection conn = new database.MySqlConnection().openConnection();
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
        try (Connection conn = new database.MySqlConnection().openConnection();
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
        try (Connection conn = new database.MySqlConnection().openConnection();
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
        try (Connection conn = new database.MySqlConnection().openConnection();
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
        try (Connection conn = new database.MySqlConnection().openConnection();
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
        try (Connection conn = new database.MySqlConnection().openConnection();
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
        try (Connection conn = new database.MySqlConnection().openConnection();
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
        try (Connection conn = new database.MySqlConnection().openConnection();
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
        try (Connection conn = new database.MySqlConnection().openConnection();
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
