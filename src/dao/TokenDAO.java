package dao;
import model.Token;
import database.MySqlConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TokenDAO {

    public int createToken(Token token) {
        String apptQuery = "INSERT INTO appointments (patient_id, doctor_id, appointment_date, appointment_time, reason, status, type) VALUES (?, ?, CURRENT_DATE, CURRENT_TIME, 'Walk-in', 'pending', 'walk-in')";
        String query = "INSERT INTO queue (appointment_id, patient_id, doctor_id, token_number, status) VALUES (?, ?, ?, ?, 'waiting')";
        try (Connection conn = new MySqlConnection().openConnection();
             PreparedStatement apptStmt = conn.prepareStatement(apptQuery, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            // Insert Appointment
            apptStmt.setString(1, token.getPatientId());
            apptStmt.setString(2, token.getDoctorId());
            apptStmt.executeUpdate();
            
            ResultSet rs = apptStmt.getGeneratedKeys();
            int appointmentId = 1;
            if (rs.next()) {
                appointmentId = rs.getInt(1);
            }

            // Insert Queue
            pstmt.setInt(1, appointmentId);
            pstmt.setString(2, token.getPatientId());
            pstmt.setString(3, token.getDoctorId());
            pstmt.setInt(4, token.getTokenNumber());
            
            pstmt.executeUpdate();
            
            ResultSet queueRs = pstmt.getGeneratedKeys();
            if (queueRs.next()) {
                return queueRs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<Token> getAllWaitingTokens() {
        List<Token> tokens = new ArrayList<>();
        String query = "SELECT q.queue_id, q.token_number, q.status, q.created_at, q.patient_id, q.doctor_id, " +
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
                    rs.getTimestamp("created_at")
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
        String query = "SELECT COUNT(*) FROM queue WHERE status = 'waiting'";
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
                       "p.full_name AS patient_name, d.full_name AS doctor_name " +
                       "FROM queue q " +
                       "JOIN patients p ON q.patient_id = p.patient_id " +
                       "LEFT JOIN doctors d ON q.doctor_id = d.doctor_id " +
                       "WHERE q.status = 'waiting' " +
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
                    rs.getTimestamp("created_at")
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
