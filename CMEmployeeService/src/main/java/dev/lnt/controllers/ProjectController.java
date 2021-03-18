package dev.lnt.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import dev.lnt.services.ProjectService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/projects")
@AllArgsConstructor
public class ProjectController {

	private final ProjectService projectService;
	
	@PostMapping
	public ResponseEntity<Project> createProject(@RequestBody Project project){
		
		Project newProject = projectService.createProject(project);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(newProject);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Project> getProjectById(@PathVariable int id){
		
		return ResponseEntity
				.status(HttpStatus.FOUND)
				.body(projectService.getProjectById(id).get());
	}
	
	@GetMapping
	public ResponseEntity<List<Project>> getAllProjects(){
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(projectService.getAllProjects());
		
	}
	
	@PutMapping
	public ResponseEntity<Project> updateProject(@RequestBody Project project) throws Throwable{
		
		return ResponseEntity
				.status(HttpStatus.ACCEPTED)
				.body(projectService.updateProject(project));
		
	}
	
	@DeleteMapping
	public ResponseEntity<Boolean> deleteProject(@RequestBody Project project){
		
		return ResponseEntity
				.status(HttpStatus.ACCEPTED)
				.body(projectService.deleteProject(project));
		
	}
	
	@ExceptionHandler({Exception.class})
	public ResponseEntity<Void> handleException(){
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}
