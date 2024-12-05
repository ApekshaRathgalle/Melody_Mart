package com.Login.AdminLogin.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static String jdbcURL = "jdbc:mysql://localhost:3306/users";
    private static String jdbcUsername = "root";
    private static String jdbcPassword = "1234";

    // Method to establish a connection to the database
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        // Load the MySQL JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }
}