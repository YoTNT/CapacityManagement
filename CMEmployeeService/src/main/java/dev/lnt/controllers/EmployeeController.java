package dev.lnt.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.lnt.entities.Employee;
import dev.lnt.services.EmployeeService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeController {

	private final EmployeeService employeeService;
	
	@PostMapping
	public ResponseEntity<Employee> createProject(@RequestBody Employee employee){
		
		Employee newEmployee = employeeService.createEmployee(employee);
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(newEmployee);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int id){
		
		return ResponseEntity
				.status(HttpStatus.FOUND)
				.body(employeeService.getEmployeeById(id).get());
		
	}
	
	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployees(){
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(employeeService.getAllEmployees());
		
	}
	
	@PutMapping
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) throws Throwable{
		
		return ResponseEntity
				.status(HttpStatus.ACCEPTED)
				.body(employeeService.updateEmployee(employee));
		
	}
	
	@DeleteMapping
	public ResponseEntity<Boolean> deleteEmployee(@RequestBody Employee employee){
		
		return ResponseEntity
				.status(HttpStatus.ACCEPTED)
				.body(employeeService.deleteEmployee(employee));
		
	}
	
	@ExceptionHandler({Exception.class})
	public ResponseEntity<Void> handleException(){
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
