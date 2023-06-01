package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.CartFreezeService;

@RestController
public class CartFreezeController {

	@Autowired
	private CartFreezeService cartFreezeService;
	
	@PostMapping("/cartFreezeService")
	public void cartFreezeService(@RequestParam("email") String email) {
		cartFreezeService.updateCartStateToFrozen(email);
	}
}
