package com.example.demo.model;

import java.util.List;

public class JsonDataModel {
	
	
	public JsonDataModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	private InventoryStockFeed InventoryStockFeed;
	public InventoryStockFeed getInventoryStockFeed() {
		return InventoryStockFeed;
	}
	public void setInventoryStockFeed(InventoryStockFeed inventoryStockFeed) {
		InventoryStockFeed = inventoryStockFeed;
	}
	public JsonDataModel(com.example.demo.model.JsonDataModel.InventoryStockFeed inventoryStockFeed) {
		super();
		InventoryStockFeed = inventoryStockFeed;
	}
	
	public static class InventoryStockFeed{
		private String MessageType;
		private articleStockDetails articleStockDetails;
		public InventoryStockFeed(String messageType,
				com.example.demo.model.JsonDataModel.articleStockDetails articleStockDetails) {
			super();
			MessageType = messageType;
			this.articleStockDetails = articleStockDetails;
		}
		public InventoryStockFeed() {
			super();
			// TODO Auto-generated constructor stub
		}
		public String getMessageType() {
			return MessageType;
		}
		public void setMessageType(String messageType) {
			MessageType = messageType;
		}
		public articleStockDetails getArticleStockDetails() {
			return articleStockDetails;
		}
		public void setArticleStockDetails(articleStockDetails articleStockDetails) {
			this.articleStockDetails = articleStockDetails;
		}
		
	}
	public static class articleStockDetails{
		private article_stockinfo article_stockinfo;

		public articleStockDetails(com.example.demo.model.JsonDataModel.article_stockinfo article_stockinfo) {
			super();
			this.article_stockinfo = article_stockinfo;
		}

		public articleStockDetails() {
			super();
			// TODO Auto-generated constructor stub
		}

		public article_stockinfo getArticle_stockinfo() {
			return article_stockinfo;
		}

		public void setArticle_stockinfo(article_stockinfo article_stockinfo) {
			this.article_stockinfo = article_stockinfo;
		}
		
		
	}
	public static class article_stockinfo{
		private List<item> items;

		public article_stockinfo() {
			super();
			// TODO Auto-generated constructor stub
		}

		public article_stockinfo(List<item> items) {
			super();
			this.items = items;
		}

		public List<item> getItems() {
			return items;
		}

		public void setItems(List<item> items) {
			this.items = items;
		}
		
		
	}
	public static class item{
		private String sku;
		private site_info site_info;
		public item() {
			super();
			// TODO Auto-generated constructor stub
		}
		public item(String sku, com.example.demo.model.JsonDataModel.site_info site_info) {
			super();
			this.sku = sku;
			this.site_info = site_info;
		}
		public String getSku() {
			return sku;
		}
		public void setSku(String sku) {
			this.sku = sku;
		}
		public site_info getSite_info() {
			return site_info;
		}
		public void setSite_info(site_info site_info) {
			this.site_info = site_info;
		}
		
	}
	public static class site_info{
		private List<itemSite> item;

		public site_info() {
			super();
			// TODO Auto-generated constructor stub
		}

		public List<itemSite> getItem() {
			return item;
		}

		public void setItem(List<itemSite> item) {
			this.item = item;
		}

		public site_info(List<itemSite> item) {
			super();
			this.item = item;
		}

		
		
	}
	public static class itemSite{
		private String site;
		private String qty;
		public itemSite(String site, String qty) {
			super();
			this.site = site;
			this.qty = qty;
		}
		public itemSite() {
			super();
			// TODO Auto-generated constructor stub
		}
		public String getSite() {
			return site;
		}
		public void setSite(String site) {
			this.site = site;
		}
		public String getQty() {
			return qty;
		}
		public void setQty(String qty) {
			this.qty = qty;
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}