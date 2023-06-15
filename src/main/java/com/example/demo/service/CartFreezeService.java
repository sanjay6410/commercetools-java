package com.example.demo.service;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commercetools.api.models.cart.Cart;
import com.commercetools.api.models.cart.CartUpdate;
import com.commercetools.api.models.cart.CartUpdateActionBuilder;
import com.commercetools.api.models.cart.CartUpdateBuilder;
import com.commercetools.api.models.customer.Customer;
import com.commercetools.api.models.order.PaymentInfo;
import com.example.demo.DAO.CartDao;
import com.example.demo.DAO.CustomerDao;
import com.example.demo.config.ProjectApiConfig;

@Service
public class CartFreezeService {

	@Autowired
	private ProjectApiConfig apiRoot;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private CartDao cartDao;
	
	public void updateCartStateToFrozen(String email) throws InterruptedException, ExecutionException {
	    Optional<Customer> cust=Optional.ofNullable(customerDao.getCustomerByEmail(email)
	    		.get()
	    		.getResults().get(0));
		if(cust.isPresent()) {
			Customer customer=cust.get();
			String custId=customer.getId();
			Cart cart=cartDao.getCartByCustomerById(custId);
			System.out.println("cartId "+cart.getId());
			PaymentInfo paymentInfo=cart.getPaymentInfo();
			if(paymentInfo != null && cart.getCartState().toString().equals("Active")) {
				System.out.println("payment is present");
				CartUpdate cartUpdate=CartUpdateBuilder.of()
						.version(cart.getVersion())
						.actions(CartUpdateActionBuilder.of().freezeCartBuilder()
						
								.build())
						
						.build();
				
				apiRoot.createApiClient().carts().withId(cart.getId()).post(cartUpdate).executeBlocking().getBody();
				System.out.println("Updated");
			}
		}else {
			String custNotFound="Customer not Found";
			System.out.println(custNotFound);
		}
		
		
	}
}
