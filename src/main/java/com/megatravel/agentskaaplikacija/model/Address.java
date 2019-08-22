package com.megatravel.agentskaaplikacija.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 32, nullable = false, unique = false)
    private String country;
    @Column(length = 32, nullable = false, unique = false)
    private String city;
    @OneToMany(mappedBy = "adress")
	private List<HousingUnit> units;
    
    public Address() { }

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

	public List<HousingUnit> getUnits() {
		return units;
	}

	public void setUnits(List<HousingUnit> units) {
		this.units = units;
	}
    
}
