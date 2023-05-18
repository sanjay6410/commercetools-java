package com.example.demo.model;

public class ArticlePriceDetails {

	private ArticlePriceinfo articlePriceinfo;

	public ArticlePriceDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArticlePriceDetails(ArticlePriceinfo articlePriceinfo) {
		super();
		this.articlePriceinfo = articlePriceinfo;
	}

	@Override
	public String toString() {
		return "ArticlePriceDetails [articlePriceinfo=" + articlePriceinfo + ", getArticlePriceinfo()="
				+ getArticlePriceinfo() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	public ArticlePriceinfo getArticlePriceinfo() {
		return articlePriceinfo;
	}

	public void setArticlePriceinfo(ArticlePriceinfo articlePriceinfo) {
		this.articlePriceinfo = articlePriceinfo;
	}
	
	
}
