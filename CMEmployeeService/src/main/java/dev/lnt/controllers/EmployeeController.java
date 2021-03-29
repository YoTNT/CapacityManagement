package dev.lnt.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import dev.lnt.entities.Seat;
import dev.lnt.exceptions.EmployeeNotFoundException;
import dev.lnt.services.EmployeeService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
@CrossOrigin("*")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	private static Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@PostMapping
	public ResponseEntity<Employee> createProject(@RequestBody Employee employee){
		logger.info("create new employee - process started");
		logger.info("request employee: " + employee);
		Employee newEmployee = employeeService.createEmployee(employee);
		logger.info("new employee created: " + newEmployee);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(newEmployee);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int id){
		logger.info("get employee by id - process started");
		logger.info("request employee id: "+id);
		Optional<Employee> employee = employeeService.getEmployeeById(id);
		if(!employee.isPresent()) {
			logger.info("employee with id ["+id+"] not found");
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(null);
		}
		else {
			logger.info("employee with id ["+id+"] found: " + employee.get());
			return ResponseEntity
					.status(HttpStatus.FOUND)
					.body(employeeService.getEmployeeById(id).get());
		}
	}
	
	@GetMapping("/{id}/seat")
	public ResponseEntity<Seat> getSeatByEmployeeId(@PathVariable int id){
		logger.info("get seat by employee id - process started");
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(employeeService.getSeatByEmployeeId(id));
	}
	
	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployees(){
		logger.info("get all employees - process started");
		List<Employee> employees = employeeService.getAllEmployees();
		logger.info("get all employees including: "+employees);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(employees);
		
	}
	
	@PutMapping
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
		logger.info("update employee - process started");
		logger.info("request employee: "+employee);
		Employee updatedEmployee = employeeService.updateEmployee(employee);
		logger.info("update succeed with employee: " + updatedEmployee);
		return ResponseEntity
				.status(HttpStatus.ACCEPTED)
				.body(updatedEmployee);
		
	}
	
	@DeleteMapping
	public ResponseEntity<Boolean> deleteEmployee(@RequestBody Employee employee){
		logger.info("delete employee (by id) - process started");
		logger.info("request employee: "+employee);
		boolean succeed = employeeService.deleteEmployee(employee);
		if(succeed)
			logger.info("delete succeed");
		else
			logger.info("delete failed");
		return ResponseEntity
				.status(HttpStatus.ACCEPTED)
				.body(succeed);
		
	}
	
//	@ExceptionHandler({Exception.class})
//	public ResponseEntity<Void> handleException(){
//		logger.info("EmployeeController handleException - unexpected exception occured");
//		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//	}
	
	@ExceptionHandler({EmployeeNotFoundException.class})
	public ResponseEntity<Void> employeeNotFoundHandler(){
		logger.info("request employee not found");
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
}
