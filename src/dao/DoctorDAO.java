package dao;
import model.Doctor;
import java.sql.*;

public class DoctorDAO {

    public void createTableIfNotExists() {}

    public Doctor getDoctorById(String doctorId) {
        String sql = "SELECT d.doctor_id, d.user_id, d.full_name, d.specialization, " +
                     "d.department_id, dep.department_name, d.contact_number, d.availability " +
                     "FROM doctors d " +
                     "LEFT JOIN departments dep ON d.department_id = dep.department_id " +
                     "WHERE d.doctor_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
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
        try (Connection conn = DatabaseConnection.getConnection();
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

    public java.util.List<Doctor> getAvailableDoctorsByDepartment(int departmentId) {
        java.util.List<Doctor> list = new java.util.ArrayList<>();
        String sql = "SELECT d.doctor_id, d.user_id, d.full_name, d.specialization, " +
                     "d.department_id, dep.department_name, d.contact_number, d.availability " +
                     "FROM doctors d " +
                     "LEFT JOIN departments dep ON d.department_id = dep.department_id " +
                     "WHERE d.department_id = ? AND d.availability = 'available'";
        try (Connection conn = DatabaseConnection.getConnection();
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
        d.setDepartmentName(rs.getString("department_name")); // ADD THIS
        return d;
    }
}