package com.example.demo.model;

public class PricingSectionItem {

	    private String priceType;
	    private String country;
	    private String channel;
	    private String finalPrice;
		public PricingSectionItem() {
			super();
			// TODO Auto-generated constructor stub
		}
		public PricingSectionItem(String priceType, String country, String channel, String finalPrice) {
			super();
			this.priceType = priceType;
			this.country = country;
			this.channel = channel;
			this.finalPrice = finalPrice;
		}
		@Override
		public String toString() {
			return "PricingSectionItem [priceType=" + priceType + ", country=" + country + ", channel=" + channel
					+ ", finalPrice=" + finalPrice + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
					+ ", toString()=" + super.toString() + "]";
		}
		public String getPriceType() {
			return priceType;
		}
		public void setPriceType(String priceType) {
			this.priceType = priceType;
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
			return finalPrice;
		}
		public void setFinalPrice(String finalPrice) {
			this.finalPrice = finalPrice;
		}
	    
	    
}
