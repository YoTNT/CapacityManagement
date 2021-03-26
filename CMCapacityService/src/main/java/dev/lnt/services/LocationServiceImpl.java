package dev.lnt.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import dev.lnt.entities.Location;
import dev.lnt.exceptions.LocationNotFoundException;
import dev.lnt.repositories.LocationRepository;

@Service
@CacheConfig(cacheNames = {"locations"})
public class LocationServiceImpl implements LocationService{

	@Autowired
	LocationRepository lr;
	
	private static Logger logger = LoggerFactory.getLogger(LocationServiceImpl.class);
	
	@Override
	public Location createLocation(Location location) {
		logger.info("create new location with: " + location);
		Location newLocation = lr.save(location);
		logger.info("new location created. new location: " + newLocation);
		return newLocation;
	}

	@Override
	public Optional<Location> getLocationById(int id) {
		return lr.findById(id);
	}

	@Override
	@Cacheable
	public List<Location> getAllLocations() {
		List<Location> locations = (List<Location>)lr.findAll();
		return locations;
	}

	@Override
	public Location updateLocation(Location location) {
		int targetId = location.getId();
		
		if(!lr.findById(targetId).isPresent()) {
			logger.info("try to update location with location id [" + targetId + "] but not found in database. update failed");
			throw new LocationNotFoundException("Cannot update an unexisted location with ID: " + targetId);
		}
		else {
			logger.info("update succeed");
			return lr.save(location);
		}
	}

	@Override
	public boolean deleteLocation(Location location) {
		int targetId = location.getId();
		
		if(!lr.findById(targetId).isPresent()) {
			return false;
		}
		else {
			lr.delete(location);
			return true;
		}
	}

}
