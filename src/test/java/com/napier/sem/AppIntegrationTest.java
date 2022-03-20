package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060", 30000);

    }

    @Test
    void testGetEmployee()
    {
        Employee emp = app.getEmployee(255530);
        assertEquals(emp.getEmp_no(), 255530);
        assertEquals(emp.getFirst_name(), "Ronghao");
        assertEquals(emp.getLast_name(), "Garigliano");
    }
    @Test
    void testGetAllSalaries(){
        ArrayList<Employee> all = app.getAllSalaries();
        assertEquals(all.size(),240124);
    }
    @Test
    void testAddEmployee()
    {
        Employee emp = new Employee();
        emp.setEmp_no(500000);
        emp.setFirst_name("Kevin");
        emp.setLast_name("Chalmers");
        app.addEmployee(emp);
        Employee a = app.getEmployee(500000);
        if(a != null){
            assertEquals(a.getEmp_no(), 500000);
            assertEquals(a.getFirst_name(), "Kevin");
            assertEquals(a.getLast_name(), "Chalmers");
        }

    }
}