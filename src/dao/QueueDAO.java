package dao;

import model.QueueItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class QueueDAO {

    public QueueItem getCurrentQueueForPatient(String patientId) {
        String sql = "SELECT q.*, up.full_name AS doctor_name, dep.department_name " +
                     "FROM queue q " +
                     "LEFT JOIN appointments a ON q.appointment_id = a.appointment_id " +
                     "LEFT JOIN doctors d ON q.doctor_id = d.doctor_id " +
                     "LEFT JOIN user_profiles up ON d.user_id = up.user_id " +
                     "LEFT JOIN departments dep ON q.department_id = dep.department_id " +
                     "WHERE q.patient_id = ? AND q.status IN ('waiting', 'in consultation') " +
                     "ORDER BY q.created_at DESC LIMIT 1";
                     
        try (Connection conn = database.MySqlConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, patientId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    QueueItem q = new QueueItem();
                    q.setQueueId(rs.getInt("queue_id"));
                    q.setAppointmentId(rs.getInt("appointment_id"));
                    q.setPatientId(rs.getString("patient_id"));
                    q.setDoctorId(rs.getString("doctor_id"));
                    q.setTokenNumber(rs.getInt("token_number"));
                    q.setStatus(rs.getString("status"));
                    q.setDoctorName(rs.getString("doctor_name"));
                    q.setDepartmentName(rs.getString("department_name"));
                    return q;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public QueueItem getCurrentQueueForPatientInDept(String patientId, int departmentId) {
        String sql = "SELECT q.*, up.full_name AS doctor_name, dep.department_name " +
                     "FROM queue q " +
                     "LEFT JOIN appointments a ON q.appointment_id = a.appointment_id " +
                     "LEFT JOIN doctors d ON q.doctor_id = d.doctor_id " +
                     "LEFT JOIN user_profiles up ON d.user_id = up.user_id " +
                     "LEFT JOIN departments dep ON q.department_id = dep.department_id " +
                     "WHERE q.patient_id = ? AND q.department_id = ? AND q.status IN ('waiting', 'in consultation') " +
                     "ORDER BY q.created_at DESC LIMIT 1";
                     
        try (Connection conn = database.MySqlConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, patientId);
            pstmt.setInt(2, departmentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    QueueItem q = new QueueItem();
                    q.setQueueId(rs.getInt("queue_id"));
                    q.setAppointmentId(rs.getInt("appointment_id"));
                    q.setPatientId(rs.getString("patient_id"));
                    q.setDoctorId(rs.getString("doctor_id"));
                    q.setTokenNumber(rs.getInt("token_number"));
                    q.setStatus(rs.getString("status"));
                    q.setDoctorName(rs.getString("doctor_name"));
                    q.setDepartmentName(rs.getString("department_name"));
                    return q;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean cancelQueue(int queueId, String patientId) {
        String sql = "UPDATE queue SET status = 'completed' WHERE queue_id = ? AND patient_id = ?";
        
        try (Connection conn = database.MySqlConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, queueId);
            pstmt.setString(2, patientId);
            
            if (pstmt.executeUpdate() > 0) {
                // If possible update appointment to cancelled (simple attempt)
                String updateAppt = "UPDATE appointments SET status = 'cancelled' WHERE appointment_id = (SELECT appointment_id FROM queue WHERE queue_id = ?)";
                try (PreparedStatement pstmtAppt = conn.prepareStatement(updateAppt)) {
                    pstmtAppt.setInt(1, queueId);
                    pstmtAppt.executeUpdate();
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getCurrentlyServingToken(int departmentId) {
        String sql = "SELECT MIN(q.token_number) AS min_token FROM queue q " +
                     "WHERE q.department_id = ? AND q.status IN ('waiting', 'in consultation')";
        try (Connection conn = database.MySqlConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, departmentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int token = rs.getInt("min_token");
                    if (!rs.wasNull()) return token;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getDoctorCurrentlyServingToken(String doctorId) {
        String sql = "SELECT token_number FROM queue WHERE doctor_id = ? AND status = 'in consultation' LIMIT 1";
        try (Connection conn = database.MySqlConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, doctorId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("token_number");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getPatientQueueToken(String patientId, int departmentId) {
        String sql = "SELECT q.token_number FROM queue q " +
                     "WHERE q.patient_id = ? AND q.department_id = ? " +
                     "AND q.status IN ('waiting', 'in consultation') LIMIT 1";
        try (Connection conn = database.MySqlConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, patientId);
            pstmt.setInt(2, departmentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("token_number");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getPeopleAheadCount(int departmentId, int patientToken) {
        String sql = "SELECT COUNT(*) AS count FROM queue q " +
                     "WHERE q.department_id = ? AND q.token_number < ? " +
                     "AND q.status IN ('waiting', 'in consultation')";
        try (Connection conn = database.MySqlConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, departmentId);
            pstmt.setInt(2, patientToken);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("count");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getWaitlistPosition(String patientId, int departmentId) {
        // Returns the patient's waitlist slot number for the given department, -1 if not on waitlist
        String sql = "SELECT w.waitlist_id FROM waitlist w " +
                     "JOIN doctors d ON w.doctor_id = d.doctor_id " +
                     "WHERE w.patient_id = ? AND d.department_id = ? " +
                     "AND w.status = 'waiting' ORDER BY w.waitlist_id ASC LIMIT 1";
        try (Connection conn = database.MySqlConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, patientId);
            pstmt.setInt(2, departmentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("waitlist_id");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getTotalQueueCount(int departmentId) {
        String sql = "SELECT COUNT(*) AS total_count FROM queue q " +
                     "WHERE q.department_id = ? AND q.status = 'waiting'";
        try (Connection conn = database.MySqlConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, departmentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total_count");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
