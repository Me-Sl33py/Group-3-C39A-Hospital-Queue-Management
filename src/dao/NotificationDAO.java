package dao;

import database.MySqlConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAO {

    private Connection getConnection() throws SQLException {
        return new MySqlConnection().openConnection();
    }

    public List<String[]> getRecentNotifications() {
        List<String[]> list = new ArrayList<>();

        String sql =
            "SELECT id, message, created_at " +
            "FROM admin_notification " +
            "ORDER BY created_at DESC";

        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new String[]{
                    rs.getString("id"),
                    rs.getString("message"),
                    rs.getString("created_at")
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean addNotification(String title, String message) {

        String sql =
            "INSERT INTO admin_notification(message) VALUES (?)";

        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, message);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}