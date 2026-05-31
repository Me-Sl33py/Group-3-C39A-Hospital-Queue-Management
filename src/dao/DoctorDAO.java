/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao;
 
import model.Doctor;
 
import java.sql.*;
 
public class DoctorDAO {
 
    // ── Create table if it does not exist ─────────────────────────────────────
    public void createTableIfNotExists() {
        String sql = """
                CREATE TABLE IF NOT EXISTS doctors (
                    doctor_id       INT          AUTO_INCREMENT PRIMARY KEY,
                    full_name       VARCHAR(100) NOT NULL,
                    email           VARCHAR(100) NOT NULL UNIQUE,
                    phone           VARCHAR(30),
                    specialization  VARCHAR(100),
                    assigned_room   VARCHAR(100),
                    shift_hours     VARCHAR(100),
                    security_level  VARCHAR(50)  DEFAULT 'Level 1',
                    account_status  VARCHAR(20)  DEFAULT 'ACTIVE',
                    last_login      VARCHAR(50)
                );
                """;
        try (Connection conn = DatabaseConnection.getConnection();
             Statement  stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("doctors table ready.");
        } catch (SQLException e) {
            System.err.println("createTableIfNotExists error: " + e.getMessage());
        }
    }
 
    // ── SELECT doctor by ID ───────────────────────────────────────────────────
    public Doctor getDoctorById(int doctorId) {
        String sql = "SELECT doctor_id, full_name, email, phone, specialization, " +
                     "assigned_room, shift_hours, security_level, account_status, " +
                     "last_login FROM doctors WHERE doctor_id = ?";
 
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
 
            ps.setInt(1, doctorId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("getDoctorById error: " + e.getMessage());
        }
        return null;
    }
 
    // ── SELECT doctor by email (used for login) ───────────────────────────────
    public Doctor getDoctorByEmail(String email) {
        String sql = "SELECT doctor_id, full_name, email, phone, specialization, " +
                     "assigned_room, shift_hours, security_level, account_status, " +
                     "last_login FROM doctors WHERE email = ?";
 
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
 
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("getDoctorByEmail error: " + e.getMessage());
        }
        return null;
    }
 
    // ── INSERT new doctor ─────────────────────────────────────────────────────
    public boolean insertDoctor(Doctor doctor) {
        String sql = "INSERT INTO doctors " +
                     "(full_name, email, phone, specialization, assigned_room, " +
                     "shift_hours, security_level, account_status, last_login) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
 
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
 
            ps.setString(1, doctor.getFullName());
            ps.setString(2, doctor.getEmail());
            ps.setString(3, doctor.getPhone());
            ps.setString(4, doctor.getSpecialization());
            ps.setString(5, doctor.getAssignedRoom());
            ps.setString(6, doctor.getShiftHours());
            ps.setString(7, doctor.getSecurityLevel());
            ps.setString(8, doctor.getAccountStatus());
            ps.setString(9, doctor.getLastLogin());
            int rows = ps.executeUpdate();
            return rows > 0;
 
        } catch (SQLException e) {
            System.err.println("insertDoctor error: " + e.getMessage());
            return false;
        }
    }
 
    // ── UPDATE doctor profile (editable fields only) ──────────────────────────
    public boolean updateDoctorProfile(Doctor doctor) {
        String sql = "UPDATE doctors SET full_name = ?, email = ?, phone = ?, " +
                     "specialization = ?, assigned_room = ? WHERE doctor_id = ?";
 
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
 
            ps.setString(1, doctor.getFullName());
            ps.setString(2, doctor.getEmail());
            ps.setString(3, doctor.getPhone());
            ps.setString(4, doctor.getSpecialization());
            ps.setString(5, doctor.getAssignedRoom());
            ps.setInt(6, doctor.getDoctorId());
            int rows = ps.executeUpdate();
            return rows > 0;
 
        } catch (SQLException e) {
            System.err.println("updateDoctorProfile error: " + e.getMessage());
            return false;
        }
    }
 
    // ── UPDATE last login timestamp ───────────────────────────────────────────
    public boolean updateLastLogin(int doctorId, String lastLogin) {
        String sql = "UPDATE doctors SET last_login = ? WHERE doctor_id = ?";
 
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
 
            ps.setString(1, lastLogin);
            ps.setInt(2, doctorId);
            int rows = ps.executeUpdate();
            return rows > 0;
 
        } catch (SQLException e) {
            System.err.println("updateLastLogin error: " + e.getMessage());
            return false;
        }
    }
 
    // ── DELETE doctor ─────────────────────────────────────────────────────────
    public boolean deleteDoctor(int doctorId) {
        String sql = "DELETE FROM doctors WHERE doctor_id = ?";
 
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
 
            ps.setInt(1, doctorId);
            int rows = ps.executeUpdate();
            return rows > 0;
 
        } catch (SQLException e) {
            System.err.println("deleteDoctor error: " + e.getMessage());
            return false;
        }
    }
 
    // ── Helper: map ResultSet row → Doctor ───────────────────────────────────
    private Doctor mapRow(ResultSet rs) throws SQLException {
        return new Doctor(
                rs.getInt("doctor_id"),
                rs.getString("full_name"),
                rs.getString("email"),
                rs.getString("phone"),
                rs.getString("specialization"),
                rs.getString("assigned_room"),
                rs.getString("shift_hours"),
                rs.getString("security_level"),
                rs.getString("account_status"),
                rs.getString("last_login")
        );
    }
}