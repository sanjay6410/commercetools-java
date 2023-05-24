package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.commercetools.api.models.cart.Cart;
import com.commercetools.api.models.customer.CustomerSignInResult;
import com.example.demo.service.CartMergeService;

@RestController
public class CartMergeController {

	@Autowired
	private CartMergeService cartMergeService;
	
	//CUSTOMER CREATION FOR ANONYMOUS CART
	@PostMapping("/mergeCart")
	public CustomerSignInResult mergeCart(@RequestParam("email") String email,@RequestParam("anonymousId")
	                                                            String anonymousId,@RequestParam("password") String password) {
	
		return cartMergeService.mergeCartWithAnonymousCart(email,password, anonymousId);
		//return "true";
	}
}
