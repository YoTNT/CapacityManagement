package dev.lnt.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "location")
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "location_id")
	private int id;
	@Column(name = "base_bu")
	private String baseBU;
	@Column(name = "base_dept")
	private String baseDept;
	@Column(name = "base_location")
	private String baseLocation;
	@Column(name = "maximum_seats")
	private int maximumSeats;
	@Column(name = "parent_id")
	private int parentId;
	
	@OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
	private Set<Seat> seats = new HashSet<Seat>();
}
