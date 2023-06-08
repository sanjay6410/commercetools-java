package com.example.demo.model;

import jakarta.validation.constraints.NotNull;

public class CustomerAddress {

	@NotNull
	private String countryCode;
	private String streetName;
	private String streetNumber;
	private String city;
	private String region;
	private String state;
	private String building;
	public CustomerAddress(String countryCode, String streetName, String streetNumber, String city, String region,
			String state, String building) {
		super();
		this.countryCode = countryCode;
		this.streetName = streetName;
		this.streetNumber = streetNumber;
		this.city = city;
		this.region = region;
		this.state = state;
		this.building = building;
	}
	public CustomerAddress() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getStreetNumber() {
		return streetNumber;
	}
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	
	
	
	
	
}
