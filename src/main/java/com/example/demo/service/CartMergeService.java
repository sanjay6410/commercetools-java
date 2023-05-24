package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commercetools.api.models.cart.CartResourceIdentifier;
import com.commercetools.api.models.cart.CartResourceIdentifierBuilder;
import com.commercetools.api.models.customer.AnonymousCartSignInMode;
import com.commercetools.api.models.customer.CustomerDraft;
import com.commercetools.api.models.customer.CustomerDraftBuilder;
import com.commercetools.api.models.customer.CustomerSignInResult;
import com.commercetools.api.models.customer.CustomerSignin;
import com.commercetools.api.models.customer.CustomerSigninBuilder;
import com.example.demo.DAO.CustomerDao;
import com.example.demo.config.ProjectApiConfig;

@Service
public class CartMergeService {

	@Autowired
	private ProjectApiConfig apiConfig;
	
	@Autowired
	private CustomerDao customerDao;
	
	public void cartMerge(String email,String password,String anonymousCartId) {
	
		
		CartResourceIdentifier cartIdentifier=CartResourceIdentifierBuilder.of()
				.id(anonymousCartId)
				.build();
		CustomerDraft customerDraft=CustomerDraftBuilder.of().email(email)
				.password(password)
				.anonymousCart(cartIdentifier)
				
				.build();
//		AnonymousCartSignInMode cartMergeSignIn=
//				AnonymousCartSignInMode.MERGE_WITH_EXISTING_CUSTOMER_CART;
		
		
		apiConfig.createApiClient().customers().post(customerDraft).executeBlocking().getBody();
				
	}
	
	public CustomerSignInResult mergeCartWithAnonymousCart(String email,String password,String anonymousCartId) {
		CartResourceIdentifier cartIdentifier=CartResourceIdentifierBuilder.of()
				.id(anonymousCartId)
				.build();
		CustomerSignin custSignIn=CustomerSigninBuilder.of().anonymousCart(cartIdentifier)
				.anonymousCartSignInMode(AnonymousCartSignInMode.MERGE_WITH_EXISTING_CUSTOMER_CART)
				.email(email)
				.password(password)
				.build();
		return apiConfig.createApiClient().login().post(custSignIn).executeBlocking().getBody();
	}
	

	
	
	
	
	
	
	
}
