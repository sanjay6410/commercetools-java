package com.example.demo.controller;
//package com.example.demo.controller;
//
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.commercetools.api.models.inventory.InventoryEntryDraft;
//import com.commercetools.api.models.inventory.InventoryEntryDraftBuilder;
//import com.commercetools.api.models.inventory.InventoryPagedQueryResponse;
//import com.example.demo.config.ProjectApiConfig;
//import com.example.demo.model.Item;
//import com.example.demo.model.ItemSite;
//import com.example.demo.service.InventoryService;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//@RestController
//public class inventoryController {
//
//	@Autowired
//	private ProjectApiConfig apiConfig;
//	
//	@Autowired
//	private InventoryService inventoryService;
//
//	private static List<Item> readFromInputObj(JsonNode jsonNode, Class deserialize, ObjectMapper objectMapper) {
//        try {
//            return objectMapper.readValue(objectMapper.treeAsTokens(jsonNode), objectMapper.getTypeFactory()
//                    .constructCollectionType(List.class, deserialize));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//	private static List<ItemSite> readFromInputObjItemSite(JsonNode jsonNode, Class deserialize, ObjectMapper objectMapper) {
//        try {
//            return objectMapper.readValue(objectMapper.treeAsTokens(jsonNode), objectMapper.getTypeFactory()
//                    .constructCollectionType(List.class, deserialize));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//	@PostMapping("/uploadJsonFile")
//	public ResponseEntity<?> uploadJsonFile(@RequestParam("file") MultipartFile file) {
//		try {
//			byte[] jsonData = file.getBytes();
//			String jsonStr = new String(jsonData, StandardCharsets.UTF_8);
//			//System.out.println("String       " + jsonStr);
//			ObjectMapper objectMapper=new ObjectMapper();
//			//JsonDataModel dataModel1=objectMapper.readValue(jsonStr, JsonDataModel.class);
//			//System.out.println(dataModel1.getInventoryStockFeed());
//					//.getArticleStockDetails().getArticle_stockinfo().getItems().get(1));
//			//Application dataModel=objectMapper.readValue(jsonStr, Application.class);
//			//System.out.println(dataModel.getInventoryStockFeedClass().getArticleStockDetails());
//			//UserJsonCheck checkJson=objectMapper.readValue(jsonData, UserJsonCheck.class);
//			//System.out.println(checkJson.getUsername() +"  "+ checkJson.getPassword());
//			
//			JsonNode jsonNode=objectMapper.readValue(jsonStr, JsonNode.class);
//			JsonNode at=jsonNode.at("/InventoryStockFeed/articleStockDetails/article_stockinfo/item");
//			List<Item> items = readFromInputObj(at, Item.class, objectMapper);
//	        //System.out.println(items.get(0).getSku()+" "+items.get(0).getSite_info());
//	         System.out.println(items);
////			JsonNode atSite=jsonNode.at("/InventoryStockFeed/articleStockDetails/article_stockinfo/item/site-info/itemSite");
////			List<ItemSite> sites = readFromInputObjItemSite(atSite, ItemSite.class, objectMapper);
////			System.out.println(getSku(items,sites));
//			
//			String sku =items.get(0).getSku();
//			long qty=items.get(0).getSite_info().getItemSite().get(0).qty;
//			System.out.println(sku);
//			System.out.println(qty);
//			
//			String sku1=items.get(1).getSku();
//			System.out.println(sku1);
//			long qty1=items.get(1).getSite_info().getItemSite().get(0).getQty();
//			System.out.println(qty1);
////			System.out.println(inventoryService.getSkusAndQtysList(items));
////			
////			Map<String, List<Long>> skusAndQtysList = inventoryService.getSkusAndQtysList(items);
//
//			// Loop through the map and create InventoryEntryDrafts for each SKU and corresponding quantities
////			for (Map.Entry<String, List<Long>> entry : skusAndQtysList.entrySet()) {
////			    String sku2 = entry.getKey();
////			    List<Long> qtys = entry.getValue();
////			    System.out.println(sku2);
////			    System.out.println(qtys);
////			    for (Long qty2 : qtys) {
////			        InventoryEntryDraft inventoryDraft = InventoryEntryDraftBuilder.of()
////			                .sku(sku)
////			                .quantityOnStock(qty2)
////			                .build();
////			        // post the inventory entry draft to the API
////			        apiConfig.createApiClient().inventory().post(inventoryDraft).executeBlocking().getBody();
////			    }
////			}
//			
//			InventoryEntryDraft inventoryDraft=InventoryEntryDraftBuilder.of().sku(sku1).quantityOnStock(qty1)
//				
//				.build();	
//			apiConfig.createApiClient().inventory().post(inventoryDraft).executeBlocking().getBody();
//			
//			return ResponseEntity.ok("File uploaded successfully and created inventory");
//		} catch (Exception e) {
//			throw new RuntimeException("Error Handling File "+e);
//		}
//	}
//	
//	@GetMapping("/getInventoryBySKU")
//	public InventoryPagedQueryResponse getInventoryBySKU(@RequestParam("sku") String sku) {
//		 InventoryPagedQueryResponse i = apiConfig.createApiClient()
//				.inventory().get() .withWhere("sku= :skuVar").withPredicateVar("skuVar", sku)
//				.executeBlocking().getBody();
//		 return i;
////		 @NotNull
////		@Valid/*
////		List<InventoryEntry> results = i.getResults();*/
//		
//	}
//}
