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
            "SELECT notification_id, title, message, created_at " +
            "FROM notifications " +
            "ORDER BY created_at DESC " +
            "LIMIT 3";

        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new String[]{
                    rs.getString("notification_id"),
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
            "INSERT INTO notifications(title, message) VALUES (?, ?)";

        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, title);
            ps.setString(2, message);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}