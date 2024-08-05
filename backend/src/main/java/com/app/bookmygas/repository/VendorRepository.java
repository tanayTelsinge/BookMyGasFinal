package com.app.bookmygas.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.bookmygas.entity.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Integer> {
    // Custom query methods can be added here if needed
}
