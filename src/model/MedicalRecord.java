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
    private String doctorName;
    private String diagnosis;
    private String prescription;
    private String notes;
    private String createdAt;

    public MedicalRecord() {}

    public MedicalRecord(int recordId, int appointmentId, String patientId,
                         String doctorId, String doctorName, String diagnosis,
                         String prescription, String notes, String createdAt) {
        this.recordId      = recordId;
        this.appointmentId = appointmentId;
        this.patientId     = patientId;
        this.doctorId      = doctorId;
        this.doctorName    = doctorName;
        this.diagnosis     = diagnosis;
        this.prescription  = prescription;
        this.notes         = notes;
        this.createdAt     = createdAt;
    }

    public int    getRecordId()      { return recordId;      }
    public int    getAppointmentId() { return appointmentId; }
    public String getPatientId()     { return patientId;     }
    public String getDoctorId()      { return doctorId;      }
    public String getDoctorName()    { return doctorName;    }
    public String getDiagnosis()     { return diagnosis;     }
    public String getPrescription()  { return prescription;  }
    public String getNotes()         { return notes;         }
    public String getCreatedAt()     { return createdAt;     }

    public void setRecordId(int recordId)           { this.recordId      = recordId;      }
    public void setAppointmentId(int appointmentId) { this.appointmentId = appointmentId; }
    public void setPatientId(String patientId)      { this.patientId     = patientId;     }
    public void setDoctorId(String doctorId)        { this.doctorId      = doctorId;      }
    public void setDoctorName(String doctorName)    { this.doctorName    = doctorName;    }
    public void setDiagnosis(String diagnosis)      { this.diagnosis     = diagnosis;     }
    public void setPrescription(String prescription){ this.prescription  = prescription;  }
    public void setNotes(String notes)              { this.notes         = notes;         }
    public void setCreatedAt(String createdAt)      { this.createdAt     = createdAt;     }
}