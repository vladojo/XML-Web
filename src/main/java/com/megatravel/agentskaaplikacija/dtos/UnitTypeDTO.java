package com.megatravel.agentskaaplikacija.dtos;

import com.megatravel.agentskaaplikacija.model.UnitType;

public class UnitTypeDTO {

    private Long id;
    private String name;
	
    public UnitTypeDTO() { }
    
    public UnitTypeDTO(UnitType type) {
    	this.id = type.getId();
    	this.name = type.getName();
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
    
}
