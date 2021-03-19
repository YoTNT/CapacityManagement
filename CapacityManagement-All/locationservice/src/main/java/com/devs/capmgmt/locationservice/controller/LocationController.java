package com.devs.capmgmt.locationservice.controller;

import com.devs.capmgmt.locationservice.entity.LocationEntity;
import com.devs.capmgmt.locationservice.service.LocationEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;

@RestController
@RequestMapping("/location")    //http://localhost:8010/location
public class LocationController {

    @Autowired
    LocationEntityService ls;

    //test
    @GetMapping("/status/check")
    public String status() {
        return "Working";
    }

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
    public String deleteLocation(@PathVariable String locationId) throws Exception {
        ls.deleteLocationById(locationId);
        return "Deleted";
    }

    //get all by baseBU
    @GetMapping(path="basebu/{baseBU}", produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<LocationEntity> getLocationByBaseBU(@PathVariable("baseBU") String baseBU) throws Exception {
        List<LocationEntity> locationsByBaseBU = ls.getLocationsByBaseBU(baseBU);
        return locationsByBaseBU;
    }
    //get all by baseDept
    @GetMapping(path="basedept/{baseDept}", produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<LocationEntity> getLocationByBaseDept(@PathVariable("baseDept") String baseDept) throws Exception {
        List<LocationEntity> locationsByBaseDept = ls.getLocationsByBaseDept(baseDept);
        return locationsByBaseDept;
    }

    //get all by baseLocation
    @GetMapping(path="baselocation/{baselocation}", produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<LocationEntity> getLocationByBaselocation(@PathVariable("baselocation") String baselocation) throws Exception {
        List<LocationEntity> locationsByBaselocation = ls.getLocationsByBaseLocation(baselocation);
        return locationsByBaselocation;
    }

    //get all by seats less than or equal to
    @GetMapping(path="seats/{seats}", produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<LocationEntity> getLocationByMaximumSeats(@PathVariable("seats") int seats) throws Exception {
        List<LocationEntity> locationsByMaximumSeats = ls.getLocationsByMaximumSeats(seats);
        return locationsByMaximumSeats;
    }




}
