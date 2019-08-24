package com.megatravel.agentskaaplikacija.dtos;

import java.util.ArrayList;
import java.util.List;

import com.megatravel.agentskaaplikacija.model.HousingUnit;
import com.megatravel.agentskaaplikacija.model.BonusOptionUnit;

public class HousingUnitDTO {

    private Long id;
    private String description;
    private int allowedPeople;
    private int daysForCancelling;
	private boolean allowedCancelling;
    private double price;
    private double rating;
    private AddressDTO adress;
    private UnitTypeDTO type;
    private List<OptionDTO> options;
	
	public HousingUnitDTO() { }
	
	public HousingUnitDTO(HousingUnit unit) {
		this.id = unit.getId();
		this.description = unit.getDescription();
		this.allowedPeople = unit.getAllowedPeople();
		this.daysForCancelling = unit.getDaysForCancelling();
		this.allowedCancelling = unit.isAllowedCancelling();
		this.price = unit.getPrice();
		this.rating = unit.getRating();
		this.adress = new AddressDTO(unit.getAdress());
		this.type = new UnitTypeDTO(unit.getType());
		this.options = new ArrayList<OptionDTO>();
		if(unit.getOptionJoins() != null)
			for(BonusOptionUnit join : unit.getOptionJoins()) {
				this.options.add(new OptionDTO(join));
			}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAllowedPeople() {
		return allowedPeople;
	}

	public void setAllowedPeople(int allowedPeople) {
		this.allowedPeople = allowedPeople;
	}

	public int getDaysForCancelling() {
		return daysForCancelling;
	}

	public void setDaysForCancelling(int daysForCancelling) {
		this.daysForCancelling = daysForCancelling;
	}

	public boolean isAllowedCancelling() {
		return allowedCancelling;
	}

	public void setAllowedCancelling(boolean allowedCancelling) {
		this.allowedCancelling = allowedCancelling;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public AddressDTO getAdress() {
		return adress;
	}

	public void setAdress(AddressDTO adress) {
		this.adress = adress;
	}

	public UnitTypeDTO getType() {
		return type;
	}

	public void setType(UnitTypeDTO type) {
		this.type = type;
	}

	public List<OptionDTO> getOptions() {
		return options;
	}

	public void setOptions(List<OptionDTO> options) {
		this.options = options;
	}
	
}
