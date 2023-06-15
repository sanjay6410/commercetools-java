package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commercetools.api.models.cart.Cart;
import com.commercetools.api.models.common.Address;
import com.commercetools.api.models.common.BaseAddress;
import com.commercetools.api.models.common.BaseAddressBuilder;
import com.commercetools.api.models.customer.Customer;
import com.commercetools.api.models.customer.CustomerUpdate;
import com.commercetools.api.models.customer.CustomerUpdateBuilder;
import com.example.demo.DAO.CustomerDao;
import com.example.demo.config.ProjectApiConfig;

@Service
public class CustomerService {

	@Autowired
	private ProjectApiConfig apiRoot;
	
	@Autowired
	private CustomerDao customerDao;
	
	public String addAddressToTheExistingCustomer(String email, Address customerAddress) throws RuntimeException, InterruptedException, ExecutionException {
	    Customer customer = customerDao.getCustomerByEmail(email)
	    		.get()
	            .getResults()
	            .stream()
	            .findFirst()
	            .orElseThrow(() -> new RuntimeException("Customer not found for email: " + email));

	    List<Address> custAddresses = customer.getAddresses();

	    for (Address address : custAddresses) {
	        if (isAddressMatch(address, customerAddress)) {
	            System.out.println("Matched");
	            return "Address already exists";
	        }
	    }

	    BaseAddress newAddress = BaseAddressBuilder.of()
	            .country(customerAddress.getCountry())
	            .streetName(customerAddress.getStreetName())
	            .streetNumber(customerAddress.getStreetNumber())
	            .building(customerAddress.getBuilding())
	            .postalCode(customerAddress.getPostalCode())
	            .city(customerAddress.getCity())
	            .region(customerAddress.getRegion())
	            .state(customerAddress.getState())
	            .company(customerAddress.getCompany())
	            .department(customerAddress.getDepartment())
	            .apartment(customerAddress.getApartment())
	            .pOBox(customerAddress.getPOBox())
	            .build();

	    CustomerUpdate customerUpdate = CustomerUpdateBuilder.of()
	            .version(customer.getVersion())
	            .plusActions(t -> t.addAddressBuilder().address(newAddress))
	            .build();

	    apiRoot.createApiClient()
	            .customers()
	            .withId(customer.getId())
	            .post(customerUpdate)
	            .executeBlocking()
	            .getBody();

	    return "Address added successfully";
	}

	private boolean isAddressMatch(Address existingAddress, Address newAddress) {
	    if (existingAddress.getStreetName() == null || newAddress.getStreetName() == null) {
	        return false; // Return false if either street name is null
	    }

	    // Perform the rest of the field comparisons
	    return existingAddress.getStreetName().equals(newAddress.getStreetName())
	            && existingAddress.getStreetNumber().equals(newAddress.getStreetNumber())
	            && existingAddress.getBuilding().equals(newAddress.getBuilding())
	            && existingAddress.getPostalCode().equals(newAddress.getPostalCode())
	            && existingAddress.getCity().equals(newAddress.getCity())
	            && existingAddress.getRegion().equals(newAddress.getRegion())
	            && existingAddress.getState().equals(newAddress.getState())
	            && existingAddress.getCompany().equals(newAddress.getCompany())
	            && existingAddress.getDepartment().equals(newAddress.getDepartment())
	            && existingAddress.getApartment().equals(newAddress.getApartment())
	            && existingAddress.getPOBox().equals(newAddress.getPOBox());
	}


	
	
	public Customer updateFirstName(String email,String firstName) throws InterruptedException, ExecutionException  {
		List<Customer> customers=customerDao.getCustomerByEmail(email).get().getResults();
		Optional<Customer> customerOptional=customers.isEmpty() ? Optional.empty() : Optional.ofNullable(customers.get(0));
		Customer customer=customerOptional.orElseThrow(() -> new RuntimeException("Customer Not Found With Email "+email));
		
		CustomerUpdate customerUpdate=CustomerUpdateBuilder.of()
				.version(customer.getVersion())
				.plusActions(t -> t.setFirstNameBuilder().firstName(firstName))
				.build();
		return apiRoot.createApiClient().customers().withId(customer.getId()).post(customerUpdate).executeBlocking().getBody();
	}
	
	public CompletableFuture<Cart> getCustomerCartsByEmail(String email)  {
		return customerDao.getCustomerCartsByEmail(email);
	}
	
	//throws InterruptedException, ExecutionException
	
	
	
}
