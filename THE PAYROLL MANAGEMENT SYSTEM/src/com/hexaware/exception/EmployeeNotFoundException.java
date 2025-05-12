package com.hexaware.exception;

// Custom exception class for handling cases where an employee is not found
@SuppressWarnings("serial")
public class EmployeeNotFoundException extends RuntimeException {

    // Constructor for the exception with a message
    public EmployeeNotFoundException(String message) {
        super(message); // Call the superclass constructor with the message
    }

    // Constructor for the exception with a message and cause
    public EmployeeNotFoundException(String message, Throwable cause) {
        super(message, cause); // Call the superclass constructor with the message and cause
    }
}