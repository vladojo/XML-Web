package com.megatravel.poruka.communications;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.megatravel.poruka.dtos.UserDTO;

@FeignClient("korisnik")
public interface KorisnikCommunication {

	@RequestMapping(method = RequestMethod.GET,
			value = "/rest/all-users/{id}")
	public ResponseEntity<UserDTO> findUser(@PathVariable("id") Long id);

}