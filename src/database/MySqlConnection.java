/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * MySqlConnection — implements the Db interface to handle all MySQL connections.
 * 
 * IMPORTANT: openConnection() opens a connection, and closeConnection() closes it.
 * Every DAO method must call closeConnection() in its finally block to avoid leaks.
 * 
 * @author User
 */
public class MySqlConnection implements DB {

    // ==================== Database Configuration ====================
    // Configuration is now loaded dynamically from db.properties

    /**
     * Static helper method to get a connection directly.
     */
    public static Connection getConnection() {
        return new MySqlConnection().openConnection();
    }

    /**
     * Opens and returns a MySQL database connection.
     * @return Connection object, or null if connection failed
     */
    @Override
    public Connection openConnection() {
        try {
            // Load database configuration from db.properties
            Properties props = new Properties();
            try (FileInputStream in = new FileInputStream("db.properties")) {
                props.load(in);
            } catch (IOException e) {
                System.out.println("[DB] Error loading db.properties: " + e.getMessage());
                return null;
            }

            String dbHost = props.getProperty("db.host", "localhost");
            String dbPort = props.getProperty("db.port", "3306");
            String dbName = props.getProperty("db.name", "hospital_queue_management_db");
            String dbUser = props.getProperty("db.user", "root");
            String dbPass = props.getProperty("db.password", "");
            
            String dbUrl = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

            // Load the MySQL JDBC driver (needed for older JDK/JDBC versions)
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);

            if (conn == null) {
                System.out.println("[DB] Connection NOT successful.");
            } else {
                System.out.println("[DB] Connection successful.");
            }
            return conn;

        } catch (ClassNotFoundException e) {
            // Driver JAR not found — add mysql-connector-j to your project libraries
            System.out.println("[DB] MySQL driver not found: " + e.getMessage());
        } catch (SQLException e) {
            // Wrong credentials, database doesn't exist, or MySQL not running
            System.out.println("[DB] Connection failed: " + e.getMessage());
        }
        return null;
    }

    /**
     * Closes the given database connection safely.
     * Always call this in a finally block inside every DAO method.
     * @param conn the Connection to close
     */
    @Override
    public void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("[DB] Connection closed.");
            } catch (SQLException e) {
                System.out.println("[DB] Error closing connection: " + e.getMessage());
            }
        }
    }

    /**
     * Runs a SELECT query and returns the ResultSet.
     * Caller is responsible for closing the statement/result set.
     * @param conn open Connection object
     * @param query SQL SELECT query string
     * @return ResultSet of results, or null if error
     */
    @Override
    public ResultSet runQuery(Connection conn, String query) {
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            return ps.executeQuery();
        } catch (SQLException e) {
            System.out.println("[DB] runQuery error: " + e.getMessage());
        }
        return null;
    }

    /**
     * Runs an INSERT, UPDATE, or DELETE query.
     * @param conn open Connection object
     * @param query SQL DML query string
     * @return number of rows affected, or -1 if error
     */
    @Override
    public int executeUpdate(Connection conn, String query) {
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("[DB] executeUpdate error: " + e.getMessage());
        }
        return -1;
    }
}
