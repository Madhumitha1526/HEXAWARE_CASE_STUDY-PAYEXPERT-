package com.hexaware.model;

// Class representing a Tax record
public class Tax {

    private int taxID;             // Unique identifier for the tax record
    private int employeeID;        // ID of the employee associated with the tax
    private int taxYear;           // The year for which the tax is calculated
    private double taxableIncome;  // The income on which tax is calculated
    private double taxAmount;      // The amount of tax calculated

    // Constructor to initialize a Tax object
    public Tax(int taxID, int employeeID, int taxYear, double taxableIncome, double taxAmount) {
        this.taxID = taxID;
        this.employeeID = employeeID;
        this.taxYear = taxYear;
        this.taxableIncome = taxableIncome;
        this.taxAmount = taxAmount;
    }

    // Getters and Setters
    public int getTaxID() {
        return taxID;
    }

    public void setTaxID(int taxID) {
        this.taxID = taxID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getTaxYear() {
        return taxYear;
    }

    public void setTaxYear(int taxYear) {
        this.taxYear = taxYear;
    }

    public double getTaxableIncome() {
        return taxableIncome;
    }

    public void setTaxableIncome(double taxableIncome) {
        this.taxableIncome = taxableIncome;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    @Override
    public String toString() {
        return "Tax [taxID=" + taxID +
               ", employeeID=" + employeeID +
               ", taxYear=" + taxYear +
               ", taxableIncome=" + taxableIncome +
               ", taxAmount=" + taxAmount + "]";
    }
}
