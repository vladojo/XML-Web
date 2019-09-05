package com.megatravel.korisnik.dtos;

import com.megatravel.korisnik.model.User;
import com.megatravel.korisnik.model.UserState;
import com.megatravel.korisnik.model.UserType;

public class UserDTO {

    private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private UserState state;
	private UserType type;
	private String workCertificateNumber;
	
	public UserDTO() { }
	
	public UserDTO(User user) { 
		this.id = user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.state = user.getState();
		this.type = user.getType();
		this.workCertificateNumber = user.getWorkCertificateNumber();
	}

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

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public String getWorkCertificateNumber() {
		return workCertificateNumber;
	}

	public void setWorkCertificateNumber(String workCertificateNumber) {
		this.workCertificateNumber = workCertificateNumber;
	}
	
}
