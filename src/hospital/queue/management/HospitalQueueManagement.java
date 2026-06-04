/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hospital.queue.management;

import controller.DashboardController;
import database.Db;
import database.MySqlConnection;

public class HospitalQueueManagement {

    public static void main(String[] args) {

        // Open database connection
        Db database = new MySqlConnection();
        database.openConnection();

        // Open Dashboard
        DashboardController.startApplication();
    }
}