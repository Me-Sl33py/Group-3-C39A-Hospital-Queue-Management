package dao;

import database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDAO {

    public int getPatientCount() {

        int count = 0;

        try {

            Connection conn =
                    new MySqlConnection().openConnection();

            String sql =
                    "SELECT COUNT(*) FROM patients";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                count = rs.getInt(1);
            }

            conn.close();

        } catch(Exception e){
            e.printStackTrace();
        }

        return count;
    }

    public int getDoctorCount() {

        int count = 0;

        try {

            Connection conn =
                    new MySqlConnection().openConnection();

            String sql =
                    "SELECT COUNT(*) FROM doctors";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                count = rs.getInt(1);
            }

            conn.close();

        } catch(Exception e){
            e.printStackTrace();
        }

        return count;
    }

    public int getAppointmentCount() {

        int count = 0;

        try {

            Connection conn =
                    new MySqlConnection().openConnection();

            String sql =
                    "SELECT COUNT(*) FROM appointments";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                count = rs.getInt(1);
            }

            conn.close();

        } catch(Exception e){
            e.printStackTrace();
        }

        return count;
    }

    public int getReceptionistCount() {

        int count = 0;

        try {

            Connection conn =
                    new MySqlConnection().openConnection();

            String sql =
                    "SELECT COUNT(*) FROM users WHERE role='receptionist'";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                count = rs.getInt(1);
            }

            conn.close();

        } catch(Exception e){
            e.printStackTrace();
        }

        return count;
    }
}


