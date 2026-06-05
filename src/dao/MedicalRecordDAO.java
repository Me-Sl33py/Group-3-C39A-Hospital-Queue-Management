package dao;

import db.DBConnection;
import model.MedicalRecord;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordDAO {

    public List<MedicalRecord> getMedicalRecordsByPatient(String patientId) {
        List<MedicalRecord> records = new ArrayList<>();
        String sql = "SELECT m.*, a.appointment_date, d.full_name AS doctor_name, dep.department_name " +
                     "FROM medical_records m " +
                     "JOIN appointments a ON m.appointment_id = a.appointment_id " +
                     "JOIN doctors d ON m.doctor_id = d.doctor_id " +
                     "JOIN departments dep ON d.department_id = dep.department_id " +
                     "WHERE m.patient_id = ? " +
                     "ORDER BY a.appointment_date DESC";
                     
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, patientId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    MedicalRecord r = new MedicalRecord();
                    r.setRecordId(rs.getInt("record_id"));
                    r.setAppointmentId(rs.getInt("appointment_id"));
                    r.setPatientId(rs.getString("patient_id"));
                    r.setDoctorId(rs.getString("doctor_id"));
                    r.setDiagnosis(rs.getString("diagnosis"));
                    r.setPrescription(rs.getString("prescription"));
                    r.setNotes(rs.getString("notes"));
                    r.setRecordDate(rs.getDate("appointment_date"));
                    r.setDoctorName(rs.getString("doctor_name"));
                    r.setDepartmentName(rs.getString("department_name"));
                    records.add(r);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return records;
    }
}
