package dev.lnt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import dev.lnt.entities.Project;
import dev.lnt.repositories.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	ProjectRepository pr;
	
	@Override
	public Project createProject(Project project) {
		
		Project newProject = pr.save(project);
		return newProject;
		
	}

	@Override
	public Optional<Project> getProjectById(int id){	// TODO: Defensive Copy Might Need
		return pr.findById(id);
	}

	@Override
	@Cacheable("projects")
	public List<Project> getAllProjects() {
		List<Project> projects = (List<Project>)pr.findAll();
		return projects;
	}

	@Override
	public Project updateProject(Project project) throws Exception{	// General exception for now!
		
		int targetId = project.getId();
		if(!pr.findById(targetId).isPresent())
			throw new Exception("Cannot update an unexisted project with ID: " + targetId);
		else
			return pr.save(project);
	}

	@Override
	public boolean deleteProject(Project project) {
		
		int targetId = project.getId();
		
		if(!pr.findById(targetId).isPresent())
			return false;
		else {
			pr.delete(project);
			return true;
		}
		
	}

	
	
}
