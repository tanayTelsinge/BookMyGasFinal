package com.app.bookmygas.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.bookmygas.entity.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {
    // Custom query methods can be added here if needed
}
