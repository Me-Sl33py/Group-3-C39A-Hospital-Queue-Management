/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hospital.queue.management;

import controller.DashboardController;
import database.Db;
import database.MySqlConnection;
import javax.swing.UIManager;

public class HospitalQueueManagement {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Initialize database if it doesn't exist
        // database.DatabaseSetup.initialize(); // Removed as user already has DB

        // Open database connection
        Db database = new MySqlConnection();
        database.openConnection();

        // Launch the Login screen as the entry point
        java.awt.EventQueue.invokeLater(() -> {
            view.UserLogin loginFrame = new view.UserLogin();
            // UserLogin internally creates its own controller in its constructor.
            loginFrame.setVisible(true);
        });
    }
}