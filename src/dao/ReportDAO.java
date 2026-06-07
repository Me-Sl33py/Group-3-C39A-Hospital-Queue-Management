package dao;

import database.MySqlConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportDAO {

    private Connection getConnection() throws SQLException {
        return new MySqlConnection().openConnection();
    }

    public int getMonthlyVisitCount() {
        String sql = "SELECT COUNT(*) FROM appointments WHERE MONTH(appointment_date) = MONTH(CURDATE())";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    public int getTotalDoctorCount() {
        String sql = "SELECT COUNT(*) FROM doctors";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    public int getActiveDoctorCount() {
        String sql = "SELECT COUNT(*) FROM doctors WHERE status = 'Active'";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    public int getTotalPatientCount() {
        String sql = "SELECT COUNT(*) FROM patients";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    public List<String[]> getMonthlyAppointments() {
        List<String[]> list = new ArrayList<>();
        String sql = "SELECT p.full_name, d.department_name, a.status, a.appointment_date, doc.full_name " +
                     "FROM appointments a " +
                     "JOIN patients p ON a.patient_id = p.patient_id " +
                     "JOIN doctors doc ON a.doctor_id = doc.doctor_id " +
                     "JOIN departments d ON doc.department_id = d.department_id " +
                     "WHERE MONTH(a.appointment_date) = MONTH(CURDATE())";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new String[]{
                    rs.getString(1), rs.getString(2),
                    rs.getString(3), rs.getString(4), rs.getString(5), ""
                });
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
}