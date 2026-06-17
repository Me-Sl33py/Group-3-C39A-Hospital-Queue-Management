/*
 * Hospital Queue Management System
 * MySqlConnection — reads credentials from db.properties
 */
package database;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * MySqlConnection — implements the Db interface to handle all MySQL connections.
 *
 * IMPORTANT: openConnection() opens a connection, and closeConnection() closes it.
 * Every DAO method must call closeConnection() in its finally block to avoid leaks.
 */
public class MySqlConnection implements Db {

    private static Properties loadProperties() {
        Properties props = new Properties();
        File f = new File("db.properties");
        if (f.exists()) {
            try (InputStream in = new FileInputStream(f)) {
                props.load(in);
                return props;
            } catch (IOException e) {
                System.err.println("[DB] Could not read db.properties: " + e.getMessage());
            }
        }
        try (InputStream in = MySqlConnection.class.getClassLoader()
                .getResourceAsStream("db.properties")) {
            if (in != null) { props.load(in); return props; }
        } catch (IOException e) {
            System.err.println("[DB] Could not read classpath db.properties: " + e.getMessage());
        }
        System.err.println("[DB] WARNING: db.properties not found! Copy db.properties.example and fill in your password.");
        return props;
    }

    /**
     * Opens and returns a MySQL database connection.
     * @return Connection object, or null if connection failed
     */
    @Override
    public Connection openConnection() {
        try {
            Properties p = loadProperties();
            String host     = p.getProperty("db.host",     "localhost");
            String port     = p.getProperty("db.port",     "3306");
            String dbName   = p.getProperty("db.name",     "hospital_queue_management_db");
            String user     = p.getProperty("db.user",     "root");
            String password = p.getProperty("db.password", "I_1t_Relax!");
            String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName +
                         "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            if (conn == null) {
                System.out.println("[DB] Connection NOT successful.");
            } else {
                System.out.println("[DB] Connection successful.");
            }
            return conn;
        } catch (ClassNotFoundException e) {
            System.out.println("[DB] MySQL driver not found: " + e.getMessage());
        } catch (SQLException e) {
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
