package com.devs.capmgmt.locationservice.repository;

import com.devs.capmgmt.locationservice.entity.LocationEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LocationRepository extends CrudRepository<LocationEntity, Long> {
    //get by id
    LocationEntity findByLocationId(String locationId);
    //get all by baseBU
    List<LocationEntity> findLocationEntityByBaseBU(String baseBU);
    //get all by baseDept
    List<LocationEntity> findLocationEntityByBaseDept(String baseDept);
    //get all by baseLocation
    List<LocationEntity> findLocationEntityByBaseLocation(String baseLocation);
    //get all by seats less than or equal to
    List<LocationEntity> findLocationEntityByMaximumSeatsLessThanEqual(int seats);

}
