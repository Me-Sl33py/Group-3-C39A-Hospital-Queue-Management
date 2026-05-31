/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
 
import model.Patient;
 
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
 
public class PatientDAO {
 
    // ── Create table if it does not exist ─────────────────────────────────────
    public void createTableIfNotExists() {
        String sql = """
                CREATE TABLE IF NOT EXISTS patients (
                    patient_id  INT          AUTO_INCREMENT PRIMARY KEY,
                    name        VARCHAR(100) NOT NULL,
                    status      VARCHAR(50)  NOT NULL DEFAULT 'Waiting'
                );
                """;
        try (Connection conn = DatabaseConnection.getConnection();
             Statement  stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("patients table ready.");
        } catch (SQLException e) {
            System.err.println("createTableIfNotExists error: " + e.getMessage());
        }
    }
 
    // ── SELECT all patients ───────────────────────────────────────────────────
    public List<Patient> getAllPatients() {
        List<Patient> list = new ArrayList<>();
        String sql = "SELECT patient_id, name, status FROM patients";
 
        try (Connection conn = DatabaseConnection.getConnection();
             Statement  stmt = conn.createStatement();
             ResultSet  rs   = stmt.executeQuery(sql)) {
 
            while (rs.next()) {
                Patient p = new Patient(
                        rs.getInt("patient_id"),
                        rs.getString("name"),
                        rs.getString("status")
                );
                list.add(p);
            }
        } catch (SQLException e) {
            System.err.println("getAllPatients error: " + e.getMessage());
        }
        return list;
    }
 
    // ── SELECT single patient by ID ───────────────────────────────────────────
    public Patient getPatientById(int patientId) {
        String sql = "SELECT patient_id, name, status FROM patients WHERE patient_id = ?";
 
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
 
            ps.setInt(1, patientId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Patient(
                            rs.getInt("patient_id"),
                            rs.getString("name"),
                            rs.getString("status")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("getPatientById error: " + e.getMessage());
        }
        return null;
    }
 
    // ── SELECT next waiting patient ───────────────────────────────────────────
    public Patient getNextWaitingPatient() {
        String sql = "SELECT patient_id, name, status FROM patients " +
                     "WHERE status = 'Waiting' ORDER BY patient_id ASC LIMIT 1";
 
        try (Connection conn = DatabaseConnection.getConnection();
             Statement  stmt = conn.createStatement();
             ResultSet  rs   = stmt.executeQuery(sql)) {
 
            if (rs.next()) {
                return new Patient(
                        rs.getInt("patient_id"),
                        rs.getString("name"),
                        rs.getString("status")
                );
            }
        } catch (SQLException e) {
            System.err.println("getNextWaitingPatient error: " + e.getMessage());
        }
        return null;
    }
 
    // ── INSERT new patient ────────────────────────────────────────────────────
    public boolean insertPatient(Patient patient) {
        String sql = "INSERT INTO patients (name, status) VALUES (?, ?)";
 
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
 
            ps.setString(1, patient.getName());
            ps.setString(2, patient.getStatus());
            int rows = ps.executeUpdate();
            return rows > 0;
 
        } catch (SQLException e) {
            System.err.println("insertPatient error: " + e.getMessage());
            return false;
        }
    }
 
    // ── UPDATE patient status ─────────────────────────────────────────────────
    public boolean updatePatientStatus(int patientId, String newStatus) {
        String sql = "UPDATE patients SET status = ? WHERE patient_id = ?";
 
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
 
            ps.setString(1, newStatus);
            ps.setInt(2, patientId);
            int rows = ps.executeUpdate();
            return rows > 0;
 
        } catch (SQLException e) {
            System.err.println("updatePatientStatus error: " + e.getMessage());
            return false;
        }
    }
 
    // ── DELETE patient ────────────────────────────────────────────────────────
    public boolean deletePatient(int patientId) {
        String sql = "DELETE FROM patients WHERE patient_id = ?";
 
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
 
            ps.setInt(1, patientId);
            int rows = ps.executeUpdate();
            return rows > 0;
 
        } catch (SQLException e) {
            System.err.println("deletePatient error: " + e.getMessage());
            return false;
        }
    }
}
 
