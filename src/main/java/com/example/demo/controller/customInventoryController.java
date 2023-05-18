package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.InventoryService;

@RestController
public class customInventoryController {

	@Autowired
	private InventoryService inventoryService;
	
	@PostMapping("/uploadJsonFileAndCreateInventory")
	public ResponseEntity<?> uploadJsonFileAndCreateInventory(@RequestParam("file") MultipartFile file) {
	try {
		inventoryService.createInventoryFromJson(file);	
		return ResponseEntity.ok("Inventory Created Successfully");
	}catch(Exception e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while creating the inventory "+e);
	}
	}
	
}
