package com.example.demo.DAO;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.commercetools.api.models.cart.Cart;
import com.commercetools.api.models.customer.Customer;
import com.commercetools.api.models.customer.CustomerPagedQueryResponse;
import com.example.demo.config.ProjectApiConfig;

import io.vrap.rmf.base.client.ApiHttpResponse;

@Component
public class CustomerDao {

	@Autowired
	private ProjectApiConfig apiConfig;
	
	public CompletableFuture<CustomerPagedQueryResponse> getAllCustomers(){
		//return apiConfig.createApiClient().customers().get().executeBlocking().getBody();
		return apiConfig.createApiClient().customers().get()
				.execute().thenApply(t -> t.getBody());
		
	}
	
//	public CompletableFuture<ProductPagedQueryResponse> queryWithSku(String sku) {
//		return byProjectKeyRequestBuilder.products()
//		.get()
//		.withWhere("masterData(staged(masterVariant(sku = :sku))) or masterData(staged(variants(sku = :sku)))")
//		.addPredicateVar("sku", sku)
//		.execute().thenApply(ApiHttpResponse::getBody);
//		}
	
	public CompletableFuture<Customer> getCustomerById(String customerId) {
		return 
//				apiConfig.createApiClient().customers().withId(customerId)
//				.get().executeBlocking().getBody();
				apiConfig.createApiClient().customers().withId(customerId).get()
				.execute().thenApply(t -> t.getBody()); 
	}
	
	public CompletableFuture<CustomerPagedQueryResponse> getCustomerByEmail(String email) {
		return 
//				apiConfig.createApiClient().customers().get()
//				//.withWhere("email= :emailVar").withPredicateVar("emailVar", email)
//				.withWhere("email=\""+email+"\"")
//				.executeBlocking().getBody();
				apiConfig.createApiClient().customers().get()
				.withWhere("email=\""+email+"\"").execute().thenApply(t ->t.getBody());
	}
	
	
	public CompletableFuture<Cart> getCustomerCartsByEmail(String email) {
	    return apiConfig.createApiClient().customers().get()
	            .withWhere("email=\"" + email + "\"").execute()
	            .thenApply(t -> {
	                Optional<Customer> customerOptional = Optional.ofNullable(t.getBody().getResults().get(0));
	                Customer customer = customerOptional.orElseThrow(() -> new RuntimeException("Customer not found"));
	                String customerId = customer.getId();
	                System.out.println("CustomerId: " + customerId);
	                return customerId;
	            })
	            .thenCompose(customerId -> apiConfig.createApiClient().carts().withCustomerId(customerId).get().execute())
	            .thenApply(t -> {
	                System.out.println(t.getBody());
	                return t.getBody();
	            });
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
