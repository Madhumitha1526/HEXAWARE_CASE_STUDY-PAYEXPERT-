package com.hexaware.dao;

import com.hexaware.model.Employee;
import com.hexaware.util.DBConnUtil;
import com.hexaware.util.DBPropertyUtil;
import com.hexaware.exception.EmployeeNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EmployeeServiceImpl implements IEmployeeService {
    private Connection conn;

    // Constructor to initialize the database connection
    public EmployeeServiceImpl() {
        try {
            Properties props = DBPropertyUtil.loadProperties("db.properties");
            conn = DBConnUtil.getConnection(props);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Retrieve an employee by their ID
    @Override
    public Employee getEmployeeById(int id) {
        String query = "SELECT * FROM Employee WHERE EmployeeID=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapEmployee(rs);
            } else {
                throw new EmployeeNotFoundException("Employee not found with ID: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve all employees from the database
    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM Employee";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                employees.add(mapEmployee(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    // Add a new employee to the database
    @Override
    public boolean addEmployee(Employee employee) {
        String query = "INSERT INTO Employee (FirstName, LastName, DateOfBirth, Gender, Email, PhoneNumber, Address, Position, JoiningDate, TerminationDate) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            setEmployeeFields(stmt, employee);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Update an existing employee's information
    @Override
    public boolean updateEmployee(Employee employee) {
        String query = "UPDATE Employee SET FirstName=?, LastName=?, DateOfBirth=?, Gender=?, Email=?, PhoneNumber=?, Address=?, Position=?, JoiningDate=?, TerminationDate=? " +
                       "WHERE EmployeeID=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            setEmployeeFields(stmt, employee);
            stmt.setInt(11, employee.getEmployeeID());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Remove an employee from the database
    @Override
    public boolean removeEmployee(int id) {
        String query = "DELETE FROM Employee WHERE EmployeeID=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Map a ResultSet to an Employee object
    private Employee mapEmployee(ResultSet rs) throws SQLException {
        return new Employee(
            rs.getInt("EmployeeID"),
            rs.getString("FirstName"),
            rs.getString("LastName"),
            rs.getDate("DateOfBirth")     != null ? rs.getDate("DateOfBirth").toLocalDate()     : null,
            rs.getString("Gender"),
            rs.getString("Email"),
            rs.getString("PhoneNumber"),
            rs.getString("Address"),
            rs.getString("Position"),
            rs.getDate("JoiningDate")     != null ? rs.getDate("JoiningDate").toLocalDate()     : null,
            rs.getDate("TerminationDate") != null ? rs.getDate("TerminationDate").toLocalDate() : null
        );
    }

    // Set the fields of a PreparedStatement for an Employee object
    private void setEmployeeFields(PreparedStatement stmt, Employee employee) throws SQLException {
        stmt.setString(1, employee.getFirstName());
        stmt.setString(2, employee.getLastName());

        // Use ternary + check to avoid NullPointerException for date conversion
        if (employee.getDateOfBirth() != null) {
            stmt.setDate(3, Date.valueOf(employee.getDateOfBirth()));
        } else {
            stmt.setNull(3, Types.DATE);
        }

        stmt.setString(4, employee.getGender());
        stmt.setString(5, employee.getEmail());
        stmt.setString(6, employee.getPhoneNumber());
        stmt.setString(7, employee.getAddress());
        stmt.setString(8, employee.getPosition());

        if (employee.getJoiningDate() != null) {
            stmt.setDate(9, Date.valueOf(employee.getJoiningDate()));
        } else {
            stmt.setNull(9, Types.DATE);
        }

        if (employee.getTerminationDate() != null) {
            stmt.setDate(10, Date.valueOf(employee.getTerminationDate()));
        } else {
            stmt.setNull(10, Types.DATE);
        }
    }
}