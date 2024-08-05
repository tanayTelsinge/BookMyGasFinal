package com.app.bookmygas.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.bookmygas.entity.GasAgency;
import com.app.bookmygas.entity.Order;
import com.app.bookmygas.entity.User;
import com.app.bookmygas.entity.Vendor;
import com.app.bookmygas.service.OrderService;

@RestController
@CrossOrigin
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping
	public ResponseEntity<List<Order>> getAllOrders(@RequestParam(name = "userId") Integer userId) {
		List<Order> orders = orderService.getAllOrders();
		if (userId != null) {
			List<Order> filteredOrders = new ArrayList<>();
			for (Order order : orders) {
				User user = order.getUser();
				if (user.getUserId() == userId) {
					filteredOrders.add(order);
				}
			}
			orders.clear();
			orders.addAll(filteredOrders);
		}
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable("id") Integer id) {
		try {
			Order order = orderService.getOrderById(id);
			return new ResponseEntity<>(order, HttpStatus.OK);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@PostMapping
	public ResponseEntity<Order> createOrder(@RequestBody Order order) {
		Order createdOrder = orderService.createOrder(order);
		return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Order> updateOrder(@PathVariable("id") Integer id, @RequestBody Order order) {
		try {
			Order updatedOrder = orderService.updateOrder(id, order);
			return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOrder(@PathVariable("id") Integer id) {
		try {
			orderService.deleteOrder(id);
			return ResponseEntity.noContent().build();
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}