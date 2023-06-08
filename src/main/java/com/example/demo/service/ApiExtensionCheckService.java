package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commercetools.api.models.product.Product;
import com.example.demo.config.ProjectApiConfig;

@Service
public class ApiExtensionCheckService  {
//implements HttpFunction
	@Autowired
	private ProjectApiConfig apiRoot;
	
	//private static final Logger logger = Logger.getLogger(Example.class.getName());
	
	public void checkExtensionProduct(String productId) {
		Product product = apiRoot.createApiClient().products().withId(productId).get().executeBlocking().getBody();
		String currentSku = product.getMasterData().getCurrent().getMasterVariant().getSku();
		String stagedSku = product.getMasterData().getStaged().getMasterVariant().getSku();
		String currentKey = product.getMasterData().getCurrent().getMasterVariant().getKey();
		String stagedKey = product.getMasterData().getStaged().getMasterVariant().getKey();
		Object currentMatrixId=product.getMasterData().getCurrent().getMasterVariant().getAttribute("matrixId").getValue();
		System.out.println(currentMatrixId);
		Object stagedMatrixId=product.getMasterData().getStaged().getMasterVariant().getAttribute("matrixId").getValue();
		System.out.println(stagedMatrixId);
		Object currentBaseId=product.getMasterData().getCurrent().getMasterVariant().getAttribute("baseId").getValue();
		System.out.println(currentBaseId);
		Object stagedBaseId=product.getMasterData().getStaged().getMasterVariant().getAttribute("baseId").getValue();
		System.out.println(stagedBaseId);
		stagedBaseId.toString();
		
		Boolean status=product.getMasterData().getHasStagedChanges();
		System.out.println(status);
		if (!currentSku.equals(stagedSku) || !currentKey.equals(stagedKey) 
				|| !currentMatrixId.toString().equals(stagedMatrixId) || !currentBaseId.equals(stagedBaseId) ) {
			System.out.println("ERROR");
        
		} else {
			System.out.println("SUCCESS");
		}

	}

//	 @Override
//	  public void service(HttpRequest request, HttpResponse response) throws Exception {
//	    logger.info("SANJAYYYY");
//	    logger.info(request.getUri());
//	    Optional<String> reqsChar=request.getCharacterEncoding();
//	    String a=reqsChar.get();
//	    String input=request.getInputStream().toString();
//	    logger.info("INPUT STREAM"+input);
//	    BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
//	    StringBuilder requestBody = new StringBuilder();
//	    String line;
//
//	    while ((line = reader.readLine()) != null) {
//	      requestBody.append(line);
//	    }
//
//	    reader.close();
//	    logger.info("INPUT STREAM"+requestBody);
//
//	    request.
//	    
//	    System.out.println("Request body: " + requestBody.toString());
//	    logger.info(a);
//	    String requestBody = new String(request.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
//	    request.getPath();
//	    response.setStatusCode(200);
//	    BufferedWriter writer = response.getWriter();
//	    writer.write("Hello world! from gcp");
//	    
//	    if (!currentSku.equals(stagedSku) || !currentKey.equals(stagedKey)) {
//	    	Map<String, Object> error = new HashMap<>();
//	        error.put("message", "You cannot update the sku and key restricted");
//	        Map<String, Object> res = new HashMap<>();
//	        res.put("errors", Collections.singletonList(error));
//	        response.setStatusCode(400);
//	        response.setContentType("application/json");
//	        response.getWriter().write(new ObjectMapper().writeValueAsString(res));
//	        response.getWriter().write("SKU and KEY updation on Product is restricted");
//	        
//		} else {
//			response.setStatusCode(200);
//			response.getWriter().close();
//		}
//	  }
////	 res.status(400).json({
////	      errors : [{
////	        code: "InvalidInput",
////	        message: "You can not put more than 10 items into the cart."
////	      }]
////	    });
//	
	
}
