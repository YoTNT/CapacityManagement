package dev.lnt.services;

import java.util.List;
import java.util.Optional;

import dev.lnt.entities.Location;
import dev.lnt.entities.Seat;

public interface SeatService {

	Seat createSeat(Seat seat);
	
	Optional<Seat> getSeatById(int id);
	List<Seat> getAllSeats();
	
	Seat updateSeat(Seat seat);
	
	boolean deleteSeat(Seat seat);
	
	List<Seat> getSeatsByLocationId(Location location);
	
	List<Seat> querySeatsBySeatLocationAndAvailabilityStatus(
			String seatLocation, 
			String availabilityStatus);
}
