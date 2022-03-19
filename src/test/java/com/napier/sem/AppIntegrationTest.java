package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

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
}