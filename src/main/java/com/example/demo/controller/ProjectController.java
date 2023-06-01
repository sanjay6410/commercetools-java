package com.example.demo.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.common.LocalizedString;
import com.commercetools.api.models.common.LocalizedStringBuilder;
import com.commercetools.api.models.custom_object.CustomObjectPagedQueryResponse;
import com.commercetools.api.models.customer.Customer;
import com.commercetools.api.models.customer.CustomerDraft;
import com.commercetools.api.models.customer.CustomerDraftBuilder;
import com.commercetools.api.models.customer.CustomerPagedQueryResponse;
import com.commercetools.api.models.customer.CustomerSignInResult;
import com.commercetools.api.models.product.Product;
import com.commercetools.api.models.product.ProductPagedQueryResponse;
import com.commercetools.api.models.product.ProductProjection;
import com.commercetools.api.models.product.ProductUpdate;
import com.commercetools.api.models.project.Project;
import com.commercetools.api.models.type.CustomFieldsDraft;
import com.commercetools.api.models.type.CustomFieldsDraftBuilder;
import com.commercetools.api.models.type.FieldContainerBuilder;
import com.commercetools.api.models.type.FieldDefinition;
import com.commercetools.api.models.type.ResourceTypeId;
import com.commercetools.api.models.type.Type;
import com.commercetools.api.models.type.TypeDraft;
import com.commercetools.api.models.type.TypeDraftBuilder;
import com.commercetools.api.models.type.TypeResourceIdentifier;
import com.commercetools.api.models.type.TypeResourceIdentifierBuilder;
import com.example.demo.config.ProjectApiConfig;
import com.example.demo.model.CreateCustomType;
import com.example.demo.model.CustomObject;
import com.example.demo.model.CustomerCreation;
import com.example.demo.model.Movie;
import com.example.demo.model.ProductDesc;

@RestController
public class ProjectController {

	@Autowired
	private ProjectApiConfig apiConfig;
	
	@GetMapping("/project")
	public Project project() {
		ProjectApiRoot par = apiConfig.createApiClient();
		// System.out.println(clientId);
		return par.get().executeBlocking().getBody();
	}

	@GetMapping("/getAllCustomers")
	public CustomerPagedQueryResponse getAllCustomers() {
		ProjectApiRoot par = apiConfig.createApiClient();
		return par.customers().get().executeBlocking().getBody();
	}
	
	@GetMapping("/getCustomerByKey/{cust_key}")
	public Customer getCustomerByKey(@PathVariable("cust_key") String cust_key) {
		return apiConfig.createApiClient().customers().withKey(cust_key)
				.get().executeBlocking().getBody();
	}
	
	@GetMapping("/getCustomerById/{cust_id}")
	public Customer getCustomerById(@PathVariable("cust_id") String cust_id) {
		return apiConfig.createApiClient().customers().withId(cust_id)
				.get().executeBlocking().getBody();
	}
	
	@GetMapping("/getAllProducts")
	public ProductPagedQueryResponse getAllProducts() {
		return apiConfig.createApiClient().products().get()
				.executeBlocking().getBody();
	}
	
	@GetMapping("/getProductByKey")
	public Product getProductByKey(@RequestParam("product_key") String product_key) {
		return apiConfig.createApiClient().products().withKey(product_key)
				.get().executeBlocking().getBody();
	}
	
	@PostMapping("/updateProductDescByKey")
	public void updateProductDescByKey(@RequestBody ProductDesc productDesc) {
		String productKey=productDesc.getProductKey();
		System.out.println(productKey);
		ProductProjection product = apiConfig.createApiClient()
				  .productProjections()
				  .withKey(productKey)
				  .get().executeBlocking().getBody();
		System.out.println(product.getDescription().get(Locale.ENGLISH));
		LocalizedString productDescription=productDesc.getProductDesc();
		product.setDescription(productDescription);
		System.out.println("Updated "+product.getDescription().get(Locale.ENGLISH));
		
	}
	
	@PostMapping("/updateProductDescByKey2")
	public ResponseEntity<?> updateProductDescByKey2(@RequestBody ProductDesc productDesc) {
		try {
		String productKey=productDesc.getProductKey();
		ProductProjection product = apiConfig.createApiClient()
				  .productProjections()
				  .withKey(productKey)
				  .get().executeBlocking().getBody();
		LocalizedString productDescription=productDesc.getProductDesc();
		ProductUpdate productUpdate=ProductUpdate.builder().version(product.getVersion())
				.plusActions(actionBuilder ->
			    actionBuilder.setDescriptionBuilder().description(productDescription)
			    
			  )
				.plusActions(actionBuilder ->
				actionBuilder.publishBuilder())
				
				
			  .build();
		Product updatedProduct = apiConfig.createApiClient()
				  .products()
				  .withKey(productKey)
				  .post(productUpdate)
				  .executeBlocking()
				  .getBody();
		return ResponseEntity.ok("Product Description Updated Successfully ");
		
		//System.out.println(updatedProduct.get);
		}catch(Exception e) {
			throw new RuntimeException("Product Not Updated Successfully "+ e);
		}
	}
	
	@PostMapping("/createCustomer")
	public ResponseEntity<?> createCustomer(  @RequestBody CustomerCreation customerCreation)
			//,@RequestParam("typeIdParam") String typeIdParam) 
	{
		try {
		String emailCustomer=customerCreation.getEmail();
		String passwordCustomer=customerCreation.getPassword();
		//String typeId="sdnjs"
		//System.out.println(emailCustomer);
//		Map<String, String> texts = new HashMap<>();
//		texts.put("en", "9");
		//texts.put("es", "texto en español 1");

		 String typeKey=customerCreation.getCustom().getType().getKey();
		 String fieldName=customerCreation.getNameOfTheField();
		 String value=customerCreation.getValue();
		//String typeId=typeIdParam;
	     System.out.println("ASSSSSSSSSSSSSSSS"+typeKey);
	     //System.out.println(customerCreation.getFields());
		TypeResourceIdentifier identifier=TypeResourceIdentifierBuilder
				.of()
				.key(typeKey)
				.build();

		CustomFieldsDraft customFieldsDraft=CustomFieldsDraftBuilder.of()
				.type(identifier)
				//t - FieldContainerBuilder
				//.fields(t -> t.addValue("", customerCreation.getFields()))
				//.fields(customerCreation.getFields())
				.fields(FieldContainerBuilder.of().addValue(fieldName, LocalizedStringBuilder
						.of().addValue("en", value).build()).build())
				.build();
		CustomerDraft customerDraft=CustomerDraftBuilder.of()
				.email(emailCustomer).password(passwordCustomer)
				.custom(customFieldsDraft)
				.build();
		//System.out.println(emailCustomer);
		CustomerSignInResult createCustomer=apiConfig.createApiClient().customers()
				.post(customerDraft).executeBlocking().getBody();
		return ResponseEntity.ok("Customer Created Successfully ");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Customer Not Created Successfully       "+e.getMessage());
		}
				
	}
	@PostMapping("/createCustomType")
	public  ResponseEntity<?> createCustomType(@RequestBody CreateCustomType createCustomType){
		try {
		String key=createCustomType.getKey();
		LocalizedString name=createCustomType.getName();
		ResourceTypeId[] resourceTypeIds=createCustomType.getResourceTypeIds();
		LocalizedString description=createCustomType.getDescription();
		List<FieldDefinition> fieldDefinition=createCustomType.getFieldDefinitions();
		TypeDraft CustomType=TypeDraftBuilder.of().key(key).name(name)
				.resourceTypeIds(resourceTypeIds).description(description)
				.fieldDefinitions(fieldDefinition).build();
		
		Type createType=apiConfig.createApiClient().types()
				.post(CustomType).executeBlocking().getBody();
		return ResponseEntity.ok("Type Created Successfully ");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Type Not Created Successfully       "+e.getMessage());
		}
	}
	
	@GetMapping("/customObjects")
	public CustomObjectPagedQueryResponse getCustomObjects() {
		return apiConfig.createApiClient().customObjects()
				.get().executeBlocking().getBody();
	}
	
	@GetMapping("/customObjectByContainerName")
	public CustomObjectPagedQueryResponse getCustomObjectByContainerName(@RequestBody CustomObject customObject) {
		String container=customObject.getContainerName();
		String key=customObject.getKeyCustomObject();
		
		return apiConfig.createApiClient().customObjects()
				.withContainer(container).get().executeBlocking().getBody();
	}
	
//	@PostMapping("/updateCustomObject")
//	public void updateCustomObject(@RequestBody CustomObject customObject) {
//		com.commercetools.api.models.custom_object.CustomObject customObjectUpdate=apiConfig.createApiClient().customObjects().withContainer(customObject.getContainerName())
//		.get().
//		 Object value=customObjectUpdate.getValue();
//		 value=2;
//		 CustomObjectDraft customObjectDraft=CustomObjectDraftBuilder.of().value(value).build();
//		 apiConfig.createApiClient().customObjects().post(customObjectDraft).executeBlocking().getBody();
//	}
	
	@GetMapping("/customObjectByContainerNameAndKey")
	public com.commercetools.api.models.custom_object.CustomObject getCustomObjectByContainerNameAndKey(@RequestBody CustomObject customObject) {
		String container=customObject.getContainerName();
		String key=customObject.getKeyCustomObject();
		return apiConfig.createApiClient().customObjects()
				.withContainerAndKey(container, key)
				.get().executeBlocking().getBody();
	}
	
	@GetMapping("/hello33/{id}")
	public void hello(@PathVariable("id") int id ) {
		System.out.println(id);
	}
	
	@GetMapping("/helloParam")
	public void helloParam(@RequestParam("name") String name) {
		System.out.println(name);
	}
	
	@GetMapping("/helloBody")
	public void helloBody(@RequestBody Movie movie) {
		System.out.println(movie.getName());
		System.out.println(movie.getRating());
	}
}

