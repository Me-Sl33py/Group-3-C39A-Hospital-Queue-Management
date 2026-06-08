package controller;

import dao.HomeDao;
import view.HomePanel;

public class HomeController {

    private HomePanel panel;
    private HomeDao dao;

    public HomeController(HomePanel panel) {
        this.panel = panel;
        this.dao = new HomeDao();
    }

    public void loadAll() {

        panel.getPatientLabel()
                .setText(String.valueOf(dao.getTotalPatients()));

        panel.getDoctorLabel()
                .setText(String.valueOf(dao.getTotalDoctors()));

        panel.getDepartmentLabel()
                .setText(String.valueOf(dao.getTotalDepartments()));

        panel.getReceptionistLabel()
                .setText(String.valueOf(dao.getTotalReceptionists()));
    }
}