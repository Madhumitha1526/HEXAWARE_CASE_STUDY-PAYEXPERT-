package com.hexaware.exception;

// Custom exception class for handling database connection errors
@SuppressWarnings("serial")
public class DatabaseConnectionException extends RuntimeException {

    // Constructor for the exception with a message
    public DatabaseConnectionException(String message) {
        super(message); // Call the superclass constructor with the message
    }

    // Constructor for the exception with a message and cause
    public DatabaseConnectionException(String message, Throwable cause) {
        super(message, cause); // Call the superclass constructor with the message and cause
    }
}
