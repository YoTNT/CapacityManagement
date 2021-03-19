package com.devs.capmgmt.locationservice.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="location")
public class LocationEntity implements Serializable {
    private static final Long serialVersionUID = 4636993423388698134L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable=false, unique=true)
    private String locationId;

    @Column(nullable=false, length=10)
    private String baseBU;

    @Column(nullable=false, length=10)
    private String baseDept;

    @Column(nullable=false, length=100)
    private String baseLocation;

    @Column(nullable=false)
    private int maximumSeats;

    @Column
    private int parentId;

    @OneToMany(mappedBy="locationEntityDetails", cascade=CascadeType.ALL)
    private List<SeatEntity> seatEntities;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getBaseBU() {
        return baseBU;
    }

    public void setBaseBU(String baseBU) {
        this.baseBU = baseBU;
    }

    public String getBaseDept() {
        return baseDept;
    }

    public void setBaseDept(String baseDept) {
        this.baseDept = baseDept;
    }

    public String getBaseLocation() {
        return baseLocation;
    }

    public void setBaseLocation(String baseLocation) {
        this.baseLocation = baseLocation;
    }

    public int getMaximumSeats() {
        return maximumSeats;
    }

    public void setMaximumSeats(int maximumSeats) {
        this.maximumSeats = maximumSeats;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public List<SeatEntity> getSeatEntities() {
        return seatEntities;
    }

    public void setSeatEntities(List<SeatEntity> seatEntities) {
        this.seatEntities = seatEntities;
    }

    @Override
    public String toString() {
        return "LocationEntity{" +
                "id=" + id +
                ", locationId='" + locationId + '\'' +
                ", baseBU='" + baseBU + '\'' +
                ", baseDept='" + baseDept + '\'' +
                ", baseLocation='" + baseLocation + '\'' +
                ", maximumSeats=" + maximumSeats +
                ", parentId=" + parentId +
                ", seatEntities=" + seatEntities +
                '}';
    }
}
