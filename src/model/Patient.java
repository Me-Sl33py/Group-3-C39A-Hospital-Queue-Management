package model;

public class Patient {
    private String patientId;
    private String fullName;
    private java.sql.Date dob;
    private int age;
    private String gender;
    private String contactNumber;
    private String address;
    private String bloodGroup;
    private String reason;
    private java.sql.Timestamp createdAt;

    public Patient(String patientId, String fullName, java.sql.Date dob, int age, String gender, String contactNumber, String address, String bloodGroup, String reason, java.sql.Timestamp createdAt) {
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

    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public java.sql.Date getDob() { return dob; }
    public void setDob(java.sql.Date dob) { this.dob = dob; }

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
