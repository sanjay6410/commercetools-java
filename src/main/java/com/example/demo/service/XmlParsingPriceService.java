package com.example.demo.service;

import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.commercetools.api.models.channel.ChannelResourceIdentifier;
import com.commercetools.api.models.channel.ChannelResourceIdentifierBuilder;
import com.commercetools.api.models.common.PriceDraft;
import com.commercetools.api.models.common.PriceDraftBuilder;
import com.commercetools.api.models.product.Product;
import com.commercetools.api.models.product.ProductUpdate;
import com.commercetools.api.models.product.ProductUpdateActionBuilder;
import com.commercetools.api.models.product.ProductUpdateBuilder;
import com.example.demo.DAO.ProductDao;
import com.example.demo.config.ProjectApiConfig;
import com.example.demo.model.Item;
import com.example.demo.model.PricingSection;
import com.example.demo.model.PricingSectionItem;
import com.example.demo.model.SapbridgeArticlePrice;
import com.example.demo.model.UserXmlCheck;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

@Service
public class XmlParsingPriceService {

	@Autowired
	private ProjectApiConfig apiConfig;
	
	@Autowired
	private ProductDao productDao;
	
	public void xmlParsingForPrice(MultipartFile file) {
		try {
			byte[] xmlFileData=file.getBytes();
			String strXmlData=new String(xmlFileData,StandardCharsets.UTF_8);
			//System.out.println(strXmlData);
			
			JAXBContext jaxbContext=(JAXBContext) JAXBContext.newInstance(SapbridgeArticlePrice.class);
			Unmarshaller unMarshaller=jaxbContext.createUnmarshaller();
			SapbridgeArticlePrice sapbridgeArticlePrice=
					(SapbridgeArticlePrice) unMarshaller.unmarshal(new StringReader(strXmlData));
			List<Item> priceInfoItems=sapbridgeArticlePrice.getArticlePriceDetails().getArticlePriceinfo().getPriceInfoitems();
			//System.out.println(priceInfoItems);
			for(Item item:priceInfoItems) {
				String sku=item.getSku();
				//System.out.println(sku);
				PricingSection section=item.getPricingSection();
				PricingSectionItem[] skuitems=section.getItem();
				for(int i=0;i<sku.length() && i< skuitems.length; i++) {
					PricingSectionItem itemPriceSection=skuitems[i];
					System.out.println(sku+" "+itemPriceSection.getChannel()+" "+itemPriceSection.getFinalPrice());
					productUpdate(sku,itemPriceSection.getChannel(),itemPriceSection.getFinalPrice(),itemPriceSection.getCountry());
				}
			}
		}catch(Exception e) {
			throw new RuntimeException("error" +e);
		}
		
	}
	
	
	public void productUpdate(String sku, String channel, String price, String country) {
	    String currency_Code ;
	    if (country.equals("DE")) {
	        currency_Code = "EUR";
	    } else if (country.equals("US")) {
	        currency_Code = "USD";
	    } else {
	        currency_Code = "EUR";
	    }
//	    
	    // Check if results list is empty before accessing elements
	    List<Product> results = productDao.getProductVarientsBySku(sku).getResults();
	    if (results.isEmpty()) {
	        throw new RuntimeException("Product not found for SKU: " + sku);
	    }
	    String priceId = null;
	    
	    
	    Product product = results.get(0);
//	    System.out.println(product.getVersion());
//	    System.out.println(product.getMasterData().getCurrent().getMasterVariant().getPrices().get(0).getId());
//	    if(currency_Code.equals("EUR")) {
//	    	priceId=product.getMasterData().getCurrent().getVariants().get(0).getPrices().get(0).getId();
//	    }else if(currency_Code.equals("USD")) {
//	    	priceId=product.getMasterData().getCurrent().getVariants().get(0).getPrices().get(3).getId();
//	    }
	    
	    ChannelResourceIdentifier channelResourceIdentifier = ChannelResourceIdentifierBuilder.of()
	    		
	            .key("sunrise-store-"+channel)
	            .build();
	    
	    PriceDraft priceDraft = PriceDraftBuilder.of()
	            .value(t -> t.currencyCode(currency_Code).centAmount(Math.round(Double.parseDouble(price)*100)))
	            .channel(channelResourceIdentifier)
	            .build();
	    
//	    ProductUpdate productUpdate = ProductUpdateBuilder.of()
//	            .version(product.getVersion())
//	            .plusActions(ProductUpdateActionBuilder.of()
//	                    .changePriceBuilder()
//	                    .priceId(priceId)
//	                    .price(priceDraft)
//	                    
//	                    .build())
//	            .build();
	    
	    ProductUpdate productUpdate = ProductUpdateBuilder.of()
	            .version(product.getVersion())
	            .plusActions(ProductUpdateActionBuilder.of().addPriceBuilder()
	            		.price(priceDraft)
//	            		.variantId(product.)
	            		.sku(sku)
	            		.build())
	            
	            .build();
//	    
	    apiConfig.createApiClient().products().withId(product.getId()).post(productUpdate).executeBlocking().getBody();
	}

	
	public void xmlParsingForSampleUser(MultipartFile file) {
		try {
			byte[] xmlFileData=file.getBytes();
			String strXmlData=new String(xmlFileData,StandardCharsets.UTF_8);
			System.out.println(strXmlData);
			JAXBContext jaxbContext=(JAXBContext) JAXBContext.newInstance(UserXmlCheck.class);
			Unmarshaller unMarshaller=jaxbContext.createUnmarshaller();
			UserXmlCheck UserXmlCheck=(UserXmlCheck) unMarshaller.unmarshal(new StringReader(strXmlData));
			System.out.println(UserXmlCheck.getUsername());
			
			
		}catch(Exception e) {
			throw new RuntimeException("error " +e);
		}
		
	}
}
