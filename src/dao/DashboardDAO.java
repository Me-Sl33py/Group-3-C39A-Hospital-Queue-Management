package dao;

import database.MySqlConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DashboardDAO {

    private Connection getConnection() throws SQLException {
        return new MySqlConnection().openConnection();
    }

    public int getPatientCount() {
        String sql = "SELECT COUNT(*) FROM patients";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    public int getDoctorCount() {
        String sql = "SELECT COUNT(*) FROM doctors";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    public int getAppointmentCount() {
        String sql = "SELECT COUNT(*) FROM appointments";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    public int getReceptionistCount() {
        String sql = "SELECT COUNT(*) FROM users WHERE role = 'Receptionist'";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    public List<String[]> getRecentUsers() {
        List<String[]> list = new ArrayList<>();
        String sql = "SELECT user_id, full_name, created_at, status, gender, role FROM users ORDER BY created_at DESC LIMIT 10";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new String[]{
                    rs.getString("user_id"),
                    rs.getString("full_name"),
                    rs.getString("created_at"),
                    rs.getString("status"),
                    rs.getString("gender"),
                    rs.getString("role")
                });
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
}