package com.example.demo.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.commercetools.api.models.customer.Customer;
import com.example.demo.config.ProjectApiConfig;

@Component
public class CustomerDao {

	@Autowired
	private ProjectApiConfig apiConfig;
	
	public Customer getCustomerById(String customerId) {
		return 
				apiConfig.createApiClient().customers().withId(customerId)
				.get().executeBlocking().getBody();
	}
}
