package com.devs.capmgmt.locationservice.controller;

import com.devs.capmgmt.locationservice.entity.LocationEntity;
import com.devs.capmgmt.locationservice.entity.SeatEntity;
import com.devs.capmgmt.locationservice.service.SeatEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seat")    //http://localhost:8010/seat
public class SeatController {
    @Autowired
    SeatEntityService ss;

    //test
    @GetMapping("/status/check")
    public String status() {
        return "Working";
    }

    //create seat
    @PostMapping(consumes= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public SeatEntity createSeat(@PathVariable String locationId, @RequestBody SeatEntity seatEntity) {

        SeatEntity newSeatCreated = ss.createSeat(seatEntity);
        return newSeatCreated;
    }

    //get all seats
    @GetMapping(produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<SeatEntity> getSeats() {
        List<SeatEntity> allSeats = ss.getSeats();
        return allSeats;
    }

    //get by id
    @GetMapping(value="/{seatId}", produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public SeatEntity getSeatById(@PathVariable("seatId") String seatId) throws Exception {
        SeatEntity responseSeat = ss.getSeatBySeatId(seatId);
        return responseSeat;
    }

    //update seat
    @PutMapping(value="/{seatId}",
            consumes= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public SeatEntity updateSeatById(@PathVariable String seatId, @RequestBody SeatEntity seatEntity) throws Exception {
        SeatEntity updatedSeat = ss.updateSeat(seatId, seatEntity);
        return updatedSeat;
    }

    //delete seat
    @DeleteMapping(value="/{seatId}",produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public String deleteSeat(@PathVariable String seatId) throws Exception {
        ss.deleteSeatById(seatId);
        return "Deleted";
    }
}
/*

    //create location
    @PostMapping(consumes= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public LocationEntity createLocation(@RequestBody LocationEntity locationEntity) {
        LocationEntity newUserCreated = ls.createLocation(locationEntity);
        return newUserCreated;
    }

    //get all locations
    @GetMapping(produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<LocationEntity> getLocations() {
        List<LocationEntity> allCurrentLocations = ls.getLocations();
        return allCurrentLocations;
    }
    //get by id
    @GetMapping(value="/{locationId}", produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public LocationEntity getLocationById(@PathVariable("locationId") String locationId) throws Exception {
        LocationEntity responseLocation = ls.getLocationByLocationId(locationId);
        return responseLocation;
    }
    //update location
    @PutMapping(value="/{locationId}",
            consumes= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public LocationEntity updateLocationById(@PathVariable String locationId, @RequestBody LocationEntity locationEntity) throws Exception {
        LocationEntity updatedLocation = ls.updateLocation(locationId, locationEntity);
        return updatedLocation;
    }

    //delete location
    @DeleteMapping(value="/{locationId}",produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public String deleteUser(@PathVariable String locationId) throws Exception {
        ls.deleteLocationById(locationId);
        return "Deleted";
    }
 */