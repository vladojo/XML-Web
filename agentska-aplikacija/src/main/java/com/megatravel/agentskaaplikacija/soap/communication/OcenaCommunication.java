package com.megatravel.agentskaaplikacija.soap.communication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.megatravel.agentskaaplikacija.model.Rating;
import com.megatravel.agentskaaplikacija.repositories.RatingsRepository;
import com.megatravel.agentskaaplikacija.repositories.ReservationsRepository;
import com.megatravel.agentskaaplikacija.soap.AllRatingsRequest;
import com.megatravel.agentskaaplikacija.soap.AllRatingsResponse;
import com.megatravel.agentskaaplikacija.soap.RatingDTO;

@Service
public class OcenaCommunication {

	private static final String URI = "http://localhost:8082/ocena/services";
	
	@Autowired
	private Jaxb2Marshaller marshaller;
	
	private WebServiceTemplate template;
	
	@Autowired
	private RatingsRepository ratingsRepository;
	
	@Autowired
	private ReservationsRepository reservationsRepository;
	
	public void syncRatings() {
		this.template = new WebServiceTemplate(this.marshaller);
		AllRatingsResponse response = (AllRatingsResponse) template.marshalSendAndReceive(URI, new AllRatingsRequest());
		for(RatingDTO ratingDTO : response.getRatings()) {
			if(ratingsRepository.findById(ratingDTO.getId()).isPresent()) continue;
			Rating rating = new Rating();
			rating.setId(ratingDTO.getId());
			rating.setValue(ratingDTO.getValue());
			rating.setReservation(reservationsRepository.getOne(ratingDTO.getReservation()));
			ratingsRepository.save(rating);
		}
	}
	
}
