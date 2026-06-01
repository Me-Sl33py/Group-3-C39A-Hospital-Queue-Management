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

    public void createTableIfNotExists() {
        // Table already exists in your team's schema — nothing to create
    }

    // Get all patients
    public List<Patient> getAllPatients() {
        List<Patient> list = new ArrayList<>();
        String sql = "SELECT patient_id, user_id, full_name, age, gender, " +
                     "contact_number, address FROM patients";
        try (Connection conn = DatabaseConnection.getConnection();
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
        String sql = "SELECT patient_id, user_id, full_name, age, gender, " +
                     "contact_number, address FROM patients WHERE patient_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
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
        String sql = "SELECT p.patient_id, p.user_id, p.full_name, p.age, p.gender, " +
                     "p.contact_number, p.address FROM patients p " +
                     "JOIN queue q ON p.patient_id = q.patient_id " +
                     "WHERE q.status = 'waiting' " +
                     "ORDER BY q.token_number ASC LIMIT 1";
        try (Connection conn = DatabaseConnection.getConnection();
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
        try (Connection conn = DatabaseConnection.getConnection();
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
        try (Connection conn = DatabaseConnection.getConnection();
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
        return new Patient(
            rs.getString("patient_id"),
            rs.getInt("user_id"),
            rs.getString("full_name"),
            rs.getInt("age"),
            rs.getString("gender"),
            rs.getString("contact_number"),
            rs.getString("address")
        );
    }
}