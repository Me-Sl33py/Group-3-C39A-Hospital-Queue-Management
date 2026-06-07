import java.sql.*;

public class CheckDB {
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_queue_management_db", "root", "Aayush@2060");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("DESCRIBE patients");
            while(rs.next()) {
                System.out.println(rs.getString("Field") + " - " + rs.getString("Type"));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
