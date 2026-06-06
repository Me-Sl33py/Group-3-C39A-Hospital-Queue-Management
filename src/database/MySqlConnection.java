/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
/**
 *
 * @author User
 */
public class MySqlConnection implements Db {

    // Database credentials (easy to update here)
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hospital_queue_management_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "I_1t_Relax!";

    @Override
    public Connection openConnection() {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connection Successful");
            return conn;
        } catch(Exception e) {
            System.err.println("Database connection failed: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Connection Closed Successfully");
            } catch (Exception e) {
                System.err.println("Failed to close connection: " + e.getMessage());
            }
        }
    }

    @Override
    public ResultSet runQuery(Connection conn, String Query) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int executeUpdate(Connection conn, String Query) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}