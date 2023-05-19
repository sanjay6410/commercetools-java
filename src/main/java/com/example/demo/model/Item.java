package com.example.demo.model;

import jakarta.xml.bind.annotation.XmlElement;

public class Item {
//    public String sku;
//    public SiteInfo site_info;
//
//    
//    public Item(String sku, SiteInfo site_info) {
//		super();
//		this.sku = sku;
//		this.site_info = site_info;
//	}
//
//	public Item() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	public String getSku() {
//        return sku;
//    }
//
//    public void setSku(String sku) {
//        this.sku = sku;
//    }
//
//    public SiteInfo getSite_info() {
//        return site_info;
//    }
//
//    public void setSite_info(SiteInfo site_info) {
//        this.site_info = site_info;
//    }
//
//	@Override
//	public String toString() {
//		return "Item [sku=" + sku + ", site_info=" + site_info + ", getSku()=" + getSku() + ", getSite_info()="
//				+ getSite_info() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
//				+ super.toString() + "]";
//	}
	//FOR XML
	private String customsku;
	@XmlElement(name="pricing_section")
	private PricingSection custompricingSection;
	public String getSku() {
		return customsku;
	}
	public void setSku(String sku) {
		this.customsku = sku;
	}
	public PricingSection getPricingSection() {
		return custompricingSection;
	}
	public void setPricingSection(PricingSection pricingSection) {
		this.custompricingSection = pricingSection;
	}
	@Override
	public String toString() {
		return "Item [customsku=" + customsku + ", custompricingSection=" + custompricingSection + "]";
	}
	
    
}
