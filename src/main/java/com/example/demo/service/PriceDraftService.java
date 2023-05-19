package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commercetools.api.models.common.PriceDraft;
import com.commercetools.api.models.common.PriceDraftBuilder;
import com.example.demo.config.ProjectApiConfig;

@Service
public class PriceDraftService {

	@Autowired
	private ProjectApiConfig apiConfig;
	public void createPrice() {
		PriceDraft priceDraft=PriceDraftBuilder.of()
				.value(t -> t.currencyCode("USD").centAmount(100L))
				.build();
		
		//apiConfig.createApiClient().pri
	}
}
