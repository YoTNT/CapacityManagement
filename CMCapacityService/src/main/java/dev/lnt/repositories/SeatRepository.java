package dev.lnt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.lnt.entities.Location;
import dev.lnt.entities.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer>{

	List<Seat> findByLocation(Location location);
	
	@Query("SELECT s FROM Seat s WHERE "
			+ "(:seat_location is null or s.seatLocation = :seat_location) and "
			+ "(:availability_status is null or s.availabilityStatus = :availability_status)")
	List<Seat> queryBySeatLocationAndAvailabilityStatus(
			@Param("seat_location") String seatLocation, 
			@Param("availability_status") String availabilityStatus);
}
