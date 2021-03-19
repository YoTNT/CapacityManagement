package com.devs.capmgmt.locationservice.service;

import com.devs.capmgmt.locationservice.entity.LocationEntity;
import com.devs.capmgmt.locationservice.entity.SeatEntity;

import java.util.List;


public interface SeatEntityService {
    //create seat
    SeatEntity createSeat(SeatEntity seatEntity);
    //get all seats
    List<SeatEntity> getSeats();
    //get by id
    SeatEntity getSeatBySeatId(String seatId) throws Exception;
    //update seat
    SeatEntity updateSeat(String seatId, SeatEntity seatEntity) throws Exception;
    //delete seat
    void deleteSeatById(String seatId) throws Exception;
    //get all by availabilityStatus
    List<SeatEntity> getSeatsByAvailabilityStatus(String availabilityStatus) throws Exception;
    //get all by seats by cost less than or equal to
    List<LocationEntity> getSeatsByMaximumCost(float amount) throws Exception;
}
