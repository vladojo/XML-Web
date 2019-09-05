package com.megatravel.smestaj.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.DATE)
	private Date start;
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.DATE)
	private Date end;
    @Column(nullable = false)
    private boolean realised;
    @Column(nullable = true, unique = false)
    private Long user;
    @Column(nullable = true, unique = true)
    private Long rating;
    @ManyToOne
    private HousingUnit unit;
    
    public Reservation() { }

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

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public boolean isRealised() {
		return realised;
	}

	public void setRealised(boolean realised) {
		this.realised = realised;
	}

	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}

	public Long getRating() {
		return rating;
	}

	public void setRating(Long rating) {
		this.rating = rating;
	}

	public HousingUnit getUnit() {
		return unit;
	}

	public void setUnit(HousingUnit unit) {
		this.unit = unit;
	}
    
}