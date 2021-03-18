package dev.lnt.repositories;

import org.springframework.stereotype.Repository;

import dev.lnt.entities.Employee;
import dev.lnt.entities.Project;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer>{

	List<Project> findByProjectCategory(String projectCategory);
	
}
