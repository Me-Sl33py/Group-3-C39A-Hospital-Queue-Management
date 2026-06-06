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

            ResultSet keys = userPs.getGeneratedKeys();
            int userId = -1;
            if (keys.next()) userId = keys.getInt(1);

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
                     "u.username AS email, u.role, u.status " +
                     "FROM users u " +
                     "LEFT JOIN patients p ON u.user_id = p.user_id " +
                     "LEFT JOIN doctors  d ON u.user_id = d.user_id " +
                     "WHERE u.username LIKE ? OR p.full_name LIKE ? " +
                     "   OR d.full_name LIKE ? OR u.role LIKE ?";
        try {
            Connection conn = new MySqlConnection().openConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            String kw = "%" + keyword + "%";
            ps.setString(1, kw); ps.setString(2, kw);
            ps.setString(3, kw); ps.setString(4, kw);
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

    public List<String[]> getAllUsers() { return searchUsers(""); }

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

            PreparedStatement ps = conn.prepareStatement(
                "UPDATE users SET status = ?, gender = ?, phone = ? WHERE user_id = ?");
            ps.setString(1, newStatus.toLowerCase());
            ps.setString(2, newGender.toLowerCase());
            ps.setString(3, newPhone);
            ps.setInt(4, userId);
            ps.executeUpdate();

            PreparedStatement ps2 = conn.prepareStatement(
                "UPDATE patients SET gender = ?, contact_number = ? WHERE user_id = ?");
            ps2.setString(1, newGender.toLowerCase());
            ps2.setString(2, newPhone);
            ps2.setInt(3, userId);
            ps2.executeUpdate();

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

    // ── DOCTORS ──────────────────────────────────────────────────

    // FIX: Added u.status to query and JOIN with users table
    public List<String[]> searchDoctors(String keyword) {
        List<String[]> list = new ArrayList<>();
        String sql = "SELECT d.doctor_id, d.full_name, d.contact_number, " +
                     "d.specialization, dept.department_name, d.availability, u.status " +
                     "FROM doctors d " +
                     "JOIN departments dept ON d.department_id = dept.department_id " +
                     "JOIN users u ON d.user_id = u.user_id " +
                     "WHERE d.full_name LIKE ? OR d.specialization LIKE ? " +
                     "OR dept.department_name LIKE ?";
        try {
            Connection conn = new MySqlConnection().openConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            String kw = "%" + keyword + "%";
            ps.setString(1, kw); ps.setString(2, kw); ps.setString(3, kw);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new String[]{
                    rs.getString("doctor_id"),
                    rs.getString("full_name"),
                    rs.getString("contact_number"),
                    rs.getString("specialization"),
                    rs.getString("department_name"),
                    rs.getString("availability"),
                    rs.getString("status")        // FIX: added status
                });
            }
            conn.close();
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public boolean addDoctor(String ignoredId, int ignoredUserId, String fullName,
                              String phone, String specialization,
                              int departmentId, String availability) {
        Connection conn = null;
        try {
            conn = new MySqlConnection().openConnection();
            conn.setAutoCommit(false);

            // 1. Create user account
            String userSql = "INSERT INTO users (username, password, role, status, full_name, phone) " +
                             "VALUES (?, 'doctor123', 'doctor', 'active', ?, ?)";
            PreparedStatement userPs = conn.prepareStatement(userSql,
                java.sql.Statement.RETURN_GENERATED_KEYS);
            userPs.setString(1, fullName.toLowerCase().replace(" ", ".") + "@hospital.com");
            userPs.setString(2, fullName);
            userPs.setString(3, phone);
            userPs.executeUpdate();

            // 2. Get generated user_id
            ResultSet keys = userPs.getGeneratedKeys();
            int newUserId = -1;
            if (keys.next()) newUserId = keys.getInt(1);

            // 3. Generate doctor_id from real user_id
            String realDoctorId = "d-" + String.format("%03d", newUserId);

            // 4. Insert into doctors
            String docSql = "INSERT INTO doctors (doctor_id, user_id, full_name, " +
                            "specialization, department_id, contact_number, availability) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement docPs = conn.prepareStatement(docSql);
            docPs.setString(1, realDoctorId);
            docPs.setInt(2, newUserId);
            docPs.setString(3, fullName);
            docPs.setString(4, specialization);
            docPs.setInt(5, departmentId);
            docPs.setString(6, phone);
            docPs.setString(7, availability.toLowerCase()); // FIX: ensure lowercase for ENUM
            docPs.executeUpdate();

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

    // FIX: Added status parameter, now also updates users table
    public boolean updateDoctor(String doctorId, String fullName, String phone,
                                 String specialization, int departmentId,
                                 String availability, String status) {
        Connection conn = null;
        try {
            conn = new MySqlConnection().openConnection();
            conn.setAutoCommit(false);

            // Update doctors table
            PreparedStatement ps = conn.prepareStatement(
                "UPDATE doctors SET full_name=?, contact_number=?, " +
                "specialization=?, department_id=?, availability=? " +
                "WHERE doctor_id=?");
            ps.setString(1, fullName);
            ps.setString(2, phone);
            ps.setString(3, specialization);
            ps.setInt(4, departmentId);
            ps.setString(5, availability.toLowerCase()); // FIX: ensure lowercase for ENUM
            ps.setString(6, doctorId);
            ps.executeUpdate();

            // FIX: Also update users table (status, full_name, phone)
            PreparedStatement ps2 = conn.prepareStatement(
                "UPDATE users u JOIN doctors d ON u.user_id = d.user_id " +
                "SET u.status=?, u.full_name=?, u.phone=? " +
                "WHERE d.doctor_id=?");
            ps2.setString(1, status.toLowerCase());
            ps2.setString(2, fullName);
            ps2.setString(3, phone);
            ps2.setString(4, doctorId);
            ps2.executeUpdate();

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

    public boolean removeDoctor(String doctorId) {
        Connection conn = null;
        try {
            conn = new MySqlConnection().openConnection();
            conn.setAutoCommit(false);

            // Get user_id first
            PreparedStatement getUser = conn.prepareStatement(
                "SELECT user_id FROM doctors WHERE doctor_id=?");
            getUser.setString(1, doctorId);
            ResultSet rs = getUser.executeQuery();
            int userId = -1;
            if (rs.next()) userId = rs.getInt("user_id");

            // Delete doctor
            PreparedStatement delDoc = conn.prepareStatement(
                "DELETE FROM doctors WHERE doctor_id=?");
            delDoc.setString(1, doctorId);
            delDoc.executeUpdate();

            // Delete user if found
            if (userId != -1) {
                PreparedStatement delUser = conn.prepareStatement(
                    "DELETE FROM users WHERE user_id=?");
                delUser.setInt(1, userId);
                delUser.executeUpdate();
            }

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

    // ── DEPARTMENTS ───────────────────────────────────────────────

    public List<String[]> searchDepartments(String keyword) {
        List<String[]> list = new ArrayList<>();
        String sql = "SELECT d.department_id, d.department_name, " +
                     "COALESCE((SELECT doc.full_name FROM doctors doc " +
                     "WHERE doc.department_id = d.department_id LIMIT 1), 'N/A') AS head_doctor, " +
                     "COUNT(doc2.doctor_id) AS total_doctors " +
                     "FROM departments d " +
                     "LEFT JOIN doctors doc2 ON doc2.department_id = d.department_id " +
                     "WHERE d.department_name LIKE ? " +
                     "GROUP BY d.department_id, d.department_name";
        try {
            Connection conn = new MySqlConnection().openConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new String[]{
                    rs.getString("department_id"),
                    rs.getString("department_name"),
                    rs.getString("head_doctor"),
                    rs.getString("total_doctors")
                });
            }
            conn.close();
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public boolean addDepartment(String name) {
        String sql = "INSERT INTO departments (department_name) VALUES (?)";
        try {
            Connection conn = new MySqlConnection().openConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            int rows = ps.executeUpdate();
            conn.close();
            return rows > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public boolean updateDepartment(int deptId, String name) {
        String sql = "UPDATE departments SET department_name=? WHERE department_id=?";
        try {
            Connection conn = new MySqlConnection().openConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, deptId);
            int rows = ps.executeUpdate();
            conn.close();
            return rows > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public boolean removeDepartment(int deptId) {
        String sql = "DELETE FROM departments WHERE department_id=?";
        try {
            Connection conn = new MySqlConnection().openConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, deptId);
            int rows = ps.executeUpdate();
            conn.close();
            return rows > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public int getDepartmentIdByName(String name) {
        String sql = "SELECT department_id FROM departments WHERE department_name=?";
        try {
            Connection conn = new MySqlConnection().openConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("department_id");
                conn.close();
                return id;
            }
            conn.close();
        } catch (Exception e) { e.printStackTrace(); }
        return -1;
    }

    public int getNextUserId() {
        String sql = "SELECT COALESCE(MAX(user_id), 0) + 1 AS next_id FROM users";
        try {
            Connection conn = new MySqlConnection().openConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("next_id");
                conn.close();
                return id;
            }
            conn.close();
        } catch (Exception e) { e.printStackTrace(); }
        return 1;
    }
}