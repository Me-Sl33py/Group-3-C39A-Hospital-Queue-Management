package model;

<<<<<<< HEAD
/**
 * Model class for User — maps to the 'users' table
 * Contains constructor, getters and setters
 */
public class User {

    // Fields matching the users table columns
    private int userId;
    private String username;
    private String password;
    private String role; // patient, doctor, receptionist, admin

    // Default constructor
    public User() {
    }

    // Full constructor (when reading from database)
    public User(int userId, String username, String password, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Constructor without ID (for new user registration)
=======
public class User {
    private int userId;
    private String username;
    private String password;
    private String role;

    public User() {}

>>>>>>> admin_module
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

<<<<<<< HEAD
    // ==================== Getters ====================
    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    // ==================== Setters ====================
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
=======
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
>>>>>>> admin_module
