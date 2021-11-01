package com.ajt.ems.models.db.ems;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(
		name = "countries")
public class Countries {

	@Id
	@Column(name = "country_id")
	private String countryId;

	@Column(name = "country_name", nullable = false, unique = true)
	private String countryName;

	@JoinColumn(name = "region_id", referencedColumnName = "region_id",nullable = false, unique = true)
	@OneToOne(cascade = CascadeType.ALL)
	private Regions regionId;
}
