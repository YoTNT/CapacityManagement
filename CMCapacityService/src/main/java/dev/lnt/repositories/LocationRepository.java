package dev.lnt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.lnt.entities.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer>{

	
	
}
