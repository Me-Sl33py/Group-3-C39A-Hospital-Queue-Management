package dao;

import database.MySqlConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private Connection getConnection() throws SQLException {
        return new MySqlConnection().openConnection();
    }

    public List<String[]> searchUsers(String keyword) {
        List<String[]> list = new ArrayList<>();
        String sql = "SELECT user_id, full_name, phone, gender, dob, role, status " +
             "FROM users WHERE full_name LIKE ? OR dob LIKE ? OR role LIKE ?";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            String kw = "%" + keyword + "%";
            ps.setString(1, kw); ps.setString(2, kw); ps.setString(3, kw);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new String[]{
                    rs.getString("user_id"),
                    rs.getString("full_name"),
                    rs.getString("phone"),
                    rs.getString("gender"),
                    rs.getString("dob"),
                    rs.getString("role"),
                    rs.getString("status")
                });
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public boolean createUser(String fullName, String phone, String gender,
                          String dob, String role, String password) {
    String sql = "INSERT INTO users (full_name, phone, gender, dob, role, password, status) " +
                 "VALUES (?, ?, ?, ?, ?, ?, 'active')";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, fullName); ps.setString(2, phone);
       ps.setString(3, gender);
       ps.setString(4, dob);
       ps.setString(5, role);    ps.setString(6, password);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean updateUser(int userId, String status, String gender, String phone) {
        String sql = "UPDATE users SET status=?, gender=?, phone=? WHERE user_id=?";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, status); ps.setString(2, gender);
            ps.setString(3, phone);  ps.setInt(4, userId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean deactivateUser(int userId) {
        return setUserStatus(userId, "inactive");
    }

    public boolean activateUser(int userId) {
        return setUserStatus(userId, "active");
    }

    private boolean setUserStatus(int userId, String status) {
        String sql = "UPDATE users SET status=? WHERE user_id=?";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, status); ps.setInt(2, userId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}