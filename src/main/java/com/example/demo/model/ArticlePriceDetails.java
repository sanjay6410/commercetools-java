package com.example.demo.model;

import jakarta.xml.bind.annotation.XmlElement;

public class ArticlePriceDetails {

	@XmlElement(name = "article_priceinfo")
	private ArticlePriceinfo customarticlePriceinfo;

	public ArticlePriceDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArticlePriceDetails(ArticlePriceinfo articlePriceinfo) {
		super();
		this.customarticlePriceinfo = articlePriceinfo;
	}


	public ArticlePriceinfo getArticlePriceinfo() {
		return customarticlePriceinfo;
	}

	public void setArticlePriceinfo(ArticlePriceinfo articlePriceinfo) {
		this.customarticlePriceinfo = articlePriceinfo;
	}
	
	
}
