package com.megatravel.poruka.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.poruka.communications.KorisnikCommunication;
import com.megatravel.poruka.communications.SmestajCommunication;
import com.megatravel.poruka.dtos.UserDTO;
import com.megatravel.poruka.model.Message;
import com.megatravel.poruka.repositories.MessagesRepository;

@Service
public class MessagesService {

	@Autowired
	private MessagesRepository messagesRepository;

	@Autowired
	private SmestajCommunication smestajCommunication;
	
	@Autowired
	private KorisnikCommunication korisnikCommunication;
	
	public List<UserDTO> getChats(Long id) {
		List<Long> usersID = new ArrayList<Long>();
		List<Message> messages = this.messagesRepository.findAll();
		for(Message message : messages) {
			if(message.getSender().equals(id)) usersID.add(message.getReceiver());
			if(message.getReceiver().equals(id)) usersID.add(message.getSender());
		}
		List<UserDTO> dtos = new ArrayList<UserDTO>();
		for(Long userId : usersID) {
			dtos.add(this.getUser(userId));
		}
		return dtos;
	}

	public List<Message> getMessagesBetween(Long id1, Long id2) {
		List<Message> messages = this.messagesRepository.findAll();
		List<Message> messagesBetween = new ArrayList<Message>();
		for(Message message : messages) {
			if(message.getReceiver().equals(id1) && message.getSender().equals(id2)) messagesBetween.add(message);
			if(message.getReceiver().equals(id2) && message.getSender().equals(id1)) messagesBetween.add(message);
		}
		return messagesBetween;
	}

	public Message sendMessage(Long senderId, Long receiverId, String content) {
		canCommunicate(senderId, receiverId);
		Message message = new Message();
		message.setContent(content);
		message.setSender(senderId);
		message.setReceiver(receiverId);
		return this.messagesRepository.save(message);
	}

	private UserDTO getUser(Long id) {
		ResponseEntity<UserDTO> response = korisnikCommunication.findUser(id);
		if(response.getStatusCode().is2xxSuccessful()) {
			return response.getBody();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	private void canCommunicate(Long senderId, Long receiverId) {
		ResponseEntity<Void> response = smestajCommunication.usersCanCommunicate(receiverId, senderId);
		if(!response.getStatusCode().is2xxSuccessful()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
	}

}
