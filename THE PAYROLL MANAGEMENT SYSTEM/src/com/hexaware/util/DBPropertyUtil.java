package com.hexaware.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// Utility class for loading database properties from a file
public class DBPropertyUtil {

    // Method to load properties from a specified file
    public static Properties loadProperties(String fileName) throws IOException {
        Properties props = new Properties(); // Create a new Properties object
        
        // Load properties from the specified file
        try (InputStream input = DBPropertyUtil.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new IOException("Unable to find " + fileName); // Throw an exception if the file is not found
            }
            props.load(input); // Load the properties from the input stream
        }
        
        return props; // Return the loaded properties
    }
}
