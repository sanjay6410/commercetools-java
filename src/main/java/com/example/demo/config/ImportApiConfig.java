package com.example.demo.config;

import org.springframework.context.annotation.Configuration;

import com.commercetools.importapi.client.ProjectApiRoot;
import com.commercetools.importapi.defaultconfig.ImportApiRootBuilder;
import com.commercetools.importapi.defaultconfig.ServiceRegion;

import io.vrap.rmf.base.client.oauth2.ClientCredentials;

@Configuration
public class ImportApiConfig {

	public ProjectApiRoot createApiClient() {
		final ProjectApiRoot apiRoot = ImportApiRootBuilder.of()
				.defaultClient(ClientCredentials.of().withClientId("bcbPfQGgvNX4EPFwuQpzsFsp").withClientSecret("ca4XjCRe8myaKO9IOK6V3w8kTb2c1qZc").build(),
						ServiceRegion.GCP_AUSTRALIA_SOUTHEAST1)
				.build("demo-ecommerce");

		return apiRoot;
	}
}
