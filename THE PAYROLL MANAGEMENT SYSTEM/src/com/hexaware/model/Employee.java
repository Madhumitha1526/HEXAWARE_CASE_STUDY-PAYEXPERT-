package com.hexaware.model;

import java.time.LocalDate;

// Class representing an Employee
public class Employee {

    private int employeeID;                // Unique identifier for the employee
    private String firstName;              // Employee's first name
    private String lastName;               // Employee's last name
    private LocalDate dateOfBirth;         // Employee's date of birth
    private String gender;                 // Employee's gender
    private String email;                  // Employee's email address
    private String phoneNumber;            // Employee's phone number
    private String address;                // Employee's address
    private String position;               // Employee's job position
    private LocalDate joiningDate;         // Date the employee joined the company
    private LocalDate terminationDate;     // Date the employee left the company (if applicable)

    // Constructor to initialize an Employee object
    public Employee(int employeeID, String firstName, String lastName, LocalDate dateOfBirth, String gender,
                    String email, String phoneNumber, String address, String position,
                    LocalDate joiningDate, LocalDate terminationDate) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.position = position;
        this.joiningDate = joiningDate;
        this.terminationDate = terminationDate;
    }

    // Getters and Setters
    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public LocalDate getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(LocalDate terminationDate) {
        this.terminationDate = terminationDate;
    }

    @Override
    public String toString() {
        return "Employee [employeeID=" + employeeID +
               ", firstName=" + firstName +
               ", lastName=" + lastName +
               ", dateOfBirth=" + dateOfBirth +
               ", gender=" + gender +
               ", email=" + email +
               ", phoneNumber=" + phoneNumber +
               ", address=" + address +
               ", position=" + position +
               ", joiningDate=" + joiningDate +
               ", terminationDate=" + terminationDate + "]";
    }
}
