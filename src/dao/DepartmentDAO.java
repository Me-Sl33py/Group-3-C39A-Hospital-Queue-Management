package dao;
import model.Department;
import database.MySqlConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {

    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        String query = "SELECT * FROM departments";
        try (Connection conn = new MySqlConnection().openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                departments.add(new Department(rs.getInt("department_id"), rs.getString("department_name"), rs.getString("description")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }
    
    public Department getDepartmentByName(String name) {
        String query = "SELECT * FROM departments WHERE department_name = ?";
        try (Connection conn = new MySqlConnection().openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Department(rs.getInt("department_id"), rs.getString("department_name"), rs.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
