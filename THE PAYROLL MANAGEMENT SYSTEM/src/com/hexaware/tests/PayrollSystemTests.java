package com.hexaware.tests;

import com.hexaware.dao.EmployeeServiceImpl;
import com.hexaware.dao.PayrollServiceImpl;
import com.hexaware.dao.TaxServiceImpl;
import com.hexaware.model.Employee;
import com.hexaware.model.Payroll;
import com.hexaware.model.Tax;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.time.LocalDate;

public class PayrollSystemTests {

    private PayrollServiceImpl payrollService;   // Service for handling payroll operations
    private EmployeeServiceImpl employeeService; // Service for handling employee operations
    private TaxServiceImpl taxService;           // Service for handling tax operations

    // Setup method to initialize services before each test
    @Before
    public void setUp() {
        payrollService  = new PayrollServiceImpl();
        employeeService = new EmployeeServiceImpl();
        taxService      = new TaxServiceImpl();
    }

    // Test case to verify gross salary calculation for an employee
    @Test
    public void testCalculateGrossSalaryForEmployee() {
        // Given
        double basicSalary         = 6000.00;
        double overtimePay         = 1200.00;
        double expectedGrossSalary = basicSalary + overtimePay;

        // When
        Payroll payroll    = new Payroll(0, 1, null, null, basicSalary, overtimePay, 0, 0);
        double grossSalary = payroll.getBasicSalary() + payroll.getOvertimePay();

        // Then
        assertEquals(expectedGrossSalary, grossSalary, 0.01);
    }

    // Test case to verify net salary calculation after deductions
    @Test
    public void testCalculateNetSalaryAfterDeductions() {
        // Given
        double grossSalary       = 7500.00;
        double deductions        = 2000.00;
        double expectedNetSalary = grossSalary - deductions;

        // When
        Payroll payroll = new Payroll(0, 1, null, null, grossSalary, 0, deductions, grossSalary - deductions);
        double netSalary = payroll.getNetSalary();

        // Then
        assertEquals(expectedNetSalary, netSalary, 0.01);
    }

    // Test case to verify tax calculation for a high-income employee
    @Test
    public void testVerifyTaxCalculationForHighIncomeEmployee() {
        // Given
        int employeeId           = 2;
        double taxableIncome     = 150000.00;
        double expectedTaxAmount = taxableIncome * 0.30;

        // When
        Tax tax                = new Tax(0, employeeId, 2023, taxableIncome, expectedTaxAmount);
        boolean isTaxCalculated = taxService.calculateTax(tax);

        // Then
        assertTrue(isTaxCalculated);
        assertEquals(expectedTaxAmount, tax.getTaxAmount(), 0.01);
    }

    // Test case to process payroll for multiple employees
    @Test
    public void testProcessPayrollForMultipleEmployees() {
        // Given
        Employee employee1 = new Employee(
            1, "Aarav", "Sharma", LocalDate.of(1985, 5, 15), "M",
            "aarav.sharma@example.com", "9876543210", "123 Main St, Delhi",
            "Manager", LocalDate.now(), null
        );

        Employee employee2 = new Employee(
            2, "Diya", "Patel", LocalDate.of(1990, 8, 20), "F",
            "diya.patel@example.com", "1234567890", "456 Elm St, Mumbai",
            "Developer", LocalDate.now(), null
        );

        employeeService.addEmployee(employee1);
        employeeService.addEmployee(employee2);

        // When
        Payroll payroll1 = new Payroll(
            0, employee1.getEmployeeID(), LocalDate.now(), LocalDate.now(),
            6000, 1500, 600, 6900
        );

        Payroll payroll2 = new Payroll(
            0, employee2.getEmployeeID(), LocalDate.now(), LocalDate.now(),
            7000, 2000, 800, 8200
        );

        boolean isPayrollProcessed1 = payrollService.generatePayroll(payroll1);
        boolean isPayrollProcessed2 = payrollService.generatePayroll(payroll2);

        // Then
        assertTrue(isPayrollProcessed1);
        assertTrue(isPayrollProcessed2);
    }

    // Test case to verify error handling for invalid employee data
    @Test
    public void testVerifyErrorHandlingForInvalidEmployeeData() {
        // Given
        Employee invalidEmployee = new Employee(
            0, "", "", null, "", "", "", "", "", null, null
        );

        // When
        boolean isAdded = employeeService.addEmployee(invalidEmployee);

        // Then
        assertFalse(isAdded);
    }
}
