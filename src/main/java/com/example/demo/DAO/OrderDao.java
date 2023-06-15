package com.example.demo.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.commercetools.api.models.order.Order;
import com.example.demo.config.ProjectApiConfig;

@Component
public class OrderDao {

	@Autowired
	private ProjectApiConfig apiConfig;
	
	public Order getOrderById(String orderId) {
		return apiConfig.createApiClient().orders().withId(orderId).get().executeBlocking().getBody();
//		return apiConfig.createApiClient().orders()
				//.withId(orderId).get().execute().thenAccept(t -> t.getBody());
				
		
	}
	
}
