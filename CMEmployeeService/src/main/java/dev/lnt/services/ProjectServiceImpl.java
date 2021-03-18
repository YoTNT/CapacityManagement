package dev.lnt.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import dev.lnt.entities.Project;
import dev.lnt.exceptions.ProjectNotFoundException;
import dev.lnt.repositories.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	ProjectRepository pr;
	
	private static Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);
	
	@Override
	public Project createProject(Project project) {
		logger.info("create new project with: " + project);
		Project newProject = pr.save(project);
		logger.info("project created. new project: " + newProject);
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
	public Project updateProject(Project project){	// General exception for now!
		logger.info("update project with: " + project);
		int targetId = project.getId();
		if(!pr.findById(targetId).isPresent()) {
			logger.info("try to update project with project id ["+targetId+"] but not found in database. update failed");
			throw new ProjectNotFoundException("Cannot update an unexisted project with ID: " + targetId);
		}
		else {
			logger.info("update succeed");
			return pr.save(project);
		}
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
