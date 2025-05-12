package com.hexaware.dao;

import java.time.LocalDate;
import java.util.List;
import com.hexaware.model.FinancialRecord;

// Interface for Financial Record Service
public interface IFinancialRecordService {

    // Add a new financial record
    boolean addFinancialRecord(FinancialRecord record);

    // Retrieve a financial record by its ID
    FinancialRecord getFinancialRecordById(int recordId);

    // Retrieve all financial records for a specific employee
    List<FinancialRecord> getFinancialRecordsForEmployee(int employeeId);

    // Retrieve financial records for a specific date
    List<FinancialRecord> getFinancialRecordsForDate(LocalDate recordDate);

    // Generate an income statement for a specific employee and date range
    List<FinancialRecord> generateIncomeStatement(int employeeId, LocalDate startDate, LocalDate endDate);
}