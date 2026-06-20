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
        try (Connection conn = database.MySqlConnection.getConnection();
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
        String sql = "SELECT m.record_id, m.appointment_id, m.patient_id, m.doctor_id, " +
                     "u.full_name as doctor_name, " +
                     "m.diagnosis, m.prescription, m.notes, m.created_at " +
                     "FROM medical_records m " +
                     "LEFT JOIN users u ON m.doctor_id = u.user_id " +
                     "WHERE m.patient_id = ? ORDER BY m.created_at DESC";
        try (Connection conn = database.MySqlConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, patientId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String doctorName = rs.getString("doctor_name");
                    if (doctorName == null) doctorName = "Unknown Doctor";
                    
                    String createdAt = "";
                    if (rs.getTimestamp("created_at") != null) {
                        createdAt = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(rs.getTimestamp("created_at"));
                    }

                    list.add(new MedicalRecord(
                        rs.getInt("record_id"),
                        rs.getInt("appointment_id"),
                        rs.getString("patient_id"),
                        rs.getString("doctor_id"),
                        doctorName,
                        rs.getString("diagnosis"),
                        rs.getString("prescription"),
                        rs.getString("notes"),
                        createdAt
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("getRecordsByPatient error: " + e.getMessage());
        }
        return list;
    }

    public List<MedicalRecord> getMedicalRecordsByPatient(String patientId) {
        List<MedicalRecord> list = new ArrayList<>();
        String sql = "SELECT m.record_id, m.appointment_id, m.patient_id, m.doctor_id, " +
                     "m.diagnosis, m.prescription, m.notes, m.created_at AS recordDate, " +
                     "up.full_name AS doctorName, dep.department_name AS departmentName " +
                     "FROM medical_records m " +
                     "LEFT JOIN doctors d ON m.doctor_id = d.doctor_id " +
                     "LEFT JOIN user_profiles up ON d.user_id = up.user_id " +
                     "LEFT JOIN departments dep ON d.department_id = dep.department_id " +
                     "WHERE m.patient_id = ? ORDER BY m.created_at DESC";
        try (Connection conn = database.MySqlConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, patientId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String docNameStr = rs.getString("doctorName");
                    if (docNameStr == null) {
                        docNameStr = "Unknown Doctor";
                    }

                    String createdAt = "";
                    if (rs.getTimestamp("recordDate") != null) {
                        createdAt = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(rs.getTimestamp("recordDate"));
                    }

                    MedicalRecord record = new MedicalRecord(
                        rs.getInt("record_id"),
                        rs.getInt("appointment_id"),
                        rs.getString("patient_id"),
                        rs.getString("doctor_id"),
                        docNameStr,
                        rs.getString("diagnosis"),
                        rs.getString("prescription"),
                        rs.getString("notes"),
                        createdAt
                    );
                    record.setRecordDate(rs.getTimestamp("recordDate"));
                    record.setDepartmentName(rs.getString("departmentName"));
                    list.add(record);
                }
            }
        } catch (SQLException e) {
            System.err.println("getMedicalRecordsByPatient error: " + e.getMessage());
        }
        return list;
    }
}
