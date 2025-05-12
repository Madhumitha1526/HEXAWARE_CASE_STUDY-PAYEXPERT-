package com.hexaware.exception;

// Custom exception class for handling payroll generation errors
@SuppressWarnings("serial")
public class PayrollGenerationException extends RuntimeException {

    // Constructor for the exception with a message
    public PayrollGenerationException(String message) {
        super(message); // Call the superclass constructor with the message
    }

    // Constructor for the exception with a message and cause
    public PayrollGenerationException(String message, Throwable cause) {
        super(message, cause); // Call the superclass constructor with the message and cause
    }
}