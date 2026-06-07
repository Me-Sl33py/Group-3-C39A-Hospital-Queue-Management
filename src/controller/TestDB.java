package controller;

import java.sql.Connection;

public class TestDB {

    public static void main(String[] args) {

        try (Connection conn = dao.DatabaseConnection.getConnection()) {
            if (conn != null) {
                System.out.println("Database Connected!");
            } else {
                System.out.println("Connection Failed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}