package com.megatravel.ocena.dtos;

import com.megatravel.ocena.model.Rating;

public class RatingDTO {

    private Long id;
    private int value;
	
    public RatingDTO() { }

	public RatingDTO(Rating rating) {
		this.id = rating.getId();
		this.value = rating.getValue();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
    
}
