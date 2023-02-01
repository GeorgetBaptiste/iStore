package com.istore;

import java.sql.Connection;
import java.sql.DriverManager;

public class  DatabaseConnection {
    private Connection conn ;

    public DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "root");
            System.out.println("Database connection successful.");
        } catch (Exception e) {
            System.out.println("Error : database connection failed.");
        }
    }

    public Connection getConnection() {
        return conn;
    }
}
