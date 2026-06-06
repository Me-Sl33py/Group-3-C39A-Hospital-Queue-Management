package controller;

import view.DashboardView;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.List;

public class DashboardController {
    private final DashboardView view;
    private final view.WithTabbedPane mainFrame;

    public DashboardController(DashboardView view, view.WithTabbedPane mainFrame) {
        this.view = view;
        this.mainFrame = mainFrame;
        initEventHandlers();
        refreshData();
    }

    private void initEventHandlers() {
        view.getBtnNewPatientReg().addActionListener(e -> {
            mainFrame.switchToTab(1); // Switch to Register Walk-in tab
            mainFrame.getBtnRegisterWalkin().doClick(); // Select sidebar button
        });
        view.getBtnGenEmergency().addActionListener(e -> {
            mainFrame.switchToTab(2); // Switch to Generate Token tab
            mainFrame.getBtnGenerateToken().doClick(); // Select sidebar button
        });
    }

    public void refreshData() {
        dao.PatientDAO patientDAO = new dao.PatientDAO();
        dao.TokenDAO tokenDAO = new dao.TokenDAO();
        dao.DoctorDAO doctorDAO = new dao.DoctorDAO();
        
        int totalPatients = patientDAO.getTotalPatientsCount();
        int waiting = tokenDAO.countTotalWaiting();
        int availableDoctors = 0;
        
        List<model.Doctor> doctors = doctorDAO.getAllDoctors();
        if (doctors != null) {
            for (model.Doctor d : doctors) {
                if ("Available".equalsIgnoreCase(d.getAvailability())) {
                    availableDoctors++;
                }
            }
        }
        view.getLblTotalVal().setText(String.valueOf(totalPatients));
        view.getLblWaitingVal().setText(String.valueOf(waiting));
        view.getLblDoctorsVal().setText(String.valueOf(availableDoctors));

        List<model.Token> liveTokens = tokenDAO.getAllWaitingTokens();
        DefaultTableModel model = (DefaultTableModel) view.getTblWaitlist().getModel();
        model.setRowCount(0); 

        if (liveTokens != null) {
            for (model.Token t : liveTokens) {
                String timeStr = "";
                if (t.getCreatedAt() != null) {
                    timeStr = new SimpleDateFormat("hh:mm a").format(t.getCreatedAt());
                }
                model.addRow(new Object[]{t.getTokenNumber(), t.getPatientName(), t.getDoctorName() != null ? t.getDoctorName() : "Unassigned", t.getStatus(), timeStr});
            }
        }
    }
}