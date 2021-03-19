package com.devs.capmgmt.locationservice.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="seat")
public class SeatEntity implements Serializable {
    private static final Long serialVersionUID = 4636993423388643134L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable=false, unique=true)
    private String seatId;

    @Column(nullable=false, length=50)
    private String availabilityStatus;

    @Column
    private float cost;

    @Column(nullable=false, length=100)
    private String seatLocation;

    @Column(nullable=false, length=10)
    private String locationId;

    @ManyToOne
    @JoinColumn(name="location_locationId") //name should indicate joining tableName_joiningColumnName
    private LocationEntity locationEntityDetails;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getSeatLocation() {
        return seatLocation;
    }

    public void setSeatLocation(String seatLocation) {
        this.seatLocation = seatLocation;
    }

    public LocationEntity getLocationDetails() {
        return locationEntityDetails;
    }

    public void setLocationDetails(LocationEntity locationEntityDetails) {
        this.locationEntityDetails = locationEntityDetails;
    }

    @Override
    public String toString() {
        return "SeatEntity{" +
                "id=" + id +
                ", availabilityStatus='" + availabilityStatus + '\'' +
                ", cost=" + cost +
                ", seatLocation='" + seatLocation + '\'' +
                ", locationId='" + locationId + '\'' +
                ", locationEntityDetails=" + locationEntityDetails +
                '}';
    }
}
