package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.commercetools.api.models.cart.Cart;
import com.commercetools.api.models.type.CustomFieldsDraft;
import com.example.demo.DAO.CustomObjectDao;
import com.example.demo.service.CartService;

@RestController
public class CartController {

	@Autowired
	private CartService service;
	
	@Autowired
	private CustomObjectDao customObjectDao;

	@PostMapping("/createCart")
	public ResponseEntity<?> createCart(@RequestParam("customerId") String customerId, @RequestParam("sku") String sku,
			@RequestParam("qty") Long qty) {
		try {
		Cart cart=service.createCart(customerId, sku, qty);
		return ResponseEntity.ok(cart);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Cart Not Created " +e.getMessage());
		}
	}

	@GetMapping("/getCartByCustomerFirstName")
	public ResponseEntity<?> getCartByCustomerFirstName(@RequestParam("firstName") String firstName) {
		try {
		Cart customerCart = service.getCartByCustomerFirstName(firstName);
		return ResponseEntity.ok(customerCart);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Cart Not Found with "+firstName);
		}

	}
	
	
	@PostMapping("/createOrderFromCart")
	public ResponseEntity<?> createOrderFromCart(@RequestParam("cartId") String cartId) {
		try {
		return ResponseEntity.ok(service.createOrderWithCart(cartId));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Order Not Created "+e.getMessage());
		}
	}
	
	@PostMapping("/createCartForAnonymous")
	public ResponseEntity<?> createCartForAnonymous( @RequestParam("sku") String sku,
			@RequestParam("qty") Long qty) {
		try {
		Cart cart=service.createCartForAnonymous(sku, qty);
		com.commercetools.api.models.custom_object.CustomObject customObject=
				customObjectDao.getCustomObjectByKey("AnonmousId").getResults().get(0).get();
		Object value= customObject.getValue();
		System.out.println(customObject.getValue());
		
		return ResponseEntity.ok(cart);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Cart Not Created " +e.getMessage());
		}
	}

	@PostMapping("/updateCartWithPayment")
	public Cart updateCartWithPayment(String cartId) {
		return service.updateCartWithPayment(cartId);
	}
	
	@PostMapping("/createCartWithCustomField")
	public ResponseEntity<?> createCartWithCustomField(@RequestParam("customerId") String customerId, @RequestParam("sku") String sku,
			@RequestParam("qty") Long qty,@RequestParam("fieldName") String fieldName,@RequestParam("value") String value) {
		try {
		Cart cart=service.createCartWithCustomField(customerId, sku, qty,value,fieldName);
		return ResponseEntity.ok(cart);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Cart Not Created " +e.getMessage());
		}
	}
	
	
	
	
	
	
	
	
	
}
