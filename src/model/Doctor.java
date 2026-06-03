package model;

public class Doctor {
    private String doctorId;
    private int userId;
    private String fullName;
    private String specialization;
    private int departmentId;
    private String contactNumber;
    private String availability;

    public Doctor() {}

    public Doctor(String doctorId, int userId, String fullName,
                  String specialization, int departmentId, String contactNumber) {
        this.doctorId = doctorId;
        this.userId = userId;
        this.fullName = fullName;
        this.specialization = specialization;
        this.departmentId = departmentId;
        this.contactNumber = contactNumber;
        this.availability = "available";
    }

    public String getDoctorId() { return doctorId; }
    public void setDoctorId(String doctorId) { this.doctorId = doctorId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public int getDepartmentId() { return departmentId; }
    public void setDepartmentId(int departmentId) { this.departmentId = departmentId; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    public String getAvailability() { return availability; }
    public void setAvailability(String availability) { this.availability = availability; }
}

