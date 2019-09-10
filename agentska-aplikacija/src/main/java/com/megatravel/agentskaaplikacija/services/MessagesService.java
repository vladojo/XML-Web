package com.megatravel.agentskaaplikacija.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.agentskaaplikacija.model.Message;
import com.megatravel.agentskaaplikacija.model.User;
import com.megatravel.agentskaaplikacija.model.UserType;
import com.megatravel.agentskaaplikacija.repositories.MessagesRepository;
import com.megatravel.agentskaaplikacija.repositories.UserRepository;

@Service
public class MessagesService {

	@Autowired
	private MessagesRepository messagesRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getChats(Long id) {
		Optional<User> agent = userRepository.findById(id);
		if(!agent.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} else {
			if(agent.get().getType().equals(UserType.AGENT)) {
				List<User> results = new ArrayList<User>();
				for(Message message : agent.get().getSent()) {
					User user = message.getReceiver();
					if(!results.contains(user)) {
						results.add(user);
					}
				}
				for(Message message : agent.get().getReceived()) {
					User user = message.getSender();
					if(!results.contains(user)) {
						results.add(user);
					}
				}
				return results;
			} else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
		}
	}

	public List<Message> getMessagesBetween(Long id, Long userId) {
		Optional<User> agent = this.userRepository.findById(id);
		Optional<User> user = this.userRepository.findById(userId);
		if(agent.isPresent() && agent.get().getType().equals(UserType.AGENT) &&
				user.isPresent() && user.get().getType().equals(UserType.USER)) {
			List<Message> result = new ArrayList<>();
			for(Message message : agent.get().getSent()) {
				if(message.getReceiver().equals(user.get())) {
					result.add(message);
				}
			}
			for(Message message : agent.get().getReceived()) {
				if(message.getSender().equals(user.get())) {
					result.add(message);
				}
			}
			return result;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	public Message sendMessage(Long id, Long userId, String content) {
		Optional<User> agent = this.userRepository.findById(id);
		Optional<User> user = this.userRepository.findById(userId);
		if(agent.isPresent() && agent.get().getType().equals(UserType.AGENT) &&
				user.isPresent() && user.get().getType().equals(UserType.USER)) {
			Message message = new Message();
			message.setContent(content);
			message.setSender(agent.get());
			message.setReceiver(user.get());
			return this.messagesRepository.save(message);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
}
