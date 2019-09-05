package com.megatravel.korisnik.soap;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.megatravel.korisnik.model.User;
import com.megatravel.korisnik.repositories.UserRepository;
import com.megatravel.korisnik.services.LoginRegistrationService;

@Endpoint
public class UsersEndpoint {

	private static final String NAMESPACE_URI = "http://www.megatravel.com/korisnik/soap";

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LoginRegistrationService loginRegistrationService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "allUsersRequest")
	@ResponsePayload
	public AllUsersResponse sviKorisnici(@RequestPayload AllUsersRequest request) {
		List<User> uesrs = userRepository.findAll();
		List<UserDTO> usersDTO = new ArrayList<UserDTO>();
		for(User user : uesrs) {
			UserDTO userDTO = new UserDTO();
			userDTO.setId(user.getId());
			userDTO.setFirstName(user.getFirstName());
			userDTO.setLastName(user.getLastName());
			userDTO.setEmail(user.getEmail());
			userDTO.setWorkCertificateNumber(user.getWorkCertificateNumber());
			userDTO.setPassword(user.getPassword());
			switch(user.getState()) {
				case ACTIVE : userDTO.setState(UserState.ACTIVE); break;
				case BLOCKED : userDTO.setState(UserState.BLOCKED); break;
			}
			switch(user.getType()) {
				case AGENT : userDTO.setType(UserType.AGENT); break;
				case ADMIN : userDTO.setType(UserType.ADMIN); break;
				case USER : userDTO.setType(UserType.USER); break;
			}
			usersDTO.add(userDTO);
		}
		AllUsersResponse response = new AllUsersResponse();
		response.getUsers().addAll(usersDTO);
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "agentLoginRequest")
	@ResponsePayload
	public AgentLoginResponse logovanjeAgenta(@RequestPayload AgentLoginRequest request) {
		loginRegistrationService.agentLogin(request.getEmail(), request.getPassword());
		AgentLoginResponse response = new AgentLoginResponse();
		response.setValid(true);
		return response;
	}
	
}
