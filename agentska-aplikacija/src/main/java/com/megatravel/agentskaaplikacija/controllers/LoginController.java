package com.megatravel.agentskaaplikacija.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.agentskaaplikacija.dtos.LoginRequestDTO;
import com.megatravel.agentskaaplikacija.dtos.UserDTO;
import com.megatravel.agentskaaplikacija.services.LoginService;

@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value = "/login",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
		return new ResponseEntity<UserDTO>(new UserDTO(this.loginService.login(loginRequestDTO)), HttpStatus.OK);
	}
	
}
