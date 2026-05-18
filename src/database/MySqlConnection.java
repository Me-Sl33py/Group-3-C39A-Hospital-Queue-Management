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
            String database = "working_db";
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
