import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class FixDB {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/hospital_queue_management_db";
        String user = "root";
        String password = "I_1t_Relax!";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, user, password);
                 Statement stmt = conn.createStatement()) {
                
                // Add 'deactive' alongside 'inactive' first
                stmt.executeUpdate("ALTER TABLE users MODIFY COLUMN status ENUM('active', 'inactive', 'deactive') DEFAULT 'active'");
                stmt.executeUpdate("ALTER TABLE doctors MODIFY COLUMN status ENUM('active', 'inactive', 'deactive') DEFAULT 'active'");
                
                // Update all existing 'inactive' to 'deactive'
                stmt.executeUpdate("UPDATE users SET status = 'deactive' WHERE status = 'inactive'");
                stmt.executeUpdate("UPDATE doctors SET status = 'deactive' WHERE status = 'inactive'");
                
                // Now strictly restrict the enum to 'active', 'deactive'
                stmt.executeUpdate("ALTER TABLE users MODIFY COLUMN status ENUM('active', 'deactive') DEFAULT 'active'");
                stmt.executeUpdate("ALTER TABLE doctors MODIFY COLUMN status ENUM('active', 'deactive') DEFAULT 'active'");
                
                System.out.println("Success! Properly changed enums to deactive.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
