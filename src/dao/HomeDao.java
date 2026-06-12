package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import database.MySqlConnection;

public class HomeDao {

    public int getTotalPatients() {
        return getCount("SELECT COUNT(*) FROM patients");
    }

    public int getTotalDoctors() {
        return getCount("SELECT COUNT(*) FROM doctors");
    }

    public int getTotalDepartments() {
        return getCount("SELECT COUNT(*) FROM departments");
    }

    public int getTotalReceptionists() {
        return getCount("SELECT COUNT(*) FROM receptionists");
    }

    private int getCount(String sql) {

        int count = 0;

        try (
            Connection conn = new MySqlConnection().openConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery()
        ) {

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }
}