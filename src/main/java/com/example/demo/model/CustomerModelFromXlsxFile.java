package com.example.demo.model;

public class CustomerModelFromXlsxFile {

	    private String salutation;
	    private String firstName;
	    private String lastName;
	    private String middleName;
	    private String country;
	    private String building;
	    private String city;
	    private String setAsDefaultShippingAddress;
		public CustomerModelFromXlsxFile() {
			super();
			// TODO Auto-generated constructor stub
		}
		public CustomerModelFromXlsxFile(String salutation, String firstName, String lastName, String middleName,
				String country, String building, String city, String setAsDefaultShippingAddress) {
			super();
			this.salutation = salutation;
			this.firstName = firstName;
			this.lastName = lastName;
			this.middleName = middleName;
			this.country = country;
			this.building = building;
			this.city = city;
			this.setAsDefaultShippingAddress = setAsDefaultShippingAddress;
		}
		@Override
		public String toString() {
			return "CustomerModelFromXlsxFile [salutation=" + salutation + ", firstName=" + firstName + ", lastName="
					+ lastName + ", middleName=" + middleName + ", country=" + country + ", building=" + building
					+ ", city=" + city + ", setAsDefaultShippingAddress=" + setAsDefaultShippingAddress + "]";
		}
		public String getSalutation() {
			return salutation;
		}
		public void setSalutation(String salutation) {
			this.salutation = salutation;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getMiddleName() {
			return middleName;
		}
		public void setMiddleName(String middleName) {
			this.middleName = middleName;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public String getBuilding() {
			return building;
		}
		public void setBuilding(String building) {
			this.building = building;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getSetAsDefaultShippingAddress() {
			return setAsDefaultShippingAddress;
		}
		public void setSetAsDefaultShippingAddress(String setAsDefaultShippingAddress) {
			this.setAsDefaultShippingAddress = setAsDefaultShippingAddress;
		}
	    
	    
}
