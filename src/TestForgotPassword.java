import dao.UserDAO;
import dao.SecurityQuestionDAO;

public class TestForgotPassword {
    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        int userId = dao.searchUserForReset("John Doe", "1234567890", new java.sql.Date(System.currentTimeMillis()), "Kathmandu");
        System.out.println("User ID: " + userId);
        
        SecurityQuestionDAO secDao = new SecurityQuestionDAO();
        boolean passed = secDao.verifySecurityAnswers(1, new String[]{"a", "b", "c", "d", "e"});
        System.out.println("Passed: " + passed);
    }
}
