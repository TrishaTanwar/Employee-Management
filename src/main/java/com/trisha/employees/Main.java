package com.trisha.employees;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/employees")
public class Main {

	private final EmployeeRepository employeeRepository;

	public Main(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public static void main(String[] args) {

		SpringApplication.run(Main.class, args);

	}

	record NewEmployeeRequest(
			String name,
			String email,
			String designation

	){}

	@GetMapping("/display")
	public List<Employee> getEmployees(){
		return employeeRepository.findAll();
	}

	@PostMapping("/add")
	public void addEmployee(@RequestBody NewEmployeeRequest request){
		Employee employee = new Employee();
		employee.setName(request.name());
		employee.setEmail(request.email());
		employee.setDesignation(request.designation());
		employeeRepository.save(employee);
	}

	@PutMapping("update/{employeeid}")
	public void updateEmployee(@PathVariable("employeeid") Integer id,
							   @RequestBody NewEmployeeRequest request){
		Employee employee = employeeRepository.getReferenceById(id);
		employee.setName(request.name());
		employee.setEmail(request.email());
		employee.setDesignation(request.designation());
		employeeRepository.save(employee);
	}

	@DeleteMapping("delete/{employeeid}")
	public void deleteEmployee(@PathVariable("employeeid") Integer id){
		employeeRepository.deleteById(id);
	}


}
