package com.megatravel.agentskaaplikacija.soap.communication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.megatravel.agentskaaplikacija.model.User;
import com.megatravel.agentskaaplikacija.repositories.UserRepository;
import com.megatravel.agentskaaplikacija.soap.AgentLoginRequest;
import com.megatravel.agentskaaplikacija.soap.AgentLoginResponse;
import com.megatravel.agentskaaplikacija.soap.AllUsersRequest;
import com.megatravel.agentskaaplikacija.soap.AllUsersResponse;
import com.megatravel.agentskaaplikacija.soap.UserDTO;

@Service
public class KorisnikCommunication {

	private static final String URI = "http://localhost:8082/korisnik/services";
	
	@Autowired
	private Jaxb2Marshaller marshaller;
	
	private WebServiceTemplate template;
	
	@Autowired
	private UserRepository userRepository;
	
	public void syncUsers() {
		this.template = new WebServiceTemplate(this.marshaller);
		AllUsersResponse response = (AllUsersResponse) template.marshalSendAndReceive(URI, new AllUsersRequest());
		for(UserDTO userDTO : response.getUsers()) {
			if(userRepository.findById(userDTO.getId()).isPresent()) continue;
			User user = new User();
			user.setId(userDTO.getId());
			user.setFirstName(userDTO.getFirstName());
			user.setLastName(userDTO.getLastName());
			user.setEmail(userDTO.getEmail());
			user.setWorkCertificateNumber(userDTO.getWorkCertificateNumber());
			user.setPassword(userDTO.getPassword());
			switch(userDTO.getState()) {
				case ACTIVE : user.setState(com.megatravel.agentskaaplikacija.model.UserState.ACTIVE); break;
				case BLOCKED : user.setState(com.megatravel.agentskaaplikacija.model.UserState.BLOCKED); break;
			}
			switch(userDTO.getType()) {
				case AGENT : user.setType(com.megatravel.agentskaaplikacija.model.UserType.AGENT); break;
				case ADMIN : user.setType(com.megatravel.agentskaaplikacija.model.UserType.ADMIN); break;
				case USER : user.setType(com.megatravel.agentskaaplikacija.model.UserType.USER); break;
			}
			userRepository.save(user);
		}
	}

	public boolean loginAgent(String email, String password) {
		this.template = new WebServiceTemplate(this.marshaller);
		AgentLoginRequest request = new AgentLoginRequest();
		request.setEmail(email);
		request.setPassword(password);
		AgentLoginResponse response = (AgentLoginResponse) template.marshalSendAndReceive(URI, request);
		return response.isValid();
	}
	
}