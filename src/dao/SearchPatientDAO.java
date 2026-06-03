/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.MySqlConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SearchPatientDAO {

    private final MySqlConnection dbConn = new MySqlConnection();

    // Load ALL patients from the database
    public List<Object[]> getAllPatients() {
        List<Object[]> list = new ArrayList<>();
        String sql = "SELECT patient_id, full_name, age, gender, " +
                     "contact_number, address FROM patients";

        Connection conn = dbConn.openConnection();
        if (conn == null) return list;

        try (Statement stmt = conn.createStatement();
             ResultSet rs   = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Object[]{
                    rs.getString("patient_id"),
                    rs.getString("full_name"),
                    rs.getInt("age"),
                    rs.getString("gender"),
                    rs.getString("contact_number"),
                    rs.getString("address")
                });
            }
        } catch (SQLException e) {
            System.err.println("getAllPatients error: " + e.getMessage());
        } finally {
            try { conn.close(); } catch (SQLException ignored) {}
        }
        return list;
    }

    // Search patients by name or ID
    public List<Object[]> searchPatients(String keyword) {
        List<Object[]> list = new ArrayList<>();
        String sql = "SELECT patient_id, full_name, age, gender, " +
                     "contact_number, address FROM patients " +
                     "WHERE full_name LIKE ? OR patient_id LIKE ?";

        Connection conn = dbConn.openConnection();
        if (conn == null) return list;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            String pattern = "%" + keyword + "%";
            ps.setString(1, pattern);
            ps.setString(2, pattern);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Object[]{
                        rs.getString("patient_id"),
                        rs.getString("full_name"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("contact_number"),
                        rs.getString("address")
                    });
                }
            }
        } catch (SQLException e) {
            System.err.println("searchPatients error: " + e.getMessage());
        } finally {
            try { conn.close(); } catch (SQLException ignored) {}
        }
        return list;
    }
}
