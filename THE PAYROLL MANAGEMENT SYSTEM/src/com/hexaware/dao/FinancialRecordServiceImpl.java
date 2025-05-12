package com.hexaware.dao;

import com.hexaware.model.FinancialRecord;
import com.hexaware.util.DBConnUtil;
import com.hexaware.util.DBPropertyUtil;
import com.hexaware.exception.FinancialRecordException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FinancialRecordServiceImpl implements IFinancialRecordService {
    private Connection conn;

    // Constructor to initialize the database connection
    public FinancialRecordServiceImpl() {
        try {
            Properties props = DBPropertyUtil.loadProperties("db.properties");
            conn = DBConnUtil.getConnection(props);
        } catch (Exception e) {
            System.out.println("Error initializing connection: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Add a new financial record
    @Override
    public boolean addFinancialRecord(FinancialRecord record) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(
                "INSERT INTO FinancialRecord (EmployeeID, RecordDate, Description, Amount, RecordType) " +
                "VALUES (?, ?, ?, ?, ?)"
            );
            stmt.setInt(1, record.getEmployeeID());
            stmt.setDate(2, Date.valueOf(record.getRecordDate()));
            stmt.setString(3, record.getDescription());
            stmt.setDouble(4, record.getAmount());
            stmt.setString(5, record.getRecordType());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error adding financial record: " + e.getMessage());
            throw new FinancialRecordException("Error adding financial record: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Retrieve a financial record by its ID
    @Override
    public FinancialRecord getFinancialRecordById(int recordId) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(
                "SELECT * FROM FinancialRecord WHERE RecordID = ?"
            );
            stmt.setInt(1, recordId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return new FinancialRecord(
                    rs.getInt("RecordID"),
                    rs.getInt("EmployeeID"),
                    rs.getDate("RecordDate").toLocalDate(),
                    rs.getString("Description"),
                    rs.getDouble("Amount"),
                    rs.getString("RecordType")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving financial record: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    // Retrieve all financial records for a specific employee
    @Override
    public List<FinancialRecord> getFinancialRecordsForEmployee(int employeeId) {
        List<FinancialRecord> list = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(
                "SELECT * FROM FinancialRecord WHERE EmployeeID = ?"
            );
            stmt.setInt(1, employeeId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new FinancialRecord(
                    rs.getInt("RecordID"),
                    rs.getInt("EmployeeID"),
                    rs.getDate("RecordDate").toLocalDate(),
                    rs.getString("Description"),
                    rs.getDouble("Amount"),
                    rs.getString("RecordType")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving financial records for employee: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    // Retrieve financial records for a specific date
    @Override
    public List<FinancialRecord> getFinancialRecordsForDate(LocalDate recordDate) {
        List<FinancialRecord> list = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(
                "SELECT * FROM FinancialRecord WHERE RecordDate = ?"
            );
            stmt.setDate(1, Date.valueOf(recordDate));
            rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new FinancialRecord(
                    rs.getInt("RecordID"),
                    rs.getInt("EmployeeID"),
                    rs.getDate("RecordDate").toLocalDate(),
                    rs.getString("Description"),
                    rs.getDouble("Amount"),
                    rs.getString("RecordType")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving financial records for date: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    // Generate an income statement for a specific employee and date range
    @Override
    public List<FinancialRecord> generateIncomeStatement(int employeeId, LocalDate startDate, LocalDate endDate) {
        List<FinancialRecord> incomeStatement = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(
                "SELECT * FROM FinancialRecord WHERE EmployeeID = ? AND RecordDate BETWEEN ? AND ?"
            );
            stmt.setInt(1, employeeId);
            stmt.setDate(2, Date.valueOf(startDate));
            stmt.setDate(3, Date.valueOf(endDate));
            rs = stmt.executeQuery();

            while (rs.next()) {
                incomeStatement.add(new FinancialRecord(
                    rs.getInt("RecordID"),
                    rs.getInt("EmployeeID"),
                    rs.getDate("RecordDate").toLocalDate(),
                    rs.getString("Description"),
                    rs.getDouble("Amount"),
                    rs.getString("RecordType")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error generating income statement: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return incomeStatement;
    }
}