package com.megatravel.agentskaaplikacija.controllers;

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

import com.megatravel.agentskaaplikacija.dtos.MessageDTO;
import com.megatravel.agentskaaplikacija.dtos.UserDTO;
import com.megatravel.agentskaaplikacija.model.Message;
import com.megatravel.agentskaaplikacija.model.User;
import com.megatravel.agentskaaplikacija.services.MessagesService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MessagesController {

	@Autowired
	private MessagesService messagesService;
	
	@RequestMapping(value = "/rest/agents/{id}/chats",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDTO>> getChats(@PathVariable("id") Long id) {
		List<UserDTO> result = new ArrayList<>();
		for(User user : this.messagesService.getChats(id)) {
			result.add(new UserDTO(user));
		}
		return new ResponseEntity<List<UserDTO>>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rest/agents/{id}/chats-with",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MessageDTO>> getMessagesWith(@PathVariable("id") Long id,
			@RequestParam("user") Long user) {
		List<MessageDTO> result = new ArrayList<>();
		for(Message message : this.messagesService.getMessagesBetween(id, user)) {
			result.add(new MessageDTO(message));
		}
		return new ResponseEntity<List<MessageDTO>>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rest/agents/{id}/chats",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageDTO> sendMessage(@PathVariable("id") Long id,
			@RequestBody MessageDTO messageDTO,
			@RequestParam("user") Long user) {
		return new ResponseEntity<MessageDTO>(new MessageDTO(this.messagesService.sendMessage(id, user, messageDTO.getContent())), HttpStatus.CREATED);
	}
	
}
