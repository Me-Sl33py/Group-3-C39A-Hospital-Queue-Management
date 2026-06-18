import database.MySqlConnection;
import java.sql.*;

public class TestDB {
    public static void main(String[] args) {
        try (Connection conn = new MySqlConnection().openConnection()) {
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet rs = meta.getColumns(null, null, "queue", null);
            while (rs.next()) {
                System.out.println("Column: " + rs.getString("COLUMN_NAME"));
            }
            System.out.println("--- Data ---");
            Statement stmt = conn.createStatement();
            ResultSet dataRs = stmt.executeQuery("SELECT q.department_id, dep.department_name FROM queue q LEFT JOIN departments dep ON q.department_id = dep.department_id LIMIT 5");
            while (dataRs.next()) {
                System.out.println("Dept ID: " + dataRs.getInt(1) + ", Dept Name: " + dataRs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
