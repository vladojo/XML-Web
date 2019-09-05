package com.megatravel.ocena.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int value;
    @Column(nullable = false, updatable = false, unique = true)
	private Long reservation;
    
    public Rating() { }

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

	public Long getReservation() {
		return reservation;
	}

	public void setReservation(Long reservation) {
		this.reservation = reservation;
	}
    
}
