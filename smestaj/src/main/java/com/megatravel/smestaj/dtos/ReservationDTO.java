package com.megatravel.smestaj.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.megatravel.smestaj.model.Reservation;

public class ReservationDTO {

    private Long id;
    private double price;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private String start;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private String end;
    private boolean realised;
    private HousingUnitDTO unit;
	
    public ReservationDTO() { }
    
    public ReservationDTO(Reservation reservation) {
    	this.id = reservation.getId();
    	this.price = reservation.getPrice();
    	this.start = reservation.getStart().toString();
    	this.end = reservation.getEnd().toString();
    	this.realised = reservation.isRealised();
    	this.unit = new HousingUnitDTO(reservation.getUnit());
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public boolean isRealised() {
		return realised;
	}

	public void setRealised(boolean realised) {
		this.realised = realised;
	}

	public HousingUnitDTO getUnit() {
		return unit;
	}

	public void setUnit(HousingUnitDTO unit) {
		this.unit = unit;
	}
    
}
