package com.example.demo.model;

public class ArticlePriceinfo {

	 private String sku;
	 private PricingSection pricingSection;
	public ArticlePriceinfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ArticlePriceinfo(String sku, PricingSection pricingSection) {
		super();
		this.sku = sku;
		this.pricingSection = pricingSection;
	}
	@Override
	public String toString() {
		return "ArticlePriceinfo [sku=" + sku + ", pricingSection=" + pricingSection + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public PricingSection getPricingSection() {
		return pricingSection;
	}
	public void setPricingSection(PricingSection pricingSection) {
		this.pricingSection = pricingSection;
	}
	 
	 
}
