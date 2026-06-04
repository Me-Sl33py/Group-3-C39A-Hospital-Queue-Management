package dao;
import database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {

    // ── DASHBOARD COUNTS ─────────────────────────────────────────

    public int getPatientCount() {
        int count = 0;
        try {
            Connection conn = new MySqlConnection().openConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM patients");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) count = rs.getInt(1);
            conn.close();
        } catch (Exception e) { e.printStackTrace(); }
        return count;
    }

    public int getDoctorCount() {
        int count = 0;
        try {
            Connection conn = new MySqlConnection().openConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM doctors");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) count = rs.getInt(1);
            conn.close();
        } catch (Exception e) { e.printStackTrace(); }
        return count;
    }

    public int getAppointmentCount() {
        int count = 0;
        try {
            Connection conn = new MySqlConnection().openConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM appointments");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) count = rs.getInt(1);
            conn.close();
        } catch (Exception e) { e.printStackTrace(); }
        return count;
    }

    public int getReceptionistCount() {
        int count = 0;
        try {
            Connection conn = new MySqlConnection().openConnection();
            PreparedStatement ps = conn.prepareStatement(
                "SELECT COUNT(*) FROM users WHERE role='receptionist'");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) count = rs.getInt(1);
            conn.close();
        } catch (Exception e) { e.printStackTrace(); }
        return count;
    }

    // ── CREATE USER ──────────────────────────────────────────────

    public boolean createUser(String fullName, String phone, String gender,
                               String email, String role, String password) {
        Connection conn = null;
        try {
            conn = new MySqlConnection().openConnection();
            conn.setAutoCommit(false);

            // 1. Insert into users table
        String userSql = "INSERT INTO users (username, password, role, status, full_name, gender, phone, email) " +
     "VALUES (?, ?, ?, 'active', ?, ?, ?, ?)";
PreparedStatement userPs = conn.prepareStatement(userSql,
    java.sql.Statement.RETURN_GENERATED_KEYS);
userPs.setString(1, email);
userPs.setString(2, password);
userPs.setString(3, role.toLowerCase());
userPs.setString(4, fullName);
userPs.setString(5, gender.toLowerCase());
userPs.setString(6, phone);
userPs.setString(7, email);
userPs.executeUpdate();

// Get generated user_id
ResultSet keys = userPs.getGeneratedKeys();
            int userId = -1;
            if (keys.next()) userId = keys.getInt(1);

            // 2. Insert into patients or doctors based on role
            if (role.equalsIgnoreCase("Patient")) {
                String patientId = "p-" + String.format("%03d", userId);
                PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO patients (patient_id, user_id, full_name, age, gender, contact_number) " +
                    "VALUES (?, ?, ?, 0, ?, ?)");
                ps.setString(1, patientId);
                ps.setInt(2, userId);
                ps.setString(3, fullName);
                ps.setString(4, gender.toLowerCase());
                ps.setString(5, phone);
                ps.executeUpdate();

            } else if (role.equalsIgnoreCase("Doctor")) {
                String doctorId = "d-" + String.format("%03d", userId);
                PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO doctors (doctor_id, user_id, full_name, department_id, contact_number) " +
                    "VALUES (?, ?, ?, 1, ?)");
                ps.setString(1, doctorId);
                ps.setInt(2, userId);
                ps.setString(3, fullName);
                ps.setString(4, phone);
                ps.executeUpdate();
            }
            // Admin / Receptionist → users table only

            conn.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            try { if (conn != null) conn.rollback(); } catch (Exception ex) { ex.printStackTrace(); }
            return false;
        } finally {
            try { if (conn != null) conn.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }

    // ── MANAGE USERS ─────────────────────────────────────────────

    public List<String[]> searchUsers(String keyword) {
        List<String[]> list = new ArrayList<>();
        String sql = "SELECT u.user_id, " +
                     "COALESCE(p.full_name, d.full_name, u.full_name, u.username) AS full_name, " +
                     "COALESCE(p.contact_number, d.contact_number, u.phone, '-') AS phone, " +
                     "COALESCE(p.gender, u.gender, 'N/A') AS gender, " +
                     "u.username AS email, " +
                     "u.role, " +
                     "u.status " +
                     "FROM users u " +
                     "LEFT JOIN patients p ON u.user_id = p.user_id " +
                     "LEFT JOIN doctors  d ON u.user_id = d.user_id " +
                     "WHERE u.username LIKE ? " +
                     "   OR p.full_name LIKE ? " +
                     "   OR d.full_name LIKE ? " +
                     "   OR u.role     LIKE ?";
        try {
            Connection conn = new MySqlConnection().openConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            String kw = "%" + keyword + "%";
            ps.setString(1, kw);
            ps.setString(2, kw);
            ps.setString(3, kw);
            ps.setString(4, kw);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new String[]{
                    String.valueOf(rs.getInt("user_id")),
                    rs.getString("full_name"),
                    rs.getString("phone"),
                    rs.getString("gender"),
                    rs.getString("email"),
                    rs.getString("role"),
                    rs.getString("status")
                });
            }
            conn.close();
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public List<String[]> getAllUsers() {
        return searchUsers("");
    }

    public boolean updateUserRole(int userId, String newRole) {
        try {
            Connection conn = new MySqlConnection().openConnection();
            PreparedStatement ps = conn.prepareStatement(
                "UPDATE users SET role = ? WHERE user_id = ?");
            ps.setString(1, newRole);
            ps.setInt(2, userId);
            int rows = ps.executeUpdate();
            conn.close();
            return rows > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public boolean deactivateUser(int userId) {
        try {
            Connection conn = new MySqlConnection().openConnection();
            PreparedStatement ps = conn.prepareStatement(
                "UPDATE users SET status = 'inactive' WHERE user_id = ?");
            ps.setInt(1, userId);
            int rows = ps.executeUpdate();
            conn.close();
            return rows > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public boolean activateUser(int userId) {
        try {
            Connection conn = new MySqlConnection().openConnection();
            PreparedStatement ps = conn.prepareStatement(
                "UPDATE users SET status = 'active' WHERE user_id = ?");
            ps.setInt(1, userId);
            int rows = ps.executeUpdate();
            conn.close();
            return rows > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }
    
   public boolean updateUser(int userId, String newStatus, String newGender, String newPhone) {
    Connection conn = null;
    try {
        conn = new MySqlConnection().openConnection();
        conn.setAutoCommit(false);

        // Update users table
        PreparedStatement ps = conn.prepareStatement(
    "UPDATE users SET status = ?, gender = ?, phone = ? WHERE user_id = ?");
ps.setString(1, newStatus.toLowerCase());
ps.setString(2, newGender.toLowerCase());
ps.setString(3, newPhone);
ps.setInt(4, userId);
        ps.executeUpdate();

        // Update patients table
        PreparedStatement ps2 = conn.prepareStatement(
            "UPDATE patients SET gender = ?, contact_number = ? WHERE user_id = ?");
        ps2.setString(1, newGender.toLowerCase());
        ps2.setString(2, newPhone);
        ps2.setInt(3, userId);
        ps2.executeUpdate();

        // Update doctors table
        PreparedStatement ps3 = conn.prepareStatement(
            "UPDATE doctors SET contact_number = ? WHERE user_id = ?");
        ps3.setString(1, newPhone);
        ps3.setInt(2, userId);
        ps3.executeUpdate();

        conn.commit();
        return true;

    } catch (Exception e) {
        e.printStackTrace();
        try { if (conn != null) conn.rollback(); } catch (Exception ex) { ex.printStackTrace(); }
        return false;
    } finally {
        try { if (conn != null) conn.close(); } catch (Exception e) { e.printStackTrace(); }
    }
}

}


