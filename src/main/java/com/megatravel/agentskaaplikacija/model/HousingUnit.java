package com.megatravel.agentskaaplikacija.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class HousingUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 512, nullable = false, unique = false)
    private String description;
    @Column(nullable = false)
    private int allowedPeople;
    @Column(nullable = false)
    private int daysForCancelling;
    @Column(nullable = false)
	private boolean allowedCancelling;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private double rating;
    @ManyToOne
    private Address adress;
    @ManyToOne
    private User agent;
    @ManyToOne
    private UnitType type;
    @OneToMany(mappedBy = "unit")
    private List<BonusOptionUnit> optionJoins;
    @OneToMany(mappedBy = "unit")
    private List<Reservation> reservations;
    
    public HousingUnit() { }

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

	public Address getAdress() {
		return adress;
	}

	public void setAdress(Address adress) {
		this.adress = adress;
	}

	public User getAgent() {
		return agent;
	}

	public void setAgent(User agent) {
		this.agent = agent;
	}

	public UnitType getType() {
		return type;
	}

	public void setType(UnitType type) {
		this.type = type;
	}

	public List<BonusOptionUnit> getOptionJoins() {
		return optionJoins;
	}

	public void setOptionJoins(List<BonusOptionUnit> optionJoins) {
		this.optionJoins = optionJoins;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
    
}
