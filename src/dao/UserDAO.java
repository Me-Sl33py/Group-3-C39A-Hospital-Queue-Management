package dao;

import database.MySqlConnection;
import model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * DAO class for User — handles all database queries for the users table
 * Methods: login, register, search, update password, etc.
 */
public class UserDAO {

    // Database connection object
    private MySqlConnection db;

    // Constructor — create database connection
    public UserDAO() {
        this.db = new MySqlConnection();
    }

    // ==================== LOGIN METHODS ====================

    /**
     * Login using username/ID and password
     * @param username the username or ID entered by user
     * @param password the password entered by user
     * @return User object if login successful, null if failed
     */
    public User loginById(String username, String password) {
        Connection conn = null;
        try {
            conn = db.openConnection();
            // Query the users table for matching username and password
            String sql = "SELECT * FROM users WHERE LOWER(username) = LOWER(?) AND password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            // If a row is found, create and return a User object
            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                return user;
            }
        } catch (Exception e) {
            System.out.println("Login by ID error: " + e);
        } finally {
            // Always close connection
            if (conn != null) {
                db.closeConnection(conn);
            }
        }
        return null; // login failed
    }

    /**
     * Login using phone number and password
     * Searches patients and doctors tables for matching contact number
     * @param phone the phone number entered by user
     * @param password the password entered by user
     * @return User object if login successful, null if failed
     */
    public User loginByPhone(String phone, String password) {
        Connection conn = null;
        try {
            conn = db.openConnection();
            // First try to find in patients table
            String sql = "SELECT u.* FROM users u "
                       + "JOIN patients p ON u.user_id = p.user_id "
                       + "WHERE (p.contact_number = ? OR LOWER(p.full_name) = LOWER(?)) AND u.password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, phone);
            ps.setString(2, phone);
            ps.setString(3, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                return user;
            }

            // If not found in patients, try doctors table
            sql = "SELECT u.* FROM users u "
                + "JOIN doctors d ON u.user_id = d.user_id "
                + "WHERE (d.contact_number = ? OR LOWER(d.full_name) = LOWER(?)) AND u.password = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, phone);
            ps.setString(2, phone);
            ps.setString(3, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                return user;
            }
        } catch (Exception e) {
            System.out.println("Login by phone error: " + e);
        } finally {
            if (conn != null) {
                db.closeConnection(conn);
            }
        }
        return null; // login failed
    }

    // ==================== REGISTRATION METHODS ====================

    /**
     * Register a new user in the users table
     * @param username the username for login
     * @param password the password for login
     * @param role the user role (patient, doctor, etc.)
     * @return the generated user_id, or -1 if failed
     */
    public int registerUser(String username, String password, String role) {
        Connection conn = null;
        try {
            conn = db.openConnection();
            String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
            // RETURN_GENERATED_KEYS tells MySQL to give us the auto-generated user_id
            PreparedStatement ps = conn.prepareStatement(sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                // Get the auto-generated user_id
                ResultSet keys = ps.getGeneratedKeys();
                if (keys.next()) {
                    return keys.getInt(1);
                }
            }
        } catch (Exception e) {
            System.out.println("Register user error: " + e);
        } finally {
            if (conn != null) {
                db.closeConnection(conn);
            }
        }
        return -1; // registration failed
    }

    /**
     * Register a new patient in the patients table
     * @param patientId generated patient ID like "p-002"
     * @param userId the user_id from the users table
     * @param fullName patient's full name
     * @param age patient's age
     * @param gender patient's gender (male/female/other)
     * @param contactNumber patient's phone number
     * @param address patient's address/location
     * @return true if registration successful, false if failed
     */
    public boolean registerPatient(String patientId, int userId, String fullName,
                                   int age, String gender, String contactNumber,
                                   String address) {
        Connection conn = null;
        try {
            conn = db.openConnection();
            String sql = "INSERT INTO patients (patient_id, user_id, full_name, age, "
                       + "gender, contact_number, address) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, patientId);
            ps.setInt(2, userId);
            ps.setString(3, fullName);
            ps.setInt(4, age);
            ps.setString(5, gender);
            ps.setString(6, contactNumber);
            ps.setString(7, address);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Register patient error: " + e);
        } finally {
            if (conn != null) {
                db.closeConnection(conn);
            }
        }
        return false;
    }

    // ==================== SEARCH / FORGOT PASSWORD METHODS ====================

    /**
     * Search for a user by patient details (for forgot password)
     * @param fullName patient's full name
     * @param phone patient's phone number
     * @param dob patient's date of birth as java.sql.Date
     * @param location patient's address/location
     * @return the user_id if found, -1 if not found
     */
    public int searchUserForReset(String fullName, String phone, java.sql.Date dob,
                                  String location) {
        Connection conn = null;
        try {
            conn = db.openConnection();
            // Search patients table by full name and phone number
            String sql = "SELECT p.user_id FROM patients p "
                       + "JOIN users u ON p.user_id = u.user_id "
                       + "WHERE (LOWER(p.full_name) = LOWER(?) OR LOWER(u.username) = LOWER(?)) "
                       + "AND p.contact_number = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, fullName.trim());
            ps.setString(2, fullName.trim());
            ps.setString(3, phone.trim());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("user_id");
            }
        } catch (Exception e) {
            System.out.println("Search user error: " + e);
        } finally {
            if (conn != null) {
                db.closeConnection(conn);
            }
        }
        return -1; // user not found
    }

    /**
     * Update user's password (for forgot password)
     * @param userId the user_id whose password to update
     * @param newPassword the new password
     * @return true if update successful, false if failed
     */
    public boolean updatePassword(int userId, String newPassword) {
        Connection conn = null;
        try {
            conn = db.openConnection();
            String sql = "UPDATE users SET password = ? WHERE user_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, newPassword);
            ps.setInt(2, userId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Update password error: " + e);
        } finally {
            if (conn != null) {
                db.closeConnection(conn);
            }
        }
        return false;
    }

    // ==================== HELPER METHODS ====================

    /**
     * Check if a username already exists in the users table
     * @param username the username to check
     * @return true if username exists, false if available
     */
    public boolean isUsernameExists(String username) {
        Connection conn = null;
        try {
            conn = db.openConnection();
            String sql = "SELECT user_id FROM users WHERE username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // true if a row was found
        } catch (Exception e) {
            System.out.println("Check username error: " + e);
        } finally {
            if (conn != null) {
                db.closeConnection(conn);
            }
        }
        return false;
    }

    /**
     * Generate the next patient ID (e.g., "p-002", "p-003")
     * Looks at the last patient_id in the database and increments it
     * @return the next patient ID string
     */
    public String generatePatientId() {
        Connection conn = null;
        try {
            conn = db.openConnection();
            String sql = "SELECT patient_id FROM patients ORDER BY patient_id DESC LIMIT 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Get last ID like "p-001" and extract the number
                String lastId = rs.getString("patient_id");
                int num = Integer.parseInt(lastId.split("-")[1]);
                // Return next ID like "p-002"
                return String.format("p-%03d", num + 1);
            }
            return "p-001"; // first patient
        } catch (Exception e) {
            System.out.println("Generate patient ID error: " + e);
        } finally {
            if (conn != null) {
                db.closeConnection(conn);
            }
        }
        return "p-001";
    }
    public String checkLogin(String identifier, String password) {
        String query = 
            "select u.role, u.user_id from users u " +
            "left join patients p on u.user_id = p.user_id " +
            "left join doctors d on u.user_id = d.user_id " +
            "where (LOWER(u.username) = LOWER(?) " +
            "or p.contact_number = ? " +
            "or LOWER(p.full_name) = LOWER(?) " +
            "or d.contact_number = ? " +
            "or LOWER(d.full_name) = LOWER(?)) " +
            "and u.password = ?";
            
        Connection conn = null;
        try {
            conn = db.openConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, identifier);
            ps.setString(2, identifier);
            ps.setString(3, identifier);
            ps.setString(4, identifier);
            ps.setString(5, identifier);
            ps.setString(6, password);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("role");
            }
        } catch (Exception e) {
            System.out.println("checkLogin error: " + e);
        } finally {
            if (conn != null) {
                db.closeConnection(conn);
            }
        }
        return null;
    }

    public int getUserId(String identifier) {
        String query = 
            "select u.user_id from users u " +
            "left join patients p " +
            "on u.user_id = p.user_id " +
            "where u.username = ? " +
            "or p.contact_number = ?";
            
        Connection conn = null;
        try {
            conn = db.openConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, identifier);
            ps.setString(2, identifier);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("user_id");
            }
        } catch (Exception e) {
            System.out.println("getUserId error: " + e);
        } finally {
            if (conn != null) {
                db.closeConnection(conn);
            }
        }
        return -1;
    }
}
