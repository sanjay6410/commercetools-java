package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionStage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commercetools.api.models.category.Category;
import com.commercetools.api.models.product.Product;
import com.example.demo.DAO.ProductDao;
import com.example.demo.config.ProjectApiConfig;

@Service
public class ProductService {

	@Autowired
	private ProjectApiConfig apiConfig;
	
	@Autowired
	private ProductDao productDao;
	
	public List<String> productHierarchy(String sku) {
	    Product product = productDao.getProductBySku(sku).getResults().get(0);
	    List<String> hierarchy = new ArrayList<>();
	    
	    hierarchy.add(product.getMasterData().getCurrent().getName().get("en"));

	    Category category1 = productDao.getCategoryById(product.getMasterData().getCurrent().getCategories().get(0).getId());
	    hierarchy.add(category1.getName().get("en"));

	    Category category2 = productDao.getCategoryById(category1.getParent().getId());
	    hierarchy.add(category2.getName().get("en"));

	    if (!category2.getParent().getId().isEmpty()) {
	        Category category3 = productDao.getCategoryById(category2.getParent().getId());
	        hierarchy.add(category3.getName().get("en"));
	    }

	   // System.out.println(hierarchy);
	    return hierarchy;
	}

	
	
}
