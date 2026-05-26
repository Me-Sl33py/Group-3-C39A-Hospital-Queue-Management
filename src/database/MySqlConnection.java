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
        try{
            String username = "root";
            String password = "I_1t_Relax!";
            String database = "hospital_queue_management_db";
            Connection conn;
            conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/" + database, username, password
            );
            if(conn == null){
                System.out.print("Connection not Sucessfull");
            }else{
                System.out.print("Connection Sucessfull");
            }
            return conn;
        }catch(Exception e){
            System.out.print(e);
        }
        return null;
    }

    @Override
    public void closeConnection(Connection conn) {
        // Close the database connection safely
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Connection Closed");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public ResultSet runQuery(Connection conn, String Query) {
        // Execute a SELECT query and return the ResultSet
        try {
            return conn.createStatement().executeQuery(Query);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public int executeUpdate(Connection conn, String Query) {
        // Execute INSERT, UPDATE, or DELETE query and return affected rows
        try {
            return conn.createStatement().executeUpdate(Query);
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }
    
}
