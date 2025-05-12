package com.hexaware.model;

import java.time.LocalDate;

// Class representing a Financial Record
public class FinancialRecord {

    private int recordID;         // Unique identifier for the financial record
    private int employeeID;       // ID of the employee associated with the financial record
    private LocalDate recordDate; // Date of the financial record
    private String description;   // Description of the financial record
    private double amount;        // Amount of the financial record
    private String recordType;    // Type of the financial record (e.g., income, expense)

    // Constructor to initialize a FinancialRecord object
    public FinancialRecord(int recordID, int employeeID, LocalDate recordDate,
                           String description, double amount, String recordType) {
        this.recordID = recordID;
        this.employeeID = employeeID;
        this.recordDate = recordDate;
        this.description = description;
        this.amount = amount;
        this.recordType = recordType;
    }

    // Getters and Setters
    public int getRecordID() {
        return recordID;
    }

    public void setRecordID(int recordID) {
        this.recordID = recordID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    @Override
    public String toString() {
        return "FinancialRecord [recordID=" + recordID +
               ", employeeID=" + employeeID +
               ", recordDate=" + recordDate +
               ", description=" + description +
               ", amount=" + amount +
               ", recordType=" + recordType + "]";
    }
}
