package dev.lnt.entities;

import static javax.persistence.FetchType.LAZY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "project")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "project_id")
	private int id;
	@Column(name = "start_date")
	private Date startDate;
	@Column(name = "end_date")
	private Date endDate;
	@Column(name = "project_category")
	private String projectCategory;			// TODO: Enum possible
	
	@OneToOne(fetch = LAZY)
	@JoinTable(name = "employee_project",
	joinColumns = {@JoinColumn(name = "project_id")},
	inverseJoinColumns = {@JoinColumn(name = "ps_number")})
	private Employee manager;
	
	@Column(name = "customer_code")
	private int customerCode;
	@Column(name = "customer_name")
	private String customerName;
	
	@Override
	public String toString() {
		return "Project [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", projectCategory="
				+ projectCategory + ", customerCode=" + customerCode + ", customerName=" + customerName + "]";
	}
	
}
