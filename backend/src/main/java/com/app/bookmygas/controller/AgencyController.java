package com.app.bookmygas.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.bookmygas.entity.GasAgency;
import com.app.bookmygas.entity.Vendor;
import com.app.bookmygas.service.AgencyService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/agencies")
public class AgencyController {

    @Autowired
    private AgencyService agencyService;

    @PostMapping
    public ResponseEntity<GasAgency> createAgency(@Valid @RequestBody GasAgency GasAgency) {
        GasAgency createdAgency = agencyService.createAgency(GasAgency);
        return new ResponseEntity<>(createdAgency, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GasAgency> getAgencyById(@PathVariable Integer id) {
        GasAgency GasAgency = agencyService.getAgencyById(id);
        return GasAgency != null ? new ResponseEntity<>(GasAgency, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<GasAgency>> getAllAgencies(@RequestParam("vendorId") Integer vendorId) {
		List<GasAgency> agencies = agencyService.getAllAgencies();
		if(vendorId != null) {
			List<GasAgency> filteredAgencies = new ArrayList<>();
			for(GasAgency agency : agencies) {
				Vendor vendor = agency.getVendor();
				if(vendor.getVendorId() == vendorId) {
				  filteredAgencies.add(agency);
				}
			}
			agencies.clear();
			agencies.addAll(filteredAgencies);
		}
        return new ResponseEntity<>(agencies, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GasAgency> updateAgency(@PathVariable Integer id, @Valid @RequestBody GasAgency GasAgency) {
        GasAgency updatedAgency = agencyService.updateAgency(id, GasAgency);
        return updatedAgency != null ? new ResponseEntity<>(updatedAgency, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgency(@PathVariable Integer id) {
        boolean isDeleted = agencyService.deleteAgency(id);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}