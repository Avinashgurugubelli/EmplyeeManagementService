package com.ajt.ems.models.db.ems;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@Table(
		name = "employees")
public class Employees {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "employee_id")
	private Integer employeeId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "phone_number", unique = true)
	private String phoneNumber;

	@Column(name = "hire_date", nullable = false)
	private Instant hireDate;

	@Column(name = "salary", nullable = false)
	private Float salary;

	@JoinColumn(name = "job_id", referencedColumnName = "job_id", nullable = false)
//	@OneToOne(cascade = CascadeType.ALL)
//	@PrimaryKeyJoinColumn
	private Integer jobId;

	@JoinColumn(name = "manager_id", referencedColumnName = "employee_id")
//	@OneToOne(cascade = CascadeType.ALL)
	private Integer managerId;

	@JoinColumn(name = "department_id", referencedColumnName = "department_id")
//	@OneToOne(cascade = CascadeType.ALL)
	private Integer departmentId;
}
