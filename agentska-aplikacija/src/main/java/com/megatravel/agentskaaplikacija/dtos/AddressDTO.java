package com.megatravel.agentskaaplikacija.dtos;

import com.megatravel.agentskaaplikacija.model.Address;

public class AddressDTO {

    private Long id;
    private String country;
    private String city;
	
    public AddressDTO() { }
    
    public AddressDTO(Address address) {
    	this.id = address.getId();
    	this.country = address.getCountry();
    	this.city = address.getCity();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
    
}
