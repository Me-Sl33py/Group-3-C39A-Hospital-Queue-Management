import dao.PatientDAO;
import model.Patient;
import java.sql.Date;
import java.sql.Timestamp;

public class TestController {
    public static void main(String[] args) {
        String name = "Qaaa Aaa";
        Date sqlDob = new Date(System.currentTimeMillis());
        int age = 0;
        String gender = "Male";
        String phone = "0123456789";
        String bloodGroup = "A+";
        
        dao.PatientDAO patientDAO = new dao.PatientDAO();
        String patientId = patientDAO.generatePatientId();
        model.Patient patient = new model.Patient(patientId, name, sqlDob, age, gender, phone, "", bloodGroup, "", new java.sql.Timestamp(System.currentTimeMillis()));

        System.out.println("Inserting " + patientId);
        String savedId = patientDAO.insertPatient(patient);
        System.out.println("Result: " + savedId);
    }
}
