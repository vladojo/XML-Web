package com.megatravel.agentskaaplikacija.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class BonusOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 32)
    private String name;
    @Column(nullable = false, unique = false, length = 64)
    private String description;
    @OneToMany(mappedBy = "option")
	private List<BonusOptionUnit> unitJoins;
    
    public BonusOption() { }

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<BonusOptionUnit> getUnitJoins() {
		return unitJoins;
	}

	public void setUnitJoins(List<BonusOptionUnit> unitJoins) {
		this.unitJoins = unitJoins;
	}
    
}
