/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
 
public class Doctor {
 
    private int    doctorId;
    private String fullName;
    private String email;
    private String phone;
    private String specialization;
    private String assignedRoom;
    private String shiftHours;
    private String securityLevel;
    private String accountStatus;
    private String lastLogin;
 
    // ── Constructors ──────────────────────────────────────────────────────────
 
    public Doctor() {}
 
    public Doctor(int doctorId, String fullName, String email, String phone,
                  String specialization, String assignedRoom, String shiftHours,
                  String securityLevel, String accountStatus, String lastLogin) {
        this.doctorId       = doctorId;
        this.fullName       = fullName;
        this.email          = email;
        this.phone          = phone;
        this.specialization = specialization;
        this.assignedRoom   = assignedRoom;
        this.shiftHours     = shiftHours;
        this.securityLevel  = securityLevel;
        this.accountStatus  = accountStatus;
        this.lastLogin      = lastLogin;
    }
 
    // ── Getters ───────────────────────────────────────────────────────────────
 
    public int    getDoctorId()       { return doctorId;       }
    public String getFullName()       { return fullName;       }
    public String getEmail()          { return email;          }
    public String getPhone()          { return phone;          }
    public String getSpecialization() { return specialization; }
    public String getAssignedRoom()   { return assignedRoom;   }
    public String getShiftHours()     { return shiftHours;     }
    public String getSecurityLevel()  { return securityLevel;  }
    public String getAccountStatus()  { return accountStatus;  }
    public String getLastLogin()      { return lastLogin;      }
 
    // ── Setters ───────────────────────────────────────────────────────────────
 
    public void setDoctorId(int doctorId)              { this.doctorId       = doctorId;       }
    public void setFullName(String fullName)           { this.fullName       = fullName;       }
    public void setEmail(String email)                 { this.email          = email;          }
    public void setPhone(String phone)                 { this.phone          = phone;          }
    public void setSpecialization(String specialization){ this.specialization = specialization;}
    public void setAssignedRoom(String assignedRoom)   { this.assignedRoom   = assignedRoom;   }
    public void setShiftHours(String shiftHours)       { this.shiftHours     = shiftHours;     }
    public void setSecurityLevel(String securityLevel) { this.securityLevel  = securityLevel;  }
    public void setAccountStatus(String accountStatus) { this.accountStatus  = accountStatus;  }
    public void setLastLogin(String lastLogin)         { this.lastLogin      = lastLogin;      }
 
    @Override
    public String toString() {
        return "Doctor{doctorId=" + doctorId +
               ", fullName='"       + fullName       + '\'' +
               ", specialization='" + specialization + '\'' +
               ", accountStatus='"  + accountStatus  + '\'' + '}';
    }
}
