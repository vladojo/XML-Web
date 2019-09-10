package com.megatravel.agentskaaplikacija.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megatravel.agentskaaplikacija.model.User;
import com.megatravel.agentskaaplikacija.model.UserType;
import com.megatravel.agentskaaplikacija.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll() {
		List<User> users = new ArrayList<>();
		for(User user : this.userRepository.findAll()) {
			if(user.getType().equals(UserType.USER)) {
				users.add(user);
			}
		}
		return users;
	}
	
}
