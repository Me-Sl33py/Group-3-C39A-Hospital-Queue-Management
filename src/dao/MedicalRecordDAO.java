/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao;
 
import model.MedicalRecord;
 
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
 
public class MedicalRecordDAO {
 
    // ── Create table if it does not exist ─────────────────────────────────────
    public void createTableIfNotExists() {
        String sql = """
                CREATE TABLE IF NOT EXISTS medical_records (
                    record_id      INT           AUTO_INCREMENT PRIMARY KEY,
                    patient_id     INT           NOT NULL,
                    patient_name   VARCHAR(100)  NOT NULL,
                    doctor_id      INT           NOT NULL,
                    clinical_notes TEXT          NOT NULL,
                    date_created   DATETIME      DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY (patient_id) REFERENCES patients(patient_id)
                        ON DELETE CASCADE
                );
                """;
        try (Connection conn = DatabaseConnection.getConnection();
             Statement  stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("medical_records table ready.");
        } catch (SQLException e) {
            System.err.println("createTableIfNotExists error: " + e.getMessage());
        }
    }
 
    // ── SELECT all records ────────────────────────────────────────────────────
    public List<MedicalRecord> getAllRecords() {
        List<MedicalRecord> list = new ArrayList<>();
        String sql = "SELECT record_id, patient_id, patient_name, doctor_id, " +
                     "clinical_notes, date_created FROM medical_records " +
                     "ORDER BY date_created DESC";
 
        try (Connection conn = DatabaseConnection.getConnection();
             Statement  stmt = conn.createStatement();
             ResultSet  rs   = stmt.executeQuery(sql)) {
 
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            System.err.println("getAllRecords error: " + e.getMessage());
        }
        return list;
    }
 
    // ── SELECT records by patient ID ──────────────────────────────────────────
    public List<MedicalRecord> getRecordsByPatient(int patientId) {
        List<MedicalRecord> list = new ArrayList<>();
        String sql = "SELECT record_id, patient_id, patient_name, doctor_id, " +
                     "clinical_notes, date_created FROM medical_records " +
                     "WHERE patient_id = ? ORDER BY date_created DESC";
 
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
 
            ps.setInt(1, patientId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("getRecordsByPatient error: " + e.getMessage());
        }
        return list;
    }
 
    // ── INSERT new medical record ─────────────────────────────────────────────
    public boolean insertRecord(MedicalRecord record) {
        String sql = "INSERT INTO medical_records " +
                     "(patient_id, patient_name, doctor_id, clinical_notes) " +
                     "VALUES (?, ?, ?, ?)";
 
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
 
            ps.setInt(1, record.getPatientId());
            ps.setString(2, record.getPatientName());
            ps.setInt(3, record.getDoctorId());
            ps.setString(4, record.getClinicalNotes());
            int rows = ps.executeUpdate();
            return rows > 0;
 
        } catch (SQLException e) {
            System.err.println("insertRecord error: " + e.getMessage());
            return false;
        }
    }
 
    // ── UPDATE clinical notes ─────────────────────────────────────────────────
    public boolean updateRecord(int recordId, String clinicalNotes) {
        String sql = "UPDATE medical_records SET clinical_notes = ? WHERE record_id = ?";
 
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
 
            ps.setString(1, clinicalNotes);
            ps.setInt(2, recordId);
            int rows = ps.executeUpdate();
            return rows > 0;
 
        } catch (SQLException e) {
            System.err.println("updateRecord error: " + e.getMessage());
            return false;
        }
    }
 
    // ── DELETE record ─────────────────────────────────────────────────────────
    public boolean deleteRecord(int recordId) {
        String sql = "DELETE FROM medical_records WHERE record_id = ?";
 
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
 
            ps.setInt(1, recordId);
            int rows = ps.executeUpdate();
            return rows > 0;
 
        } catch (SQLException e) {
            System.err.println("deleteRecord error: " + e.getMessage());
            return false;
        }
    }
 
    // ── Helper: map ResultSet row → MedicalRecord ─────────────────────────────
    private MedicalRecord mapRow(ResultSet rs) throws SQLException {
        return new MedicalRecord(
                rs.getInt("record_id"),
                rs.getInt("patient_id"),
                rs.getString("patient_name"),
                rs.getInt("doctor_id"),
                rs.getString("clinical_notes"),
                rs.getTimestamp("date_created").toLocalDateTime()
        );
    }
}
