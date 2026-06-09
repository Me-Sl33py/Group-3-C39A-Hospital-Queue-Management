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

   @Override
public Connection openConnection() {

    try {

        Class.forName("com.mysql.cj.jdbc.Driver");

        String url =
                "jdbc:mysql://localhost:3306/hospital_queue_management_db";

        String username = "root";
        String password = "I_1t_Relax!";

        Connection conn =
                DriverManager.getConnection(
                        url,
                        username,
                        password
                );

        System.out.println("Connection Successful");

        return conn;

    } catch (Exception e) {

        e.printStackTrace();

    }

    return null;
}

    @Override
    public void closeConnection(Connection conn) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResultSet runQuery(Connection conn, String Query) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int executeUpdate(Connection conn, String Query) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}