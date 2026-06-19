import dao.UserDAO;
import database.MySqlConnection;
import model.Doctor;
import view.DoctorPanel;
import controller.DoctorController;
import dao.DoctorDAO;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class TestDB {
    public static void main(String[] args) {
        try {
            UserDAO userDAO = new UserDAO();
            DoctorDAO docDao = new DoctorDAO();
            String doctorId = userDAO.getDoctorIdByUserId(8);
            System.out.println("Got doctorId: " + doctorId);
            
            Doctor doc = docDao.getDoctorById(doctorId);
            System.out.println("Got Doctor: " + (doc != null ? doc.getFullName() : "null"));

            System.out.println("Instantiating UI...");
            DoctorPanel dp = new DoctorPanel();
            DoctorController dc = new DoctorController(dp);
            
            System.out.println("Setting current doctor...");
            dc.setCurrentDoctor(doc);
            
            System.out.println("Showing UI...");
            dp.setVisible(true);
            System.out.println("Success!");
            
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
