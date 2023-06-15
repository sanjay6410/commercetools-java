package com.example.demo.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.CustomerRestPasswordService;

@RestController
public class CustomerRestPasswordController {

	@Autowired
	private CustomerRestPasswordService resetPasswordService;
	
	@PostMapping("/resetPassword")
	public String resetPassword(@RequestParam("email") String email,@RequestParam("newPassword") String newPassword) throws InterruptedException, ExecutionException {
		resetPasswordService.resetPassword(email,newPassword);
		return "Reset Password Successfully";
	}
}
