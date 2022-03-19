package com.napier.sem;



import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printSalariesTestNull()
    {
        app.printSalaries(null);
    }

    @Test
    void printSalariesTestEmpty()
    {
        ArrayList<Employee> employess = new ArrayList<Employee>();
        app.printSalaries(employess);
    }
    @Test
    void printSalariesTestContainsNull()
    {
        ArrayList<Employee> employess = new ArrayList<Employee>();
        employess.add(null);
        app.printSalaries(employess);
    }
    @Test
    void printSalaries()
    {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        Employee emp = new Employee();
        emp.setEmp_no(1);
        emp.setFirst_name("Kevin");
        emp.setLast_name("Chalmers");
        emp.setTitle("Engineer");
        emp.setSalary(55000) ;
        employees.add(emp);
        app.printSalaries(employees);
    }

    @Test
    void dislpayEmployee(){
        Department dep = new Department();
        dep.setDept_name("Sales");
        dep.setDept_no("d001");

        Employee emp2 = new Employee();
        dep.manager = emp2;
        Employee emp = new Employee();
        emp.setManager(emp2);
        emp.setEmp_no(1);
        emp.setDepartment(dep);
        emp.setFirst_name("Kevin");
        emp.setLast_name("Chalmers");
        emp.setTitle("Engineer");
        emp.setSalary(55000) ;
        app.displayEmployee(emp);
    }
    @Test
    void dislpayEmployeeEmpty()
    {
        app.displayEmployee(null);
    }

    @Test
    void dislpayEmployeeTestEmpty()
    {
        Employee emp = new Employee();
        app.displayEmployee(emp);
    }

}