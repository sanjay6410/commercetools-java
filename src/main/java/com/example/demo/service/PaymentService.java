package com.example.demo.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commercetools.api.models.common.MoneyBuilder;
import com.commercetools.api.models.common.TypedMoney;
import com.commercetools.api.models.payment.Payment;
import com.commercetools.api.models.payment.PaymentDraft;
import com.commercetools.api.models.payment.PaymentDraftBuilder;
import com.commercetools.api.models.payment.PaymentMethodInfoBuilder;
import com.commercetools.api.models.payment.TransactionDraftBuilder;
import com.commercetools.api.models.payment.TransactionState;
import com.commercetools.api.models.payment.TransactionType;
import com.example.demo.config.ProjectApiConfig;

@Service
public class PaymentService {

	@Autowired
	private ProjectApiConfig apiConfig;
	
	//Money money
	public void createPayment(TypedMoney money) {
		PaymentDraft paymentDraft=PaymentDraftBuilder.of()
				//.key("cashMethod")
				.amountPlanned(MoneyBuilder.of().centAmount(money.getCentAmount()).currencyCode(money.getCurrencyCode()).build())
				.paymentMethodInfo(PaymentMethodInfoBuilder.of().method("CASH").build())
				//.amountPlanned(MoneyBuilder.of().centAmount(1000L).currencyCode("USD").build())
				.interfaceId(UUID.randomUUID().toString())
				.transactions(TransactionDraftBuilder.of().type(TransactionType.CHARGE).amount(MoneyBuilder.of().centAmount(money.getCentAmount()).currencyCode(money.getCurrencyCode()).build()).state(TransactionState.INITIAL).build())
				
				.build();
		
		Payment payment=apiConfig.createApiClient().payments().post(paymentDraft).executeBlocking().getBody();
	}
}
