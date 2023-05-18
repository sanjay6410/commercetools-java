package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/addPaymentToOrder")
	public ResponseEntity<?> addPaymentToOrder(@RequestParam("orderId") String orderId) {
		try {
		orderService.updateOrderWithPayment(orderId);
		return ResponseEntity.ok("Payment Is  Added for Order  "+ orderId);
		}catch(Exception e) {
			return ResponseEntity.internalServerError().body("Payment Is Not Added for Order"+orderId +e);
		}
	}
}
