package dao;
import model.Patient;
import database.MySqlConnection;
import java.sql.*;

public class PatientDAO {

    public String generatePatientId() {
        String newId = "PAT001";
        try (Connection conn = new MySqlConnection().openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT patient_id FROM patients ORDER BY created_at DESC LIMIT 1")) {
            if (rs.next()) {
                String lastId = rs.getString("patient_id");
                if (lastId != null && lastId.startsWith("PAT")) {
                    int num = Integer.parseInt(lastId.substring(3));
                    newId = String.format("PAT%03d", num + 1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newId;
    }

    public String generateUsername(String fullName) {
        String baseUsername = fullName.toLowerCase().replaceAll("\\s+", "");
        String newUsername = baseUsername;
        int counter = 1;
        
        try (Connection conn = new MySqlConnection().openConnection()) {
            String query = "SELECT COUNT(*) FROM users WHERE username = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            
            while (true) {
                pstmt.setString(1, newUsername);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    newUsername = baseUsername + counter;
                    counter++;
                } else {
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newUsername;
    }

    public int insertUser(String username, String password, String role) {
        String query = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        try (Connection conn = new MySqlConnection().openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, role);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean insertPatient(String patientId, int userId, String fullName, java.sql.Date dob, int age, String gender, String contactNumber, String address) {
        String query = "INSERT INTO patients (patient_id, user_id, full_name, dob, age, gender, contact_number, address) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = new MySqlConnection().openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, patientId);
            pstmt.setInt(2, userId);
            pstmt.setString(3, fullName);
            pstmt.setDate(4, dob);
            pstmt.setInt(5, age);
            pstmt.setString(6, gender);
            pstmt.setString(7, contactNumber);
            pstmt.setString(8, address);
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String insertPatient(Patient patient) {
        String userQuery = "INSERT INTO users (username, password, role) VALUES (?, 'password123', 'patient')";
        String query = "INSERT INTO patients (patient_id, user_id, full_name, dob, age, gender, contact_number, address, blood_group, reason) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            pstmt.setDate(4, patient.getDob());
            pstmt.setInt(5, patient.getAge());
            pstmt.setString(6, patient.getGender().toLowerCase());
            pstmt.setString(7, patient.getContactNumber());
            pstmt.setString(8, patient.getAddress());
            pstmt.setString(9, patient.getBloodGroup());
            pstmt.setString(10, patient.getReason());
            
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
                    rs.getDate("dob"),
                    rs.getInt("age"),
                    rs.getString("gender"),
                    rs.getString("contact_number"),
                    rs.getString("address"),
                    rs.getString("blood_group"),
                    rs.getString("reason"),
                    rs.getTimestamp("created_at")
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
                    rs.getDate("dob"),
                    rs.getInt("age"),
                    rs.getString("gender"),
                    rs.getString("contact_number"),
                    rs.getString("address"),
                    rs.getString("blood_group"),
                    rs.getString("reason"),
                    rs.getTimestamp("created_at")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public java.util.List<Patient> getAllPatients() {
        java.util.List<Patient> patients = new java.util.ArrayList<>();
        String query = "SELECT * FROM patients ORDER BY created_at DESC";
        try (Connection conn = new MySqlConnection().openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                patients.add(new Patient(
                    rs.getString("patient_id"),
                    rs.getString("full_name"),
                    rs.getDate("dob"),
                    rs.getInt("age"),
                    rs.getString("gender"),
                    rs.getString("contact_number"),
                    rs.getString("address"),
                    rs.getString("blood_group"),
                    rs.getString("reason"),
                    rs.getTimestamp("created_at")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
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
