package dev.lnt.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import dev.lnt.entities.Project;
import dev.lnt.exceptions.ProjectNotFoundException;
import dev.lnt.services.ProjectService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/projects")
@AllArgsConstructor
@CrossOrigin("*")
public class ProjectController {

	private final ProjectService projectService;
	private static Logger logger = LoggerFactory.getLogger(ProjectController.class);
	
	@PostMapping
	public ResponseEntity<Project> createProject(@RequestBody Project project){
		logger.info("create new project - process started");
		logger.info("request project: " + project);
		Project newProject = projectService.createProject(project);
		logger.info("new project created: " + newProject);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(newProject);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Project> getProjectById(@PathVariable int id){
		logger.info("get project by id - process started");
		logger.info("request project id: " + id);
		Optional<Project> project = projectService.getProjectById(id);
		if(!project.isPresent()) {
			logger.info("project with id ["+id+"] not found");
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(null);
		}
		else {
			logger.info("project with id ["+id+"] found: " + project.get());
			return ResponseEntity
					.status(HttpStatus.FOUND)
					.body(project.get());
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Project>> getAllProjects(){
		logger.info("get all projects - process started");
		List<Project> projects = projectService.getAllProjects();
		logger.info("get all projects including: " + projects);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(projects);
		
	}
	
	@PutMapping
	public ResponseEntity<Project> updateProject(@RequestBody Project project){
		logger.info("update project - process started");
		logger.info("request project: " + project);
		Project updatedProject = projectService.updateProject(project);
		logger.info("update succeed with project: " + updatedProject);
		return ResponseEntity
				.status(HttpStatus.ACCEPTED)
				.body(updatedProject);
		
	}
	
	@DeleteMapping
	public ResponseEntity<Boolean> deleteProject(@RequestBody Project project){
		logger.info("delete project (by id) - process started");
		logger.info("request project: " + project);
		boolean succeed = projectService.deleteProject(project);
		
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
//		logger.info("ProjectController handleException - unexpected exception occured");
//		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//	}
	
	@ExceptionHandler({ProjectNotFoundException.class})
	public ResponseEntity<Void> projectNotFoundHandler(){
		logger.info("request project not found");
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
