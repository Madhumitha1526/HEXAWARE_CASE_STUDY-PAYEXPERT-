package com.hexaware.dao;

import java.util.List;
import com.hexaware.model.Tax;

// Interface for Tax Service
public interface ITaxService {

    // Retrieve tax information by tax ID
    Tax getTaxById(int taxId);

    // Retrieve all taxes for a specific employee
    List<Tax> getTaxesForEmployee(int employeeId);

    // Retrieve all taxes for a specific year
    List<Tax> getTaxesForYear(int taxYear);

    // Calculate and add tax for an employee
    boolean calculateTax(Tax tax);

    // Generate a tax summary for an employee for a specific year
    List<Tax> generateTaxSummary(int employeeId, int taxYear);
}