import java.sql.*;

public class CheckUserDB {
    public static void main(String[] args) {
        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_queue_management_db", "root", "Aayush@2060");
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            while(rs.next()) {
                System.out.println("User - ID: " + rs.getInt("user_id") + ", User: " + rs.getString("username") + ", Pass: " + rs.getString("password"));
            }
            rs = stmt.executeQuery("SELECT * FROM receptionists");
            while(rs.next()) {
                System.out.println("Receptionist - RecID: " + rs.getString("receptionist_id") + ", UserID: " + rs.getInt("user_id") + ", Name: " + rs.getString("full_name"));
            }
            c.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
