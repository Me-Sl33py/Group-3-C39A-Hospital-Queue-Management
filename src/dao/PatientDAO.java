package dao;
import model.Patient;
import database.MySqlConnection;
import java.sql.*;

public class PatientDAO {

    public String insertPatient(Patient patient) {
        String userQuery = "INSERT INTO users (username, password, role) VALUES (?, 'password123', 'patient')";
        String query = "INSERT INTO patients (patient_id, user_id, full_name, age, gender, contact_number, address) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = new MySqlConnection().openConnection();
             PreparedStatement userStmt = conn.prepareStatement(userQuery, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            userStmt.setString(1, patient.getPatientId()); // username is patient_id
            userStmt.executeUpdate();
            
            ResultSet rs = userStmt.getGeneratedKeys();
            int userId = 1;
            if (rs.next()) {
                userId = rs.getInt(1);
            }

            pstmt.setString(1, patient.getPatientId());
            pstmt.setInt(2, userId);
            pstmt.setString(3, patient.getFullName());
            pstmt.setInt(4, patient.getAge());
            pstmt.setString(5, patient.getGender().toLowerCase());
            pstmt.setString(6, patient.getContactNumber());
            pstmt.setString(7, patient.getAddress());
            
            pstmt.executeUpdate();
            return patient.getPatientId();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Patient getPatientById(String id) {
        String query = "SELECT * FROM patients WHERE patient_id = ?";
        try (Connection conn = new MySqlConnection().openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Patient(
                    rs.getString("patient_id"),
                    rs.getString("full_name"),
                    rs.getInt("age"),
                    rs.getString("gender"),
                    rs.getString("contact_number"),
                    rs.getString("address"),
                    "" // reason is not in patients table, stored in queue/waitlist
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Patient getLatestPatient() {
        String query = "SELECT * FROM patients ORDER BY created_at DESC LIMIT 1";
        try (Connection conn = new MySqlConnection().openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return new Patient(
                    rs.getString("patient_id"),
                    rs.getString("full_name"),
                    rs.getInt("age"),
                    rs.getString("gender"),
                    rs.getString("contact_number"),
                    rs.getString("address"),
                    "" // reason is not in patients table, stored in queue/waitlist
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getTotalPatientsCount() {
        String query = "SELECT COUNT(*) FROM patients";
        try (Connection conn = new MySqlConnection().openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
