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
}
