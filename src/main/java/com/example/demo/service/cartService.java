package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commercetools.api.models.cart.Cart;
import com.commercetools.api.models.cart.CartDraft;
import com.commercetools.api.models.cart.CartDraftBuilder;
import com.commercetools.api.models.cart.CartResourceIdentifier;
import com.commercetools.api.models.cart.CartResourceIdentifierBuilder;
import com.commercetools.api.models.cart.CartUpdate;
import com.commercetools.api.models.cart.CartUpdateBuilder;
import com.commercetools.api.models.cart.LineItemDraft;
import com.commercetools.api.models.cart.LineItemDraftBuilder;
import com.commercetools.api.models.common.LocalizedStringBuilder;
import com.commercetools.api.models.custom_object.CustomObjectDraft;
import com.commercetools.api.models.custom_object.CustomObjectDraftBuilder;
import com.commercetools.api.models.customer.CustomerPagedQueryResponse;
import com.commercetools.api.models.order.Order;
import com.commercetools.api.models.order.OrderFromCartDraft;
import com.commercetools.api.models.order.OrderFromCartDraftBuilder;
import com.commercetools.api.models.payment.PaymentResourceIdentifier;
import com.commercetools.api.models.payment.PaymentResourceIdentifierBuilder;
import com.commercetools.api.models.type.CustomFieldsDraft;
import com.commercetools.api.models.type.CustomFieldsDraftBuilder;
import com.commercetools.api.models.type.FieldContainerBuilder;
import com.commercetools.api.models.type.TypeResourceIdentifier;
import com.commercetools.api.models.type.TypeResourceIdentifierBuilder;
import com.example.demo.DAO.CartDao;
import com.example.demo.DAO.CustomObjectDao;
import com.example.demo.config.ProjectApiConfig;

@Service
public class CartService {

	@Autowired
	private ProjectApiConfig apiConfig;

	@Autowired
	private CartDao cartDao;

	@Autowired
	private CustomObjectDao customObjectDao;

	public Cart createCart(String customerId, String sku, Long qty) {

		LineItemDraft lineItemDraft = LineItemDraftBuilder.of().sku(sku).quantity(qty).build();
		
		CartDraft cartDraft = CartDraftBuilder.of().currency("EUR").customerId(customerId).lineItems(lineItemDraft)
				.shippingAddress(t -> t.country("DE"))
				 .shippingMethod(t -> t.id("c66f2046-4bd0-490b-a8ef-a57100386ce3"))
				// .anonymousId(CustomObjectDraftBuilder.of().getKey())
				//.discountCodes("SANDC01")
				//.custom(customFieldsDraft)

				
				
				.build();
		
		//CartUpdate cartUpdate=CartUpdateBuilder.of().actions()

		Cart cart = apiConfig.createApiClient().carts()
				
				.post(cartDraft)
				.executeBlocking().getBody();
		return cart;
	}

	public Cart getCartByCustomerFirstName(String firstName) {
		CustomerPagedQueryResponse cust = cartDao.getCustomerByFirstName(firstName);
		String CustomerId = cust.getResults().get(0).getId();
		Cart customerCart = apiConfig.createApiClient().carts().withCustomerId(CustomerId).get().executeBlocking()
				.getBody();
		return customerCart;
	}

	public Order createOrderWithCart(String cartId) {

		CartResourceIdentifier cartIdentifier = CartResourceIdentifierBuilder.of().id(cartId).build();
		Cart cart = cartDao.getCartById(cartId);
		Long cartVersion = cart.getVersion();
		OrderFromCartDraft orderFromCartDraft = OrderFromCartDraftBuilder.of().cart(cartIdentifier).version(cartVersion)
                
				.build();
		Order createOrder = apiConfig.createApiClient().orders().post(orderFromCartDraft).executeBlocking().getBody();

		return createOrder;

	}

	public Cart createCartForAnonymous(String sku, Long qty) {

		com.commercetools.api.models.custom_object.CustomObject customObject = customObjectDao
				.getCustomObjectByKey("AnonmousId").getResults().get(0).get();

		LineItemDraft lineItemDraft = LineItemDraftBuilder.of().sku(sku).quantity(qty).build();
		String value = (String) customObject.getValue();
		CartDraft cartDraft = CartDraftBuilder.of().currency("EUR").lineItems(lineItemDraft)
				.shippingAddress(t -> t.country("US")).anonymousId(value)

				.build();
		// System.out.println(value);

		Cart cart = apiConfig.createApiClient().carts().post(cartDraft).executeBlocking().getBody();
		CustomObjectDraft customObjectDraft = CustomObjectDraftBuilder.of().container("Customer").key("AnonmousId")
				.value((Integer.parseInt(value) + 1 + "")).build();
		// CustomObject object=(CustomObject)
		apiConfig.createApiClient().customObjects().post(customObjectDraft).executeBlocking().getBody();
		return cart;
	}
	
	
	public Cart updateCartWithPayment(String cartId) {
		Cart cart=cartDao.getCartById(cartId);
		
		PaymentResourceIdentifier paymentIdentifier=PaymentResourceIdentifierBuilder.of()
				.id("82b13c39-df6e-4c36-9b88-5328c767cd02")
				.build();
		CartUpdate cartUpdate=CartUpdateBuilder.of()
				.version(cart.getVersion())
				.plusActions(t -> t.addPaymentBuilder().payment(paymentIdentifier))
				.build();
		Cart cartUpdated=apiConfig.createApiClient().carts()
				.withId(cartId).post(cartUpdate).executeBlocking().getBody();
		return cartUpdated;
	}
	
	public Cart createCartWithCustomField(String customerId, String sku, Long qty,String value,String fieldName) {

		LineItemDraft lineItemDraft = LineItemDraftBuilder.of().sku(sku).quantity(qty).build();

		TypeResourceIdentifier resourceIdentifier=TypeResourceIdentifierBuilder.of().id("3a10c133-88b7-43d3-9ff8-2742615233ab").build();
		
		CustomFieldsDraft customFieldsDraft=CustomFieldsDraftBuilder.of()
				.type(resourceIdentifier)
				//t - FieldContainerBuilder
				//.fields(t -> t.addValue("", customerCreation.getFields()))
				//.fields(customerCreation.getFields())
				.fields(FieldContainerBuilder.of().addValue(fieldName,value).build())
				.build();
		
		CartDraft cartDraft = CartDraftBuilder.of().currency("EUR").customerId(customerId).lineItems(lineItemDraft)
				.shippingAddress(t -> t.country("DE"))
				 .shippingMethod(t -> t.id("c66f2046-4bd0-490b-a8ef-a57100386ce3"))
				// .anonymousId(CustomObjectDraftBuilder.of().getKey())
				//.discountCodes("SANDC01")
				.custom(customFieldsDraft)

				
				
				.build();
		
		//CartUpdate cartUpdate=CartUpdateBuilder.of().actions()

		Cart cart = apiConfig.createApiClient().carts()
				
				.post(cartDraft)
				.executeBlocking().getBody();
		return cart;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
