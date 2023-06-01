package com.example.demo.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commercetools.api.models.order.Order;
import com.commercetools.api.models.order.OrderUpdate;
import com.commercetools.api.models.order.OrderUpdateActionBuilder;
import com.commercetools.api.models.order.OrderUpdateBuilder;
import com.commercetools.api.models.payment.PaymentResourceIdentifier;
import com.commercetools.api.models.payment.PaymentResourceIdentifierBuilder;
import com.commercetools.api.models.type.TypeResourceIdentifier;
import com.commercetools.api.models.type.TypeResourceIdentifierBuilder;
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
	
	public void updateOrderWithCustomType(String orderId) {
		Order order=orderDao.getOrderById(orderId);
		
		UUID uuid=UUID.randomUUID();
		
		TypeResourceIdentifier resourceIdentifier=TypeResourceIdentifierBuilder.of()
				.key("OrderPlacedType")
				.build();
		
		OrderUpdate orderUpdate=OrderUpdateBuilder.of()
				.version(order.getVersion())
				.actions(OrderUpdateActionBuilder.of().setCustomTypeBuilder()
						.type(resourceIdentifier)
						.fields(t -> t.addValue("Backend-confirmation-code","BE-code-"+ uuid.toString()))
						.build())
				.build();
		apiConfig.createApiClient().orders().withId(orderId).post(orderUpdate).executeBlocking().getBody();
	}
}
