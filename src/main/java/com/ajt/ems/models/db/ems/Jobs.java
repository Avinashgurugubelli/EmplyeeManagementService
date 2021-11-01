package com.ajt.ems.models.db.ems;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "Jobs")
@Table(
		name = "jobs"
)
public class Jobs {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "job_id")
	private Integer jobId;

	@Column(name = "job_title", nullable = false, unique = true)
	private String jobTitle;

	@Column(name = "min_salary", nullable = true)
	private Float minSalary;

	@Column(name = "max_salary", nullable = true)
	private Float maxSalary;
}
