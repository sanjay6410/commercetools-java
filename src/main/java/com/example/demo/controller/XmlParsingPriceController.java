package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.XmlParsingPriceService;

@RestController
public class XmlParsingPriceController {

	@Autowired
	private XmlParsingPriceService xmlService;
	
	@PostMapping("/updatePriceUsingXmlFile")
	public void updatePriceUsingXmlFile(@RequestParam("file") MultipartFile file) {
		xmlService.xmlParsingForPrice(file);
	}
}
