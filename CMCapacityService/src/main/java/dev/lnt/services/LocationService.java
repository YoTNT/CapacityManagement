package dev.lnt.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import dev.lnt.entities.Location;
import dev.lnt.entities.Seat;

public interface LocationService {

	Location createLocation(Location location);
	
	Optional<Location> getLocationById(int id);
	List<Location> getAllLocations();
	
	Location updateLocation(Location location);
	
	boolean deleteLocation(Location location);
	
//	Set<Seat> getAllSeatsByLocationId(int id);
	
}
