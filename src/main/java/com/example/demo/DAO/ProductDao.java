package com.example.demo.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.commercetools.api.models.category.Category;
import com.commercetools.api.models.product.ProductPagedQueryResponse;
import com.example.demo.config.ProjectApiConfig;

@Component
public class ProductDao {

	@Autowired
	private ProjectApiConfig apiConfig;
	
	public ProductPagedQueryResponse getProductBySku(String sku) {
	    return apiConfig.createApiClient().products().get()
	            .withWhere("masterData(current(masterVariant(sku=\"" + sku + "\")))")
	            .executeBlocking()
	            .getBody();
	}
	
	public ProductPagedQueryResponse getProductVarientsBySku(String sku) {
	    return apiConfig.createApiClient().products().get()
	            .withWhere("masterData(current(variants(sku=\"" + sku + "\")))")
	            .executeBlocking()
	            .getBody();
	}

	public Category getCategoryById(String id) {
		return apiConfig.createApiClient().categories().withId(id).get().executeBlocking().getBody();
	}


}
