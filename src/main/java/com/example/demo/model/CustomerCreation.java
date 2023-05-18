package com.example.demo.model;

import com.commercetools.api.models.type.CustomFieldsDraft;

public class CustomerCreation {

	private String email;
	private String password;
	private CustomFieldsDraft custom;
	private String nameOfTheField;
	private String value;
   public CustomerCreation(String email, String password, CustomFieldsDraft custom, String nameOfTheField,
			String value) {
		super();
		this.email = email;
		this.password = password;
		this.custom = custom;
		this.nameOfTheField = nameOfTheField;
		this.value = value;
	}
public String getNameOfTheField() {
		return nameOfTheField;
	}
	public void setNameOfTheField(String nameOfTheField) {
		this.nameOfTheField = nameOfTheField;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	// private Function<FieldContainerBuilder, FieldContainerBuilder> fields;
	public CustomFieldsDraft getCustom() {
		return custom;
	}
	public void setCustom(CustomFieldsDraft custom) {
		this.custom = custom;
	}
//	public Function<FieldContainerBuilder, FieldContainerBuilder> getFields() {
//		return fields;
//	}
//	public void setFields(Function<FieldContainerBuilder, FieldContainerBuilder> fields) {
//		this.fields = fields;
//	}
	public CustomerCreation(String email, String password, CustomFieldsDraft custom) {
		super();
		this.email = email;
		this.password = password;
		this.custom = custom;
		//this.fields = fields;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public CustomerCreation(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public CustomerCreation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
