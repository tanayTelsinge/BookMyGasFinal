package com.app.bookmygas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.app.bookmygas.entity.GasAgency;
import com.app.bookmygas.entity.Order;
import com.app.bookmygas.entity.User;
import com.app.bookmygas.repository.AgencyRepository;
import com.app.bookmygas.repository.OrderRepository;
import com.app.bookmygas.repository.UserRepository;

@Service
public class OrderService {
	

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
	private UserRepository userRepository;
    @Autowired
	private AgencyRepository agencyRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Integer id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id " + id));
    }

    public Order createOrder(Order order) {
        Optional<User> user = userRepository.findById(order.getUserId());
        Optional<GasAgency> gasAgency = agencyRepository.findById(order.getAgencyId());

        order.setUser(user.get());
        order.setGasAgency(gasAgency.get());

        return orderRepository.save(order);
    }

    public Order updateOrder(Integer id, Order order) {
        if (orderRepository.existsById(id)) {
           order.setOrderId(id);
            return orderRepository.save(order);
        } else {
            throw new RuntimeException("Order not found with id " + id);
        }
    }

    public void deleteOrder(Integer id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            throw new RuntimeException("Order not found with id " + id);
        }
    }

}
