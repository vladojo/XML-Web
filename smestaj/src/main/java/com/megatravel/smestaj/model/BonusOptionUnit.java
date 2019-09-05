package com.megatravel.smestaj.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BonusOptionUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private BonusOption option;
    @ManyToOne
    private HousingUnit unit;
    
    public BonusOptionUnit() { }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BonusOption getOption() {
		return option;
	}

	public void setOption(BonusOption option) {
		this.option = option;
	}

	public HousingUnit getUnit() {
		return unit;
	}

	public void setUnit(HousingUnit unit) {
		this.unit = unit;
	}
	
}
