package dao;

import model.Appointment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

    public boolean createAppointment(String patientId, String doctorId, LocalDate date, LocalTime time, String reason) {
        String sql = "INSERT INTO appointments (patient_id, doctor_id, appointment_date, appointment_time, reason, status, type) " +
                     "VALUES (?, ?, ?, ?, ?, 'pending', 'online')";
        
        try (Connection conn = database.MySqlConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, patientId);
            pstmt.setString(2, doctorId);
            pstmt.setDate(3, Date.valueOf(date));
            pstmt.setTime(4, Time.valueOf(time));
            pstmt.setString(5, reason);
            
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Appointment> getAppointmentsByPatient(String patientId) {
        List<Appointment> list = new ArrayList<>();
        String sql = "SELECT a.*, d.full_name AS doctor_name, dep.department_name " +
                     "FROM appointments a " +
                     "JOIN doctors d ON a.doctor_id = d.doctor_id " +
                     "JOIN departments dep ON d.department_id = dep.department_id " +
                     "WHERE a.patient_id = ? " +
                     "ORDER BY a.appointment_date DESC, a.appointment_time DESC";
                     
        try (Connection conn = database.MySqlConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, patientId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Appointment a = new Appointment();
                    a.setAppointmentId(rs.getInt("appointment_id"));
                    a.setPatientId(rs.getString("patient_id"));
                    a.setDoctorId(rs.getString("doctor_id"));
                    a.setAppointmentDate(rs.getDate("appointment_date"));
                    a.setAppointmentTime(rs.getTime("appointment_time"));
                    a.setReason(rs.getString("reason"));
                    a.setStatus(rs.getString("status"));
                    a.setType(rs.getString("type"));
                    a.setDoctorName(rs.getString("doctor_name"));
                    a.setDepartmentName(rs.getString("department_name"));
                    list.add(a);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Appointment> getCompletedAppointmentsWithoutRating(String patientId) {
        List<Appointment> list = new ArrayList<>();
        String sql = "SELECT a.*, d.full_name AS doctor_name " +
                     "FROM appointments a " +
                     "JOIN doctors d ON a.doctor_id = d.doctor_id " +
                     "LEFT JOIN ratings r ON a.appointment_id = r.appointment_id " +
                     "WHERE a.patient_id = ? AND a.status = 'completed' AND r.rating_id IS NULL";
                     
        try (Connection conn = database.MySqlConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, patientId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Appointment a = new Appointment();
                    a.setAppointmentId(rs.getInt("appointment_id"));
                    a.setDoctorId(rs.getString("doctor_id"));
                    a.setAppointmentDate(rs.getDate("appointment_date"));
                    a.setDoctorName(rs.getString("doctor_name"));
                    list.add(a);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean cancelAppointment(int appointmentId, String patientId) {
        String sql = "UPDATE appointments SET status = 'cancelled' WHERE appointment_id = ? AND patient_id = ? AND status IN ('pending', 'confirmed')";
        
        try (Connection conn = database.MySqlConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, appointmentId);
            pstmt.setString(2, patientId);
            
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
