package model;
public class Doctor {
    private String doctorId;
    private int    userId;
    private String fullName;
    private String specialization;
    private int    departmentId;
    private String departmentName;   // ADD THIS
    private String contactNumber;
    private String availability;
    private String username;
    private String address;
    private String bloodGroup;

    public Doctor() {}

    public Doctor(String doctorId, int userId, String fullName,
                  String specialization, int departmentId,
                  String contactNumber, String availability) {
        this.doctorId       = doctorId;
        this.userId         = userId;
        this.fullName       = fullName;
        this.specialization = specialization;
        this.departmentId   = departmentId;
        this.contactNumber  = contactNumber;
        this.availability   = availability;
    }

    public String getDoctorId()         { return doctorId;       }
    public int    getUserId()           { return userId;         }
    public String getFullName()         { return fullName;       }
    public String getSpecialization()   { return specialization; }
    public int    getDepartmentId()     { return departmentId;   }
    public String getDepartmentName()   { return departmentName; }   // ADD THIS
    public String getContactNumber()    { return contactNumber;  }
    public String getAvailability()     { return availability;   }

    public void setDoctorId(String doctorId)             { this.doctorId       = doctorId;       }
    public void setUserId(int userId)                    { this.userId         = userId;         }
    public void setFullName(String fullName)             { this.fullName       = fullName;       }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public void setDepartmentId(int departmentId)        { this.departmentId   = departmentId;   }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; } // ADD THIS
    public void setContactNumber(String contactNumber)   { this.contactNumber  = contactNumber;  }
    public void setAvailability(String availability)     { this.availability   = availability;   }
    @Override
    public String toString() {
        return fullName != null ? "Dr. " + fullName : "Unknown Doctor";
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }
}