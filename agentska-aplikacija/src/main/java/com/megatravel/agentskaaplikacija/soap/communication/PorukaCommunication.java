package com.megatravel.agentskaaplikacija.soap.communication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.megatravel.agentskaaplikacija.model.Message;
import com.megatravel.agentskaaplikacija.repositories.MessagesRepository;
import com.megatravel.agentskaaplikacija.repositories.UserRepository;
import com.megatravel.agentskaaplikacija.soap.AllMessagesRequest;
import com.megatravel.agentskaaplikacija.soap.AllMessagesResponse;
import com.megatravel.agentskaaplikacija.soap.MessageDTO;

@Service
public class PorukaCommunication {

	private static final String URI = "http://localhost:8082/poruka/services";
	
	@Autowired
	private Jaxb2Marshaller marshaller;
	
	private WebServiceTemplate template;
	
	@Autowired
	private MessagesRepository messagesRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public void syncMessages() {
		this.template = new WebServiceTemplate(this.marshaller);
		AllMessagesResponse response = (AllMessagesResponse) template.marshalSendAndReceive(URI, new AllMessagesRequest());
		for(MessageDTO messageDTO : response.getMessages()) {
			if(messagesRepository.findById(messageDTO.getId()).isPresent()) continue;
			Message message = new Message();
			message.setContent(messageDTO.getContent());
			message.setId(messageDTO.getId());
			message.setReceiver(userRepository.getOne(messageDTO.getReceiver()));
			message.setSender(userRepository.getOne(messageDTO.getSender()));
			message.setCreatedTime(messageDTO.getCreatedTime().toGregorianCalendar().getTime());
			messagesRepository.save(message);
		}
	}
	
}