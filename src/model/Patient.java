/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Patient {
    private String patientId;
    private int    userId;
    private String fullName;
    private int    age;
    private String gender;
    private String contactNumber;
    private String address;

    public Patient() {}

    public Patient(String patientId, int userId, String fullName,
                   int age, String gender, String contactNumber, String address) {
        this.patientId     = patientId;
        this.userId        = userId;
        this.fullName      = fullName;
        this.age           = age;
        this.gender        = gender;
        this.contactNumber = contactNumber;
        this.address       = address;
    }

    public String getPatientId()     { return patientId;     }
    public int    getUserId()        { return userId;        }
    public String getFullName()      { return fullName;      }
    public int    getAge()           { return age;           }
    public String getGender()        { return gender;        }
    public String getContactNumber() { return contactNumber; }
    public String getAddress()       { return address;       }

    public void setPatientId(String patientId)         { this.patientId     = patientId;     }
    public void setUserId(int userId)                  { this.userId        = userId;        }
    public void setFullName(String fullName)           { this.fullName      = fullName;      }
    public void setAge(int age)                        { this.age           = age;           }
    public void setGender(String gender)               { this.gender        = gender;        }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    public void setAddress(String address)             { this.address       = address;       }
}