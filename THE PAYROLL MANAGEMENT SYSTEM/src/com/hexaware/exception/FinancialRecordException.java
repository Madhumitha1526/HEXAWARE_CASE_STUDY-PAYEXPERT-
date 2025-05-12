package com.hexaware.exception;

// Custom exception class for handling financial record errors
@SuppressWarnings("serial")
public class FinancialRecordException extends RuntimeException {

    // Constructor for the exception with a message
    public FinancialRecordException(String message) {
        super(message); // Call the superclass constructor with the message
    }

    // Constructor for the exception with a message and cause
    public FinancialRecordException(String message, Throwable cause) {
        super(message, cause); // Call the superclass constructor with the message and cause
    }
}