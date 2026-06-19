import dao.UserDAO;
import model.Doctor;
import view.DoctorPanel;
import controller.DoctorController;

public class TestDoctor {
    public static void main(String[] args) {
        try {
            System.out.println("Instantiating DoctorPanel...");
            DoctorPanel dp = new DoctorPanel();
            System.out.println("DoctorPanel instantiated.");
            
            System.out.println("Instantiating DoctorController...");
            DoctorController dc = new DoctorController(dp);
            System.out.println("DoctorController instantiated.");
            
            dp.setVisible(true);
            System.out.println("DoctorPanel made visible.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
