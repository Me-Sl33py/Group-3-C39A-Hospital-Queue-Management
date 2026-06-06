package dao;
import model.Doctor;
import database.MySqlConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    public List<Doctor> getDoctorsByDepartment(int departmentId) {
        List<Doctor> doctors = new ArrayList<>();
        String query = "SELECT * FROM doctors WHERE department_id = ?";
        try (Connection conn = new MySqlConnection().openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, departmentId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                doctors.add(new Doctor(
                    rs.getString("doctor_id"), 
                    rs.getString("full_name"), 
                    rs.getString("specialization"), 
                    rs.getInt("department_id"), 
                    rs.getString("availability")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }
    
    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        String query = "SELECT * FROM doctors";
        try (Connection conn = new MySqlConnection().openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                doctors.add(new Doctor(
                    rs.getString("doctor_id"), 
                    rs.getString("full_name"), 
                    rs.getString("specialization"), 
                    rs.getInt("department_id"), 
                    rs.getString("availability")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }
}
