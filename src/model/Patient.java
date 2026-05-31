/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
 
public class Patient {
 
    private int patientId;
    private String name;
    private String status; // "Waiting", "Confirmed", "No Show", "Completed"
 
    // ── Constructors ──────────────────────────────────────────────────────────
 
    public Patient() {}
 
    public Patient(int patientId, String name, String status) {
        this.patientId = patientId;
        this.name      = name;
        this.status    = status;
    }
 
    // ── Getters ───────────────────────────────────────────────────────────────
 
    public int getPatientId()  { return patientId; }
    public String getName()    { return name;       }
    public String getStatus()  { return status;     }
 
    // ── Setters ───────────────────────────────────────────────────────────────
 
    public void setPatientId(int patientId) { this.patientId = patientId; }
    public void setName(String name)        { this.name      = name;      }
    public void setStatus(String status)    { this.status    = status;    }
 
    @Override
    public String toString() {
        return "Patient{patientId=" + patientId +
               ", name='"   + name   + '\'' +
               ", status='" + status + '\'' + '}';
    }
}
