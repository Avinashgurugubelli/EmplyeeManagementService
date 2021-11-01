package com.ajt.ems.models.db.ems;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "regions")
@Table(
		name = "regions"
)
public class Regions {
	@Id
	@Column(name = "region_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer regionId;

	@Column(name = "region_name", nullable = false, unique = true)
	private String regionName;

}
