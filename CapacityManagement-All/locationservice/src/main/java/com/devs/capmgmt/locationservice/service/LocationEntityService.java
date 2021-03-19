package com.devs.capmgmt.locationservice.service;

import com.devs.capmgmt.locationservice.entity.LocationEntity;

import javax.xml.stream.Location;
import java.util.List;

public interface LocationEntityService {
    //create location
    LocationEntity createLocation(LocationEntity locationEntity);
    //get all locations
    List<LocationEntity> getLocations();
    //get by id
    LocationEntity getLocationByLocationId(String locationId) throws Exception;
    //update location
    LocationEntity updateLocation(String locationId, LocationEntity locationEntity) throws Exception;
    //delete location
    void deleteLocationById(String locationId) throws Exception;
    //get all by baseBU
    List<LocationEntity> getLocationsByBaseBU(String baseBU) throws Exception;
    //get all by baseDept
    List<LocationEntity> getLocationsByBaseDept(String baseDept) throws Exception;
    //get all by baseLocation
    List<LocationEntity> getLocationsByBaseLocation(String baseLocation) throws Exception;
    //get all by seats less than or equal to
    List<LocationEntity> getLocationsByMaximumSeats(int seats) throws Exception;
}
