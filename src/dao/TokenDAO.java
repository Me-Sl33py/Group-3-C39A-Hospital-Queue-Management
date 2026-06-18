package dao;
import model.Token;
import database.MySqlConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TokenDAO {

    public int createToken(int appointmentId, String patientId, int departmentId) {
        String tokenNumQuery = "SELECT COALESCE(MAX(token_number), 0) + 1 FROM queue WHERE department_id = ? AND DATE(created_at) = CURDATE()";
        String insertQuery = "INSERT INTO queue (appointment_id, patient_id, department_id, doctor_id, token_number, status) VALUES (?, ?, ?, NULL, ?, 'waiting')";
        
        try (Connection conn = new MySqlConnection().openConnection()) {
            int nextTokenNum = 1;
            try (PreparedStatement pstmt1 = conn.prepareStatement(tokenNumQuery)) {
                pstmt1.setInt(1, departmentId);
                try (ResultSet rs = pstmt1.executeQuery()) {
                    if (rs.next()) {
                        nextTokenNum = rs.getInt(1);
                    }
                }
            }
            
            try (PreparedStatement pstmt2 = conn.prepareStatement(insertQuery)) {
                pstmt2.setInt(1, appointmentId);
                pstmt2.setString(2, patientId);
                pstmt2.setInt(3, departmentId);
                pstmt2.setInt(4, nextTokenNum);
                pstmt2.executeUpdate();
                return nextTokenNum;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<Token> getAllWaitingTokens() {
        List<Token> tokens = new ArrayList<>();
        String query = "SELECT q.queue_id, q.token_number, q.status, q.created_at, q.patient_id, q.doctor_id, " +
                       "q.appointment_id, q.department_id, " +
                       "p.full_name AS patient_name, d.full_name AS doctor_name " +
                       "FROM queue q " +
                       "JOIN patients p ON q.patient_id = p.patient_id " +
                       "LEFT JOIN doctors d ON q.doctor_id = d.doctor_id " +
                       "WHERE q.status = 'waiting' " +
                       "ORDER BY q.created_at ASC";
        try (Connection conn = new MySqlConnection().openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Token t = new Token(
                    rs.getInt("queue_id"),
                    rs.getInt("token_number"),
                    rs.getString("patient_id"),
                    rs.getString("doctor_id"),
                    rs.getString("status"),
                    rs.getTimestamp("created_at"),
                    rs.getInt("appointment_id"),
                    rs.getInt("department_id")
                );
                t.setPatientName(rs.getString("patient_name"));
                t.setDoctorName(rs.getString("doctor_name"));
                tokens.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tokens;
    }
    
    public int countPatientsWaitingForDoctor(String doctorId) {
        String query = "SELECT COUNT(*) FROM queue WHERE doctor_id = ? AND status = 'waiting'";
        try (Connection conn = new MySqlConnection().openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, doctorId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int countTotalWaiting() {
        String query = "SELECT COUNT(*) FROM queue WHERE status = 'waiting' AND DATE(created_at) = CURDATE()";
        try (Connection conn = new MySqlConnection().openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int countInConsultation() {
        String query = "SELECT COUNT(*) FROM queue WHERE status = 'in consultation' AND DATE(created_at) = CURDATE()";
        try (Connection conn = new MySqlConnection().openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int countTotalTokens() {
        String query = "SELECT COUNT(*) FROM queue";
        try (Connection conn = new MySqlConnection().openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean assignDoctorToToken(int queueId, String doctorId) {
        String query = "UPDATE queue SET doctor_id = ?, status = 'in consultation' WHERE queue_id = ?";
        try (Connection conn = new MySqlConnection().openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, doctorId);
            pstmt.setInt(2, queueId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Token getNextUnassignedToken() {
        String query = "SELECT q.queue_id, q.token_number, q.status, q.created_at, q.patient_id, q.doctor_id, " +
                       "q.appointment_id, q.department_id, " +
                       "p.full_name AS patient_name, d.full_name AS doctor_name " +
                       "FROM queue q " +
                       "JOIN patients p ON q.patient_id = p.patient_id " +
                       "LEFT JOIN doctors d ON q.doctor_id = d.doctor_id " +
                       "WHERE q.status = 'waiting' AND q.doctor_id IS NULL " +
                       "ORDER BY q.created_at ASC LIMIT 1";
        try (Connection conn = new MySqlConnection().openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                Token t = new Token(
                    rs.getInt("queue_id"),
                    rs.getInt("token_number"),
                    rs.getString("patient_id"),
                    rs.getString("doctor_id"),
                    rs.getString("status"),
                    rs.getTimestamp("created_at"),
                    rs.getInt("appointment_id"),
                    rs.getInt("department_id")
                );
                t.setPatientName(rs.getString("patient_name"));
                t.setDoctorName(rs.getString("doctor_name"));
                return t;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
