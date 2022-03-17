package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    /**
     * Connect to the MySQL database.
     */
    private Connection con = null;

    public void displayEmployee(Employee emp) {
        if (emp != null) {
            System.out.println(
                    emp.getEmp_no() + " "
                            + emp.getFirst_name() + " "
                            + emp.getLast_name() + "\n"
                            + emp.getTitle() + "\n"
                            + "Salary:" + emp.getSalary() + "\n"
                            + emp.department + "\n"
                            + "Manager: " + emp.getManager().getLast_name() + "\n");
        }
    }

    public Employee getEmployee(int ID) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT emp_no, first_name, last_name "
                            + "FROM employees "
                            + "WHERE emp_no = " + ID;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next()) {
                Employee emp = new Employee();
                emp.setEmp_no(rset.getInt("emp_no"));
                emp.setFirst_name(rset.getString("first_name"));
                emp.setLast_name(rset.getString("last_name"));
                return emp;
            } else
                return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    /**
     * Gets all the current employees and salaries.
     *
     * @return A list of all employees and salaries, or null if there is an error.
     */
    public ArrayList<Employee> getAllSalaries() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT employees.emp_no, employees.first_name, employees.last_name, salaries.salary "
                            + "FROM employees, salaries "
                            + "WHERE employees.emp_no = salaries.emp_no AND salaries.to_date = '9999-01-01' "
                            + "ORDER BY employees.emp_no ASC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract employee information
            ArrayList<Employee> employees = new ArrayList<Employee>();
            while (rset.next()) {

                Employee emp = new Employee();
                emp.setEmp_no(rset.getInt("emp_no"));
                emp.setFirst_name(rset.getString("first_name"));
                emp.setLast_name(rset.getString("last_name"));
                employees.add(emp);
            }
            return employees;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get salary details");
            return null;
        }
    }

    /**
     * Prints a list of employees.
     *
     * @param employees The list of employees to print.
     */
    public void printSalaries(ArrayList<Employee> employees) {
        // Print header
        System.out.println(String.format("%-10s %-15s %-20s %-8s ", "Emp No", "First Name", "Last Name", "Salary"));
        // Loop over all employees in the list
        for (Employee emp : employees) {
            String emp_string =
                    String.format("%-10s %-15s %-20s %-8s ",
                            emp.getEmp_no(), emp.getFirst_name(), emp.getLast_name(), emp.getSalary() );
            System.out.println(emp_string);
        }
    }


    public ArrayList<Employee> getAllEbyRole(String title) {

        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT employees.emp_no, employees.first_name, employees.last_name, salaries.salary, titles.title "
                            + "FROM employees, salaries, titles "
                            + "WHERE employees.emp_no = salaries.emp_no AND employees.emp_no = titles.emp_no AND salaries.to_date = '9999-01-01' "
                            + "AND titles.to_date = '9999-01-01' "
                            + "AND titles.title = '" + title + "' "
                            + "ORDER BY employees.emp_no ASC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract employee information
            ArrayList<Employee> employees = new ArrayList<Employee>();
            while (rset.next()) {

                Employee emp = new Employee();
                emp.setEmp_no(rset.getInt("emp_no"));
                emp.setFirst_name(rset.getString("first_name"));
                emp.setLast_name(rset.getString("last_name"));
                employees.add(emp);
            }
            return employees;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get salary details");
            return null;
        }
    }

    public void connect() {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/employees?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }


    public Department getDepartment(String dept_No) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT dept_no, dept_name "
                            + "FROM departments "
                            + "WHERE dept_name = " +"'"+ dept_No +"'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new Department if valid.
            // Check one is returned
            if (rset.next()) {
                Department dep = new Department();
                dep.setDept_no(rset.getString("dept_no"));
                dep.setDept_name(rset.getString("dept_name"));

                return dep;
            } else
                return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    public ArrayList<Employee> getSalariesByDepartment(Department dept){
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =

            "SELECT employees.emp_no, employees.first_name, employees.last_name, salaries.salary "
            + "FROM employees, salaries, dept_emp, departments "
            + "WHERE employees.emp_no = salaries.emp_no "
            + "AND employees.emp_no = dept_emp.emp_no "
            + "AND dept_emp.dept_no = departments.dept_no "
            + "AND salaries.to_date = '9999-01-01' "
            + "AND departments.dept_name = " +"'" + dept.getDept_name() + "' "
            + "ORDER BY employees.emp_no ASC";



            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract employee information
            ArrayList<Employee> employees = new ArrayList<Employee>();
            while (rset.next()) {

                Employee emp = new Employee();
                emp.setEmp_no(rset.getInt("emp_no"));
                emp.setFirst_name(rset.getString("first_name"));
                emp.setLast_name(rset.getString("last_name"));
                employees.add(emp);
            }
            return employees;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get salary details");
            return null;
        }
    }
    public static void main(String[] args) {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();

        // Extract employee salary information
        ArrayList<Employee> employees = a.getSalariesByDepartment(a.getDepartment("Sales"));
        a.printSalaries(employees);
        // Test the size of the returned data - should be 240124
        System.out.println(employees.size());

        // Disconnect from database
        a.disconnect();
    }

}
