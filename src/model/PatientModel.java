package model;

/**
 * PatientModel — a plain Java class (POJO) that maps to the 'patients' table.
 *
 * Fields match the database columns:
 *   patient_id     VARCHAR  (e.g., "P-001")
 *   user_id        INT      (foreign key to users table)
 *   full_name      VARCHAR
 *   age            INT
 *   gender         ENUM     ("male", "female", "other")
 *   contact_number VARCHAR
 *   address        VARCHAR
 *
 * Architecture Rule: This class contains ONLY data fields + constructor + getters + setters.
 * No SQL, no UI code allowed here.
 *
 * @author Group 3 C39A
 */
public class PatientModel {

    // ==================== Fields ====================

    private String         patientId;      // e.g., "P-001", "P-002"
    private int            userId;         // foreign key to users.user_id
    private String         fullName;       // patient's full name
    private java.sql.Date  dob;            // date of birth stored as SQL date (YYYY-MM-DD)
    private int            age;            // patient's age in years (auto-calculated from dob)
    private String         gender;         // "male", "female", or "other"
    private String         contactNumber;  // 10-digit phone number
    private String         address;        // patient's home address

    // ==================== Constructors ====================

    /**
     * Default (no-argument) constructor.
     * Needed when creating an empty object and setting fields one by one.
     */
    public PatientModel() {
        // empty — fields will be set using setters
    }

    /**
     * Full constructor — use when you already have all values ready.
     * Commonly used when reading a patient from the database.
     *
     * @param patientId     the patient ID string, e.g., "P-001"
     * @param userId        the foreign key linking to the users table
     * @param fullName      the patient's full name
     * @param dob           date of birth as java.sql.Date (use new java.sql.Date(millis))
     * @param age           the patient's age (auto-calculated from dob)
     * @param gender        "male", "female", or "other"
     * @param contactNumber the patient's 10-digit contact number
     * @param address       the patient's home address
     */
    public PatientModel(String patientId, int userId, String fullName,
                        java.sql.Date dob, int age, String gender,
                        String contactNumber, String address) {
        this.patientId     = patientId;
        this.userId        = userId;
        this.fullName      = fullName;
        this.dob           = dob;
        this.age           = age;
        this.gender        = gender;
        this.contactNumber = contactNumber;
        this.address       = address;
    }

    // ==================== Getters ====================
    // Getters allow other classes to READ the private fields

    /**
     * @return the patient ID string (e.g., "P-001")
     */
    public String getPatientId() {
        return patientId;
    }

    /**
     * @return the user_id (foreign key to users table)
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @return the patient's full name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @return the patient's date of birth as a java.sql.Date (YYYY-MM-DD)
     */
    public java.sql.Date getDob() {
        return dob;
    }

    /**
     * @return the patient's age in years (auto-calculated from dob)
     */
    public int getAge() {
        return age;
    }

    /**
     * @return the patient's gender ("male", "female", or "other")
     */
    public String getGender() {
        return gender;
    }

    /**
     * @return the patient's contact number (phone)
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * @return the patient's home address
     */
    public String getAddress() {
        return address;
    }

    // ==================== Setters ====================
    // Setters allow other classes to WRITE/UPDATE the private fields

    /**
     * @param patientId the patient ID to set (e.g., "P-001")
     */
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    /**
     * @param userId the user_id (foreign key) to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @param fullName the patient's full name to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @param dob the patient's date of birth as java.sql.Date
     *            Example: new java.sql.Date(selectedDate.getTime())
     */
    public void setDob(java.sql.Date dob) {
        this.dob = dob;
    }

    /**
     * @param age the patient's age to set (auto-calculated from dob)
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @param gender "male", "female", or "other"
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @param contactNumber the 10-digit contact number to set
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * @param address the patient's home address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns a human-readable string representation of this PatientModel.
     * Useful for debugging (e.g., System.out.println(patient)).
     */
    @Override
    public String toString() {
        return "PatientModel{" +
               "patientId='" + patientId + '\'' +
               ", userId=" + userId +
               ", fullName='" + fullName + '\'' +
               ", dob=" + dob +
               ", age=" + age +
               ", gender='" + gender + '\'' +
               ", contactNumber='" + contactNumber + '\'' +
               ", address='" + address + '\'' +
               '}';
    }
}
