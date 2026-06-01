/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao;

import model.MedicalRecord;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordDAO {

    public void createTableIfNotExists() {
        // Table already exists in your team's schema — nothing to create
    }

    public boolean insertRecord(MedicalRecord record) {
        String sql = "INSERT INTO medical_records " +
                     "(appointment_id, patient_id, doctor_id, diagnosis, prescription, notes) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, record.getAppointmentId());
            ps.setString(2, record.getPatientId());
            ps.setString(3, record.getDoctorId());
            ps.setString(4, record.getDiagnosis());
            ps.setString(5, record.getPrescription());
            ps.setString(6, record.getNotes());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("insertRecord error: " + e.getMessage());
            return false;
        }
    }

    public List<MedicalRecord> getRecordsByPatient(String patientId) {
        List<MedicalRecord> list = new ArrayList<>();
        String sql = "SELECT record_id, appointment_id, patient_id, doctor_id, " +
                     "diagnosis, prescription, notes FROM medical_records " +
                     "WHERE patient_id = ? ORDER BY created_at DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, patientId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new MedicalRecord(
                        rs.getInt("record_id"),
                        rs.getInt("appointment_id"),
                        rs.getString("patient_id"),
                        rs.getString("doctor_id"),
                        rs.getString("diagnosis"),
                        rs.getString("prescription"),
                        rs.getString("notes")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("getRecordsByPatient error: " + e.getMessage());
        }
        return list;
    }
}