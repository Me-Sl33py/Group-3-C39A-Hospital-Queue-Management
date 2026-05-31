/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
 
import java.time.LocalDateTime;
 
public class MedicalRecord {
 
    private int    recordId;
    private int    patientId;
    private String patientName;
    private int    doctorId;
    private String clinicalNotes;
    private LocalDateTime dateCreated;
 
    // ── Constructors ──────────────────────────────────────────────────────────
 
    public MedicalRecord() {}
 
    public MedicalRecord(int recordId, int patientId, String patientName,
                         int doctorId, String clinicalNotes,
                         LocalDateTime dateCreated) {
        this.recordId      = recordId;
        this.patientId     = patientId;
        this.patientName   = patientName;
        this.doctorId      = doctorId;
        this.clinicalNotes = clinicalNotes;
        this.dateCreated   = dateCreated;
    }
 
    // ── Getters ───────────────────────────────────────────────────────────────
 
    public int            getRecordId()      { return recordId;      }
    public int            getPatientId()     { return patientId;     }
    public String         getPatientName()   { return patientName;   }
    public int            getDoctorId()      { return doctorId;      }
    public String         getClinicalNotes() { return clinicalNotes; }
    public LocalDateTime  getDateCreated()   { return dateCreated;   }
 
    // ── Setters ───────────────────────────────────────────────────────────────
 
    public void setRecordId(int recordId)              { this.recordId      = recordId;      }
    public void setPatientId(int patientId)            { this.patientId     = patientId;     }
    public void setPatientName(String patientName)     { this.patientName   = patientName;   }
    public void setDoctorId(int doctorId)              { this.doctorId      = doctorId;      }
    public void setClinicalNotes(String clinicalNotes) { this.clinicalNotes = clinicalNotes; }
    public void setDateCreated(LocalDateTime dateCreated) { this.dateCreated = dateCreated;  }
 
    @Override
    public String toString() {
        return "MedicalRecord{recordId=" + recordId +
               ", patientId="   + patientId     +
               ", patientName='" + patientName  + '\'' +
               ", doctorId="    + doctorId      +
               ", dateCreated=" + dateCreated   + '}';
    }
}
