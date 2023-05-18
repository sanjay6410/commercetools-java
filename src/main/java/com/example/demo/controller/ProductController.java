package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/categoryOfProduct")
	public ResponseEntity<?> categoryOfProduct(@RequestParam("sku") String sku) {
		List<String> hierarchy=productService.productHierarchy(sku);
		return ResponseEntity.ok(hierarchy);
	}
}
