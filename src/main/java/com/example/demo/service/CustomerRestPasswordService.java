package com.example.demo.service;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commercetools.api.models.customer.Customer;
import com.commercetools.api.models.customer.CustomerCreatePasswordResetToken;
import com.commercetools.api.models.customer.CustomerCreatePasswordResetTokenBuilder;
import com.commercetools.api.models.customer.CustomerResetPassword;
import com.commercetools.api.models.customer.CustomerResetPasswordBuilder;
import com.commercetools.api.models.customer.CustomerToken;
import com.example.demo.DAO.CustomerDao;
import com.example.demo.config.ProjectApiConfig;

@Service
public class CustomerRestPasswordService {

	@Autowired
	private ProjectApiConfig apiRoot;
	
	@Autowired
	private CustomerDao custDao;
	
	public void resetPassword(String email,String newPassword) throws InterruptedException, ExecutionException {
		Customer cust=custDao.getCustomerByEmail(email)
				.get()
				.getResults().get(0);
		CustomerCreatePasswordResetToken token=CustomerCreatePasswordResetTokenBuilder.of()
				.email(email)
				.build();
		CustomerToken tokenValue=apiRoot.createApiClient().customers().passwordToken().post(token).executeBlocking().getBody();
		System.out.println(tokenValue.getValue());
		CustomerResetPassword resetPassword=CustomerResetPasswordBuilder.of()
				.version(cust.getVersion())
				.tokenValue(tokenValue.getValue())
				.newPassword(newPassword)
				.build();
		apiRoot.createApiClient().customers().passwordReset().post(resetPassword).executeBlocking().getBody();
		
	}
}
