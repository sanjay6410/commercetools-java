package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.commercetools.importapi.models.importcontainers.ImportContainer;
import com.example.demo.service.ImportApiService;

@RestController
public class ImportApiController {

	@Autowired
	private ImportApiService importApiService;
	
	@PostMapping("/createContainer")
	public ImportContainer createContainer(@RequestParam("key") String key) {
		return importApiService.createContainer(key);
	}
	
	@PostMapping("/importCustomerData")
	public String importCustomerData() {
		importApiService.importCustomerData();
		return "Customer Data Imported Successfully";
	}
	
	@PostMapping("/uploadExcelFile")
	public String checkExcel(@RequestParam("file") MultipartFile file) {
		importApiService.checkExcelData(file);
		return "Customer Data Imported Successfully";
	}
}
