package com.example.demo.model;

import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;

public class ArticlePriceinfo {

	@XmlElement(name = "item")
	 private List<Item> custompriceInfoitems;

	public List<Item> getPriceInfoitems() {
		return custompriceInfoitems;
	}

	public void setPriceInfoitems(List<Item> priceInfoitems) {
		this.custompriceInfoitems = priceInfoitems;
	}
	
	
	 
	 
}
