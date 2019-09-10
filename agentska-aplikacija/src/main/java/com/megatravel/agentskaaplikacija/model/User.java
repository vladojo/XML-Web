package com.megatravel.agentskaaplikacija.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(length = 32, nullable = false)
	private String firstName;
	@Column(length = 32, nullable = false)
	private String lastName;
	@Column(length = 32, nullable = false, unique = true)
	private String email;
	@Column(length = 32, nullable = false)
	private String password;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserState state;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserType type;
	@Column(length = 10, nullable = true, unique = true)
	private String workCertificateNumber;
	@ManyToOne
	private Address address;
	@OneToMany(mappedBy = "receiver")
	private List<Message> received;
	@OneToMany(mappedBy = "sender")
	private List<Message> sent;
	@OneToMany(mappedBy = "agent")
	private List<HousingUnit> units;
	@OneToMany(mappedBy = "user")
	private List<Reservation> reservations;
	
	public User() { }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserState getState() {
		return state;
	}

	public void setState(UserState state) {
		this.state = state;
	}

	public String getWorkCertificateNumber() {
		return workCertificateNumber;
	}

	public void setWorkCertificateNumber(String workCertificateNumber) {
		this.workCertificateNumber = workCertificateNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Message> getReceived() {
		return received;
	}

	public void setReceived(List<Message> received) {
		this.received = received;
	}

	public List<Message> getSent() {
		return sent;
	}

	public void setSent(List<Message> sent) {
		this.sent = sent;
	}

	public List<HousingUnit> getUnits() {
		return units;
	}

	public void setUnits(List<HousingUnit> units) {
		this.units = units;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}
	
}
