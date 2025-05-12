package com.hexaware.dao;

import java.time.LocalDate;
import java.util.List;
import com.hexaware.model.Payroll;

// Interface for Payroll Service
public interface IPayrollService {

    // Retrieve payroll by its ID
    Payroll getPayrollById(int payrollId);

    // Retrieve all payrolls for a specific employee
    List<Payroll> getPayrollsForEmployee(int employeeId);

    // Retrieve payrolls for a specific period
    List<Payroll> getPayrollsForPeriod(LocalDate start, LocalDate end);

    // Generate payroll for an employee
    boolean generatePayroll(Payroll payroll);
}