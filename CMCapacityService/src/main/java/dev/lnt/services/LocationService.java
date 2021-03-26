package dev.lnt.services;

import java.util.List;
import java.util.Optional;

import dev.lnt.entities.Location;

public interface LocationService {

	Location createLocation(Location location);
	
	Optional<Location> getLocationById(int id);
	List<Location> getAllLocations();
	
	Location updateLocation(Location location);
	
	boolean deleteLocation(Location location);
	
}
