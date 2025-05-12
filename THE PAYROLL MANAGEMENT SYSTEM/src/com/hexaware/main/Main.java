package com.hexaware.main;


import com.hexaware.dao.IEmployeeService;
import com.hexaware.dao.IPayrollService;
import com.hexaware.dao.ITaxService;
import com.hexaware.dao.IFinancialRecordService;
import com.hexaware.dao.EmployeeServiceImpl;
import com.hexaware.dao.PayrollServiceImpl;
import com.hexaware.dao.TaxServiceImpl;
import com.hexaware.dao.FinancialRecordServiceImpl;
import com.hexaware.model.Employee;
import com.hexaware.model.Payroll;
import com.hexaware.model.Tax;
import com.hexaware.model.FinancialRecord;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create a scanner object for user input
        Scanner scanner = new Scanner(System.in);
        
        // Initialize service implementations for handling employee, payroll, tax, and financial records
        IEmployeeService employeeService = new EmployeeServiceImpl();
        IPayrollService payrollService = new PayrollServiceImpl();
        ITaxService taxService = new TaxServiceImpl();
        IFinancialRecordService financialRecordService = new FinancialRecordServiceImpl();

        // Main loop for the application
        while (true) {
            // Display the main menu options to the user
            System.out.println("\n==== PayXpert Payroll Management ====");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Remove Employee");
            System.out.println("5. Generate Payroll");
            System.out.println("6. Calculate Tax");
            System.out.println("7. Add Financial Record");
            System.out.println("8. View Financial Records for Employee");
            System.out.println("9. Generate Income Statement");
            System.out.println("10. Generate Tax Summary");
            System.out.println("11. Exit");
            System.out.print("Choose an option (1-11): ");
            
            // Read the user's choice
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline character

            // Handle the user's choice using a switch statement
            switch (choice) {
                case 1: // Add Employee
                    System.out.println("=== Add New Employee ===");
                    System.out.print("Enter First Name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Enter Last Name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Enter Date of Birth (yyyy-mm-dd): ");
                    LocalDate dob = LocalDate.parse(scanner.nextLine());
                    System.out.print("Enter Gender (M/F): ");
                    String gender = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Phone Number: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter Address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter Position: ");
                    String position = scanner.nextLine();
                    System.out.print("Enter Joining Date (yyyy-mm-dd): ");
                    LocalDate joiningDate = LocalDate.parse(scanner.nextLine());
                    
                    // Create a new Employee object with the provided details
                    Employee employee = new Employee(0, firstName, lastName, dob, gender, email, phone, address, position, joiningDate, null);
                    
                    // Attempt to add the employee and display the result
                    boolean isAdded = employeeService.addEmployee(employee);
                    System.out.println(isAdded ? "Employee added successfully." : "Failed to add employee.");
                    break;

                case 2: // View All Employees
                    System.out.println("=== List of All Employees ===");
                    List<Employee> employees = employeeService.getAllEmployees();
                    if (employees.isEmpty()) {
                        System.out.println("No employees found.");
                    } else {
                        // Print each employee's details
                        employees.forEach(System.out::println);
                    }
                    break;

                case 3: // Update Employee
                    System.out.println("=== Update Employee Information ===");
                    System.out.print("Enter Employee ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    Employee existingEmployee = employeeService.getEmployeeById(updateId);

                    if (existingEmployee != null) {
                        // Prompt for new employee details
                        System.out.print("Enter New First Name (current: " + existingEmployee.getFirstName() + "): ");
                        String newFirstName = scanner.nextLine();
                        System.out.print("Enter New Last Name (current: " + existingEmployee.getLastName() + "): ");
                        String newLastName = scanner.nextLine();
                        existingEmployee.setFirstName(newFirstName);
                        existingEmployee.setLastName(newLastName);
                        // Additional fields can be updated here...

                        // Attempt to update the employee and display the result
                        boolean isUpdated = employeeService.updateEmployee(existingEmployee);
                        System.out.println(isUpdated ? "Employee updated successfully." : "Failed to update employee.");
                    } else {
                        System.out.println("Employee not found.");
                    }
                    break;

                case 4: // Remove Employee
                    System.out.println("=== Remove Employee ===");
                    System.out.print("Enter Employee ID to remove: ");
                    int removeId = scanner.nextInt();
                    // Attempt to remove the employee and display the result
                    boolean isRemoved = employeeService.removeEmployee(removeId);
                    System.out.println(isRemoved ? "Employee removed successfully." : "Failed to remove employee.");
                    break;

                case 5: // Generate Payroll
                    System.out.println("=== Generate Payroll ===");
                    System.out.print("Enter Employee ID: ");
                    int empId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Pay Period Start Date (yyyy-mm-dd): ");
                    LocalDate startDate = LocalDate.parse(scanner.nextLine());
                    System.out.print("Enter Pay Period End Date (yyyy-mm-dd): ");
                    LocalDate endDate = LocalDate.parse(scanner.nextLine());
                    System.out.print("Enter Basic Salary: ");
                    double basicSalary = scanner.nextDouble();
                    System.out.print("Enter Overtime Pay: ");
                    double overtimePay = scanner.nextDouble();
                    System.out.print("Enter Deductions: ");
                    double deductions = scanner.nextDouble();

                    // Create a new Payroll object with the provided details
                    Payroll payroll = new Payroll(0, empId, startDate, endDate, basicSalary, overtimePay, deductions,
                            basicSalary + overtimePay - deductions);
                    
                    // Attempt to generate payroll and display the result
                    boolean isPayrollGenerated = payrollService.generatePayroll(payroll);

                    if (isPayrollGenerated) {
                        Payroll generatedPayroll = payrollService.getPayrollById(payroll.getPayrollID());
                        System.out.println("Payroll generated successfully: " + generatedPayroll);
                    } else {
                        System.out.println("Failed to generate payroll.");
                    }
                    break;

                case 6: // Calculate Tax
                    System.out.println("=== Calculate Tax ===");
                    System.out.print("Enter Employee ID: ");
                    int taxEmpId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Tax Year: ");
                    int taxYear = scanner.nextInt();
                    System.out.print("Enter Taxable Income: ");
                    double taxableIncome = scanner.nextDouble();
                    System.out.print("Enter Tax Percentage: ");
                    double taxPercentage = scanner.nextDouble();

                    // Calculate the tax amount based on the taxable income and tax percentage
                    double taxAmount = taxableIncome * (taxPercentage / 100);
                    Tax tax = new Tax(0, taxEmpId, taxYear, taxableIncome, taxAmount);
                    
                    // Attempt to calculate tax and display the result
                    boolean isTaxCalculated = taxService.calculateTax(tax);

                    if (isTaxCalculated) {
                        Tax generatedTax = taxService.getTaxById(tax.getTaxID());
                        System.out.println(generatedTax != null
                                ? "Tax calculated successfully: " + generatedTax
                                : "Tax calculated but could not retrieve the details.");
                    } else {
                        System.out.println("Failed to calculate tax.");
                    }
                    break;

                case 7: // Add Financial Record
                    System.out.println("=== Add Financial Record ===");
                    System.out.print("Enter Employee ID: ");
                    int financialEmpId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Record Date (yyyy-mm-dd): ");
                    LocalDate recordDate = LocalDate.parse(scanner.nextLine());
                    System.out.print("Enter Description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter Amount: ");
                    double amount = scanner.nextDouble();
                    System.out.print("Enter Record Type: ");
                    String recordType = scanner.next();

                    // Create a new FinancialRecord object with the provided details
                    FinancialRecord financialRecord = new FinancialRecord(0, financialEmpId, recordDate, description, amount, recordType);
                    
                    // Attempt to add the financial record and display the result
                    boolean isFinancialRecordAdded = financialRecordService.addFinancialRecord(financialRecord);

                    if (isFinancialRecordAdded) {
                        FinancialRecord generatedRecord = financialRecordService.getFinancialRecordById(financialRecord.getRecordID());
                        System.out.println("Financial record added successfully: " + generatedRecord);
                    } else {
                        System.out.println("Failed to add financial record.");
                    }
                    break;

                case 8: // View Financial Records for Employee
                    System.out.println("=== View Financial Records for Employee ===");
                    System.out.print("Enter Employee ID: ");
                    int employeeId = scanner.nextInt();
                    List<FinancialRecord> records = financialRecordService.getFinancialRecordsForEmployee(employeeId);
                    if (records.isEmpty()) {
                        System.out.println("No financial records found for this employee.");
                    } else {
                        // Print each financial record's details
                        records.forEach(System.out::println);
                    }
                    break;

                case 9: // Generate Income Statement
                    System.out.println("=== Generate Income Statement ===");
                    System.out.print("Enter Employee ID: ");
                    int empIdForStatement = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Start Date (yyyy-mm-dd): ");
                    LocalDate startDateForStatement = LocalDate.parse(scanner.nextLine());
                    System.out.print("Enter End Date (yyyy-mm-dd): ");
                    LocalDate endDateForStatement = LocalDate.parse(scanner.nextLine());

                    // Generate the income statement for the specified employee and date range
                    List<FinancialRecord> incomeStatement = financialRecordService.generateIncomeStatement(
                            empIdForStatement, startDateForStatement, endDateForStatement);

                    if (incomeStatement.isEmpty()) {
                        System.out.println("No financial records found for the specified period.");
                    } else {
                        System.out.println("Income Statement:");
                        incomeStatement.forEach(System.out::println);
                    }
                    break;

                case 10: // Generate Tax Summary
                    System.out.println("=== Generate Tax Summary ===");
                    System.out.print("Enter Employee ID: ");
                    int empIdForTaxSummary = scanner.nextInt();
                    System.out.print("Enter Tax Year: ");
                    int taxYearForSummary = scanner.nextInt();

                    // Generate the tax summary for the specified employee and year
                    List<Tax> taxSummary = taxService.generateTaxSummary(empIdForTaxSummary, taxYearForSummary);
                    if (taxSummary.isEmpty()) {
                        System.out.println("No tax records found for the specified year.");
                    } else {
                        System.out.println("Tax Summary:");
                        taxSummary.forEach(System.out::println);
                    }
                    break;

                case 11: // Exit the application
                    System.out.println("Exiting... Goodbye!");
                    scanner.close(); // Close the scanner to free resources
                    return; // Exit the main loop and terminate the program

                default: // Handle invalid choices
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
