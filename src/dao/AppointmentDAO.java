package dao;

import model.Appointment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
        String sql = "SELECT a.*, dup.full_name AS doctor_name, dep.department_name " +
                     "FROM appointments a " +
                     "JOIN doctors d ON a.doctor_id = d.doctor_id " +
                     "JOIN user_profiles dup ON d.user_id = dup.user_id " +
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
        String sql = "SELECT a.*, dup.full_name AS doctor_name " +
                     "FROM appointments a " +
                     "JOIN doctors d ON a.doctor_id = d.doctor_id " +
                     "JOIN user_profiles dup ON d.user_id = dup.user_id " +
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

    public List<Appointment> getPendingAppointments() {
        List<Appointment> list = new ArrayList<>();
        String sql = "SELECT a.*, pup.full_name AS patient_name, dup.full_name AS doctor_name " +
                     "FROM appointments a " +
                     "JOIN patients p ON a.patient_id = p.patient_id " +
                     "JOIN user_profiles pup ON p.user_id = pup.user_id " +
                     "JOIN doctors d ON a.doctor_id = d.doctor_id " +
                     "JOIN user_profiles dup ON d.user_id = dup.user_id " +
                     "WHERE a.status = 'pending' " +
                     "ORDER BY a.appointment_date ASC, a.appointment_time ASC";
        try (Connection conn = database.MySqlConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
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
                list.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean confirmArrival(int appointmentId) {
        String sql = "UPDATE appointments SET status = 'confirmed' WHERE appointment_id = ? AND status = 'pending'";
        try (Connection conn = database.MySqlConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, appointmentId);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean completeAppointment(int appointmentId) {
        String updateAppt = "UPDATE appointments SET status = 'completed' WHERE appointment_id = ?";
        String updateQueue = "UPDATE queue SET status = 'completed' WHERE appointment_id = ?";
        try (Connection conn = database.MySqlConnection.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps1 = conn.prepareStatement(updateAppt);
                 PreparedStatement ps2 = conn.prepareStatement(updateQueue)) {
                
                ps1.setInt(1, appointmentId);
                ps1.executeUpdate();
                
                ps2.setInt(1, appointmentId);
                ps2.executeUpdate();
                
                conn.commit();
                return true;
            } catch (Exception e) {
                conn.rollback();
                e.printStackTrace();
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public List<Appointment> searchPendingAppointments(String keyword) {
        List<Appointment> list = new ArrayList<>();
        String sql = "SELECT a.*, pup.full_name AS patient_name, pup.contact_number AS patient_phone, dup.full_name AS doctor_name, d.department_id " +
                     "FROM appointments a " +
                     "JOIN patients p ON a.patient_id = p.patient_id " +
                     "JOIN user_profiles pup ON p.user_id = pup.user_id " +
                     "JOIN doctors d ON a.doctor_id = d.doctor_id " +
                     "JOIN user_profiles dup ON d.user_id = dup.user_id " +
                     "WHERE a.status = 'pending' ";
        
        if (keyword != null && !keyword.trim().isEmpty()) {
            sql += "AND (pup.full_name LIKE ? OR p.patient_id LIKE ?) ";
        }
        sql += "ORDER BY a.appointment_date ASC, a.appointment_time ASC";
                     
        try (Connection conn = database.MySqlConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            if (keyword != null && !keyword.trim().isEmpty()) {
                pstmt.setString(1, "%" + keyword + "%");
                pstmt.setString(2, "%" + keyword + "%");
            }
            
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
                    a.setPatientName(rs.getString("patient_name"));
                    a.setPatientPhone(rs.getString("patient_phone"));
                    a.setDepartmentId(rs.getInt("department_id"));
                    list.add(a);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int countConfirmedToday() {
        String sql = "SELECT COUNT(*) FROM appointments WHERE status = 'confirmed' AND appointment_date = CURDATE()";
        try (Connection conn = database.MySqlConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int createWalkinAppointment(String patientId) {
        String sql = "INSERT INTO appointments (patient_id, doctor_id, appointment_date, appointment_time, reason, status, type) " +
                     "VALUES (?, NULL, CURRENT_DATE, CURRENT_TIME, 'Walk-in', 'confirmed', 'walk-in')";
        
        try (Connection conn = database.MySqlConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
             
            pstmt.setString(1, patientId);
            
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public Appointment getLatestConfirmedAppointment(String patientId) {
        String sql = "SELECT a.*, d.department_id " +
                     "FROM appointments a " +
                     "LEFT JOIN doctors d ON a.doctor_id = d.doctor_id " +
                     "WHERE a.patient_id = ? AND a.status = 'confirmed' " +
                     "ORDER BY a.appointment_id DESC LIMIT 1";
                     
        try (Connection conn = database.MySqlConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, patientId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Appointment a = new Appointment();
                    a.setAppointmentId(rs.getInt("appointment_id"));
                    a.setPatientId(rs.getString("patient_id"));
                    a.setDoctorId(rs.getString("doctor_id"));
                    a.setAppointmentDate(rs.getDate("appointment_date"));
                    a.setAppointmentTime(rs.getTime("appointment_time"));
                    a.setReason(rs.getString("reason"));
                    a.setStatus(rs.getString("status"));
                    a.setType(rs.getString("type"));
                    a.setDepartmentId(rs.getInt("department_id"));
                    return a;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
