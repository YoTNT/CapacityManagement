package dev.lnt.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import dev.lnt.entities.Employee;
import dev.lnt.entities.Seat;
import dev.lnt.exceptions.EmployeeNotFoundException;
import dev.lnt.repositories.EmployeeRepository;

@Service
@CacheConfig(cacheNames = {"employees"})
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	EmployeeRepository er;
	
	private static Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	@Override
	public Employee createEmployee(Employee employee) {
		logger.info("create new employee with: " + employee);
		Employee newEmployee = er.save(employee);
		logger.info("employee created. new employee: " + newEmployee);
		return newEmployee;
		
	}

	@Override
	@Cacheable
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
	public Employee updateEmployee(Employee employee){ // General exception for now!
		
		int targetId = employee.getId();
		
		if(!er.findById(targetId).isPresent()) {
			logger.info("try to update employee with employee id ["+targetId+"] but not found in database. update failed");
			throw new EmployeeNotFoundException("Cannot update an unexisted employee with ID: " + targetId);
		}
		else {
			logger.info("update succeed");
			return er.save(employee);
		}	
		
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

	@Override
	@Cacheable
	public Seat getSeatByEmployeeId(int id) {
		logger.info("get seat using employee'id - process started");
		Optional<Employee> employee = er.findById(id);
		if(!employee.isPresent()) {
			logger.info("requesting employee not found");
			throw new EmployeeNotFoundException("Employee with id [" + id + "] not found");
		}
		else {
			logger.info("employee found");
			Seat seat = employee.get().getSeat();
			logger.info("found seat: " + seat);
			return seat;
		}
	}

}
