package com.example.demo.controller;

import java.util.concurrent.ExecutionException;

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
	public void cartFreezeService(@RequestParam("email") String email) throws InterruptedException, ExecutionException {
		cartFreezeService.updateCartStateToFrozen(email);
	}
}
