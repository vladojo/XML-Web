package com.megatravel.smestaj.dtos;

import com.megatravel.smestaj.model.BonusOption;
import com.megatravel.smestaj.model.BonusOptionUnit;

public class OptionDTO {

    private Long id;
    private String name;
    private String description;
	
    public OptionDTO() { }
    
    public OptionDTO(BonusOption option) {
    	this.id = option.getId();
    	this.name = option.getName();
    	this.description = option.getDescription();
    }
    
    public OptionDTO(BonusOptionUnit join) {
    	this(join.getOption());
    }

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
    
}
