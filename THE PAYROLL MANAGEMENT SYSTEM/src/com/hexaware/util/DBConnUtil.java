package com.hexaware.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// Utility class for managing database connections
public class DBConnUtil {
    // Method to establish a connection to the database using the provided properties
    public static Connection getConnection(Properties props) throws SQLException {
        // Retrieve database connection details from properties
        String url = props.getProperty("db.url"); // Database URL
        String username = props.getProperty("db.username"); // Database username
        String password = props.getProperty("db.password"); // Database password
        
        // Establish and return a connection to the database
        return DriverManager.getConnection(url, username, password);
    }
}
