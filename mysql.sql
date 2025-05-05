-- database creation --
create database PayXpert;
use PayXpert;

-- Employee table--
create table Employee (
    EmployeeID int primary key,
    FirstName varchar(100) not null,
    LastName varchar(100) not null,
    DateOfBirth date not null,
    Gender varchar(10) check (Gender in ('male', 'female', 'other')),
    Email varchar(100) unique not null,
    PhoneNumber varchar(15) unique not null,
    Address varchar(255),
    Position varchar(200) not null,
    JoiningDate date not null,
    TerminationDate date default null
);

-- Payroll table --
create table Payroll (
    PayrollID int primary key,
    EmployeeID int not null,
    PayPeriodStartDate date not null,
    PayPeriodEndDate date not null,
    BasicSalary decimal(10,2) not null check (BasicSalary >= 0),
    OvertimePay decimal(10,2) default 0 check (OvertimePay >= 0),
    Deductions decimal(10,2) default 0 check (Deductions >= 0),
    foreign key (EmployeeID) references Employee(EmployeeID)
);

-- Tax table --
create table Tax (
    TaxID int primary key,
    EmployeeID int not null,
    TaxYear int not null check (TaxYear >= 2000),
    TaxableIncome decimal(10,2) not null check (TaxableIncome >= 0),
    TaxAmount decimal(10,2) not null check (TaxAmount >= 0),
    foreign key (EmployeeID) references Employee(EmployeeID)
);

-- FinancialRecord table --
create table FinancialRecord (
    RecordID int primary key,
    EmployeeID int not null,
    RecordDate date not null,
    Description varchar(255),
    Amount decimal(10,2) not null check (Amount >= 0),
    RecordType varchar(50) not null check (RecordType in ('income', 'expense', 'tax payment')),
    foreign key (EmployeeID) references Employee(EmployeeID)
);

-- Net Salary --
select PayrollID,EmployeeID,BasicSalary,OvertimePay,Deductions,(BasicSalary + OvertimePay - Deductions) as NetSalary
from Payroll;

-- inserting values inside the table --

-- 
insert into Employee (EmployeeID, FirstName, LastName, DateOfBirth, Gender, Email, PhoneNumber, Address, Position, JoiningDate, TerminationDate) values
(1, 'Divya', 'Sundari', '1984-05-25', 'Female', 'divya.sundari@hexaware.com', '9778449467', '92, Sample Street, City 1', 'Manager', '2014-11-30', null),
(2, 'Ravi', 'Sundari', '1984-07-20', 'Other', 'ravi.sundari@payxpert.com', '9075798511', '55, Sample Street, City 8', 'Accountant', '2019-11-02', null),
(3, 'Divya', 'Naidu', '1994-10-14', 'Male', 'divya.naidu@payxpert.com', '9217340413', '7, Sample Street, City 2', 'Accountant', '2010-04-16', null),
(4, 'Nithya', 'Krishnan', '1997-09-24', 'Male', 'nithya.krishnan@hexaware.com', '9607651985', '29, Sample Street, City 1', 'HR Executive', '2020-09-21', null),
(5, 'Priya', 'Reddy', '1986-05-19', 'Female', 'priya.reddy@hexaware.com', '9439009980', '37, Sample Street, City 10', 'Manager', '2014-01-02', null),
(6, 'Sunil', 'Krishnan', '1989-07-27', 'Female', 'sunil.krishnan@hexaware.com', '9862352247', '14, Sample Street, City 9', 'HR Executive', '2018-10-01', null),
(7, 'Ajay', 'Naidu', '1987-07-20', 'Male', 'ajay.naidu@hexaware.com', '9680191042', '85, Sample Street, City 2', 'Manager', '2014-06-28', null),
(8, 'Sneha', 'Ramasamy', '1988-04-04', 'Male', 'sneha.ramasamy@hexaware.com', '9326129338', '76, Sample Street, City 6', 'HR Executive', '2017-01-15', null),
(9, 'Anita', 'Krishnan', '1992-03-19', 'Other', 'anita.krishnan@hexaware.com', '9372432173', '88, Sample Street, City 1', 'Accountant', '2021-06-11', null),
(10, 'Meena', 'Ramasamy', '1986-02-14', 'Other', 'meena.ramasamy@payxpert.com', '9483267582', '10, Sample Street, City 7', 'QA Analyst', '2017-02-14', null),
(11, 'Arun', 'Naidu', '1991-02-24', 'Female', 'arun.naidu@hexaware.com', '9493100405', '48, Sample Street, City 1', 'Software Engineer', '2012-10-28', null),
(12, 'Priya', 'Naidu', '1998-03-30', 'Male', 'priya.naidu@hexaware.com', '9124150067', '5, Sample Street, City 3', 'Software Engineer', '2020-12-08', null),
(13, 'Ajay', 'Reddy', '1985-08-21', 'Other', 'ajay.reddy@hexaware.com', '9511893844', '60, Sample Street, City 6', 'QA Analyst', '2017-06-19', null),
(14, 'Manoj', 'Sundari', '1992-02-15', 'Female', 'manoj.sundari@hexaware.com', '9562113976', '56, Sample Street, City 2', 'QA Analyst', '2014-06-13', null),
(15, 'Preeti', 'Sharma', '1989-03-01', 'Other', 'preeti.sharma@payxpert.com', '9270764051', '4, Sample Street, City 6', 'HR Executive', '2019-12-26', null),
(16, 'Karthik', 'Verma', '1982-06-20', 'Female', 'karthik.verma@hexaware.com', '9030296097', '1, Sample Street, City 7', 'Software Engineer', '2015-09-20', null),
(17, 'Kiran', 'Iyer', '1987-09-23', 'Male', 'kiran.iyer@payxpert.com', '9196247551', '59, Sample Street, City 5', 'Manager', '2013-08-27', null),
(18, 'Lavanya', 'Patel', '1990-04-24', 'Other', 'lavanya.patel@payxpert.com', '9335983540', '96, Sample Street, City 8', 'Software Engineer', '2016-05-25', null),
(19, 'Lakshmi', 'Ramasamy', '1986-03-15', 'Female', 'lakshmi.ramasamy@hexaware.com', '9882629394', '80, Sample Street, City 10', 'Accountant', '2010-01-05', null),
(20, 'Gopal', 'Iyer', '1987-12-30', 'Male', 'gopal.iyer@payxpert.com', '9887101960', '94, Sample Street, City 10', 'Software Engineer', '2011-08-16', null);

insert into Payroll (PayrollID, EmployeeID, PayPeriodStartDate, PayPeriodEndDate, BasicSalary, OvertimePay, Deductions) values
(1, 1, '2025-01-01', '2025-01-15', 50000.00, 1500.00, 2000.00),
(2, 2, '2025-01-01', '2025-01-15', 40000.00, 1200.00, 1500.00),
(3, 3, '2025-01-01', '2025-01-15', 45000.00, 1300.00, 1800.00),
(4, 4, '2025-01-01', '2025-01-15', 35000.00, 1100.00, 1700.00),
(5, 5, '2025-01-01', '2025-01-15', 60000.00, 1600.00, 2500.00),
(6, 6, '2025-01-01', '2025-01-15', 52000.00, 1400.00, 2200.00),
(7, 7, '2025-01-01', '2025-01-15', 45000.00, 1000.00, 1500.00),
(8, 8, '2025-01-01', '2025-01-15', 55000.00, 1300.00, 2000.00),
(9, 9, '2025-01-01', '2025-01-15', 42000.00, 1100.00, 1600.00),
(10, 10, '2025-01-01', '2025-01-15', 48000.00, 1200.00, 1900.00),
(11, 11, '2025-01-16', '2025-01-31', 52000.00, 1500.00, 2200.00),
(12, 12, '2025-01-16', '2025-01-31', 60000.00, 1700.00, 2400.00),
(13, 13, '2025-01-16', '2025-01-31', 55000.00, 1300.00, 2000.00),
(14, 14, '2025-01-16', '2025-01-31', 45000.00, 1200.00, 1800.00),
(15, 15, '2025-01-16', '2025-01-31', 37000.00, 1000.00, 1600.00),
(16, 16, '2025-01-16', '2025-01-31', 56000.00, 1400.00, 2100.00),
(17, 17, '2025-01-16', '2025-01-31', 49000.00, 1200.00, 1700.00),
(18, 18, '2025-01-16', '2025-01-31', 63000.00, 1800.00, 2600.00),
(19, 19, '2025-01-16', '2025-01-31', 47000.00, 1100.00, 1900.00),
(20, 20, '2025-01-16', '2025-01-31', 51000.00, 1300.00, 2100.00);



insert into Tax (TaxID, EmployeeID, TaxYear, TaxableIncome, TaxAmount) values
(1, 1, 2025, 50000.00, 8000.00),
(2, 2, 2025, 40000.00, 6000.00),
(3, 3, 2025, 45000.00, 7200.00),
(4, 4, 2025, 35000.00, 5400.00),
(5, 5, 2025, 60000.00, 9600.00),
(6, 6, 2025, 52000.00, 8320.00),
(7, 7, 2025, 45000.00, 7200.00),
(8, 8, 2025, 55000.00, 8800.00),
(9, 9, 2025, 42000.00, 6720.00),
(10, 10, 2025, 48000.00, 7680.00),
(11, 11, 2025, 52000.00, 8320.00),
(12, 12, 2025, 60000.00, 9600.00),
(13, 13, 2025, 55000.00, 8800.00),
(14, 14, 2025, 45000.00, 7200.00),
(15, 15, 2025, 37000.00, 5920.00),
(16, 16, 2025, 56000.00, 8960.00),
(17, 17, 2025, 49000.00, 7840.00),
(18, 18, 2025, 63000.00, 10080.00),
(19, 19, 2025, 47000.00, 7520.00),
(20, 20, 2025, 51000.00, 8160.00);


insert into FinancialRecord (RecordID, EmployeeID, RecordDate, Description, Amount, RecordType) values
(1, 1, '2025-01-15', 'Salary Payment', 50000.00, 'income'),
(2, 2, '2025-01-15', 'Salary Payment', 40000.00, 'income'),
(3, 3, '2025-01-15', 'Salary Payment', 45000.00, 'income'),
(4, 4, '2025-01-15', 'Salary Payment', 35000.00, 'income'),
(5, 5, '2025-01-15', 'Salary Payment', 60000.00, 'income'),
(6, 6, '2025-01-15', 'Salary Payment', 52000.00, 'income'),
(7, 7, '2025-01-15', 'Salary Payment', 45000.00, 'income'),
(8, 8, '2025-01-15', 'Salary Payment', 55000.00, 'income'),
(9, 9, '2025-01-15', 'Salary Payment', 42000.00, 'income'),
(10, 10, '2025-01-15', 'Salary Payment', 48000.00, 'income'),
(11, 11, '2025-01-30', 'Overtime Payment', 1500.00, 'income'),
(12, 12, '2025-01-30', 'Overtime Payment', 1700.00, 'income'),
(13, 13, '2025-01-30', 'Overtime Payment', 1300.00, 'income'),
(14, 14, '2025-01-30', 'Overtime Payment', 1100.00, 'income'),
(15, 15, '2025-01-30', 'Overtime Payment', 1000.00, 'income'),
(16, 16, '2025-01-30', 'Overtime Payment', 1400.00, 'income'),
(17, 17, '2025-01-30', 'Overtime Payment', 1200.00, 'income'),
(18, 18, '2025-01-30', 'Overtime Payment', 1800.00, 'income'),
(19, 19, '2025-01-30', 'Overtime Payment', 1100.00, 'income'),
(20, 20, '2025-01-30', 'Overtime Payment', 1300.00, 'income');
