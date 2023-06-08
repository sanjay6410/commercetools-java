package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.commercetools.api.models.common.Address;
import com.example.demo.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/addAddressToExistingCustomer")
	public String addAddressToCustomer(@RequestParam("email") String email,@RequestBody Address customerAddress) {
		return customerService.addAddressToTheExistingCustomer(email,customerAddress);
	}
}
