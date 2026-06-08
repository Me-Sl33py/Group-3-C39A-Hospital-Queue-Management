package dao;

import database.MySqlConnection;
import java.sql.*;

public class ReceptionistDAO {

    // Get the receptionist details and user details using user_id
    public Object[] getReceptionistProfile(int userId) {
        Object[] profile = new Object[4]; // {fullName, contactNumber, currentPassword, receptionistId}
        String query = "SELECT r.full_name, r.contact_number, u.password, r.receptionist_id " +
                       "FROM receptionists r " +
                       "JOIN users u ON r.user_id = u.user_id " +
                       "WHERE u.user_id = ?";

        try (Connection conn = new MySqlConnection().openConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    profile[0] = rs.getString("full_name");
                    profile[1] = rs.getString("contact_number");
                    profile[2] = rs.getString("password");
                    profile[3] = rs.getString("receptionist_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profile;
    }

    // Update receptionist profile (full name, contact number) and password
    public boolean updateReceptionistProfile(int userId, String fullName, String contactNumber, String newPassword) {
        String updateReceptionist = "UPDATE receptionists SET full_name = ?, contact_number = ? WHERE user_id = ?";
        String updateUser = "UPDATE users SET password = ? WHERE user_id = ?";
        
        Connection conn = null;
        try {
            conn = new MySqlConnection().openConnection();
            conn.setAutoCommit(false); // transaction
            
            // Update Receptionist
            try (PreparedStatement stmtRec = conn.prepareStatement(updateReceptionist)) {
                stmtRec.setString(1, fullName);
                stmtRec.setString(2, contactNumber);
                stmtRec.setInt(3, userId);
                stmtRec.executeUpdate();
            }
            
            // Update User
            try (PreparedStatement stmtUsr = conn.prepareStatement(updateUser)) {
                stmtUsr.setString(1, newPassword);
                stmtUsr.setInt(2, userId);
                stmtUsr.executeUpdate();
            }
            
            conn.commit();
            return true;
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
