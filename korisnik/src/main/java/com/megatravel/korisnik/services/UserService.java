package com.megatravel.korisnik.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll() {
		List<User> users = new ArrayList<>();
		for(User user : this.userRepository.findAll()) {
			if(user.getType().equals(UserType.USER)) {
				users.add(user);
			}
		}
		return users;
	}
	
	public User changeUserState(Long id, UserState state) {
		Optional<User> userOpt = this.userRepository.findById(id);
		if(!userOpt.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} else {
			User user = userOpt.get();
			user.setState(state);
			this.userRepository.save(user);
			return user;
		}
	}
	
	public User addAgent(UserDTO userDTO) {
		User agent = new User();
		String email = userDTO.getEmail();
		if(this.findByEmail(email) != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		} else {
			agent.setEmail(userDTO.getEmail());
			agent.setFirstName(userDTO.getFirstName());
			agent.setLastName(userDTO.getLastName());
			agent.setPassword(userDTO.getPassword());
			agent.setState(UserState.ACTIVE);
			agent.setType(UserType.AGENT);
			agent.setWorkCertificateNumber(userDTO.getWorkCertificateNumber());
			this.userRepository.save(agent);
			return agent;
		}
	}
	
	public User findByEmail(String email) {
		List<User> users = this.userRepository.findAll();
		for(User user : users) {
			if(user.getEmail().contentEquals(email)) {
				return user;
			}
		}
		return null;
	}

	public User findById(Long id) {
		Optional<User> userOpt = this.userRepository.findById(id);
		if(userOpt.isPresent()) {
			return userOpt.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
}
