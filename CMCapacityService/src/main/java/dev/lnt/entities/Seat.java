package dev.lnt.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "seat")
public class Seat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seat_id")
	private int id;
	
//	@ManyToOne(fetch = FetchType.LAZY)	// New Way
//	@JoinColumn(name = "location_id", referencedColumnName = "id")	// New Way
	@ManyToOne
	@JoinColumn(name = "location_id")
	private Location location;
	
	@OneToOne(mappedBy = "seat")
	@JsonIgnore
	private Employee employee;
	
	@Column(name = "seat_location")
	private String seatLocation;
	@Column(name = "cost")
	private float cost;
	@Column(name = "availability_status")
	private String availabilityStatus;
	
}
