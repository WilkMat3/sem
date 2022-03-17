package com.napier.sem;

/**
 * Represents an employee
 */
public class Employee
{
    public int getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(int emp_no) {
        this.emp_no = emp_no;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }





    /**
     * Employee number
     */
    private int emp_no;

    /**
     * Employee's first name
     */
    private String first_name;

    /**
     * Employee's last name
     */
    private String last_name;

    /**
     * Employee's job title
     */
    private String title;

    /**
     * Employee's salary
     */
    private int salary;

    /**
     * Employee's current department
     */
    public Department department;

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    /**
     * Employee's manager
     */
    public Employee manager;

}
