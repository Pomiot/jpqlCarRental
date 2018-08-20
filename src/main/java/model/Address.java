package model;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	private String city;
	private String street;
	private String houseNumeber;
	
	
	public Address(){
		
	}
	
	public Address(String city, String street, String houseNumber){
		this.city = city;
		this.street = street;
		this.houseNumeber = houseNumber;
		
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNumeber() {
		return houseNumeber;
	}

	public void setHouseNumeber(String houseNumeber) {
		this.houseNumeber = houseNumeber;
	}
	
}
