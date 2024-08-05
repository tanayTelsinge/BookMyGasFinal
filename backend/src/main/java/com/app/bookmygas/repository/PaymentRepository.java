package com.app.bookmygas.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.bookmygas.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

	List<Payment> getPaymentByOrder_OrderId(Integer orderId);
}
