package com.megatravel.smestaj.communications;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.megatravel.smestaj.dtos.MessageDTO;

@FeignClient("poruka")
public interface PorukaCommunication {

	@RequestMapping(value = "/rest/users/{id}/chats",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageDTO> sendMessage(@PathVariable("id") Long id,
			@RequestBody MessageDTO messageDTO,
			@RequestParam("agent") Long agent);
	
}
