package model;

<<<<<<< HEAD
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
    private java.util.Date dob;
    private String bloodGroup;
    private String reason;
    private java.sql.Timestamp createdAt;

    // Default constructor
    public Patient() {
    }

    // Constructor from patient_integration
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

    // Constructor from receptionist
    public Patient(String patientId, String fullName, java.util.Date dob, int age, String gender, String contactNumber, String address, String bloodGroup, String reason, java.sql.Timestamp createdAt) {
        this.patientId = patientId;
        this.fullName = fullName;
        this.dob = dob;
        this.age = age;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.address = address;
        this.bloodGroup = bloodGroup;
        this.reason = reason;
        this.createdAt = createdAt;
    }

    // ==================== Getters & Setters ====================

    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public java.util.Date getDob() { return dob; }
    public void setDob(java.util.Date dob) { this.dob = dob; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public java.sql.Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(java.sql.Timestamp createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return fullName;
    }
}
