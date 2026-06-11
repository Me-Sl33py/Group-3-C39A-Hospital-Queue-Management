package dao;

import database.MySqlConnection;
import model.SecurityQuestion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * DAO class for SecurityQuestion — handles all database queries
 * for the security_questions table
 * Methods: save, get, verify security questions
 */
public class SecurityQuestionDAO {

    // Database connection object
    private MySqlConnection db;

    // The 5 fixed security questions used across the application
    public static final String[] QUESTIONS = {
        "Your Favourite food?",
        "Your First pet's name?",
        "Your Favourite game?",
        "Your Best Friend's name?",
        "Your Favourite Place to visit?"
    };

    // Constructor — create database connection
    public SecurityQuestionDAO() {
        this.db = new MySqlConnection();
    }

    /**
     * Save security questions and answers for a user
     * @param userId the user_id to save questions for
     * @param answers array of 5 answers (empty string if not answered)
     * @return true if saved successfully, false if failed
     */
    public boolean saveSecurityQuestions(int userId, String[] answers) {
        Connection conn = null;
        try {
            conn = db.openConnection();
            String sql = "INSERT INTO security_questions "
                       + "(user_id, question_1, answer_1, question_2, answer_2, "
                       + "question_3, answer_3, question_4, answer_4, "
                       + "question_5, answer_5) "
                       + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            // Set each question and answer pair
            for (int i = 0; i < 5; i++) {
                ps.setString((i * 2) + 2, QUESTIONS[i]);           // question column
                ps.setString((i * 2) + 3, answers[i].trim());      // answer column
            }

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Save security questions error: " + e);
        } finally {
            if (conn != null) {
                db.closeConnection(conn);
            }
        }
        return false;
    }

    /**
     * Get security questions and answers for a user
     * @param userId the user_id to get questions for
     * @return SecurityQuestion object if found, null if not found
     */
    public SecurityQuestion getSecurityQuestions(int userId) {
        Connection conn = null;
        try {
            conn = db.openConnection();
            String sql = "SELECT * FROM security_questions WHERE user_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Build and return a SecurityQuestion object
                SecurityQuestion sq = new SecurityQuestion();
                sq.setQuestionId(rs.getInt("question_id"));
                sq.setUserId(rs.getInt("user_id"));
                sq.setQuestion1(rs.getString("question_1"));
                sq.setAnswer1(rs.getString("answer_1"));
                sq.setQuestion2(rs.getString("question_2"));
                sq.setAnswer2(rs.getString("answer_2"));
                sq.setQuestion3(rs.getString("question_3"));
                sq.setAnswer3(rs.getString("answer_3"));
                sq.setQuestion4(rs.getString("question_4"));
                sq.setAnswer4(rs.getString("answer_4"));
                sq.setQuestion5(rs.getString("question_5"));
                sq.setAnswer5(rs.getString("answer_5"));
                return sq;
            }
        } catch (Exception e) {
            System.out.println("Get security questions error: " + e);
        } finally {
            if (conn != null) {
                db.closeConnection(conn);
            }
        }
        return null;
    }

    /**
     * Verify security answers entered by user during forgot password
     * Compares entered answers with stored answers (case-insensitive)
     * At least 3 answers must match for verification to pass
     * @param userId the user_id to verify answers for
     * @param enteredAnswers array of 5 answers entered by user
     * @return true if at least 3 answers match, false if not
     */
    public boolean verifySecurityAnswers(int userId, String[] enteredAnswers) {
        // First get the stored answers from database
        SecurityQuestion stored = getSecurityQuestions(userId);

        if (stored == null) {
            System.out.println("No security questions found for user: " + userId);
            return false;
        }

        // Put stored answers in an array for easy comparison
        String[] storedAnswers = {
            stored.getAnswer1(),
            stored.getAnswer2(),
            stored.getAnswer3(),
            stored.getAnswer4(),
            stored.getAnswer5()
        };

        // Count how many answers match (case-insensitive)
        int matchCount = 0;
        for (int i = 0; i < 5; i++) {
            String entered = enteredAnswers[i].trim();
            String saved = storedAnswers[i].trim();
            // Only compare non-empty answers
            if (!entered.isEmpty() && entered.equalsIgnoreCase(saved)) {
                matchCount++;
            }
        }

        // At least 3 correct answers required
        return matchCount >= 3;
    }

    /**
     * Check if a user already has security questions saved
     * @param userId the user_id to check
     * @return true if questions exist, false if not
     */
    public boolean hasSecurityQuestions(int userId) {
        return getSecurityQuestions(userId) != null;
    }
}
