package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InventoryStockFeed {
    @JsonProperty("MessageType")
    public String messageType;
    public ArticleStockDetails articleStockDetails;

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public ArticleStockDetails getArticleStockDetails() {
        return articleStockDetails;
    }

    public void setArticleStockDetails(ArticleStockDetails articleStockDetails) {
        this.articleStockDetails = articleStockDetails;
    }

	@Override
	public String toString() {
		return "InventoryStockFeed [messageType=" + messageType + ", articleStockDetails=" + articleStockDetails
				+ ", getMessageType()=" + getMessageType() + ", getArticleStockDetails()=" + getArticleStockDetails()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
    
    
}
