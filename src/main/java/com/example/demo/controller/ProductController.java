package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ApiExtensionCheckService;
import com.example.demo.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ApiExtensionCheckService apiExtensionCheckService;
	
	@GetMapping("/categoryOfProduct")
	public ResponseEntity<?> categoryOfProduct(@RequestParam("sku") String sku) {
		List<String> hierarchy=productService.productHierarchy(sku);
		return ResponseEntity.ok(hierarchy);
	}
	
	@PostMapping("/apiExtensionCheckService")
	public void apiExtensionCheckService(@RequestParam("productId") String productId) {
		apiExtensionCheckService.checkExtensionProduct(productId);
	}
}
