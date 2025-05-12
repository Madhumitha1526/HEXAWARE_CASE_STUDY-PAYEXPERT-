package com.hexaware.exception;

// Custom exception class for handling tax calculation errors
@SuppressWarnings("serial")
public class TaxCalculationException extends RuntimeException {

    // Constructor for the exception with a message
    public TaxCalculationException(String message) {
        super(message); // Call the superclass constructor with the message
    }

    // Constructor for the exception with a message and cause
    public TaxCalculationException(String message, Throwable cause) {
        super(message, cause); // Call the superclass constructor with the message and cause
    }
}