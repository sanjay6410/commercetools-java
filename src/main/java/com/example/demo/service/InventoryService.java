package com.example.demo.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.commercetools.api.models.inventory.InventoryEntryDraft;
import com.commercetools.api.models.inventory.InventoryEntryDraftBuilder;
import com.example.demo.config.ProjectApiConfig;
import com.example.demo.model.ArticleStockinfo;
import com.example.demo.model.Item;
import com.example.demo.model.ItemSite;
import com.example.demo.model.SiteInfo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class InventoryService {
	
	@Autowired
	private ProjectApiConfig apiConfig;

	private static ArticleStockinfo readFromArticleStockinfo(JsonNode jsonNode, Class deserializeClass,
			ObjectMapper objectMapper) {
		try {
			return objectMapper.readValue(objectMapper.treeAsTokens(jsonNode),
					objectMapper.getTypeFactory().constructType(deserializeClass));
			// .constructCollectionLikeType(ArticleStockinfo.class, deserializeClass));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static List<Item> readFromInputObj(JsonNode jsonNode, Class deserializeClass, ObjectMapper objectMapper) {
		try {
			return objectMapper.readValue(objectMapper.treeAsTokens(jsonNode),
					objectMapper.getTypeFactory().constructCollectionType(List.class, deserializeClass));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void createInventoryFromJson(MultipartFile file) {
		try {
			byte[] jsonData = file.getBytes();
			String jsonStringData = new String(jsonData, StandardCharsets.UTF_8);
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readValue(jsonStringData, JsonNode.class);
			// JsonNode atarticle_stockinfo =
			// jsonNode.at("/InventoryStockFeed/articleStockDetails/article_stockinfo");
			// ArticleStockinfo
			// info=readFromArticleStockinfo(atarticle_stockinfo,ArticleStockinfo.class,objectMapper);
			// System.out.println(info);
			JsonNode itemsNode = jsonNode.at("/InventoryStockFeed/articleStockDetails/article_stockinfo/item");
			List<Item> items = readFromInputObj(itemsNode, Item.class, objectMapper);
			//System.out.println(items.get(0).sku);
			for(Item item : items) {
		        String skus = item.getSku();
		        List<ItemSite> itemSites = item.getSite_info().getItemSite();
		        for(int i = 0; i < skus.length() && i < itemSites.size(); i++) {
		            ItemSite siteInfo = itemSites.get(i);
		            System.out.println("SKU: " + skus + ", Site for " +   ": " + siteInfo.getSite() + ", Qty: " + siteInfo.getQty());
		            createInventory(skus, siteInfo.getSite(), siteInfo.getQty());
		        }
		    }


		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	public void createInventory(String sku, String site, long qty) {
    InventoryEntryDraft inventoryDraft=InventoryEntryDraftBuilder.of()
    		.sku(sku).quantityOnStock(qty)
    		.supplyChannel(t ->t.key("sunrise-store-"+site) )
//    		.sku(sku).quantityOnStock(qty)
				
				.build();	
			apiConfig.createApiClient().inventory().post(inventoryDraft).executeBlocking().getBody();
			
	}
}
