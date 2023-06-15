package com.example.demo.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.commercetools.api.models.cart.Cart;
import com.commercetools.api.models.common.Address;
import com.example.demo.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/addAddressToExistingCustomer")
	public String addAddressToCustomer(@RequestParam("email") String email,@RequestBody Address customerAddress) throws RuntimeException, InterruptedException, ExecutionException {
		return customerService.addAddressToTheExistingCustomer(email,customerAddress);
	}
	
	@PostMapping("/getCustomerCartsByEmail")
	public CompletableFuture<Cart> getCustomerCartsByEmail(@RequestParam("email")  String email) {
		return customerService.getCustomerCartsByEmail(email);
	}
	
}
