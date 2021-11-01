package com.ajt.ems.models.db.ems;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(
		name = "dependents"
)
public class Dependents {
	@Id
	@Column(name = "dependent_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer dependentId;

	@Column(name = "first_name", nullable = false, unique = true)
	private String firstName;

	@Column(name = "last_name", nullable = false, unique = true)
	private String lastName;

	@Column(name = "relationship", nullable = false, unique = true)
	private String relationship;

	@JoinColumn(name = "employee_id", referencedColumnName = "employee_id", nullable = false, unique = true)
	@OneToOne(cascade = CascadeType.ALL)
	private Employees employeeId;
}
