package com.devs.capmgmt.locationservice.service;

import com.devs.capmgmt.locationservice.entity.LocationEntity;
import com.devs.capmgmt.locationservice.repository.LocationRepository;
import com.devs.capmgmt.locationservice.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class LocationEntityServiceImpl implements LocationEntityService {

    @Autowired
    LocationRepository lr;

    @Autowired
    Utils utils;    //to generate random locationId

    //create location
    @Override
    public LocationEntity createLocation(LocationEntity locationEntity) {
        String randomLocationId = utils.generateLocationId(10); //generates random locationId using the function defined in Utils class
        locationEntity.setLocationId(randomLocationId);
        LocationEntity storedLocation = lr.save(locationEntity);
        return storedLocation;
    }

    //get all locations
    @Override
    public List<LocationEntity> getLocations() {
        return (List<LocationEntity>)lr.findAll();
    }

    //get by id
    @Override
    public LocationEntity getLocationByLocationId(String locationId) throws Exception {
        LocationEntity responseLocation = lr.findByLocationId(locationId);
        if(responseLocation == null) throw new Exception("Location not found");
        return responseLocation;
    }

    //update location
    @Override
    public LocationEntity updateLocation(String locationId, LocationEntity locationEntity) throws Exception {
        LocationEntity updatedLocation = lr.findByLocationId(locationId);
        System.out.println("Location found");
        System.out.println(updatedLocation);
        if(locationEntity == null) throw new Exception("Location not found");
        //updates
        updatedLocation.setBaseBU(locationEntity.getBaseBU());
        updatedLocation.setBaseDept(locationEntity.getBaseDept());
        updatedLocation.setBaseLocation(locationEntity.getBaseLocation());
        updatedLocation.setMaximumSeats(locationEntity.getMaximumSeats());
        lr.save(updatedLocation);

        return updatedLocation;
    }

    //delete location
    @Override
    public void deleteLocationById(String locationId) throws Exception {
        LocationEntity deleteThisLocation = lr.findByLocationId(locationId);
        System.out.println("Location to be deleted");
        System.out.println(deleteThisLocation);
        if(deleteThisLocation == null) throw new Exception("Location not found");
        lr.delete(deleteThisLocation);
        System.out.println("Deleted...");
    }

    //get all by baseBU
    @Override
    public List<LocationEntity> getLocationsByBaseBU(String baseBU) throws Exception {
        System.out.println("baseBU :"+baseBU);
        List<LocationEntity> locationsByBaseBU =(List<LocationEntity>) lr.findLocationEntityByBaseBU(baseBU);
        return locationsByBaseBU;
    }

    //get all by baseDept
    @Override
    public List<LocationEntity> getLocationsByBaseDept(String baseDept) throws Exception {
        System.out.println("baseDept :"+baseDept);
        List<LocationEntity> locationsByBaseDept =(List<LocationEntity>) lr.findLocationEntityByBaseDept(baseDept);
        return locationsByBaseDept;
    }

    //get all by baseLocation
    @Override
    public List<LocationEntity> getLocationsByBaseLocation(String baseLocation) throws Exception {
        System.out.println("baseLocation :"+baseLocation);
        List<LocationEntity> locationsByBaseLocation =(List<LocationEntity>) lr.findLocationEntityByBaseLocation(baseLocation);
        return locationsByBaseLocation;
    }

    //get all by seats less than or equal to
    @Override
    public List<LocationEntity> getLocationsByMaximumSeats(int seats) throws Exception {
        System.out.println("seats :"+seats);
        List<LocationEntity> locationsByMaximumSeats =(List<LocationEntity>) lr.findLocationEntityByMaximumSeatsLessThanEqual(seats);
        return locationsByMaximumSeats;
    }
}
