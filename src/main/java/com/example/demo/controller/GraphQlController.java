package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.commercetools.api.models.customer.Customer;
import com.example.demo.DAO.CustomerDao;
import com.example.demo.service.CustomerService;

@Controller
public class GraphQlController {

	@Autowired
	private CustomerDao custDao;
	
	@Autowired
	private CustomerService customerService;

	@QueryMapping
	public String firstQuery() {
		return "SANJAY";

	}

	@QueryMapping
	public String fullName(@Argument String firstName, @Argument String secondName) {
		return firstName + " " + secondName;
	}
	
	@QueryMapping
	public Customer getCustomerByEmail(@Argument String email) throws InterruptedException, ExecutionException {
		List<Customer> customers = custDao.getCustomerByEmail(email)
				.get()
				.getResults();
	    Optional<Customer> customer = customers.isEmpty() ? Optional.empty() : Optional.ofNullable(customers.get(0));
	    
	    return customer.orElseThrow(() -> new IllegalArgumentException("Customer not found for email: " + email));
	}
	
	@QueryMapping
	public List<Customer> getAllCustomers() throws InterruptedException, ExecutionException{
		List<Customer> customers= custDao.getAllCustomers().get().getResults();
		return customers;
		
	}
	
	@MutationMapping
	public Customer updateFirstName(@Argument String customerEmail,@Argument String customerFirstName) throws InterruptedException, ExecutionException {
		return customerService.updateFirstName(customerEmail, customerFirstName);
	}
	
	
	
	
	
	
	
	
	
	
	
}
