package com.megatravel.agentskaaplikacija.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class UnitType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 32)
    private String name;
    @OneToMany(mappedBy = "type")
	private List<HousingUnit> units;
    
    public UnitType() { }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<HousingUnit> getUnits() {
		return units;
	}

	public void setUnits(List<HousingUnit> units) {
		this.units = units;
	}
    
}
