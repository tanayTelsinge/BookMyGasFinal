package com.app.bookmygas.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.bookmygas.entity.Vendor;
import com.app.bookmygas.repository.VendorRepository;

import java.util.List;

@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    public Vendor createVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    public Vendor getVendorById(Integer id) {
        return vendorRepository.findById(id).orElse(null);
    }

    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    public Vendor updateVendor(Integer id, Vendor vendor) {
        if (vendorRepository.existsById(id)) {
            vendor.setVendorId(id);
            return vendorRepository.save(vendor);
        }
        return null;
    }

    public boolean deleteVendor(Integer id) {
        if (vendorRepository.existsById(id)) {
            vendorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

