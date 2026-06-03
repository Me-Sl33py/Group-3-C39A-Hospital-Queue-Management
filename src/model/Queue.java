package model;

public class Queue {
    private int queueId;
    private int appointmentId;
    private String patientId;
    private String doctorId;
    private int tokenNumber;
    private String status;

    public Queue() {}

    public Queue(int appointmentId, String patientId,
                 String doctorId, int tokenNumber) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.tokenNumber = tokenNumber;
        this.status = "waiting";
    }

    public int getQueueId() { return queueId; }
    public void setQueueId(int queueId) { this.queueId = queueId; }

    public int getAppointmentId() { return appointmentId; }
    public void setAppointmentId(int appointmentId) { this.appointmentId = appointmentId; }

    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }

    public String getDoctorId() { return doctorId; }
    public void setDoctorId(String doctorId) { this.doctorId = doctorId; }

    public int getTokenNumber() { return tokenNumber; }
    public void setTokenNumber(int tokenNumber) { this.tokenNumber = tokenNumber; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
