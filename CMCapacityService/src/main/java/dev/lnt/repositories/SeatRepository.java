package dev.lnt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.lnt.entities.Location;
import dev.lnt.entities.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer>{

	List<Seat> findByLocation(Location location);
	
}
