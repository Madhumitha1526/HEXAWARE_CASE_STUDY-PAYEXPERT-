package com.hexaware.dao;

import com.hexaware.model.Payroll;
import com.hexaware.util.DBConnUtil;
import com.hexaware.util.DBPropertyUtil;
import com.hexaware.exception.PayrollGenerationException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PayrollServiceImpl implements IPayrollService {
    private Connection conn;

    // Constructor to initialize the database connection
    public PayrollServiceImpl() {
        try {
            Properties props = DBPropertyUtil.loadProperties("db.properties");
            conn = DBConnUtil.getConnection(props);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Retrieve payroll by its ID
    @Override
    public Payroll getPayrollById(int payrollId) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Payroll WHERE PayrollID=?");
            stmt.setInt(1, payrollId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Payroll(
                    rs.getInt("PayrollID"),
                    rs.getInt("EmployeeID"),
                    rs.getDate("PayPeriodStartDate") != null ? rs.getDate("PayPeriodStartDate").toLocalDate() : null,
                    rs.getDate("PayPeriodEndDate")   != null ? rs.getDate("PayPeriodEndDate").toLocalDate()   : null,
                    rs.getDouble("BasicSalary"),
                    rs.getDouble("OvertimePay"),
                    rs.getDouble("Deductions"),
                    rs.getDouble("NetSalary")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve all payrolls for a specific employee
    @Override
    public List<Payroll> getPayrollsForEmployee(int employeeId) {
        List<Payroll> list = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Payroll WHERE EmployeeID=?");
            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Payroll(
                    rs.getInt("PayrollID"),
                    rs.getInt("EmployeeID"),
                    rs.getDate("PayPeriodStartDate") != null ? rs.getDate("PayPeriodStartDate").toLocalDate() : null,
                    rs.getDate("PayPeriodEndDate")   != null ? rs.getDate("PayPeriodEndDate").toLocalDate()   : null,
                    rs.getDouble("BasicSalary"),
                    rs.getDouble("OvertimePay"),
                    rs.getDouble("Deductions"),
                    rs.getDouble("NetSalary")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Retrieve payrolls for a specific period
    @Override
    public List<Payroll> getPayrollsForPeriod(LocalDate start, LocalDate end) {
        List<Payroll> list = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM Payroll WHERE PayPeriodStartDate >= ? AND PayPeriodEndDate <= ?"
            );
            stmt.setDate(1, start != null ? Date.valueOf(start) : null);
            stmt.setDate(2, end   != null ? Date.valueOf(end)   : null);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Payroll(
                    rs.getInt("PayrollID"),
                    rs.getInt("EmployeeID"),
                    rs.getDate("PayPeriodStartDate") != null ? rs.getDate("PayPeriodStartDate").toLocalDate() : null,
                    rs.getDate("PayPeriodEndDate")   != null ? rs.getDate("PayPeriodEndDate").toLocalDate()   : null,
                    rs.getDouble("BasicSalary"),
                    rs.getDouble("OvertimePay"),
                    rs.getDouble("Deductions"),
                    rs.getDouble("NetSalary")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Generate payroll for an employee
    @Override
    public boolean generatePayroll(Payroll payroll) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO Payroll (EmployeeID, PayPeriodStartDate, PayPeriodEndDate, BasicSalary, OvertimePay, Deductions) " +
                "VALUES (?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );

            stmt.setInt(1, payroll.getEmployeeID());
            stmt.setDate(2, payroll.getPayPeriodStartDate() != null ? Date.valueOf(payroll.getPayPeriodStartDate()) : null);
            stmt.setDate(3, payroll.getPayPeriodEndDate()   != null ? Date.valueOf(payroll.getPayPeriodEndDate())   : null);
            stmt.setDouble(4, payroll.getBasicSalary());
            stmt.setDouble(5, payroll.getOvertimePay());
            stmt.setDouble(6, payroll.getDeductions());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    payroll.setPayrollID(rs.getInt(1)); // Set the generated payroll ID
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new PayrollGenerationException("Error generating payroll: " + e.getMessage());
        }
        return false;
    }
}