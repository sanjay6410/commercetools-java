package com.example.demo.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.commercetools.api.models.cart.Cart;
import com.commercetools.api.models.cart.CartPagedQueryResponse;
import com.commercetools.api.models.customer.CustomerPagedQueryResponse;
import com.example.demo.config.ProjectApiConfig;

@Component
public class CartDao {

	@Autowired
	private ProjectApiConfig apiConfig;

	public CartPagedQueryResponse getAllCarts() {
		return apiConfig.createApiClient().carts().get().executeBlocking().getBody();
	}

	public CustomerPagedQueryResponse getCustomerByFirstName(String firstName) {
		return apiConfig.createApiClient().customers().get().withWhere("firstName= :firstNameVar")
				.withPredicateVar("firstNameVar", firstName).executeBlocking().getBody();
	}
	
	public Cart getCartById(String cartId) {
		Cart cartWithId=apiConfig.createApiClient().carts().withId(cartId)
				.get().executeBlocking().getBody();
		return cartWithId;
	}
}
