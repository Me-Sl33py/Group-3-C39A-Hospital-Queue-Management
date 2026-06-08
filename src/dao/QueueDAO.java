package dao;

import model.QueueItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class QueueDAO {

    public QueueItem getCurrentQueueForPatient(String patientId) {
        String sql = "SELECT q.*, d.full_name AS doctor_name, dep.department_name " +
                     "FROM queue q " +
                     "JOIN appointments a ON q.appointment_id = a.appointment_id " +
                     "JOIN doctors d ON q.doctor_id = d.doctor_id " +
                     "JOIN departments dep ON d.department_id = dep.department_id " +
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
}
