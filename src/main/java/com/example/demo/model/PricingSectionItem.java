package com.example.demo.model;

import jakarta.xml.bind.annotation.XmlElement;

public class PricingSectionItem {

	   @XmlElement(name = "price_type")
	    private String custompriceType;
	    private String country;
	    private String channel;
	    @XmlElement(name = "final_price")
	    private String customfinalPrice;
		public PricingSectionItem() {
			super();
			// TODO Auto-generated constructor stub
		}
		public PricingSectionItem(String priceType, String country, String channel, String finalPrice) {
			super();
			this.custompriceType = priceType;
			this.country = country;
			this.channel = channel;
			this.customfinalPrice = finalPrice;
		}
		
		@Override
		public String toString() {
			return "PricingSectionItem [custompriceType=" + custompriceType + ", country=" + country + ", channel="
					+ channel + ", customfinalPrice=" + customfinalPrice + "]";
		}
		public String getPriceType() {
			return custompriceType;
		}
		public void setPriceType(String priceType) {
			this.custompriceType = priceType;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public String getChannel() {
			return channel;
		}
		public void setChannel(String channel) {
			this.channel = channel;
		}
		public String getFinalPrice() {
			return customfinalPrice;
		}
		public void setFinalPrice(String finalPrice) {
			this.customfinalPrice = finalPrice;
		}
	    
	    
}
