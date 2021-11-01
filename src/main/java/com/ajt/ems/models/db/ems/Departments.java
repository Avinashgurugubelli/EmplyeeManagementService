package com.ajt.ems.models.db.ems;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(
		name = "departments")
public class Departments {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "department_id")
	private Integer departmentId;

	@Column(name = "department_name", nullable = false, unique = true)
	private String departmentName;

	@JoinColumn(name = "location_id", referencedColumnName = "location_id" ,nullable = false)
	@OneToOne( cascade = CascadeType.ALL)
	private Locations locationId;

}
