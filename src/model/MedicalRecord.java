/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class MedicalRecord {
    private int    recordId;
    private int    appointmentId;
    private String patientId;
    private String doctorId;
    private String diagnosis;
    private String prescription;
    private String notes;

    public MedicalRecord() {}

    public MedicalRecord(int recordId, int appointmentId, String patientId,
                         String doctorId, String diagnosis,
                         String prescription, String notes) {
        this.recordId      = recordId;
        this.appointmentId = appointmentId;
        this.patientId     = patientId;
        this.doctorId      = doctorId;
        this.diagnosis     = diagnosis;
        this.prescription  = prescription;
        this.notes         = notes;
    }

    public int    getRecordId()      { return recordId;      }
    public int    getAppointmentId() { return appointmentId; }
    public String getPatientId()     { return patientId;     }
    public String getDoctorId()      { return doctorId;      }
    public String getDiagnosis()     { return diagnosis;     }
    public String getPrescription()  { return prescription;  }
    public String getNotes()         { return notes;         }

    public void setRecordId(int recordId)           { this.recordId      = recordId;      }
    public void setAppointmentId(int appointmentId) { this.appointmentId = appointmentId; }
    public void setPatientId(String patientId)      { this.patientId     = patientId;     }
    public void setDoctorId(String doctorId)        { this.doctorId      = doctorId;      }
    public void setDiagnosis(String diagnosis)      { this.diagnosis     = diagnosis;     }
    public void setPrescription(String prescription){ this.prescription  = prescription;  }
    public void setNotes(String notes)              { this.notes         = notes;         }
}