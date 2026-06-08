package session;

public class PatientSession {
    private static String patientId = "P001"; // Fallback current patient_id
    private static int userId = 1; // Fallback user_id
    private static String username = "testuser";
    private static String role = "patient";

    public static String getPatientId() {
        return patientId;
    }

    public static void setPatientId(String patientId) {
        PatientSession.patientId = patientId;
    }

    public static int getUserId() {
        return userId;
    }

    public static void setUserId(int userId) {
        PatientSession.userId = userId;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        PatientSession.username = username;
    }

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        PatientSession.role = role;
    }

    public static void clearSession() {
        patientId = null;
        userId = -1;
        username = null;
        role = null;
    }
}
