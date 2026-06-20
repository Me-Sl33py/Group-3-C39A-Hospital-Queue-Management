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
        String sql = "SELECT COUNT(*) FROM doctors WHERE status = 'active'";
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
    
public List<String[]> searchAppointments(String keyword) {
    List<String[]> list = new ArrayList<>();
    String sql = "SELECT pup.full_name, d.department_name, a.status, a.appointment_date, dup.full_name " +
                 "FROM appointments a " +
                 "JOIN patients p ON a.patient_id = p.patient_id " +
                 "JOIN user_profiles pup ON p.user_id = pup.user_id " +
                 "JOIN doctors doc ON a.doctor_id = doc.doctor_id " +
                 "JOIN user_profiles dup ON doc.user_id = dup.user_id " +
                 "JOIN departments d ON doc.department_id = d.department_id " +
                 "WHERE pup.full_name LIKE ? OR dup.full_name LIKE ?";
    try (Connection c = getConnection();
         PreparedStatement ps = c.prepareStatement(sql)) {
        String kw = "%" + keyword + "%";
        ps.setString(1, kw);
        ps.setString(2, kw);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new String[]{
                    rs.getString(1), rs.getString(2),
                    rs.getString(3), rs.getString(4), rs.getString(5)
                });
            }
        }
    } catch (SQLException e) { e.printStackTrace(); }
    return list;
}

    public List<String[]> getMonthlyAppointments() {
        List<String[]> list = new ArrayList<>();
        String sql = "SELECT pup.full_name, d.department_name, a.status, a.appointment_date, dup.full_name " +
                     "FROM appointments a " +
                     "JOIN patients p ON a.patient_id = p.patient_id " +
                     "JOIN user_profiles pup ON p.user_id = pup.user_id " +
                     "JOIN doctors doc ON a.doctor_id = doc.doctor_id " +
                     "JOIN user_profiles dup ON doc.user_id = dup.user_id " +
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
public int getTotalAppointmentCount() {
    String sql = "SELECT COUNT(*) FROM appointments WHERE MONTH(appointment_date) = MONTH(CURDATE())";
    try (Connection c = getConnection();
         PreparedStatement ps = c.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        if (rs.next()) return rs.getInt(1);
    } catch (SQLException e) { e.printStackTrace(); }
    return 0;
}

public int getTotalDepartmentCount() {
    String sql = "SELECT COUNT(*) FROM departments";
    try (Connection c = getConnection();
         PreparedStatement ps = c.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        if (rs.next()) return rs.getInt(1);
    } catch (SQLException e) { e.printStackTrace(); }
    return 0;
}
}