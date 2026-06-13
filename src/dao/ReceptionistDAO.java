package dao;

import database.MySqlConnection;
import java.sql.*;

public class ReceptionistDAO {

    // Get the receptionist details and user details using user_id
    public Object[] getReceptionistProfile(int userId) {
        Object[] profile = new Object[6]; // {fullName, contactNumber, currentPassword, receptionistId, securityQuestion, securityAnswer}
        String query = "SELECT r.full_name, up.contact_number, u.password, r.receptionist_id, sq.question_1, sq.answer_1 " +
                       "FROM receptionists r " +
                       "JOIN users u ON r.user_id = u.user_id " +
                       "LEFT JOIN user_profiles up ON u.user_id = up.user_id " +
                       "LEFT JOIN security_questions sq ON u.user_id = sq.user_id " +
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
                    profile[4] = rs.getString("question_1");
                    profile[5] = rs.getString("answer_1");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profile;
    }

    // Update receptionist profile (full name, contact number) and password
    public boolean updateReceptionistProfile(int userId, String fullName, String contactNumber, String newPassword, String securityQuestion, String securityAnswer) {
        String updateReceptionist = "UPDATE receptionists SET full_name = ? WHERE user_id = ?";
        String updateProfile = "UPDATE user_profiles SET full_name = ?, contact_number = ? WHERE user_id = ?";
        String updateUser = "UPDATE users SET password = ? WHERE user_id = ?";
        
        Connection conn = null;
        try {
            conn = new MySqlConnection().openConnection();
            conn.setAutoCommit(false); // transaction
            
            // Update Receptionist
            try (PreparedStatement stmtRec = conn.prepareStatement(updateReceptionist)) {
                stmtRec.setString(1, fullName);
                stmtRec.setInt(2, userId);
                stmtRec.executeUpdate();
            }
            
            // Update User Profiles
            try (PreparedStatement stmtProf = conn.prepareStatement(updateProfile)) {
                stmtProf.setString(1, fullName);
                stmtProf.setString(2, contactNumber);
                stmtProf.setInt(3, userId);
                stmtProf.executeUpdate();
            }
            
            // Update User
            try (PreparedStatement stmtUsr = conn.prepareStatement(updateUser)) {
                stmtUsr.setString(1, newPassword);
                stmtUsr.setInt(2, userId);
                stmtUsr.executeUpdate();
            }
            
            // Update Security Questions
            if (securityQuestion != null && securityAnswer != null) {
                boolean exists = false;
                try (PreparedStatement stmtCheck = conn.prepareStatement("SELECT 1 FROM security_questions WHERE user_id = ?")) {
                    stmtCheck.setInt(1, userId);
                    try (ResultSet rs = stmtCheck.executeQuery()) {
                        exists = rs.next();
                    }
                }
                
                if (exists) {
                    try (PreparedStatement stmtSq = conn.prepareStatement(
                            "UPDATE security_questions SET question_1 = ?, answer_1 = ? WHERE user_id = ?")) {
                        stmtSq.setString(1, securityQuestion);
                        stmtSq.setString(2, securityAnswer);
                        stmtSq.setInt(3, userId);
                        stmtSq.executeUpdate();
                    }
                } else {
                    try (PreparedStatement stmtSq = conn.prepareStatement(
                            "INSERT INTO security_questions (user_id, question_1, answer_1, question_2, answer_2, question_3, answer_3, question_4, answer_4, question_5, answer_5) " +
                            "VALUES (?, ?, ?, 'None', 'None', 'None', 'None', 'None', 'None', 'None', 'None')")) {
                        stmtSq.setInt(1, userId);
                        stmtSq.setString(2, securityQuestion);
                        stmtSq.setString(3, securityAnswer);
                        stmtSq.executeUpdate();
                    }
                }
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
