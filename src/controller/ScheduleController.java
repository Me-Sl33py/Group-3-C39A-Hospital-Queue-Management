package controller;

import dao.ScheduleDAO;
import view.SchedulePanel;
import javax.swing.JLabel;
import java.util.List;

public class ScheduleController {

    private SchedulePanel panel;
    private ScheduleDAO dao;

    public ScheduleController(SchedulePanel panel) {
        this.panel = panel;
        this.dao = new ScheduleDAO();
    }

    public void loadAll() {

        JLabel[] timeLabels = {
            panel.getTime1Label(),
            panel.getTime2Label(),
            panel.getTime3Label(),
            panel.getTime4Label(),
            panel.getTime5Label()
        };

        JLabel[] entryLabels = {
            panel.getEntry1Label(),
            panel.getEntry2Label(),
            panel.getEntry3Label(),
            panel.getEntry4Label(),
            panel.getEntry5Label()
        };

        List<String[]> rows = dao.getTodaySchedule();

        System.out.println("Schedule Rows Found: " + rows.size());

        int i = 0;

        for (String[] row : rows) {

            if (i >= 5) {
                break;
            }

            timeLabels[i].setText(row[0]);

            entryLabels[i].setText(
                "Dr. " + row[1] +
                " | Patient: " + row[2] +
                " | Status: " + row[3]
            );

            i++;
        }

        while (i < 5) {
            timeLabels[i].setText("");
            entryLabels[i].setText("");
            i++;
        }
    }
}