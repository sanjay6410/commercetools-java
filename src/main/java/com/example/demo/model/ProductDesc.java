package com.example.demo.model;

import com.commercetools.api.models.common.LocalizedString;

public class ProductDesc {

	private String productKey;
	private LocalizedString productDesc;
	public String getProductKey() {
		return productKey;
	}
	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}
	public LocalizedString getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(LocalizedString productDesc) {
		this.productDesc = productDesc;
	}
	public ProductDesc() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductDesc(String productKey, LocalizedString productDesc) {
		super();
		this.productKey = productKey;
		this.productDesc = productDesc;
	}
	
	
}
