package controller;

import java.sql.Connection;
import db.DBConnection;

public class TestDB {

    public static void main(String[] args) {

        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            System.out.println("Database Connected!");
        } else {
            System.out.println("Connection Failed!");
        }
    }
}