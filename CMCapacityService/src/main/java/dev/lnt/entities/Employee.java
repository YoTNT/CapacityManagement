package dev.lnt.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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

	@OneToOne(fetch = FetchType.LAZY)
	@JoinTable(name = "employee_seat",
	joinColumns = {@JoinColumn(name = "ps_number")},
	inverseJoinColumns = {@JoinColumn(name = "seat_id")})
	private Seat seat;
	
	@OneToOne(mappedBy = "manager", fetch = FetchType.LAZY)
	private Project project;
	
	@OneToOne(mappedBy = "employee", fetch = FetchType.LAZY)
	private User user;
	
	@Column(name = "name")
	private String name;
	@Column(name = "grade")
	private String grade;				// TODO: Enum possible
	@Column(name = "employee_status")
	private String employeeStatus;		// TODO: Enum possible
	@Column(name = "billed_status")
	private String billedStatus;		// TODO: Enum possible
	
}
