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

        // Open WithTabbedPane and hook it up to MainController
        java.awt.EventQueue.invokeLater(() -> {
            view.WithTabbedPane mainFrame = new view.WithTabbedPane();
            controller.MainController mainController = new controller.MainController(mainFrame);
            mainFrame.setVisible(true);
        });
    }
}