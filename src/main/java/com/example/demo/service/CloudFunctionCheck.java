package com.example.demo.service;

import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;

public class CloudFunctionCheck implements HttpFunction {

	@Override
	public void service(HttpRequest request, HttpResponse response) throws Exception {
	    response.getWriter().write("sanjjjjjjjjjaaaaaaaaaaayyyyyyyyyyy");;
		System.out.println("Hello From Cloud Function");
		response.setStatusCode(200);
		
	}

}
