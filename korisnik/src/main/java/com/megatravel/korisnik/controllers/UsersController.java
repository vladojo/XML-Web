package com.megatravel.korisnik.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.korisnik.dtos.UserDTO;
import com.megatravel.korisnik.model.User;
import com.megatravel.korisnik.model.UserState;
import com.megatravel.korisnik.services.UserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsersController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET,
			value = "/rest/users",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDTO>> findAll() {
		List<UserDTO> result = new ArrayList<>();
		for(User user : this.userService.findAll()) {
			result.add(new UserDTO(user));
		}
		return new ResponseEntity<List<UserDTO>>(result, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST,
			value = "/rest/agents",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> addAgent(@RequestBody UserDTO agentDTO) {
		UserDTO agent = new UserDTO(this.userService.addAgent(agentDTO));
		return new ResponseEntity<UserDTO>(agent, HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT,
			value = "/rest/users/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> changeState(@PathVariable("id") Long id, @RequestParam("state") UserState state) {
		this.userService.changeUserState(id, state);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET,
			value = "/rest/all-users/{id}")
	public ResponseEntity<UserDTO> findUser(@PathVariable("id") Long id) {
		UserDTO userDTO = new UserDTO(this.userService.findById(id));
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
	}
	
}
