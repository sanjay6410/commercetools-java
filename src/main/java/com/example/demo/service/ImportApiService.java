package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.commercetools.importapi.models.customers.CustomerAddress;
import com.commercetools.importapi.models.customers.CustomerAddressBuilder;
import com.commercetools.importapi.models.customers.CustomerImport;
import com.commercetools.importapi.models.customers.CustomerImportBuilder;
import com.commercetools.importapi.models.importcontainers.ImportContainer;
import com.commercetools.importapi.models.importcontainers.ImportContainerDraft;
import com.commercetools.importapi.models.importcontainers.ImportContainerDraftBuilder;
import com.commercetools.importapi.models.importrequests.CustomerImportRequest;
import com.commercetools.importapi.models.importrequests.CustomerImportRequestBuilder;
import com.example.demo.config.ImportApiConfig;

@Service
public class ImportApiService {

	@Autowired
	private ImportApiConfig apiRoot;
	
	@Autowired
	private ExcelRead excelRead;
	
	public ImportContainer createContainer(String key) {
		ImportContainerDraft containerDraft=ImportContainerDraftBuilder.of()
				.key(key)
				.build();
		return apiRoot.createApiClient().importContainers().post(containerDraft).executeBlocking().getBody();
	}
	
	public void importCustomerData() {
		
		CustomerAddress custAddress=CustomerAddressBuilder.of()
				.key("importApiCustomerImportCheckAddress")
				.country("DE")
				.build();
		
		CustomerImport customerImportData=CustomerImportBuilder.of()
				.key("importApiCustomerImportCheck")
				.email("importApiCustomerImportCheck@example.com")
				.password("importApiCustomerImportCheck")
				.addresses(custAddress)
				.build();
		CustomerImportRequest customerImport=CustomerImportRequestBuilder.of()
				.resources(customerImportData)
				.build();
		
		apiRoot.createApiClient().customers().importContainers().withImportContainerKeyValue("first-container-fromCode")
		.post(customerImport).executeBlocking().getBody();

	}
	public void checkExcelData(MultipartFile file) {
		excelRead.ExcelReadFromFile(file);
	}
	
}
