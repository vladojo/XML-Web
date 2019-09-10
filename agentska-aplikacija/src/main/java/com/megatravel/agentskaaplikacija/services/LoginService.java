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
import com.megatravel.agentskaaplikacija.soap.communication.KorisnikCommunication;

@Service
public class LoginService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private KorisnikCommunication korisnikCommunication;
	
	@Autowired
	private SyncService syncService;
	
	public User login(LoginRequestDTO loginRequestDTO) {
		String email = loginRequestDTO.getEmail();
		String password = loginRequestDTO.getPassword();
		List<User> users = this.userRepository.findAll();
		for(User user : users) {
			if(user.getEmail().contentEquals(email) &&
					user.getType().equals(UserType.AGENT) &&
					user.getPassword().contentEquals(password) &&
					korisnikCommunication.loginAgent(email, password)) {
				syncService.sync();
				return user;
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

}
