package dao;

import database.MySqlConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    private Connection getConnection() throws SQLException {
        return new MySqlConnection().openConnection();
    }

    private String generateDoctorId(Connection c) throws SQLException {
        String sql = "SELECT doctor_id FROM doctors ORDER BY doctor_id DESC LIMIT 1";
        try (PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                String lastId = rs.getString("doctor_id"); // e.g. "D-105"
                int num = Integer.parseInt(lastId.substring(2)); // extracts 105
                return "D-" + (num + 1); // returns "D-106"
            } else {
                return "D-101"; // first doctor
            }
        }
    }

    public List<String[]> searchDoctors(String keyword) {
        List<String[]> list = new ArrayList<>();

        String sql = "SELECT d.doctor_id, d.full_name, d.contact_number, " +
                     "d.specialization, dept.department_name, " +
                     "d.availability, d.status " +
                     "FROM doctors d " +
                     "JOIN departments dept ON d.department_id = dept.department_id " +
                     "WHERE d.full_name LIKE ? " +
                     "OR d.specialization LIKE ? " +
                     "OR dept.department_name LIKE ?";

        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            String kw = "%" + keyword + "%";

            ps.setString(1, kw);
            ps.setString(2, kw);
            ps.setString(3, kw);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new String[]{
                    rs.getString("doctor_id"),
                    rs.getString("full_name"),
                    rs.getString("contact_number"),
                    rs.getString("specialization"),
                    rs.getString("department_name"),
                    rs.getString("availability"),
                    rs.getString("status")
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean addDoctor(String fullName,
                             String phone,
                             String specialization,
                             int deptId,
                             String availability) {

        String sql = "INSERT INTO doctors " +
                     "(doctor_id, user_id, full_name, contact_number, specialization, department_id, availability, status) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, 'active')";

        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            String doctorId = generateDoctorId(c);
            int userId = 3; // temporary value

            ps.setString(1, doctorId);
            ps.setInt(2, userId);
            ps.setString(3, fullName);
            ps.setString(4, phone);
            ps.setString(5, specialization);
            ps.setInt(6, deptId);
            ps.setString(7, availability);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateDoctor(String doctorId,
                                String fullName,
                                String phone,
                                String specialization,
                                int deptId,
                                String availability,
                                String status) {

        String sql = "UPDATE doctors SET " +
                     "full_name=?, " +
                     "contact_number=?, " +
                     "specialization=?, " +
                     "department_id=?, " +
                     "availability=?, " +
                     "status=? " +
                     "WHERE doctor_id=?";

        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, fullName);
            ps.setString(2, phone);
            ps.setString(3, specialization);
            ps.setInt(4, deptId);
            ps.setString(5, availability);
            ps.setString(6, status);
            ps.setString(7, doctorId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean removeDoctor(String doctorId) {

        String sql = "DELETE FROM doctors WHERE doctor_id=?";

        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, doctorId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}