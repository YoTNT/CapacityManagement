package dev.lnt.repositories;

import org.springframework.stereotype.Repository;

import dev.lnt.entities.Employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	List<Employee> findByGrade(String grade);
	List<Employee> findByEmployeeStatus(String employeeStatus);
	List<Employee> findByBilledStatus(String billedStatus);
	
}
