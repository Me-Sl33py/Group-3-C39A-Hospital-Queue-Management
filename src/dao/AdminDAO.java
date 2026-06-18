package dao;

import database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * AdminDAO — handles all database queries related to admin operations.
 *
 * Architecture Rule: This class contains ONLY SQL code. No UI, no JOptionPane.
 *
 * @author Group 3 C39A
 */
public class AdminDAO {

    /**
     * Checks whether a given user_id belongs to an admin role.
     *
     * @param userId the user_id to verify
     * @return true if the user has admin role, false otherwise
     */
    public boolean isAdmin(int userId) {
        String sql = "SELECT 1 FROM user_profiles WHERE user_id = ? AND role = 'admin'";
        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.err.println("[AdminDAO] isAdmin error: " + e.getMessage());
        }
        return false;
    }

    /**
     * Returns the total number of registered users in the system.
     *
     * @return count of all users, or 0 on error
     */
    public int getTotalUserCount() {
        String sql = "SELECT COUNT(*) AS total FROM users";
        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt("total");
        } catch (SQLException e) {
            System.err.println("[AdminDAO] getTotalUserCount error: " + e.getMessage());
        }
        return 0;
    }
}
