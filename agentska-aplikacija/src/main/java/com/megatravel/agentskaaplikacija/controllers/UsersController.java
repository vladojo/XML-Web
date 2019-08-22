package com.megatravel.agentskaaplikacija.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.agentskaaplikacija.dtos.UserDTO;
import com.megatravel.agentskaaplikacija.model.User;
import com.megatravel.agentskaaplikacija.services.UserService;

//!!!!!

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
	
}
