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
        String sql = "SELECT COUNT(*) FROM users WHERE role = 'receptionist'";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    public List<String[]> getRecentUsers() {
        List<String[]> list = new ArrayList<>();
        String sql =
            "SELECT u.user_id, " +
            "COALESCE(a.full_name, r.full_name, d.full_name, p.full_name, u.username) AS full_name, " +
            "u.created_at, u.status, " +
            "COALESCE(p.gender, 'N/A') AS gender, " +
            "u.role " +
            "FROM users u " +
            "LEFT JOIN admins a ON u.user_id = a.user_id " +
            "LEFT JOIN receptionists r ON u.user_id = r.user_id " +
            "LEFT JOIN doctors d ON u.user_id = d.user_id " +
            "LEFT JOIN patients p ON u.user_id = p.user_id " +
            "ORDER BY u.created_at DESC LIMIT 10";
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

    public List<String[]> searchUsers(String keyword) {
        List<String[]> list = new ArrayList<>();
        String sql =
            "SELECT u.user_id, " +
            "COALESCE(a.full_name, r.full_name, d.full_name, p.full_name, u.username) AS full_name, " +
            "u.created_at, u.status, " +
            "COALESCE(p.gender, 'N/A') AS gender, " +
            "u.role " +
            "FROM users u " +
            "LEFT JOIN admins a ON u.user_id = a.user_id " +
            "LEFT JOIN receptionists r ON u.user_id = r.user_id " +
            "LEFT JOIN doctors d ON u.user_id = d.user_id " +
            "LEFT JOIN patients p ON u.user_id = p.user_id " +
            "WHERE COALESCE(a.full_name, r.full_name, d.full_name, p.full_name, u.username) LIKE ? " +
            "OR u.role LIKE ? " +
            "ORDER BY u.created_at DESC";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            String kw = "%" + keyword + "%";
            ps.setString(1, kw);
            ps.setString(2, kw);
            try (ResultSet rs = ps.executeQuery()) {
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
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
}