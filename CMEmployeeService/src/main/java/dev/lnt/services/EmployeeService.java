package dev.lnt.services;

import java.util.List;
import java.util.Optional;

import dev.lnt.entities.Employee;

public interface EmployeeService {

	Employee createEmployee(Employee employee);
	
	Optional<Employee> getEmployeeById(int id);
	List<Employee> getAllEmployees();
	List<Employee> getAllEmployeesByGrade(String grade);
	List<Employee> getAllEmployeesByEmployeeStatus(String status);
	
	Employee updateEmployee(Employee employee) throws Throwable;
	
	boolean deleteEmployee(Employee employee);
	
}
