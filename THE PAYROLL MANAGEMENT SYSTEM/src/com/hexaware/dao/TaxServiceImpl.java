package com.hexaware.dao;

import com.hexaware.model.Tax;
import com.hexaware.util.DBConnUtil;
import com.hexaware.util.DBPropertyUtil;
import com.hexaware.exception.TaxCalculationException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TaxServiceImpl implements ITaxService {
    private Connection conn;

    // Constructor to initialize the database connection
    public TaxServiceImpl() {
        try {
            Properties props = DBPropertyUtil.loadProperties("db.properties");
            conn = DBConnUtil.getConnection(props);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Retrieve tax information by tax ID
    @Override
    public Tax getTaxById(int taxId) {
        Tax tax = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM Tax WHERE TaxID=?");
            stmt.setInt(1, taxId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                tax = new Tax(
                    rs.getInt("TaxID"),
                    rs.getInt("EmployeeID"),
                    rs.getInt("TaxYear"),
                    rs.getDouble("TaxableIncome"),
                    rs.getDouble("TaxAmount")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tax;
    }

    // Retrieve all taxes for a specific employee
    @Override
    public List<Tax> getTaxesForEmployee(int employeeId) {
        List<Tax> list = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM Tax WHERE EmployeeID=?");
            stmt.setInt(1, employeeId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Tax(
                    rs.getInt("TaxID"),
                    rs.getInt("EmployeeID"),
                    rs.getInt("TaxYear"),
                    rs.getDouble("TaxableIncome"),
                    rs.getDouble("TaxAmount")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
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

    // Retrieve all taxes for a specific year
    @Override
    public List<Tax> getTaxesForYear(int taxYear) {
        List<Tax> list = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM Tax WHERE TaxYear=?");
            stmt.setInt(1, taxYear);
            rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Tax(
                    rs.getInt("TaxID"),
                    rs.getInt("EmployeeID"),
                    rs.getInt("TaxYear"),
                    rs.getDouble("TaxableIncome"),
                    rs.getDouble("TaxAmount")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
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

    // Calculate and add tax for an employee
    @Override
    public boolean calculateTax(Tax tax) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(
                "INSERT INTO Tax (EmployeeID, TaxYear, TaxableIncome, TaxAmount) VALUES (?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );
            stmt.setInt(1, tax.getEmployeeID());
            stmt.setInt(2, tax.getTaxYear());
            stmt.setDouble(3, tax.getTaxableIncome());
            stmt.setDouble(4, tax.getTaxAmount());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    tax.setTaxID(rs.getInt(1));
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new TaxCalculationException("Error calculating tax: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // Generate a tax summary for an employee for a specific year
    @Override
    public List<Tax> generateTaxSummary(int employeeId, int taxYear) {
        List<Tax> taxSummary = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(
                "SELECT * FROM Tax WHERE EmployeeID=? AND TaxYear=?"
            );
            stmt.setInt(1, employeeId);
            stmt.setInt(2, taxYear);
            rs = stmt.executeQuery();
            while (rs.next()) {
                taxSummary.add(new Tax(
                    rs.getInt("TaxID"),
                    rs.getInt("EmployeeID"),
                    rs.getInt("TaxYear"),
                    rs.getDouble("TaxableIncome"),
                    rs.getDouble("TaxAmount")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error generating tax summary: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return taxSummary;
    }
}