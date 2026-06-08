package dao;
<<<<<<< HEAD

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
}
