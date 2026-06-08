package dao;

import database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class SecurityQuestionsDao {
    private MySqlConnection db;

    public SecurityQuestionsDao() {
        this.db = new MySqlConnection();
    }

    public boolean insertSecurityQuestions(
        int userId,
        String q1, String a1,
        String q2, String a2,
        String q3, String a3,
        String q4, String a4,
        String q5, String a5) {

        Connection conn = null;
        try {
            conn = db.openConnection();
            String query =
                "insert into security_questions " +
                "(user_id, question_1, answer_1, " +
                "question_2, answer_2, question_3, answer_3, " +
                "question_4, answer_4, question_5, answer_5) " +
                "values (?,?,?,?,?,?,?,?,?,?,?)";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setString(2, q1);
            ps.setString(3, a1);
            ps.setString(4, q2);
            ps.setString(5, a2);
            ps.setString(6, q3);
            ps.setString(7, a3);
            ps.setString(8, q4);
            ps.setString(9, a4);
            ps.setString(10, q5);
            ps.setString(11, a5);
            
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            System.out.println("Insert security questions error: " + e);
            return false;
        } finally {
            if (conn != null) {
                db.closeConnection(conn);
            }
        }
    }

    public boolean updateSecurityQuestions(
        int userId,
        String q1, String a1,
        String q2, String a2,
        String q3, String a3,
        String q4, String a4,
        String q5, String a5) {

        Connection conn = null;
        try {
            conn = db.openConnection();
            String query = "UPDATE security_questions SET " +
                "question_1=?, answer_1=?, question_2=?, answer_2=?, " +
                "question_3=?, answer_3=?, question_4=?, answer_4=?, " +
                "question_5=?, answer_5=? WHERE user_id=?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, q1); ps.setString(2, a1);
            ps.setString(3, q2); ps.setString(4, a2);
            ps.setString(5, q3); ps.setString(6, a3);
            ps.setString(7, q4); ps.setString(8, a4);
            ps.setString(9, q5); ps.setString(10, a5);
            ps.setInt(11, userId);
            
            int result = ps.executeUpdate();
            if (result == 0) {
                return insertSecurityQuestions(userId, q1, a1, q2, a2, q3, a3, q4, a4, q5, a5);
            }
            return true;
        } catch (Exception e) {
            System.out.println("Update security questions error: " + e);
            return false;
        } finally {
            if (conn != null) db.closeConnection(conn);
        }
    }

    public String[] getSecurityAnswers(int userId) {
        Connection conn = null;
        try {
            conn = db.openConnection();
            String query = "SELECT answer_1, answer_2, answer_3, answer_4, answer_5 FROM security_questions WHERE user_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, userId);
            java.sql.ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new String[]{
                    rs.getString("answer_1"), rs.getString("answer_2"), 
                    rs.getString("answer_3"), rs.getString("answer_4"), 
                    rs.getString("answer_5")
                };
            }
        } catch (Exception e) {
            System.out.println("Get security answers error: " + e);
        } finally {
            if (conn != null) db.closeConnection(conn);
        }
        return null;
    }

    public boolean verifySecurityAnswers(int userId, String[] enteredAnswers) {
        String query = "SELECT question_1, answer_1, question_2, answer_2, question_3, answer_3, question_4, answer_4, question_5, answer_5 FROM security_questions WHERE user_id = ?";
        try (Connection conn = new MySqlConnection().openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            java.sql.ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int matches = 0;
                String[] dbAnswers = {
                    rs.getString("answer_1"),
                    rs.getString("answer_2"),
                    rs.getString("answer_3"),
                    rs.getString("answer_4"),
                    rs.getString("answer_5")
                };
                for (int i = 0; i < 5; i++) {
                    if (enteredAnswers[i] != null && !enteredAnswers[i].trim().isEmpty() &&
                        dbAnswers[i] != null && dbAnswers[i].equalsIgnoreCase(enteredAnswers[i].trim())) {
                        matches++;
                    }
                }
                return matches >= 3;
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
