package com.optimagrowth.license.model;

import javax.persistence.*;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter @Setter @ToString
@Entity
@Table(name="licenses")
public class License extends RepresentationModel<License> {

	@Id
	@Column(name = "license_id", nullable = false)
	private String licenseId;
	private String description;
	@Column(name = "organization_id", nullable = false)
	private String organizationId;
	@Column(name = "product_name", nullable = false)
	private String productName;
	@Column(name = "license_type", nullable = false)
	private String licenseType;
	@Column(name="comment")
	private String comment;

	// Added to fill with the property from the configserver
	@Transient
	private String environment;

	@Transient
	private String secret;

	public License withEnvironmentAndSecret(String environment, String secret){
		this.setEnvironment(environment);
		this.setSecret(secret);
		return this;
	}
}