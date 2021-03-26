package dev.lnt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.lnt.entities.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer>{

}
