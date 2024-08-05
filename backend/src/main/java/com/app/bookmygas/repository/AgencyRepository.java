package com.app.bookmygas.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.bookmygas.entity.GasAgency;

@Repository
public interface AgencyRepository extends JpaRepository<GasAgency, Integer> {
    // Custom query methods can be added here if needed
	List<GasAgency> findByVendor_VendorId(Integer vendorId);
}
