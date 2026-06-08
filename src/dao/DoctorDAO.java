package dao;
import model.Doctor;
import database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    public void createTableIfNotExists() {}

    public Doctor getDoctorById(String doctorId) {
        String sql = "SELECT d.doctor_id, d.user_id, d.full_name, d.specialization, " +
                     "d.department_id, dep.department_name, d.contact_number, d.availability " +
                     "FROM doctors d " +
                     "LEFT JOIN departments dep ON d.department_id = dep.department_id " +
                     "WHERE d.doctor_id = ?";
        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, doctorId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        } catch (SQLException e) {
            System.err.println("getDoctorById error: " + e.getMessage());
        }
        return null;
    }

    public boolean updateDoctorProfile(Doctor doctor) {
        String sql = "UPDATE doctors SET full_name = ?, specialization = ?, " +
                     "contact_number = ? WHERE doctor_id = ?";
        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, doctor.getFullName());
            ps.setString(2, doctor.getSpecialization());
            ps.setString(3, doctor.getContactNumber());
            ps.setString(4, doctor.getDoctorId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("updateDoctorProfile error: " + e.getMessage());
            return false;
        }
    }

    public List<Doctor> getAvailableDoctorsByDepartment(int departmentId) {
        List<Doctor> list = new ArrayList<>();
        String sql = "SELECT d.doctor_id, d.user_id, d.full_name, d.specialization, " +
                     "d.department_id, dep.department_name, d.contact_number, d.availability " +
                     "FROM doctors d " +
                     "LEFT JOIN departments dep ON d.department_id = dep.department_id " +
                     "WHERE d.department_id = ? AND d.availability = 'available'";
        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, departmentId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            System.err.println("getAvailableDoctorsByDepartment error: " + e.getMessage());
        }
        return list;
    }

    public List<Doctor> getDoctorsByDepartment(int departmentId) {
        List<Doctor> doctors = new ArrayList<>();
        String query = "SELECT * FROM doctors WHERE department_id = ?";
        try (Connection conn = MySqlConnection.getConnection();
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
        try (Connection conn = MySqlConnection.getConnection();
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

    private Doctor mapRow(ResultSet rs) throws SQLException {
        Doctor d = new Doctor(
            rs.getString("doctor_id"),
            rs.getInt("user_id"),
            rs.getString("full_name"),
            rs.getString("specialization"),
            rs.getInt("department_id"),
            rs.getString("contact_number"),
            rs.getString("availability")
        );
        try { d.setDepartmentName(rs.getString("department_name")); } catch (Exception e) {}
        return d;
    }
}
