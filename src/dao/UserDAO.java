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
            "COALESCE(a.full_name, r.full_name, d.full_name, p.full_name) AS full_name, " +
            "COALESCE(a.contact_number, r.contact_number, d.contact_number, p.contact_number) AS phone, " +
            "COALESCE(p.gender, 'N/A') AS gender, " +
            "COALESCE(p.blood_group, 'N/A') AS blood_group, " +
            "COALESCE(p.dob, NULL) AS dob, " +
            "COALESCE(p.age, 'N/A') AS age, " +
            "u.role, u.status " +
            "FROM users u " +
            "LEFT JOIN admins a ON u.user_id = a.user_id " +
            "LEFT JOIN receptionists r ON u.user_id = r.user_id " +
            "LEFT JOIN doctors d ON u.user_id = d.user_id " +
            "LEFT JOIN patients p ON u.user_id = p.user_id " +
            "WHERE (COALESCE(a.full_name, r.full_name, d.full_name, p.full_name) LIKE ? " +
            "OR u.role LIKE ? " +
            "OR CAST(p.dob AS CHAR) LIKE ?) "
        );

        if (roleFilter != null && !roleFilter.equalsIgnoreCase("All")) {
            sql.append(" AND u.role = ? ");
        }
        if (statusFilter != null && !statusFilter.equalsIgnoreCase("All")) {
            sql.append(" AND u.status = ? ");
        }
        if (ageFilter != null && !ageFilter.equalsIgnoreCase("All")) {
            if (ageFilter.equals("Under 18")) sql.append(" AND p.age < 18 ");
            else if (ageFilter.equals("18-35")) sql.append(" AND p.age BETWEEN 18 AND 35 ");
            else if (ageFilter.equals("36-50")) sql.append(" AND p.age BETWEEN 36 AND 50 ");
            else if (ageFilter.equals("50+")) sql.append(" AND p.age >= 51 ");
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
   public boolean createUser(String fullName, String phone, String gender,
                          String dob, String role, String password, String shift) {
        Connection c = null;
        try {
            c = getConnection();
            c.setAutoCommit(false);

            // Step 1: build username from full name
            String username = fullName.toLowerCase().replace(" ", "_");

            // Step 2: insert into users
            String userSql = "INSERT INTO users (username, password, role, status) VALUES (?, ?, ?, 'active')";
            int userId;
            try (PreparedStatement ps = c.prepareStatement(userSql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, username);
                ps.setString(2, password);         // ⚠ hash this before storing in production
                ps.setString(3, role.toLowerCase());
                ps.executeUpdate();
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (!keys.next()) { c.rollback(); return false; }
                    userId = keys.getInt(1);
                }
            }

            // Step 3: insert into role-specific table
            boolean ok = switch (role.toLowerCase()) {
    case "admin"        -> insertAdmin(c, userId, fullName, phone);
    case "receptionist" -> insertReceptionist(c, userId, fullName, phone, shift);
    case "doctor"       -> insertDoctor(c, userId, fullName, phone);
    case "patient"      -> insertPatient(c, userId, fullName, phone, gender, dob);
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

            // Get role so we know which table to update phone/gender in
            String role;
            try (PreparedStatement ps = c.prepareStatement(
                    "SELECT role FROM users WHERE user_id=?")) {
                ps.setInt(1, userId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) { c.rollback(); return false; }
                    role = rs.getString("role");
                }
            }

            // Update contact_number, full_name (and gender/dob if patient) in role table
            switch (role.toLowerCase()) {
                case "admin" -> {
                    try (PreparedStatement ps = c.prepareStatement(
                            "UPDATE admins SET contact_number=?, full_name=? WHERE user_id=?")) {
                        ps.setString(1, phone); ps.setString(2, fullName); ps.setInt(3, userId);
                        ps.executeUpdate();
                    }
                }
                case "receptionist" -> {
                    try (PreparedStatement ps = c.prepareStatement(
                            "UPDATE receptionists SET contact_number=?, full_name=? WHERE user_id=?")) {
                        ps.setString(1, phone); ps.setString(2, fullName); ps.setInt(3, userId);
                        ps.executeUpdate();
                    }
                }
                case "doctor" -> {
                    try (PreparedStatement ps = c.prepareStatement(
                            "UPDATE doctors SET contact_number=?, full_name=? WHERE user_id=?")) {
                        ps.setString(1, phone); ps.setString(2, fullName); ps.setInt(3, userId);
                        ps.executeUpdate();
                    }
                }
                case "patient" -> {
                    try (PreparedStatement ps = c.prepareStatement(
                            "UPDATE patients SET contact_number=?, gender=?, full_name=?, dob=?, blood_group=? WHERE user_id=?")) {
                        ps.setString(1, phone); ps.setString(2, gender); 
                        ps.setString(3, fullName); 
                        if(dob != null && dob.trim().isEmpty()) dob = null;
                        ps.setString(4, dob);
                        ps.setString(5, bloodGroup);
                        ps.setInt(6, userId);
                        ps.executeUpdate();
                    }
                }
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
    private boolean insertAdmin(Connection c, int userId, String fullName, String phone) throws SQLException {
        String id = generateId(c, "admins", "admin_id", "A");
        try (PreparedStatement ps = c.prepareStatement(
                "INSERT INTO admins (admin_id, user_id, full_name, contact_number) VALUES (?, ?, ?, ?)")) {
            ps.setString(1, id); ps.setInt(2, userId);
            ps.setString(3, fullName); ps.setString(4, phone);
            return ps.executeUpdate() > 0;
        }
    }

    // ─── Insert Receptionist ────────────────────────────────────────────────
   private boolean insertReceptionist(Connection c, int userId, String fullName,
                                   String phone, String shift) throws SQLException {
    String id = generateId(c, "receptionists", "receptionist_id", "R");
    try (PreparedStatement ps = c.prepareStatement(
            "INSERT INTO receptionists (receptionist_id, user_id, full_name, contact_number, shift) " +
            "VALUES (?, ?, ?, ?, ?)")) {
        ps.setString(1, id);
        ps.setInt(2, userId);
        ps.setString(3, fullName);
        ps.setString(4, phone);
        ps.setString(5, shift);
        return ps.executeUpdate() > 0;
    }
}

    // ─── Insert Doctor ──────────────────────────────────────────────────────
    // departmentId defaults to 1 (General Medicine) — update the method signature
    // if your UI collects department at registration time
    private boolean insertDoctor(Connection c, int userId, String fullName, String phone) throws SQLException {
        String id = generateId(c, "doctors", "doctor_id", "D");
        try (PreparedStatement ps = c.prepareStatement(
                "INSERT INTO doctors (doctor_id, user_id, full_name, specialization, department_id, contact_number) " +
                "VALUES (?, ?, ?, 'General', 1, ?)")) {
            ps.setString(1, id); ps.setInt(2, userId);
            ps.setString(3, fullName); ps.setString(4, phone);
            return ps.executeUpdate() > 0;
        }
    }

    // ─── Insert Patient ─────────────────────────────────────────────────────
    private boolean insertPatient(Connection c, int userId, String fullName,
                                  String phone, String gender, String dob) throws SQLException {
        String id = generateId(c, "patients", "patient_id", "P");
        try (PreparedStatement ps = c.prepareStatement(
                "INSERT INTO patients (patient_id, user_id, full_name, dob, age, gender, contact_number) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            ps.setString(1, id); ps.setInt(2, userId);
            ps.setString(3, fullName); ps.setString(4, dob);
            ps.setInt(5, calculateAge(dob));
            ps.setString(6, gender.toLowerCase()); ps.setString(7, phone);
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
}
