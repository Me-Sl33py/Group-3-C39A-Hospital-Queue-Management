package model;

public class Doctor {
    private String doctorId;
    private String fullName;
    private String specialization;
    private int departmentId;
    private String availability;
    private String departmentName;

    public Doctor(String doctorId, String fullName, String specialization, int departmentId, String availability) {
        this.doctorId = doctorId;
        this.fullName = fullName;
        this.specialization = specialization;
        this.departmentId = departmentId;
        this.availability = availability;
    }

    public String getDoctorId() { return doctorId; }
    public void setDoctorId(String doctorId) { this.doctorId = doctorId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public int getDepartmentId() { return departmentId; }
    public void setDepartmentId(int departmentId) { this.departmentId = departmentId; }

    public String getAvailability() { return availability; }
    public void setAvailability(String availability) { this.availability = availability; }
    
    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }

    @Override
    public String toString() {
        return fullName + " (" + (specialization != null ? specialization : "General") + ")";
    }
}
