package com.example.demo.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.commercetools.api.models.customer.Customer;
import com.commercetools.api.models.customer.CustomerPagedQueryResponse;
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
	
	public CustomerPagedQueryResponse getCustomerByEmail(String email) {
		return apiConfig.createApiClient().customers().get().withWhere("email= :emailVar").withPredicateVar("emailVar", email)
				.executeBlocking().getBody();
	}
}
