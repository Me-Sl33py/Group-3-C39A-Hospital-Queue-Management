/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hospital.queue.management;

/**
 *
 * @author User
 */
public class HospitalQueueManagement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Launch the application frontend
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new view.Patients().setVisible(true);
            }
        });
    }
    
}
