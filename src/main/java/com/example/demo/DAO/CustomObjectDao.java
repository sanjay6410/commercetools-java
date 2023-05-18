package com.example.demo.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.commercetools.api.models.custom_object.CustomObjectPagedQueryResponse;
import com.example.demo.config.ProjectApiConfig;

@Component
public class CustomObjectDao {

	@Autowired
	private ProjectApiConfig apiConfig;
	
	public CustomObjectPagedQueryResponse getCustomObjectByKey(String key) {
		return apiConfig.createApiClient().customObjects().get()
				.withWhere("key= :keyVar").withPredicateVar("keyVar", key)
				.executeBlocking().getBody();
	}
	


}
