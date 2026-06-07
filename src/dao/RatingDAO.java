package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RatingDAO {

    public boolean submitRating(int appointmentId, String patientId, String doctorId, int stars, String feedback) {
        // Verify appointment belongs to patient, is completed, and no rating exists
        String checkSql = "SELECT 1 FROM appointments a " +
                          "LEFT JOIN ratings r ON a.appointment_id = r.appointment_id " +
                          "WHERE a.appointment_id = ? AND a.patient_id = ? AND a.status = 'completed' AND r.rating_id IS NULL";
                          
        try (Connection conn = dao.DatabaseConnection.getConnection();
             PreparedStatement checkPstmt = conn.prepareStatement(checkSql)) {
             
            checkPstmt.setInt(1, appointmentId);
            checkPstmt.setString(2, patientId);
            
            try (ResultSet rs = checkPstmt.executeQuery()) {
                if (rs.next()) {
                    // Valid to insert
                    String insertSql = "INSERT INTO ratings (appointment_id, patient_id, doctor_id, stars, feedback) VALUES (?, ?, ?, ?, ?)";
                    try (PreparedStatement insertPstmt = conn.prepareStatement(insertSql)) {
                        insertPstmt.setInt(1, appointmentId);
                        insertPstmt.setString(2, patientId);
                        insertPstmt.setString(3, doctorId);
                        insertPstmt.setInt(4, stars);
                        insertPstmt.setString(5, feedback);
                        
                        return insertPstmt.executeUpdate() > 0;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
