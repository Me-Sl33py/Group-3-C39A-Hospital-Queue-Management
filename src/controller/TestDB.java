package controller;
import database.MySqlConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestDB {
    public static void main(String[] args) {
        MySqlConnection db = new MySqlConnection();
        Connection conn = db.openConnection();
        if (conn != null) {
            try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(
                    "SELECT u.user_id, u.username, u.role, d.doctor_id, d.full_name " +
                    "FROM users u " +
                    "LEFT JOIN doctors d ON u.user_id = d.user_id " +
                    "WHERE u.role = 'doctor'"
                );
                System.out.println("--- Doctors in DB ---");
                while (rs.next()) {
                    System.out.println("UserID: " + rs.getInt("user_id") +
                                       " | Username: " + rs.getString("username") +
                                       " | DocID: " + rs.getString("doctor_id") +
                                       " | Name: " + rs.getString("full_name"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                db.closeConnection(conn);
            }
        }
    }
}
