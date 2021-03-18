package dev.lnt.services;

import java.util.List;
import java.util.Optional;

import dev.lnt.entities.Project;

public interface ProjectService {

	Project createProject(Project project);
	
	Optional<Project> getProjectById(int id);
	List<Project> getAllProjects();
	
	Project updateProject(Project project) throws Throwable;
	
	boolean deleteProject(Project project);
	
}
