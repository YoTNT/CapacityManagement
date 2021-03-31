package dev.lnt.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import dev.lnt.entities.Location;
import dev.lnt.exceptions.LocationNotFoundException;
import dev.lnt.services.LocationService;
import dev.lnt.entities.Seat;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/locations")
@AllArgsConstructor
@CrossOrigin("*")
public class LocationController {

	@Autowired
	LocationService locationService;
	
	private static Logger logger = LoggerFactory.getLogger(LocationController.class);
	
	@PostMapping
	public ResponseEntity<Location> createLocation(@RequestBody Location location){
		logger.info("create new location - process started");
		logger.info("request location: " + location);
		Location newLocation = locationService.createLocation(location);
		logger.info("new location created: " + newLocation);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(newLocation);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Location> getLocationById(@PathVariable int id){
		logger.info("get location by id - process started");
		logger.info("request location id: " + id);
		Optional<Location> location = locationService.getLocationById(id);
		if(!location.isPresent()) {
			logger.info("employee with id [" + id + "] not found");
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(null);
		}
		else {
			return ResponseEntity
					.status(HttpStatus.FOUND)
					.body(locationService.getLocationById(id).get());
		}
	}
	
//	@GetMapping("/{id}/seats")
//	public ResponseEntity<Set<Seat>> getAllSeatsByLocationId(@PathVariable int id){
//		logger.info("get all seats by location (id) - process started");
//		logger.info("request location id: " + id);
//		return ResponseEntity
//				.status(HttpStatus.OK)
//				.body(locationService.getAllSeatsByLocationId(id));
//	}
	
	@GetMapping
	public ResponseEntity<List<Location>> getAllLocations(){
		logger.info("get all locations - process started");
		List<Location> locations = locationService.getAllLocations();
		logger.info("get all locations including: " + locations);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(locations);
	}
	
	@PutMapping
	public ResponseEntity<Location> updateLocation(@RequestBody Location location){
		logger.info("update location - process started");
		logger.info("request location: " + location);
		Location updatedLocation = locationService.updateLocation(location);
		logger.info("update succeed with location: " + updatedLocation);
		return ResponseEntity
				.status(HttpStatus.ACCEPTED)
				.body(updatedLocation);
	}
	
	@DeleteMapping
	public ResponseEntity<Boolean> deleteLocation(@RequestBody Location location){
		logger.info("delete location (by id) - process started");
		logger.info("request location: " + location);
		boolean succeed = locationService.deleteLocation(location);
		if(succeed)
			logger.info("delete succeed");
		else
			logger.info("delete failed");
		return ResponseEntity
				.status(HttpStatus.ACCEPTED)
				.body(succeed);
	}
	
	
	@ExceptionHandler({LocationNotFoundException.class})
	public ResponseEntity<Void> locationNotFoundHandler(){
		logger.info("request location not found");
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
}
