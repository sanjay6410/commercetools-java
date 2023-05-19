package com.example.demo.model;

import jakarta.xml.bind.annotation.XmlElement; 
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "sapbridgeArticlePrice")
public class SapbridgeArticlePrice {

	@XmlElement(name = "sessionId")
	private String sessionId;
	@XmlElement(name = "IDOCNumber")
    private String IDOCNumber;
	@XmlElement(name = "MessageType")
    private String MessageType;
	@XmlElement(name="articlePriceDetails")
    private ArticlePriceDetails customarticlePriceDetails;
    private String xmlnsNs0;
    private String prefix;
	public SapbridgeArticlePrice() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SapbridgeArticlePrice(String sessionID, String idocNumber, String messageType,
			ArticlePriceDetails articlePriceDetails, String xmlnsNs0, String prefix) {
		super();
		this.sessionId = sessionID;
		this.IDOCNumber = idocNumber;
		this.MessageType = messageType;
		this.customarticlePriceDetails = articlePriceDetails;
		this.xmlnsNs0 = xmlnsNs0;
		this.prefix = prefix;
	}
	public String getSessionID() {
		return sessionId;
	}
	public void setSessionID(String sessionID) {
		this.sessionId = sessionID;
	}
	public String getIdocNumber() {
		return IDOCNumber;
	}
	public void setIdocNumber(String idocNumber) {
		this.IDOCNumber = idocNumber;
	}
	public String getMessageType() {
		return MessageType;
	}
	public void setMessageType(String messageType) {
		this.MessageType = messageType;
	}
	public ArticlePriceDetails getArticlePriceDetails() {
		return customarticlePriceDetails;
	}
	public void setArticlePriceDetails(ArticlePriceDetails articlePriceDetails) {
		this.customarticlePriceDetails = articlePriceDetails;
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
	
    
    
}
