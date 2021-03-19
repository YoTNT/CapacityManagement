package com.devs.capmgmt.locationservice.repository;

import com.devs.capmgmt.locationservice.entity.LocationEntity;
import com.devs.capmgmt.locationservice.entity.SeatEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SeatRepository extends CrudRepository<SeatEntity, Long> {
    //get by id
    SeatEntity findBySeatId(String seatId);
    //get all by locationId
    List<SeatEntity> findSeatEntityByLocationId(String locationId);
    //get all by availabilityStatus
    List<SeatEntity> findSeatEntityByAvailabilityStatus(String availabilityStatus);
    //get all by seats by cost less than or equal to
    List<SeatEntity> findSeatEntityByCostLessThanEqual(float amount);
}
