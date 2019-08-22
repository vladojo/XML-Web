package com.megatravel.agentskaaplikacija.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.agentskaaplikacija.dtos.LoginRequestDTO;
import com.megatravel.agentskaaplikacija.model.User;
import com.megatravel.agentskaaplikacija.model.UserType;
import com.megatravel.agentskaaplikacija.repositories.UserRepository;

@Service
public class LoginService {

	@Autowired
	private UserRepository userRepository;
	
	public User login(LoginRequestDTO loginRequestDTO) {
		String email = loginRequestDTO.getEmail();
		String password = loginRequestDTO.getPassword();
		List<User> users = this.userRepository.findAll();
		for(User user : users) {
			if(user.getType().equals(UserType.AGENT)) {
				if(user.getEmail().equals(email)) {
					if(user.getPassword().equals(password)) {
						return user;
					} else {
						throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
					}
				}
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
}
