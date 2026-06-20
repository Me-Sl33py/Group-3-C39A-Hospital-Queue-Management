package hospital.queue.management;

import database.DB;
import database.MySqlConnection;
import view.UserLogin;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Main Entry Point for the Hospital Queue Management System.
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
        
        // Make scrollbars paper thin globally
        javax.swing.UIManager.put("ScrollBar.width", 5);

        // 2. Optional: Test the database connection briefly at startup
        DB database = new MySqlConnection();
        java.sql.Connection conn = database.openConnection();
        if (conn != null) {
            System.out.println("Startup Check: Database connection successful.");
            database.closeConnection(conn); // Close it since DAOs will manage their own connections
        } else {
            System.err.println("Startup Check Warning: Could not connect to the database.");
        }

        // 3. Launch the UserLogin screen as the starting point of the app
        java.awt.EventQueue.invokeLater(() -> {
            new view.UserLogin().setVisible(true);
        });
    }
}
