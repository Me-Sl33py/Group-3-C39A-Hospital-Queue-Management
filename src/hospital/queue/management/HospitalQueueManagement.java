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

        // Open database connection
        Db database = new MySqlConnection();
        database.openConnection();

        // Open WithTabbedPane instead of the old separate frames
        java.awt.EventQueue.invokeLater(() -> {
            new view.WithTabbedPane().setVisible(true);
        });
    }
}