package com.optimagrowth.license.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.optimagrowth.license.model.License;
import com.optimagrowth.license.service.LicenseService;

import java.util.List;

@RestController
@RequestMapping(value="v1/organization/{organizationId}/license")
public class LicenseController {

	@Autowired
	private LicenseService licenseService;

	@GetMapping
	public ResponseEntity<List<License>> getLicensesForOrganization(
			@PathVariable("organizationId") String organizationId){
		List<License> result = licenseService.getAllLicensesForOrganization(organizationId);

		return ResponseEntity.ok(result);
	}

	@RequestMapping(value="/{licenseId}",method = RequestMethod.GET)
	public ResponseEntity<License> getLicense( @PathVariable("organizationId") String organizationId,
			@PathVariable("licenseId") String licenseId) {
		
		License license = licenseService.getLicense(licenseId, organizationId);
		license.add( 
				linkTo(methodOn(LicenseController.class).getLicense(organizationId, license.getLicenseId())).withSelfRel(),
				linkTo(methodOn(LicenseController.class).getLicensesForOrganization(organizationId)).withRel("getLicensesForOrganization"),
				linkTo(methodOn(LicenseController.class).createLicense(organizationId, license)).withRel("createLicense"),
				linkTo(methodOn(LicenseController.class).updateLicense(organizationId, licenseId, license)).withRel("updateLicense"),
				linkTo(methodOn(LicenseController.class).deleteLicense(license.getLicenseId())).withRel("deleteLicense")
		);
		
		return ResponseEntity.ok(license);
	}

	@PutMapping(value="/{licenseId}")
	public ResponseEntity<License> updateLicense(@PathVariable("organizationId") String organizationId,
												 @PathVariable("licenseId") String licenseId,
												 @RequestBody License request) {
		request.setOrganizationId(organizationId); //get from uri instead of request body
		request.setLicenseId(licenseId); //get from uri instead of request body
		return ResponseEntity.ok(licenseService.updateLicense(request));
	}
	
	@PostMapping
	public ResponseEntity<License> createLicense(@PathVariable("organizationId") String organizationId,
												 @RequestBody License request) {
		request.setOrganizationId(organizationId); //get from uri instead of request body
		return ResponseEntity.ok(licenseService.createLicense(request));
	}

	@DeleteMapping(value="/{licenseId}")
	public ResponseEntity<String> deleteLicense(@PathVariable("licenseId") String licenseId) {
		return ResponseEntity.ok(licenseService.deleteLicense(licenseId));
	}
}
