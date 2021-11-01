package com.ajt.ems.models.db.ems;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(
		name = "locations")
public class Locations {
	@Id
	@Column(name = "location_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer locationId;

	@Column(name = "street_address", nullable = false, unique = true)
	private String streetAddress;

	@Column(name = "postal_code", nullable = false, unique = true)
	private String postalCode;

	@Column(name = "city", nullable = false, unique = true)
	private String city;

	@Column(name = "state_province", nullable = false, unique = true)
	private String stateProvince;

	@JoinColumn(name = "country_id", referencedColumnName = "country_id", nullable = false, unique = true)
	@OneToOne(cascade = CascadeType.ALL)
	private Countries countryId;
}
