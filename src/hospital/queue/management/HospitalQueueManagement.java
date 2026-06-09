/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hospital.queue.management;
import database.Db;
import database.MySqlConnection;
/**
 *
 * @author User
 */
public class HospitalQueueManagement {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Initialize DB (optional if controllers do it)
        Db database = new MySqlConnection();
        database.openConnection();
        
        // Show the admin interface
        java.awt.EventQueue.invokeLater(() -> {
            new view.admin().setVisible(true);
        });
    }
    
}