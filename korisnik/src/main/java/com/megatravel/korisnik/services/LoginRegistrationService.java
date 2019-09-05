package com.megatravel.korisnik.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.korisnik.dtos.UserDTO;
import com.megatravel.korisnik.model.User;
import com.megatravel.korisnik.model.UserState;
import com.megatravel.korisnik.model.UserType;
import com.megatravel.korisnik.repositories.UserRepository;

@Service
public class LoginRegistrationService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	public User login(String email, String password) {
		User user = userService.findByEmail(email);
		if(user == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} else {
			if(user.getType().equals(UserType.AGENT)) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
			if(user.getPassword().contentEquals(password)) {
				return user;
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			}
		}
	}
	
	public User register(UserDTO userDTO) {
		User user = new User();
		String email = userDTO.getEmail();
		if(userService.findByEmail(email) != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		} else {
			user.setEmail(userDTO.getEmail());
			user.setFirstName(userDTO.getFirstName());
			user.setLastName(userDTO.getLastName());
			user.setPassword(userDTO.getPassword());
			user.setState(UserState.ACTIVE);
			user.setType(UserType.USER);
			user.setWorkCertificateNumber(userDTO.getWorkCertificateNumber());
			this.userRepository.save(user);
			return user;
		}
	}

	public User agentLogin(String email, String password) {
		User user = userService.findByEmail(email);
		if(user == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} else {
			if(!user.getType().equals(UserType.AGENT)) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
			if(user.getPassword().contentEquals(password)) {
				return user;
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			}
		}
	}
	
}
