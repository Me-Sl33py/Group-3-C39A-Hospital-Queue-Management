package dao;
import model.Doctor;
import database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    public void createTableIfNotExists() {}

    public Doctor getDoctorById(String doctorId) {
        String sql = "SELECT d.doctor_id, d.user_id, d.full_name, d.specialization, " +
                     "d.department_id, dep.department_name, d.contact_number, d.availability " +
                     "FROM doctors d " +
                     "LEFT JOIN departments dep ON d.department_id = dep.department_id " +
                     "WHERE d.doctor_id = ?";
        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, doctorId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        } catch (SQLException e) {
            System.err.println("getDoctorById error: " + e.getMessage());
        }
        return null;
    }

    public boolean updateDoctorProfile(Doctor doctor) {
        String sql = "UPDATE doctors SET full_name = ?, specialization = ?, " +
                     "contact_number = ? WHERE doctor_id = ?";
        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, doctor.getFullName());
            ps.setString(2, doctor.getSpecialization());
            ps.setString(3, doctor.getContactNumber());
            ps.setString(4, doctor.getDoctorId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("updateDoctorProfile error: " + e.getMessage());
            return false;
        }
    }

    public List<Doctor> getAvailableDoctorsByDepartment(int departmentId) {
        List<Doctor> list = new ArrayList<>();
        String sql = "SELECT d.doctor_id, d.user_id, d.full_name, d.specialization, " +
                     "d.department_id, dep.department_name, d.contact_number, d.availability " +
                     "FROM doctors d " +
                     "LEFT JOIN departments dep ON d.department_id = dep.department_id " +
                     "WHERE d.department_id = ? AND d.availability = 'available'";
        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, departmentId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            System.err.println("getAvailableDoctorsByDepartment error: " + e.getMessage());
        }
        return list;
    }

    public List<Doctor> getDoctorsByDepartment(int departmentId) {
        List<Doctor> doctors = new ArrayList<>();
        String query = "SELECT * FROM doctors WHERE department_id = ?";
        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, departmentId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                doctors.add(new Doctor(
                    rs.getString("doctor_id"), 
                    rs.getString("full_name"), 
                    rs.getString("specialization"), 
                    rs.getInt("department_id"), 
                    rs.getString("availability")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }
    
    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        String query = "SELECT * FROM doctors";
        try (Connection conn = MySqlConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                doctors.add(new Doctor(
                    rs.getString("doctor_id"), 
                    rs.getString("full_name"), 
                    rs.getString("specialization"), 
                    rs.getInt("department_id"), 
                    rs.getString("availability")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }

    private Doctor mapRow(ResultSet rs) throws SQLException {
        Doctor d = new Doctor(
            rs.getString("doctor_id"),
            rs.getInt("user_id"),
            rs.getString("full_name"),
            rs.getString("specialization"),
            rs.getInt("department_id"),
            rs.getString("contact_number"),
            rs.getString("availability")
        );
        try { d.setDepartmentName(rs.getString("department_name")); } catch (Exception e) {}
        return d;
    }

    private String generateDoctorId(Connection c) throws SQLException {
        String sql = "SELECT doctor_id FROM doctors ORDER BY doctor_id DESC LIMIT 1";
        try (PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                String lastId = rs.getString("doctor_id");
                int num = Integer.parseInt(lastId.substring(2));
                return "D-" + (num + 1);
            } else {
                return "D-101";
            }
        }
    }

    public java.util.List<String[]> searchDoctors(String keyword, String deptFilter, String availFilter, String statusFilter) {
        java.util.List<String[]> list = new java.util.ArrayList<>();

        StringBuilder sql = new StringBuilder(
            "SELECT d.doctor_id, up.full_name, up.contact_number, " +
            "d.specialization, dept.department_name, " +
            "d.availability, u.status " +
            "FROM doctors d " +
            "JOIN user_profiles up ON d.user_id = up.user_id " +
            "JOIN users u ON d.user_id = u.user_id " +
            "JOIN departments dept ON d.department_id = dept.department_id " +
            "WHERE (up.full_name LIKE ? " +
            "OR d.specialization LIKE ? " +
            "OR dept.department_name LIKE ?) "
        );

        if (deptFilter != null && !deptFilter.equalsIgnoreCase("All")) {
            sql.append(" AND dept.department_name = ? ");
        }
        if (availFilter != null && !availFilter.equalsIgnoreCase("All")) {
            sql.append(" AND d.availability = ? ");
        }
        if (statusFilter != null && !statusFilter.equalsIgnoreCase("All")) {
            sql.append(" AND u.status = ? ");
        }

        try (Connection c = database.MySqlConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql.toString())) {

            String kw = "%" + keyword + "%";

            ps.setString(1, kw);
            ps.setString(2, kw);
            ps.setString(3, kw);
            
            int paramIndex = 4;
            if (deptFilter != null && !deptFilter.equalsIgnoreCase("All")) {
                ps.setString(paramIndex++, deptFilter);
            }
            if (availFilter != null && !availFilter.equalsIgnoreCase("All")) {
                ps.setString(paramIndex++, availFilter.toLowerCase()); // 'available' / 'unavailable'
            }
            if (statusFilter != null && !statusFilter.equalsIgnoreCase("All")) {
                ps.setString(paramIndex++, statusFilter.toLowerCase());
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new String[]{
                    rs.getString("doctor_id"),
                    rs.getString("full_name"),
                    rs.getString("contact_number"),
                    rs.getString("specialization"),
                    rs.getString("department_name"),
                    rs.getString("availability"),
                    rs.getString("status")
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean addDoctor(String fullName,
                             String phone,
                             String specialization,
                             int deptId,
                             String availability) {

        try (Connection c = database.MySqlConnection.getConnection()) {
            c.setAutoCommit(false);
            
            // 1. insert user
            String username = "doc_" + System.currentTimeMillis() % 10000;
            String uSql = "INSERT INTO users (username, password) VALUES (?, 'doc123')";
            int uId = 0;
            try (PreparedStatement ps = c.prepareStatement(uSql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, username);
                ps.executeUpdate();
                try(ResultSet rs = ps.getGeneratedKeys()){
                    if(rs.next()) uId = rs.getInt(1);
                }
            }
            
            // 2. insert profile
            String pSql = "INSERT INTO user_profiles (user_id, full_name, contact_number, role) VALUES (?, ?, ?, 'doctor')";
            try(PreparedStatement ps = c.prepareStatement(pSql)){
                ps.setInt(1, uId);
                ps.setString(2, fullName);
                ps.setString(3, phone);
                ps.executeUpdate();
            }
            
            // 3. insert doctor
            String dSql = "INSERT INTO doctors (doctor_id, user_id, full_name, username, specialization, department_id, availability) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try(PreparedStatement ps = c.prepareStatement(dSql)){
                ps.setString(1, generateDoctorId(c));
                ps.setInt(2, uId);
                ps.setString(3, fullName);
                ps.setString(4, username);
                ps.setString(5, specialization);
                ps.setInt(6, deptId);
                ps.setString(7, availability);
                ps.executeUpdate();
            }
            
            c.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateDoctor(String doctorId,
                                String fullName,
                                String phone,
                                String specialization,
                                int deptId,
                                String availability,
                                String status) {
        try (Connection c = database.MySqlConnection.getConnection()) {
            c.setAutoCommit(false);
            
            // Get user_id from doctors
            int uId = -1;
            try(PreparedStatement ps = c.prepareStatement("SELECT user_id FROM doctors WHERE doctor_id=?")){
                ps.setString(1, doctorId);
                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next()) uId = rs.getInt("user_id");
                }
            }
            if (uId == -1) return false;
            
            // Update profile
            try(PreparedStatement ps = c.prepareStatement("UPDATE user_profiles SET full_name=?, contact_number=? WHERE user_id=?")){
                ps.setString(1, fullName);
                ps.setString(2, phone);
                ps.setInt(3, uId);
                ps.executeUpdate();
            }
            
            // Update user status
            try(PreparedStatement ps = c.prepareStatement("UPDATE users SET status=? WHERE user_id=?")){
                ps.setString(1, status);
                ps.setInt(2, uId);
                ps.executeUpdate();
            }
            
            // Update doctor
            try(PreparedStatement ps = c.prepareStatement("UPDATE doctors SET specialization=?, department_id=?, availability=? WHERE doctor_id=?")){
                ps.setString(1, specialization);
                ps.setInt(2, deptId);
                ps.setString(3, availability);
                ps.setString(4, doctorId);
                ps.executeUpdate();
            }
            
            c.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deactivateDoctor(String doctorId) {
        String sql = "UPDATE users SET status = 'deactive' WHERE user_id = (SELECT user_id FROM doctors WHERE doctor_id=?)";
        try (Connection c = database.MySqlConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, doctorId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { 
            e.printStackTrace(); 
        }
        return false;
    }
}
