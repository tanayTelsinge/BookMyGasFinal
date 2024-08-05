package com.app.bookmygas.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.bookmygas.entity.ConnectionRequest;

@Repository
public interface ConnectionRepository extends JpaRepository<ConnectionRequest, Integer> {
    // Custom query methods can be added here if needed
}
