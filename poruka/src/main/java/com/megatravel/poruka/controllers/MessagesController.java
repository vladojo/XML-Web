package com.megatravel.poruka.controllers;

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

import com.megatravel.poruka.dtos.MessageDTO;
import com.megatravel.poruka.dtos.UserDTO;
import com.megatravel.poruka.model.Message;
import com.megatravel.poruka.services.MessagesService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MessagesController {

	@Autowired
	private MessagesService messagesService;
	
	@RequestMapping(value = "/rest/users/{id}/chats",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDTO>> getChats(@PathVariable("id") Long id) {
		List<UserDTO> result = messagesService.getChats(id);
		return new ResponseEntity<List<UserDTO>>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rest/users/{id}/chats-with",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MessageDTO>> getMessagesWith(@PathVariable("id") Long id,
			@RequestParam("agent") Long agent) {
		List<MessageDTO> result = new ArrayList<>();
		for(Message message : this.messagesService.getMessagesBetween(id, agent)) {
			result.add(new MessageDTO(message));
		}
		return new ResponseEntity<List<MessageDTO>>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rest/users/{id}/chats",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageDTO> sendMessage(@PathVariable("id") Long id,
			@RequestBody MessageDTO messageDTO,
			@RequestParam("agent") Long agent) {
		return new ResponseEntity<MessageDTO>(new MessageDTO(this.messagesService.sendMessage(id, agent, messageDTO.getContent())), HttpStatus.CREATED);
	}
	
}
