package dev.lnt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import dev.lnt.entities.Employee;
import dev.lnt.repositories.EmployeeRepository;

@Service
@CacheConfig(cacheNames = {"employees"})
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	EmployeeRepository er;
	
	@Override
	public Employee createEmployee(Employee employee) {
		
		Employee newEmployee = er.save(employee);
		return newEmployee;
		
	}

	@Override
	public Optional<Employee> getEmployeeById(int id) {	// TODO: Defensive Copy Might Need
		return er.findById(id);
	}

	@Override
	@Cacheable
	public List<Employee> getAllEmployees() {
		List<Employee> employees = (List<Employee>)er.findAll();
		return employees;
	}

	@Override
	@Cacheable
	public List<Employee> getAllEmployeesByGrade(String grade) {
		List<Employee> employeesByGrade = er.findByGrade(grade);
		return employeesByGrade;
	}

	@Override
	@Cacheable
	public List<Employee> getAllEmployeesByEmployeeStatus(String status) {
		List<Employee> employeesByEmployeeStatus = er.findByEmployeeStatus(status);
		return employeesByEmployeeStatus;
	}

	@Override
	public Employee updateEmployee(Employee employee) throws Exception{ // General exception for now!
		
		int targetId = employee.getId();
		
		if(!er.findById(targetId).isPresent())
			throw new Exception("Cannot update an unexisted employee with ID: " + targetId);
		else
			return er.save(employee);
		
	}

	@Override
	public boolean deleteEmployee(Employee employee) {
		
		int targetId = employee.getId();
		
		if(!er.findById(targetId).isPresent())
			return false;
		else {
			er.delete(employee);
			return true;
		}
		
	}

}
