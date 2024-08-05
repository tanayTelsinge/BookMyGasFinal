package com.app.bookmygas.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.bookmygas.entity.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
    // Custom query methods can be added here if needed
}
