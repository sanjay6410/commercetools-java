package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
		Optional<Product> productOptional = productDao.getProductBySku(sku).getResults().stream().findFirst();
		Product product = productOptional.orElseThrow(() -> new RuntimeException("No product found for SKU: " + sku));

		List<String> hierarchy = new ArrayList<>();

		hierarchy.add(product.getMasterData().getCurrent().getName().get("en"));

		Optional<Category> categoryOptional = Optional.ofNullable(productDao.getCategoryById(product.getMasterData().getCurrent().getCategories().get(0).getId()));
		categoryOptional.ifPresent(category1 -> {
		    hierarchy.add(category1.getName().get("en"));

		    Optional<Category> category2 = Optional.ofNullable(productDao.getCategoryById(category1.getParent().getId()));
		    category2.ifPresent(cat2 -> {
		        hierarchy.add(cat2.getName().get("en"));

		        if (!cat2.getParent().getId().isEmpty()) {
		            Optional<Category> category3 = Optional.ofNullable(productDao.getCategoryById(cat2.getParent().getId()));
		            category3.ifPresent(cat3 -> hierarchy.add(cat3.getName().get("en")));
		        }
		    });
		});


		// System.out.println(hierarchy);
		Collections.reverse(hierarchy);
		return hierarchy;

	}

	
	
}
