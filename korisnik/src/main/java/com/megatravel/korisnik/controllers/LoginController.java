package com.megatravel.korisnik.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.korisnik.dtos.LoginRequestDTO;
import com.megatravel.korisnik.dtos.UserDTO;
import com.megatravel.korisnik.services.LoginRegistrationService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {

	@Autowired
	private LoginRegistrationService loginService;
	
	@RequestMapping(value = "/login",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
		return new ResponseEntity<UserDTO>(new UserDTO(this.loginService.login(loginRequestDTO.getEmail(), loginRequestDTO.getPassword())), HttpStatus.OK);
	}
	
}
