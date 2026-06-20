import java.sql.*;

public class CheckDB7 {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_queue_management_db", "root", "I_1t_Relax!");
        ResultSet rs = c.getMetaData().getColumns(null, null, "patients", null);
        while(rs.next()) {
            System.out.println("COL: " + rs.getString("COLUMN_NAME"));
        }
        c.close();
    }
}
