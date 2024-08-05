package com.app.bookmygas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.bookmygas.entity.Vendor;
import com.app.bookmygas.service.VendorService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/vendors")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @PostMapping
    public ResponseEntity<Vendor> createVendor(@Valid @RequestBody Vendor vendor) {
        Vendor createdVendor = vendorService.createVendor(vendor);
        return new ResponseEntity<>(createdVendor, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendor> getVendorById(@PathVariable Integer id) {
        Vendor vendor = vendorService.getVendorById(id);
        return vendor != null ? new ResponseEntity<>(vendor, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Vendor>> getAllVendors() {
        List<Vendor> vendors = vendorService.getAllVendors();
        return new ResponseEntity<>(vendors, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vendor> updateVendor(@PathVariable Integer id, @Valid @RequestBody Vendor vendor) {
        Vendor updatedVendor = vendorService.updateVendor(id, vendor);
        return updatedVendor != null ? new ResponseEntity<>(updatedVendor, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVendor(@PathVariable Integer id) {
        boolean isDeleted = vendorService.deleteVendor(id);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}