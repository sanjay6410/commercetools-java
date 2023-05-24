package com.example.demo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.commercetools.importapi.models.customers.CustomerAddress;
import com.commercetools.importapi.models.customers.CustomerAddressBuilder;
import com.commercetools.importapi.models.customers.CustomerImport;
import com.commercetools.importapi.models.customers.CustomerImportBuilder;
import com.commercetools.importapi.models.importrequests.CustomerImportRequest;
import com.commercetools.importapi.models.importrequests.CustomerImportRequestBuilder;
import com.example.demo.config.ImportApiConfig;
import com.example.demo.model.CustomerModelFromXlsxFile;

@Component
public class ExcelRead {
	
	@Autowired
	private ImportApiConfig apiRoot;

	public void ExcelReadFromFile(MultipartFile file) {
		//MultipartFile excelFile=file;
		try {
		
			List<CustomerModelFromXlsxFile> excelDataList=readAndParseXlsxFile(file);
			for (CustomerModelFromXlsxFile excelData : excelDataList) {
                System.out.println(excelData.getFirstName());
                importCustomerData(excelData.getSalutation(), excelData.getFirstName(),
                		excelData.getLastName(), excelData.getMiddleName(), excelData.getCountry(),
                		excelData.getBuilding(), excelData.getCity(), 1, excelData.getCity()+"Key", excelData.getFirstName()+"keyCustomer",
                		excelData.getFirstName()+excelData.getLastName()+"@importData.com", excelData.getFirstName()+excelData.getCountry());
            }
		}catch (Exception e) {
        
		}
	}
	
	public List<CustomerModelFromXlsxFile> readAndParseXlsxFile(MultipartFile multipartFile) throws IOException {
        List<CustomerModelFromXlsxFile> excelDataList = new ArrayList<>();

        try (Workbook workbook = WorkbookFactory.create(multipartFile.getInputStream())) {
            org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next(); // Skip header row

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                CustomerModelFromXlsxFile excelData = parseRowToExcelData(row);
                excelDataList.add(excelData);
            }
        }

        return excelDataList;
    }
	
	private CustomerModelFromXlsxFile parseRowToExcelData(Row row) {
		CustomerModelFromXlsxFile excelData = new CustomerModelFromXlsxFile();
        excelData.setSalutation(getCellValue(row.getCell(0)));
        excelData.setFirstName(getCellValue(row.getCell(1)));
        excelData.setLastName(getCellValue(row.getCell(2)));
        excelData.setMiddleName(getCellValue(row.getCell(3)));
        excelData.setCountry(getCellValue(row.getCell(4)));
        excelData.setBuilding(getCellValue(row.getCell(5)));
        excelData.setCity(getCellValue(row.getCell(6)));
        excelData.setSetAsDefaultShippingAddress(getCellValue(row.getCell(7)));

        return excelData;
    }
	
	private String getCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }

        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        } else if (cell.getCellType() == CellType.BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        }

        return null;
    }
	
	public void importCustomerData(String salutation,String firstName,String lastName,
			String middleName,String country,String building,String city,
			int address,String keyAddress,String keyCustomer,String email,String password) {
		
		CustomerAddress custAddress=CustomerAddressBuilder.of()
				.key(keyAddress)
				.country(country)
				.building(building)
				.city(city)
				
				.build();
		
		
		CustomerImport customerImportData=CustomerImportBuilder.of()
				.key(keyCustomer)
				.email(email)
				.password(password)
				.salutation(salutation)
				.firstName(firstName)
				.lastName(lastName)
				.middleName(middleName)
				.addresses(custAddress)
				//.defaultShippingAddress(address)
				.build();
		CustomerImportRequest customerImport=CustomerImportRequestBuilder.of()
				.resources(customerImportData)
				.build();
		
		apiRoot.createApiClient().customers().importContainers().withImportContainerKeyValue("second-container-fromCode")
		.post(customerImport).executeBlocking().getBody();

	}
}

