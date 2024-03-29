package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.commercetools.api.models.common.TypedMoney;
import com.commercetools.api.models.payment.Payment;
import com.example.demo.service.PaymentService;

@RestController
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("/createPayment")
	public ResponseEntity<?> createPayment(@RequestBody TypedMoney money){
		try {
			Payment payment=  paymentService.createPayment(money);
			return ResponseEntity.ok(payment);
		}catch (Exception e) {
			return ResponseEntity.internalServerError().body("Payment Method is Not Created"+e);
		}
		
	}
}
