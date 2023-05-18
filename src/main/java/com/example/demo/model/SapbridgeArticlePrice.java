package com.example.demo.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SapbridgeArticlePrice {

	//@XmlElement(name = "sessionId")
	private String sessionID;
    private String idocNumber;
    private String messageType;
    private ArticlePriceDetails articlePriceDetails;
    private String xmlnsNs0;
    private String prefix;
	public SapbridgeArticlePrice() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SapbridgeArticlePrice(String sessionID, String idocNumber, String messageType,
			ArticlePriceDetails articlePriceDetails, String xmlnsNs0, String prefix) {
		super();
		this.sessionID = sessionID;
		this.idocNumber = idocNumber;
		this.messageType = messageType;
		this.articlePriceDetails = articlePriceDetails;
		this.xmlnsNs0 = xmlnsNs0;
		this.prefix = prefix;
	}
	public String getSessionID() {
		return sessionID;
	}
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	public String getIdocNumber() {
		return idocNumber;
	}
	public void setIdocNumber(String idocNumber) {
		this.idocNumber = idocNumber;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public ArticlePriceDetails getArticlePriceDetails() {
		return articlePriceDetails;
	}
	public void setArticlePriceDetails(ArticlePriceDetails articlePriceDetails) {
		this.articlePriceDetails = articlePriceDetails;
	}
	public String getXmlnsNs0() {
		return xmlnsNs0;
	}
	public void setXmlnsNs0(String xmlnsNs0) {
		this.xmlnsNs0 = xmlnsNs0;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	@Override
	public String toString() {
		return "SapbridgeArticlePrice [sessionID=" + sessionID + ", idocNumber=" + idocNumber + ", messageType="
				+ messageType + ", articlePriceDetails=" + articlePriceDetails + ", xmlnsNs0=" + xmlnsNs0 + ", prefix="
				+ prefix + "]";
	}
    
    
}
