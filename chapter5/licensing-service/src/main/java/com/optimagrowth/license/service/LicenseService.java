package com.optimagrowth.license.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.optimagrowth.license.config.ServiceConfig;
import com.optimagrowth.license.model.License;
import com.optimagrowth.license.repository.LicenseRepository;

import static java.util.stream.Collectors.toList;

@Service
public class LicenseService {

	@Autowired
	MessageSource messages;

	@Autowired
	private LicenseRepository licenseRepository;

	@Autowired
	ServiceConfig config;

	public List<License> getAllLicensesForOrganization(String organizationId){
		List<License> fromDb = licenseRepository.findByOrganizationId(organizationId);

		//Add the transient environment attribute to all license entities
		List<License> result = fromDb.stream().map(license -> {
			license.setEnvironment(config.getEnvironment());
			return license;
		}).collect(toList());
		return result;
	}

	public License getLicense(String licenseId, String organizationId){
		License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
		if (null == license) {
			throw new IllegalArgumentException(String.format(messages.getMessage("license.search.error.message", null, null),licenseId, organizationId));	
		}

		return license.withEnvironment(config.getEnvironment());
	}

	public License createLicense(License license){
		license.setLicenseId(UUID.randomUUID().toString());
		licenseRepository.save(license);

		return license.withEnvironment(config.getEnvironment());
	}

	public License updateLicense(License license){
		licenseRepository.save(license);

		return license.withEnvironment(config.getEnvironment());
	}

	public String deleteLicense(String licenseId){
		String responseMessage = null;
		License license = new License();
		license.setLicenseId(licenseId);
		licenseRepository.delete(license);
		responseMessage = String.format(messages.getMessage("license.delete.message", null, null),licenseId);
		return responseMessage;

	}
}
