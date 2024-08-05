package com.app.bookmygas.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.bookmygas.entity.Payment;
import com.app.bookmygas.repository.PaymentRepository;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment getPaymentById(Integer id) {
        return paymentRepository.findById(id).orElse(null);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment updatePayment(Integer id, Payment payment) {
        if (paymentRepository.existsById(id)) {
            payment.setPaymentId(id);
            return paymentRepository.save(payment);
        }
        return null;
    }

    public boolean deletePayment(Integer id) {
        if (paymentRepository.existsById(id)) {
            paymentRepository.deleteById(id);
            return true;
        }
        return false;
    }

	public List<Payment> getPaymentByOrderId(Integer orderId) {
		// TODO Auto-generated method stub
		return paymentRepository.getPaymentByOrder_OrderId(orderId);
	}
}
