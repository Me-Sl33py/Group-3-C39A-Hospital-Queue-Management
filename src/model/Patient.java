package model;

/**
 * Model class for Patient — maps to the 'patients' table
 * Contains constructor, getters and setters
 */
public class Patient {

    // Fields matching the patients table columns
    private String patientId;   // e.g., "p-001"
    private int userId;         // foreign key to users table
    private String fullName;
    private int age;
    private String gender;      // male, female, other
    private String contactNumber;
    private String address;

    // Default constructor
    public Patient() {
    }

    // Full constructor (when reading from database)
    public Patient(String patientId, int userId, String fullName, int age,
                   String gender, String contactNumber, String address) {
        this.patientId = patientId;
        this.userId = userId;
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.address = address;
    }

    // ==================== Getters ====================
    public String getPatientId() {
        return patientId;
    }

    public int getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getAddress() {
        return address;
    }

    // ==================== Setters ====================
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
