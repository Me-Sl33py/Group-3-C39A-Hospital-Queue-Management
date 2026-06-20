package dao;

import database.MySqlConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;

public class ScheduleDAO {

    private Connection getConnection() throws SQLException {
        return new MySqlConnection().openConnection();
    }

    public List<String[]> getTodaySchedule() {

        List<String[]> list = new ArrayList<>();

        String sql =
            "SELECT a.appointment_time, " +
            "dup.full_name AS doctor_name, " +
            "dp.department_name, " +
            "pup.full_name AS patient_name, " +
            "a.status " +
            "FROM appointments a " +
            "JOIN doctors d ON a.doctor_id = d.doctor_id " +
            "JOIN departments dp ON d.department_id = dp.department_id " +
            "JOIN user_profiles dup ON d.user_id = dup.user_id " +
            "JOIN patients p ON a.patient_id = p.patient_id " +
            "JOIN user_profiles pup ON p.user_id = pup.user_id " +
            "WHERE a.appointment_date = CURDATE() " +
            "ORDER BY a.appointment_time ASC";

        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                String time = rs.getTime("appointment_time")
                        .toLocalTime()
                        .format(DateTimeFormatter.ofPattern("hh:mm a"));

                list.add(new String[]{
                    time,
                    rs.getString("doctor_name"),
                    rs.getString("department_name"),
                    rs.getString("patient_name"),
                    rs.getString("status")
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}