package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commercetools.api.models.order.Order;
import com.commercetools.api.models.order.OrderUpdate;
import com.commercetools.api.models.payment.PaymentResourceIdentifier;
import com.commercetools.api.models.payment.PaymentResourceIdentifierBuilder;
import com.example.demo.DAO.OrderDao;
import com.example.demo.config.ProjectApiConfig;

@Service
public class OrderService {

	@Autowired
	private ProjectApiConfig apiConfig;
	
	@Autowired
	private OrderDao orderDao;
	
	public void updateOrderWithPayment(String orderId) {
		Order order=orderDao.getOrderById(orderId);
		PaymentResourceIdentifier paymentIdentifier=PaymentResourceIdentifierBuilder.of()
				.id("4ea4a171-1f8b-467f-ae7a-b948b450ab84")
				.build();
		OrderUpdate orderUpdate=OrderUpdate.builder().version(order.getVersion())
				.plusActions(t -> t.addPaymentBuilder().payment(paymentIdentifier)).build();
		
		Order orderPost=apiConfig.createApiClient().orders().withId(orderId).post(orderUpdate).executeBlocking().getBody();
				
	}
}
