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
import dev.lnt.entities.Seat;
import dev.lnt.exceptions.SeatNotFoundException;
import dev.lnt.repositories.SeatRepository;

@Service
@CacheConfig(cacheNames = {"seats"})
public class SeatServiceImpl implements SeatService{

	@Autowired
	SeatRepository sr;
	
	private static Logger logger = LoggerFactory.getLogger(SeatServiceImpl.class);
	
	@Override
	public Seat createSeat(Seat seat) {
		logger.info("create new seat with: " + seat);
		Seat newSeat = sr.save(seat);
		logger.info("new seat created. new seat: " + newSeat);
		return newSeat;
	}

	@Override
	@Cacheable
	public Optional<Seat> getSeatById(int id) {
		return sr.findById(id);
	}

	@Override
	@Cacheable
	public List<Seat> getAllSeats() {
		List<Seat> seats = sr.findAll();
		return seats;
	}

	@Override
	public Seat updateSeat(Seat seat) {
		int targetId = seat.getId();
		
		if(!sr.findById(targetId).isPresent()) {
			logger.info("try to update seat with seat id ["+targetId+"] but not found in database. update failed");
			throw new SeatNotFoundException("Cannot update an unexisted seat with ID: " + targetId);
		}
		else {
			logger.info("update succeed");
			return sr.save(seat);
		}
	}

	@Override
	public boolean deleteSeat(Seat seat) {

		int targetId = seat.getId();
		
		if(!sr.findById(targetId).isPresent()) {
			return false;
		}
		else {
			sr.delete(seat);
			return true;
		}
		
	}

	@Override
	@Cacheable
	public List<Seat> getSeatsByLocationId(Location location) {
		
		logger.info("try to get all seats by location id using query with location id: " + location);
		return sr.findByLocation(location);
		
	}

	@Override
	@Cacheable
	public List<Seat> querySeatsBySeatLocationAndAvailabilityStatus(
			String seatLocation, 
			String availabilityStatus) {
		
		logger.info("querying seats by seatLocation and availabilityStatus");
		return sr.queryBySeatLocationAndAvailabilityStatus(seatLocation, availabilityStatus);
	}

}
