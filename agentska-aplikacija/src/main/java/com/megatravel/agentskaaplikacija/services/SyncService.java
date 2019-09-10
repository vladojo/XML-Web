package com.megatravel.agentskaaplikacija.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.megatravel.agentskaaplikacija.soap.communication.KorisnikCommunication;
import com.megatravel.agentskaaplikacija.soap.communication.OcenaCommunication;
import com.megatravel.agentskaaplikacija.soap.communication.PorukaCommunication;
import com.megatravel.agentskaaplikacija.soap.communication.SmestajCommunication;

@Component
public class SyncService {

	@Autowired
	private KorisnikCommunication korisnikCommunication;
	
	@Autowired
	private OcenaCommunication ocenaCommunication;
	
	@Autowired
	private PorukaCommunication porukaCommunication;
	
	@Autowired
	private SmestajCommunication smestajCommunication;
	
	@PostConstruct
	public void sync() {
		korisnikCommunication.syncUsers();
		porukaCommunication.syncMessages();
		smestajCommunication.syncTypes();
		smestajCommunication.syncOptions();
		smestajCommunication.syncUnits();
		smestajCommunication.syncReservations();
		ocenaCommunication.syncRatings();
	}
	
}
