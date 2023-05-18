package com.example.demo.service;

import java.io.File;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.config.ProjectApiConfig;
import com.example.demo.model.SapbridgeArticlePrice;

@Service
public class XmlParsingPriceService {

	@Autowired
	private ProjectApiConfig apiConfig;
	
	public void xmlParsingForPrice(MultipartFile file) {
		try {
			byte[] xmlFileData=file.getBytes();
			String strXmlData=new String(xmlFileData,StandardCharsets.UTF_8);
			//System.out.println(strXmlData);
			
			JAXBContext jaxbContext=JAXBContext.newInstance(SapbridgeArticlePrice.class);
			Unmarshaller unMarshaller=jaxbContext.createUnmarshaller();
			SapbridgeArticlePrice sapbridgeArticlePrice=
					(SapbridgeArticlePrice) unMarshaller.unmarshal(new StringReader(strXmlData));
			System.out.println(sapbridgeArticlePrice.getSessionID());
			
		}catch(Exception e) {
			throw new RuntimeException("error" +e);
		}
		
	}
}
