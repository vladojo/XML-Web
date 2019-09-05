package com.megatravel.poruka.communications;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("smestaj")
public interface SmestajCommunication {

	@RequestMapping(value = "/rest/units/users-can-communicate",
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.GET)
	public ResponseEntity<Void> usersCanCommunicate(@RequestParam("agent") Long agent, @RequestParam("user") Long user);
	
}
