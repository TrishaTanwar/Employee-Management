package com.trisha.employees;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmployeeTest {

    Employee employee = new Employee("ABC",1,"abc@gmail.com","Manager");

    @Test
    void getName() {
        String expected = "ABC";
        String actual = employee.getName();
        assertEquals(expected, actual);
    }

    @Test
    void getEmployeeid() {
        Integer expected = 1;
        Integer actual = employee.getEmployeeid();
        assertEquals(expected, actual);
    }

    @Test
    void getEmail() {
        String expected = "abc@gmail.com";
        String actual = employee.getEmail();
        assertEquals(expected, actual);
    }

    @Test
    void getDesignation() {
        String expected = "Manager";
        String actual = employee.getDesignation();
        assertEquals(expected, actual);
    }
}