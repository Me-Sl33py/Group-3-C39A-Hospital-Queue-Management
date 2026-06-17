package dao;
import database.MySqlConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private Connection getConnection() throws SQLException {
        return new MySqlConnection().openConnection();
    }

    // ─── Search Users ───────────────────────────────────────────────────────
    public List<String[]> searchUsers(String keyword, String roleFilter, String statusFilter, String ageFilter) {
        List<String[]> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
            "SELECT u.user_id, " +
            "up.full_name AS full_name, " +
            "up.contact_number AS phone, " +
            "up.gender AS gender, " +
            "up.blood_group AS blood_group, " +
            "up.dob AS dob, " +
            "up.age AS age, " +
            "up.role, u.status " +
            "FROM users u " +
            "JOIN user_profiles up ON u.user_id = up.user_id " +
            "WHERE (up.full_name LIKE ? " +
            "OR up.role LIKE ? " +
            "OR CAST(up.dob AS CHAR) LIKE ?) "
        );

        if (roleFilter != null && !roleFilter.equalsIgnoreCase("All")) {
            sql.append(" AND up.role = ? ");
        }
        if (statusFilter != null && !statusFilter.equalsIgnoreCase("All")) {
            sql.append(" AND u.status = ? ");
        }
        if (ageFilter != null && !ageFilter.equalsIgnoreCase("All")) {
            if (ageFilter.equals("Under 18")) sql.append(" AND up.age < 18 ");
            else if (ageFilter.equals("18-35")) sql.append(" AND up.age BETWEEN 18 AND 35 ");
            else if (ageFilter.equals("36-50")) sql.append(" AND up.age BETWEEN 36 AND 50 ");
            else if (ageFilter.equals("50+")) sql.append(" AND up.age >= 51 ");
        }

        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql.toString())) {
            String kw = "%" + keyword + "%";
            ps.setString(1, kw); ps.setString(2, kw); ps.setString(3, kw);
            
            int paramIndex = 4;
            if (roleFilter != null && !roleFilter.equalsIgnoreCase("All")) {
                ps.setString(paramIndex++, roleFilter.toLowerCase());
            }
            if (statusFilter != null && !statusFilter.equalsIgnoreCase("All")) {
                ps.setString(paramIndex++, statusFilter.toLowerCase());
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new String[]{
                        rs.getString("user_id"),
                        rs.getString("full_name"),
                        rs.getString("phone"),
                        rs.getString("gender"),
                        rs.getString("blood_group"),
                        rs.getString("dob"),
                        rs.getString("age"),
                        rs.getString("role"),
                        rs.getString("status")
                    });
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    // ─── Create User ────────────────────────────────────────────────────────
   public boolean createUser(String username, String fullName, String phone, String gender,
                          String dob, String role, String password, String shift) {
        Connection c = null;
        try {
            c = getConnection();
            c.setAutoCommit(false);


            // Step 2: insert into users
            String userSql = "INSERT INTO users (username, password, status) VALUES (?, ?, 'active')";
            int userId;
            try (PreparedStatement ps = c.prepareStatement(userSql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, username);
                ps.setString(2, password);         // ⚠ hash this before storing in production
                ps.executeUpdate();
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (!keys.next()) { c.rollback(); return false; }
                    userId = keys.getInt(1);
                }
            }
            
            // Step 3: insert into user_profiles
            String profileSql = "INSERT INTO user_profiles (user_id, full_name, contact_number, dob, age, gender, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = c.prepareStatement(profileSql)) {
                ps.setInt(1, userId);
                ps.setString(2, fullName);
                ps.setString(3, phone);
                if (dob != null && dob.trim().isEmpty()) dob = null;
                ps.setString(4, dob);
                ps.setInt(5, calculateAge(dob));
                ps.setString(6, gender != null ? gender.toLowerCase() : "prefer not to say");
                ps.setString(7, role.toLowerCase());
                ps.executeUpdate();
            }

            // Step 4: insert into role-specific table
            boolean ok = switch (role.toLowerCase()) {
                case "admin"        -> insertAdmin(c, userId);
                case "receptionist" -> insertReceptionist(c, userId, fullName, username, shift);
                case "doctor"       -> insertDoctor(c, userId, fullName, username);
                case "patient"      -> insertPatient(c, userId);
                default             -> false;
            };

            if (ok) { c.commit(); return true; }
            else    { c.rollback(); return false; }

        } catch (SQLException e) {
            e.printStackTrace();
            try { if (c != null) c.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
        } finally {
            try { if (c != null) { c.setAutoCommit(true); c.close(); } } catch (SQLException e) { e.printStackTrace(); }
        }
        return false;
    }

    // ─── Update User (status + role-table fields) ───────────────────────────
    public boolean updateUser(int userId, String fullName, String dob, String status, String gender, String phone, String bloodGroup) {
        Connection c = null;
        try {
            c = getConnection();
            c.setAutoCommit(false);

            // Update status in users table
            try (PreparedStatement ps = c.prepareStatement(
                    "UPDATE users SET status=? WHERE user_id=?")) {
                ps.setString(1, status);
                ps.setInt(2, userId);
                ps.executeUpdate();
            }

            // Update user_profiles table
            try (PreparedStatement ps = c.prepareStatement(
                    "UPDATE user_profiles SET contact_number=?, full_name=?, gender=?, dob=?, blood_group=? WHERE user_id=?")) {
                ps.setString(1, phone);
                ps.setString(2, fullName);
                ps.setString(3, gender != null ? gender.toLowerCase() : "prefer not to say");
                if(dob != null && dob.trim().isEmpty()) dob = null;
                ps.setString(4, dob);
                ps.setString(5, bloodGroup);
                ps.setInt(6, userId);
                ps.executeUpdate();
            }

            c.commit();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            try { if (c != null) c.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
        } finally {
            try { if (c != null) { c.setAutoCommit(true); c.close(); } } catch (SQLException e) { e.printStackTrace(); }
        }
        return false;
    }

    // ─── Activate / Deactivate ──────────────────────────────────────────────
    public boolean deactivateUser(int userId) { return setUserStatus(userId, "deactive"); }
    public boolean activateUser(int userId)   { return setUserStatus(userId, "active"); }

    private boolean setUserStatus(int userId, String status) {
        String sql = "UPDATE users SET status=? WHERE user_id=?";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, status); ps.setInt(2, userId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    // ─── Insert Admin ───────────────────────────────────────────────────────
    private boolean insertAdmin(Connection c, int userId) throws SQLException {
        String id = generateId(c, "admins", "admin_id", "A");
        try (PreparedStatement ps = c.prepareStatement(
                "INSERT INTO admins (admin_id, user_id) VALUES (?, ?)")) {
            ps.setString(1, id); ps.setInt(2, userId);
            return ps.executeUpdate() > 0;
        }
    }

    // ─── Insert Receptionist ────────────────────────────────────────────────
   private boolean insertReceptionist(Connection c, int userId, String fullName, String username, String shift) throws SQLException {
        String id = generateId(c, "receptionists", "receptionist_id", "R");
        try (PreparedStatement ps = c.prepareStatement(
                "INSERT INTO receptionists (receptionist_id, user_id, full_name, username, shift) VALUES (?, ?, ?, ?, ?)")) {
            ps.setString(1, id);
            ps.setInt(2, userId);
            ps.setString(3, fullName);
            ps.setString(4, username);
            ps.setString(5, shift);
            return ps.executeUpdate() > 0;
        }
    }

    // ─── Insert Doctor ──────────────────────────────────────────────────────
    private boolean insertDoctor(Connection c, int userId, String fullName, String username) throws SQLException {
        String id = generateId(c, "doctors", "doctor_id", "D");
        try (PreparedStatement ps = c.prepareStatement(
                "INSERT INTO doctors (doctor_id, user_id, full_name, username, specialization, department_id) VALUES (?, ?, ?, ?, 'General', 1)")) {
            ps.setString(1, id); ps.setInt(2, userId); ps.setString(3, fullName); ps.setString(4, username);
            return ps.executeUpdate() > 0;
        }
    }

    // ─── Insert Patient ─────────────────────────────────────────────────────
    private boolean insertPatient(Connection c, int userId) throws SQLException {
        String id = generateId(c, "patients", "patient_id", "P");
        try (PreparedStatement ps = c.prepareStatement(
                "INSERT INTO patients (patient_id, user_id) VALUES (?, ?)")) {
            ps.setString(1, id); ps.setInt(2, userId);
            return ps.executeUpdate() > 0;
        }
    }

    // ─── Generate ID (e.g. P-001) ───────────────────────────────────────────
    private String generateId(Connection c, String table, String idColumn, String prefix) throws SQLException {
        String sql = "SELECT " + idColumn + " FROM " + table + " ORDER BY " + idColumn + " DESC LIMIT 1";
        try (PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            int next = 1;
            if (rs.next()) {
                String lastId = rs.getString(1);
                next = Integer.parseInt(lastId.split("-")[1]) + 1;
            }
            return prefix + "-" + String.format("%03d", next);
        }
    }

    // ─── Calculate Age ──────────────────────────────────────────────────────
    private int calculateAge(String dob) {
        if (dob == null || dob.trim().isEmpty()) return 0;
        try {
            java.time.LocalDate birth = java.time.LocalDate.parse(dob);
            return java.time.Period.between(birth, java.time.LocalDate.now()).getYears();
        } catch (Exception e) { return 0; }
    }
    
    public boolean changePassword(int userId, String newPassword) {
        String sql = "UPDATE users SET password=? WHERE user_id=?";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, newPassword);
            ps.setInt(2, userId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public String checkLogin(String identifier, String password) {
        String query = 
            "select p.role, u.user_id from users u " +
            "left join user_profiles p " +
            "on u.user_id = p.user_id " +
            "where (LOWER(u.username) = LOWER(?) " +
            "or p.contact_number = ? " +
            "or LOWER(p.full_name) = LOWER(?)) " +
            "and u.password = ?";
            
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, identifier);
            ps.setString(2, identifier);
            ps.setString(3, identifier);
            ps.setString(4, password);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("role");
                }
            }
        } catch (Exception e) {
            System.out.println("checkLogin error: " + e);
        }
        return null;
    }

    public int getUserId(String identifier) {
        String query = 
            "select u.user_id from users u " +
            "left join user_profiles p " +
            "on u.user_id = p.user_id " +
            "where u.username = ? " +
            "or p.contact_number = ?";
            
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, identifier);
            ps.setString(2, identifier);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("user_id");
                }
            }
        } catch (Exception e) {
            System.out.println("getUserId error: " + e);
        }
        return -1;
    }

    public int searchUserForReset(String fullName, String phone, java.sql.Date dob, String location) {
        // Primary search: Full Name AND Phone
        if (phone != null && !phone.trim().isEmpty()) {
            String sqlPhone = "SELECT user_id FROM user_profiles WHERE LOWER(full_name) = LOWER(?) AND contact_number = ?";
            try (Connection conn = getConnection();
                 PreparedStatement ps = conn.prepareStatement(sqlPhone)) {
                ps.setString(1, fullName.trim());
                ps.setString(2, phone.trim());
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt("user_id");
                    }
                }
            } catch (Exception e) {
                System.out.println("searchUserForReset (Phone) error: " + e);
            }
        }
        
        // Fallback search: Full Name AND DOB (if DOB is provided)
        if (dob != null) {
            String sqlDob = "SELECT user_id FROM user_profiles WHERE LOWER(full_name) = LOWER(?) AND dob = ?";
            try (Connection conn = getConnection();
                 PreparedStatement ps = conn.prepareStatement(sqlDob)) {
                ps.setString(1, fullName.trim());
                ps.setDate(2, dob);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt("user_id");
                    }
                }
            } catch (Exception e) {
                System.out.println("searchUserForReset (DOB) error: " + e);
            }
        }
        
        return -1;
    }

    public boolean updatePassword(int userId, String newPassword) {
        return changePassword(userId, newPassword);
    }

    public boolean isUsernameExists(String username) {
        String sql = "SELECT user_id FROM users WHERE username = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            System.out.println("isUsernameExists error: " + e);
        }
        return false;
    }

    public int registerUser(String username, String password, String role) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, username);
            ps.setString(2, password);
            if (ps.executeUpdate() > 0) {
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) return keys.getInt(1);
                }
            }
        } catch (Exception e) {
            System.out.println("registerUser error: " + e);
        }
        return -1;
    }

    public boolean registerPatient(String patientId, int userId, String fullName, int age, String gender, String contactNumber, String address) {
        String profileSql = "INSERT INTO user_profiles (user_id, full_name, age, gender, contact_number, address, role) VALUES (?, ?, ?, ?, ?, ?, 'patient')";
        String patientSql = "INSERT INTO patients (patient_id, user_id) VALUES (?, ?)";
        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps1 = conn.prepareStatement(profileSql);
                 PreparedStatement ps2 = conn.prepareStatement(patientSql)) {
                
                ps1.setInt(1, userId);
                ps1.setString(2, fullName);
                ps1.setInt(3, age);
                ps1.setString(4, gender);
                ps1.setString(5, contactNumber);
                ps1.setString(6, address);
                ps1.executeUpdate();

                ps2.setString(1, patientId);
                ps2.setInt(2, userId);
                ps2.executeUpdate();

                conn.commit();
                return true;
            } catch (Exception e) {
                conn.rollback();
                System.out.println("registerPatient error: " + e);
            }
        } catch (Exception e) {
            System.out.println("registerPatient connection error: " + e);
        }
        return false;
    }

    public String generatePatientId() {
        try (Connection conn = getConnection()) {
            return generateId(conn, "patients", "patient_id", "P");
        } catch (Exception e) {
            return "P-001";
        }
    }
    public String[] getUserDetailsByIdentifier(String identifier) {
        String query = 
            "SELECT up.user_id, up.full_name, u.username FROM user_profiles up " +
            "JOIN users u ON up.user_id = u.user_id " +
            "WHERE LOWER(u.username) = LOWER(?) OR up.contact_number = ? OR LOWER(up.full_name) = LOWER(?)";
            
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, identifier);
            ps.setString(2, identifier);
            ps.setString(3, identifier);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new String[]{
                        String.valueOf(rs.getInt("user_id")),
                        rs.getString("full_name"),
                        rs.getString("username")
                    };
                }
            }
        } catch (Exception e) {
            System.out.println("getUserDetailsByIdentifier error: " + e);
        }
        return null;
    }
}
