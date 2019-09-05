package com.megatravel.poruka.soap;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.megatravel.poruka.model.Message;
import com.megatravel.poruka.repositories.MessagesRepository;

@Endpoint
public class MessagesEndpoint {

	private static final String NAMESPACE_URI = "http://www.megatravel.com/poruka/soap";

	@Autowired
	private MessagesRepository messagesRepository;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "allMessagesRequest")
	@ResponsePayload
	public AllMessagesResponse allMessages(@RequestPayload AllMessagesRequest request) {
		List<Message> messages = messagesRepository.findAll();
		List<MessageDTO> messagesDTO = new ArrayList<MessageDTO>();
		for(Message message : messages) {
			MessageDTO messageDTO = new MessageDTO();
			messageDTO.setContent(message.getContent());
			messageDTO.setId(message.getId());
			messageDTO.setReceiver(message.getReceiver());
			messageDTO.setSender(message.getSender());
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(message.getCreatedTime());
			try {
				messageDTO.setCreatedTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar));
			} catch (DatatypeConfigurationException e) {
				e.printStackTrace();
			}
			messagesDTO.add(messageDTO);
		}
		AllMessagesResponse response = new AllMessagesResponse();
		response.getMessages().addAll(messagesDTO);
		return response;
	}
	
}
