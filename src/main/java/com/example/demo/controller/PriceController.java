package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.PriceDraftService;

@RestController
public class PriceController {

	@Autowired
	private PriceDraftService priceDraftService;
	
	
	@PostMapping("/createPrice")
	public void createPrice() {
		priceDraftService.createPrice();
	}
}
