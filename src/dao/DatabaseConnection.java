/*
 * Hospital Queue Management System
 * Database Connection - reads credentials from db.properties
 * Each developer must have their own db.properties file locally.
 */
package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    private static Connection connection = null;
    private static Properties props = null;

    /**
     * Loads db.properties from the project root directory.
     * Falls back to safe defaults if file is missing (connection will fail
     * with a clear error rather than a silent crash).
     */
    private static Properties loadProperties() {
        if (props != null) return props;

        props = new Properties();

        // 1. Try project root folder (db.properties next to nbproject/)
        File f = new File("db.properties");
        if (f.exists()) {
            try (InputStream in = new FileInputStream(f)) {
                props.load(in);
                System.out.println("[DB] Loaded credentials from: " + f.getAbsolutePath());
                return props;
            } catch (IOException e) {
                System.err.println("[DB] Could not read db.properties: " + e.getMessage());
            }
        }

        // 2. Try classpath (src/db.properties) as fallback
        try (InputStream in = DatabaseConnection.class.getClassLoader()
                .getResourceAsStream("db.properties")) {
            if (in != null) {
                props.load(in);
                System.out.println("[DB] Loaded credentials from classpath db.properties.");
                return props;
            }
        } catch (IOException e) {
            System.err.println("[DB] Could not read classpath db.properties: " + e.getMessage());
        }

        System.err.println("[DB] WARNING: db.properties not found! " +
                "Copy db.properties.example to db.properties and fill in your MySQL password.");
        return props; // empty — connection will throw a clear SQLException
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            Properties p = loadProperties();

            String host     = p.getProperty("db.host",     "localhost");
            String port     = p.getProperty("db.port",     "3306");
            String dbName   = p.getProperty("db.name",     "hospital_queue_management_db");
            String user     = p.getProperty("db.user",     "root");
            String password = p.getProperty("db.password", "");

            String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName +
                         "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("[DB] Database connected successfully.");
            } catch (ClassNotFoundException e) {
                throw new SQLException("MySQL JDBC Driver not found. Add mysql-connector-j to project libraries.");
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("[DB] Database connection closed.");
            } catch (SQLException e) {
                System.err.println("[DB] Error closing connection: " + e.getMessage());
            }
        }
    }
}
