package com.megatravel.poruka.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.megatravel.poruka.model.Message;

public class MessageDTO {

	private Long id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss")
	private String createdTime;
    private String content;
    private Long sender;
    private Long receiver;
	
    public MessageDTO() { }
    
    public MessageDTO(Message message) { 
    	this.id = message.getId();
    	this.createdTime = message.getCreatedTime().toString();
    	this.content = message.getContent();
    	this.sender = message.getSender();
    	this.receiver = message.getReceiver();
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

	public Long getSender() {
		return sender;
	}

	public void setSender(Long sender) {
		this.sender = sender;
	}

	public Long getReceiver() {
		return receiver;
	}

	public void setReceiver(Long receiver) {
		this.receiver = receiver;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
    
}
