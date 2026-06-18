package model;

import java.sql.Timestamp;

public class Token {
    private int queueId;
    private int tokenNumber;
    private String patientId;
    private String doctorId;
    private String status;
    private Timestamp createdAt;
    
    private int appointmentId;
    private int departmentId;

    // Join fields
    private String patientName;
    private String doctorName;

    public Token(int queueId, int tokenNumber, String patientId, String doctorId, String status, Timestamp createdAt) {
        this.queueId = queueId;
        this.tokenNumber = tokenNumber;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Token(int queueId, int tokenNumber, String patientId, String doctorId, String status, Timestamp createdAt, int appointmentId, int departmentId) {
        this.queueId = queueId;
        this.tokenNumber = tokenNumber;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.status = status;
        this.createdAt = createdAt;
        this.appointmentId = appointmentId;
        this.departmentId = departmentId;
    }

    public int getQueueId() { return queueId; }
    public void setQueueId(int queueId) { this.queueId = queueId; }

    public int getTokenNumber() { return tokenNumber; }
    public void setTokenNumber(int tokenNumber) { this.tokenNumber = tokenNumber; }

    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }

    public String getDoctorId() { return doctorId; }
    public void setDoctorId(String doctorId) { this.doctorId = doctorId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public int getAppointmentId() { return appointmentId; }
    public void setAppointmentId(int appointmentId) { this.appointmentId = appointmentId; }

    public int getDepartmentId() { return departmentId; }
    public void setDepartmentId(int departmentId) { this.departmentId = departmentId; }

    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }

    public String getDoctorName() { return doctorName; }
    public void setDoctorName(String doctorName) { this.doctorName = doctorName; }
}
