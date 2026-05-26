/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hospital.queue.management;

import database.Db;
import database.MySqlConnection;
import view.DashboardView;
import controller.DashboardController;

/**
 * Main entry point for the Hospital Queue Management application.
 */
public class HospitalQueueManagement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // 1. Try to initialize the database connection
        try {
            Db database = new MySqlConnection();
            database.openConnection();
        } catch (Exception e) {
            System.err.println("Database connection failed: " + e.getMessage());
        }

        // 2. Set cross-platform look and feel or Nimbus for modern layout
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // Keep default system L&F
        }

        // 3. Launch Dashboard GUI under Event Dispatch Thread (EDT)
        java.awt.EventQueue.invokeLater(() -> {
            DashboardView view = new DashboardView();
            new DashboardController(view);
            view.setVisible(true);
        });
    }
    
}
