package model;

public class Patient {
    private String patientId;
    private int userId;
    private String fullName;
    private int age;
    private String gender;
    private String contactNumber;
    private String address;
    private java.sql.Date dob;
    private String bloodGroup;
    private String username;

    public Patient() {}

    public Patient(String patientId, int userId, String fullName,
                   int age, String gender, String contactNumber, String address) {
        this.patientId = patientId;
        this.userId = userId;
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.address = address;
    }

    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public java.sql.Date getDob() { return dob; }
    public void setDob(java.sql.Date dob) { this.dob = dob; }

    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}