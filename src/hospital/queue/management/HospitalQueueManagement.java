/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hospital.queue.management;

import database.Db;
import database.MySqlConnection;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Main Entry Point for the Hospital Queue Management System (Doctor Panel Branch).
 */
public class HospitalQueueManagement {

    public static void main(String[] args) {
        
        // 1. Set the Nimbus look and feel for a modern UI appearance
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            Logger.getLogger(HospitalQueueManagement.class.getName()).log(Level.SEVERE, null, ex);
        }

        // 2. Optional: Test the database connection briefly at startup
        Db database = new MySqlConnection();
        java.sql.Connection conn = database.openConnection();
        if (conn != null) {
            database.closeConnection(conn); // Close it since DAOs will manage their own connections
        } else {
            System.err.println("Startup Check Warning: Could not connect to the database.");
        }

        // 3. Launch the DoctorPanel screen as the starting point of the app
        java.awt.EventQueue.invokeLater(() -> {
            view.DoctorPanel view = new view.DoctorPanel();
            controller.DoctorController controller = new controller.DoctorController(view);
            
            // For testing the DoctorPanel standalone without logging in, we inject a test doctor.
            // If D001 exists in your DB, it will load their actual name/specialty later.
            model.Doctor testDoctor = new model.Doctor();
            testDoctor.setDoctorId("D001");
            testDoctor.setFullName("Dr. Tester");
            controller.setCurrentDoctor(testDoctor);
            
            view.setVisible(true);
        });
    }
}