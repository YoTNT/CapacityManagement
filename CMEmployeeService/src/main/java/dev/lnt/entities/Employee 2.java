package dev.lnt.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ps_number")
	private int id;

	@OneToOne
	@JoinTable(name = "seat",
	joinColumns = {@JoinColumn(name = "ps_number")},
	inverseJoinColumns = {@JoinColumn(name = "seat_id")})
	@JsonIgnoreProperties({"employee"})
	private Seat seat;
	
	@OneToOne
	private Project project;
	
	@Column(name = "name")
	private String name;
	@Column(name = "grade")
	private String grade;				// TODO: Enum possible
	@Column(name = "employee_status")
	private String employeeStatus;		// TODO: Enum possible
	@Column(name = "billed_status")
	private String billedStatus;		// TODO: Enum possible
	
	
}
