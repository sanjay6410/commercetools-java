package com.example.demo.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CloudFunctionCheckController {

	

	@GetMapping("/helloCloud")
	public String cloudCheck() {
		return "super cloud";
	}
}
