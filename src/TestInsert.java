import dao.PatientDAO;
import model.Patient;
import java.sql.Date;
import java.sql.Timestamp;

public class TestInsert {
    public static void main(String[] args) {
        PatientDAO patientDAO = new PatientDAO();
        String patientId = patientDAO.generatePatientId();
        Date dob = new Date(System.currentTimeMillis());
        Patient p = new Patient(patientId, "Test Name", dob, 30, "male", "1234567890", "Address", "A+", "", new Timestamp(System.currentTimeMillis()));
        
        System.out.println("Trying to insert...");
        String result = patientDAO.insertPatient(p);
        System.out.println("Result: " + result);
    }
}
