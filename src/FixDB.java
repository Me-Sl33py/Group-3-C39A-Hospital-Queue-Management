import java.sql.Connection;
import java.sql.Statement;

public class FixDB {
    public static void main(String[] args) {
        System.out.println("Starting database fixes...");
        try (Connection conn = database.MySqlConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            
            if (conn == null) {
                System.out.println("Could not connect to database.");
                return;
            }

            try {
                stmt.executeUpdate("ALTER TABLE queue ADD COLUMN department_id int NOT NULL AFTER patient_id");
                System.out.println("Added department_id to queue table.");
            } catch (Exception e) {
                System.out.println(" department_id may already exist or error: " + e.getMessage());
            }

            try {
                stmt.executeUpdate("ALTER TABLE queue MODIFY COLUMN doctor_id varchar(10) NULL");
                System.out.println("Modified doctor_id in queue to allow NULL.");
            } catch (Exception e) {
                System.out.println("Error modifying doctor_id in queue: " + e.getMessage());
            }

            try {
                stmt.executeUpdate("ALTER TABLE queue MODIFY COLUMN status enum('waiting','in consultation','completed','skipped') default 'waiting'");
                System.out.println("Updated queue status enum to include 'skipped'.");
            } catch (Exception e) {
                System.out.println("Error modifying status in queue: " + e.getMessage());
            }

            try {
                stmt.executeUpdate("ALTER TABLE appointments MODIFY COLUMN doctor_id varchar(10) NULL");
                System.out.println("Modified doctor_id in appointments to allow NULL.");
            } catch (Exception e) {
                System.out.println("Error modifying doctor_id in appointments: " + e.getMessage());
            }
            
            System.out.println("Database fixes applied successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
