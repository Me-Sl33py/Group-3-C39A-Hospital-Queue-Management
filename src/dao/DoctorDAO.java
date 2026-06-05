package dao;

import db.DBConnection;
import model.Doctor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    public List<Doctor> getAvailableDoctorsByDepartment(int departmentId) {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT doctor_id, full_name FROM doctors WHERE department_id = ? AND availability = 'available'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, departmentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    doctors.add(new Doctor(
                        rs.getString("doctor_id"),
                        rs.getString("full_name")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doctors;
    }
}
