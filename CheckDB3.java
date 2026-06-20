import java.sql.*;

public class CheckDB3 {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_queue_management_db", "root", "I_1t_Relax!");
        ResultSet r = c.createStatement().executeQuery("SELECT * FROM departments");
        while(r.next()) {
            System.out.println("DEPTID:" + r.getInt("department_id") + " NAME:" + r.getString("department_name"));
        }
        c.close();
    }
}
