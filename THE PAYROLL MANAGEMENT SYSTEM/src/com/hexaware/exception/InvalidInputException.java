package com.hexaware.exception;

// Custom exception class for handling invalid input errors
@SuppressWarnings("serial")
public class InvalidInputException extends RuntimeException {

    // Constructor for the exception with a message
    public InvalidInputException(String message) {
        super(message); // Call the superclass constructor with the message
    }

    // Constructor for the exception with a message and cause
    public InvalidInputException(String message, Throwable cause) {
        super(message, cause); // Call the superclass constructor with the message and cause
    }
}