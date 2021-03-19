package com.devs.capmgmt.locationservice.service;

import com.devs.capmgmt.locationservice.entity.LocationEntity;
import com.devs.capmgmt.locationservice.entity.SeatEntity;
import com.devs.capmgmt.locationservice.repository.SeatRepository;
import com.devs.capmgmt.locationservice.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class SeatEntityServiceImpl implements SeatEntityService {

    @Autowired
    SeatRepository sr;

    @Autowired
    Utils utils;    //to generate random seatId

    //create seat
    @Override
    public SeatEntity createSeat(SeatEntity seatEntity) {
        String randomSeatId = utils.generateSeatId(15); //generates random seatId using the function defined in Utils class
        seatEntity.setSeatId(randomSeatId);
        SeatEntity storedSeat = sr.save(seatEntity);
        return storedSeat;
    }

    //get all seats
    @Override
    public List<SeatEntity> getSeats() {
        return (List<SeatEntity>)sr.findAll();
    }

    //get by id
    @Override
    public SeatEntity getSeatBySeatId(String seatId) throws Exception {
        return null;
    }

    //update seat
    @Override
    public SeatEntity updateSeat(String seatId, SeatEntity seatEntity) throws Exception {
        return null;
    }

    //delete seat
    @Override
    public void deleteSeatById(String seatId) throws Exception {

    }

    //get all by availabilityStatus
    @Override
    public List<SeatEntity> getSeatsByAvailabilityStatus(String availabilityStatus) throws Exception {
        return null;
    }

    //get all by seats by cost less than or equal to
    @Override
    public List<LocationEntity> getSeatsByMaximumCost(float amount) throws Exception {
        return null;
    }
}
