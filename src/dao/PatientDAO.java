package dao;

import model.Patient;
import database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class PatientDAO {

    public String insertPatient(Patient patient) {
        String userQuery = "INSERT INTO users (username, password, role) VALUES (?, 'password123', 'patient')";
        String query = "INSERT INTO patients (patient_id, user_id, full_name, dob, age, gender, contact_number, address, blood_group, reason) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement userStmt = conn.prepareStatement(userQuery, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            userStmt.setString(1, patient.getPatientId()); // username is patient_id
            userStmt.executeUpdate();
            
            ResultSet rs = userStmt.getGeneratedKeys();
            int userId = 1;
            if (rs.next()) {
                userId = rs.getInt(1);
            }

            pstmt.setString(1, patient.getPatientId());
            pstmt.setInt(2, userId);
            pstmt.setString(3, patient.getFullName());
            if (patient.getDob() != null) pstmt.setDate(4, new java.sql.Date(patient.getDob().getTime()));
            else pstmt.setNull(4, java.sql.Types.DATE);
            pstmt.setInt(5, patient.getAge());
            pstmt.setString(6, patient.getGender().toLowerCase());
            pstmt.setString(7, patient.getContactNumber());
            pstmt.setString(8, patient.getAddress());
            pstmt.setString(9, patient.getBloodGroup());
            pstmt.setString(10, patient.getReason());
            
            pstmt.executeUpdate();
            return patient.getPatientId();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Patient getPatientById(String patientId) {
        String sql = "SELECT p.*, u.username FROM patients p JOIN users u ON p.user_id = u.user_id WHERE p.patient_id = ?";
        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, patientId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Patient p = new Patient();
                    p.setPatientId(rs.getString("patient_id"));
                    p.setUserId(rs.getInt("user_id"));
                    p.setFullName(rs.getString("full_name"));
                    p.setDob(rs.getDate("dob"));
                    p.setAge(rs.getInt("age"));
                    p.setGender(rs.getString("gender"));
                    p.setContactNumber(rs.getString("contact_number"));
                    p.setAddress(rs.getString("address"));
                    try { p.setBloodGroup(rs.getString("blood_group")); } catch (Exception ignored) {}
                    try { p.setReason(rs.getString("reason")); } catch (Exception ignored) {}
                    try { p.setCreatedAt(rs.getTimestamp("created_at")); } catch (Exception ignored) {}
                    return p;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Patient getLatestPatient() {
        String query = "SELECT * FROM patients ORDER BY created_at DESC LIMIT 1";
        try (Connection conn = MySqlConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                Patient p = new Patient();
                p.setPatientId(rs.getString("patient_id"));
                p.setUserId(rs.getInt("user_id"));
                p.setFullName(rs.getString("full_name"));
                p.setDob(rs.getDate("dob"));
                p.setAge(rs.getInt("age"));
                p.setGender(rs.getString("gender"));
                p.setContactNumber(rs.getString("contact_number"));
                p.setAddress(rs.getString("address"));
                p.setBloodGroup(rs.getString("blood_group"));
                p.setReason(rs.getString("reason"));
                p.setCreatedAt(rs.getTimestamp("created_at"));
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM patients ORDER BY created_at DESC";
        try (Connection conn = MySqlConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Patient p = new Patient();
                p.setPatientId(rs.getString("patient_id"));
                p.setUserId(rs.getInt("user_id"));
                p.setFullName(rs.getString("full_name"));
                p.setDob(rs.getDate("dob"));
                p.setAge(rs.getInt("age"));
                p.setGender(rs.getString("gender"));
                p.setContactNumber(rs.getString("contact_number"));
                p.setAddress(rs.getString("address"));
                p.setBloodGroup(rs.getString("blood_group"));
                p.setReason(rs.getString("reason"));
                p.setCreatedAt(rs.getTimestamp("created_at"));
                patients.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    public int getTotalPatientsCount() {
        String query = "SELECT COUNT(*) FROM patients";
        try (Connection conn = MySqlConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean updatePatientProfile(String patientId, Date dob, int age, String contactNumber, String address) {
        String sql = "UPDATE patients SET dob = ?, age = ?, contact_number = ?, address = ? WHERE patient_id = ?";
        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            if (dob != null) pstmt.setDate(1, new java.sql.Date(dob.getTime()));
            else pstmt.setNull(1, java.sql.Types.DATE);
            pstmt.setInt(2, age);
            pstmt.setString(3, contactNumber);
            pstmt.setString(4, address);
            pstmt.setString(5, patientId);
            
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePatientProfile(String patientId, Date dob, int age, String contactNumber, String address, String bloodGroup) {
        String sql = "UPDATE patients SET dob = ?, age = ?, contact_number = ?, address = ?, blood_group = ? WHERE patient_id = ?";
        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            if (dob != null) pstmt.setDate(1, new java.sql.Date(dob.getTime()));
            else pstmt.setNull(1, java.sql.Types.DATE);
            pstmt.setInt(2, age);
            pstmt.setString(3, contactNumber);
            pstmt.setString(4, address);
            pstmt.setString(5, bloodGroup);
            pstmt.setString(6, patientId);
            
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUsernameAndPassword(int userId, String newUsername, String newPassword) {
        String sql = "UPDATE users SET username = ?, password = ? WHERE user_id = ?";
        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, newUsername);
            pstmt.setString(2, newPassword);
            pstmt.setInt(3, userId);
            
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateUsername(int userId, String newUsername) {
        String sql = "UPDATE users SET username = ? WHERE user_id = ?";
        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, newUsername);
            pstmt.setInt(2, userId);
            
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean validateCurrentPassword(int userId, String currentPassword) {
        String sql = "SELECT 1 FROM users WHERE user_id = ? AND password = ?";
        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, userId);
            pstmt.setString(2, currentPassword);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public String getUsernameByUserId(int userId) {
        String sql = "SELECT username FROM users WHERE user_id = ?";
        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) return rs.getString("username");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String generateUsername(String fullName) {
        String firstWord = fullName.trim().split("\\s+")[0].toLowerCase();
        int count = 0;
        try (Connection conn = MySqlConnection.getConnection()) {
            String countQuery = "SELECT count(*) FROM users WHERE username LIKE ?";
            try (PreparedStatement ps = conn.prepareStatement(countQuery)) {
                ps.setString(1, firstWord + "%");
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) count = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return firstWord + (count + 1);
    }

    public int insertUser(String username, String password, String role) {
        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) return -1;
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public String generatePatientId() {
        String sql = "SELECT patient_id FROM patients ORDER BY patient_id DESC LIMIT 1";
        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                String lastId = rs.getString("patient_id");
                String[] parts = lastId.split("-");
                if (parts.length > 1) {
                    int lastNumber = Integer.parseInt(parts[1]);
                    return String.format("P-%03d", lastNumber + 1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "P-001";
    }

    public boolean insertPatient(String patientId, int userId, String fullName, java.sql.Date dob, int age, String gender, String contactNumber, String address) {
        String sql = "INSERT INTO patients (patient_id, user_id, full_name, dob, age, gender, contact_number, address) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, patientId);
            pstmt.setInt(2, userId);
            pstmt.setString(3, fullName);
            pstmt.setDate(4, dob);
            pstmt.setInt(5, age);
            pstmt.setString(6, gender);
            pstmt.setString(7, contactNumber);
            pstmt.setString(8, address);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS queue (" +
                     "queue_id INT AUTO_INCREMENT PRIMARY KEY, " +
                     "patient_id VARCHAR(50), " +
                     "doctor_id VARCHAR(50), " +
                     "token_number INT, " +
                     "status VARCHAR(20) DEFAULT 'waiting')";
        try (Connection conn = MySqlConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Patient getNextWaitingPatient() {
        String sql = "SELECT p.patient_id, p.user_id, p.full_name, p.dob, p.age, p.gender, " +
                     "p.contact_number, p.address, p.blood_group, p.created_at, p.updated_at FROM patients p " +
                     "JOIN queue q ON p.patient_id = q.patient_id " +
                     "WHERE q.status = 'waiting' " +
                     "ORDER BY q.token_number ASC LIMIT 1";
        try (Connection conn = MySqlConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                Patient p = new Patient();
                p.setPatientId(rs.getString("patient_id"));
                p.setUserId(rs.getInt("user_id"));
                p.setFullName(rs.getString("full_name"));
                p.setAge(rs.getInt("age"));
                p.setGender(rs.getString("gender"));
                p.setContactNumber(rs.getString("contact_number"));
                p.setAddress(rs.getString("address"));
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateQueueStatus(String patientId, String newStatus) {
        String sql = "UPDATE queue SET status = ? WHERE patient_id = ? AND status != 'completed'";
        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newStatus);
            ps.setString(2, patientId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public java.util.List<Object[]> getQueueByDoctor(String doctorId) {
        java.util.List<Object[]> list = new java.util.ArrayList<>();
        String sql = "SELECT q.token_number, p.full_name, q.status " +
                     "FROM queue q JOIN patients p ON q.patient_id = p.patient_id " +
                     "WHERE q.doctor_id = ? ORDER BY q.token_number ASC";
        try (Connection conn = MySqlConnection.getConnection();
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
            e.printStackTrace();
        }
        return list;
    }
}
