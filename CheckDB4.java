import java.sql.*;

public class CheckDB4 {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_queue_management_db", "root", "I_1t_Relax!");
        
        String sql = "SELECT q.*, d.full_name AS doctor_name, dep.department_name " +
                     "FROM queue q " +
                     "LEFT JOIN appointments a ON q.appointment_id = a.appointment_id " +
                     "LEFT JOIN doctors d ON q.doctor_id = d.doctor_id " +
                     "LEFT JOIN departments dep ON q.department_id = dep.department_id " +
                     "WHERE q.patient_id = ? AND q.department_id = ? AND q.status IN ('waiting', 'in consultation') " +
                     "ORDER BY q.created_at DESC LIMIT 1";
                     
        PreparedStatement pstmt = c.prepareStatement(sql);
        pstmt.setString(1, "P-004");
        pstmt.setInt(2, 3);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            System.out.println("FOUND QUEUE: " + rs.getInt("queue_id"));
        } else {
            System.out.println("NOT FOUND!");
        }
        
        c.close();
    }
}
