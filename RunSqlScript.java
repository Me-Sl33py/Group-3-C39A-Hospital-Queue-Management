import database.MySqlConnection;
import java.sql.Connection;
import java.sql.Statement;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RunSqlScript {
    public static void main(String[] args) {
        try {
            Connection conn = new MySqlConnection().openConnection();
            String sqlContent = new String(Files.readAllBytes(Paths.get("src/database/Hospital_queue_management_db.sql")));
            
            // Basic split by semicolon. This is a naive approach but works for simple dumps.
            String[] queries = sqlContent.split(";");
            Statement stmt = conn.createStatement();
            for (String query : queries) {
                if (query.trim().length() > 0) {
                    stmt.execute(query.trim());
                }
            }
            stmt.close();
            conn.close();
            System.out.println("Database recreated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
