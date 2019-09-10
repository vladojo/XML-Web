package com.megatravel.agentskaaplikacija.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.megatravel.agentskaaplikacija.model.Message;

public class MessageDTO {

	private Long id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss")
	private String createdTime;
    private String content;
    private UserDTO sender;
    private UserDTO receiver;
	
    public MessageDTO() { }
    
    public MessageDTO(Message message) { 
    	this.id = message.getId();
    	this.createdTime = message.getCreatedTime().toString();
    	this.content = message.getContent();
    	this.sender = new UserDTO(message.getSender());
    	this.receiver = new UserDTO(message.getReceiver());
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public UserDTO getSender() {
		return sender;
	}

	public void setSender(UserDTO sender) {
		this.sender = sender;
	}

	public UserDTO getReceiver() {
		return receiver;
	}

	public void setReceiver(UserDTO receiver) {
		this.receiver = receiver;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
    
}
