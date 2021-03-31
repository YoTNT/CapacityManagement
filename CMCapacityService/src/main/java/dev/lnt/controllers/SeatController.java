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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.lnt.entities.Location;
import dev.lnt.entities.Seat;
import dev.lnt.exceptions.SeatNotFoundException;
import dev.lnt.services.SeatService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/seats")
@AllArgsConstructor
@CrossOrigin("*")
public class SeatController {
	
	@Autowired
	SeatService seatService;
	
	private static Logger logger = LoggerFactory.getLogger(SeatController.class);
	
	@PostMapping
	public ResponseEntity<Seat> createSeat(@RequestBody Seat seat){
		logger.info("create new seat - process started");
		logger.info("request seat: " + seat);
		Seat newSeat = seatService.createSeat(seat);
		logger.info("new seat created: " + newSeat);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(newSeat);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Seat> getSeatById(@PathVariable int id){
		logger.info("get seat by id - process started");
		logger.info("request seat id: " + id);
		Optional<Seat> seat = seatService.getSeatById(id);
		if(!seat.isPresent()) {
			logger.info("seat with id [" + id + "] not found");
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(null);
		}
		else {
			logger.info("seat with id [" + id + "] not found");
			return ResponseEntity
					.status(HttpStatus.FOUND)
					.body(seatService.getSeatById(id).get());
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Seat>> getAllSeats(){
		logger.info("get all seats - process started");
		List<Seat> seats = seatService.getAllSeats();
		logger.info("get all seats including: " + seats);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(seats);
	}
	
	@GetMapping("/location")
	public ResponseEntity<List<Seat>> getAllSeatsByLocationId(@RequestBody Location location){
		logger.info("get seats by location - process started");
		List<Seat> seats = seatService.getSeatsByLocationId(location);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(seats);
	}
	
	@DeleteMapping
	public ResponseEntity<Boolean> deleteSeat(@RequestBody Seat seat){
		logger.info("delete seat (by id) - process started");
		logger.info("request seat: " + seat);
		boolean succeed = seatService.deleteSeat(seat);
		if(succeed) {
			logger.info("delete failed");
		}
		else {
			logger.info("delete succeed");
		}
		return ResponseEntity
				.status(HttpStatus.ACCEPTED)
				.body(succeed);
	}
	
	@ExceptionHandler({SeatNotFoundException.class})
	public ResponseEntity<Void> seatNotFoundHandler(){
		logger.info("request seat not found");
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}

}
