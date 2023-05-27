package com.trisha.employees;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @Order(1)
    void addEmployee() {
        Employee employee = new Employee();
        employee.setName("ABC");
        employee.setDesignation("Manager");
        employee.setEmail("abc@gmail.com");
        employeeRepository.save(employee);

        Assertions.assertThat(employee.getEmployeeid()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    void getEmployee_byId() {
        Employee employee = new Employee();
        employee.setName("ABC");
        employee.setDesignation("Manager");
        employee.setEmail("abc@gmail.com");
        employeeRepository.save(employee);

        Integer id = employee.getEmployeeid();

        Employee employeeTest = employeeRepository.findById(id).get();

        Assertions.assertThat(employeeTest.getEmployeeid()).isEqualTo(id);
    }

    @Test
    @Order(3)
    void getEmployee_List() {
        Employee employee = new Employee();
        employee.setName("ABC");
        employee.setDesignation("Manager");
        employee.setEmail("abc@gmail.com");
        employeeRepository.save(employee);
        List<Employee> employeeList = employeeRepository.findAll();

        Assertions.assertThat(employeeList.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    void deleteCustomer() {
        Employee employee = new Employee();
        employee.setName("ABC");
        employee.setDesignation("Manager");
        employee.setEmail("abc@gmail.com");
        employeeRepository.save(employee);

        Employee employeeTest = employeeRepository.findById(1).get();
        employeeRepository.delete(employeeTest);

        Employee employee1 = null;

        Optional<Employee> optionalEmployee = employeeRepository.findByEmail("abc@gmail.com");

        if (optionalEmployee.isPresent()){
            employee1 = optionalEmployee.get();
        }

        Assertions.assertThat(employee1).isNull();
    }

    @Test
    @Order(5)
    void updateCustomer() {
        Employee employee = new Employee();
        employee.setName("ABC");
        employee.setDesignation("Manager");
        employee.setEmail("abc@gmail.com");
        employeeRepository.save(employee);

        employee.setEmail("abcd@gmail.com");
        Employee employeeUpdated = employeeRepository.save(employee);

        Assertions.assertThat(employeeUpdated.getEmail()).isEqualTo("abcd@gmail.com");
    }
}
