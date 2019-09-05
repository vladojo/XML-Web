package com.megatravel.ocena.soap;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.megatravel.ocena.model.Rating;
import com.megatravel.ocena.repositories.RatingRepository;

@Endpoint
public class MessagesEndpoint {

	private static final String NAMESPACE_URI = "http://www.megatravel.com/ocena/soap";

	@Autowired
	private RatingRepository ratingRepository;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "allRatingsRequest")
	@ResponsePayload
	public AllRatingsResponse sviKorisnici(@RequestPayload AllRatingsRequest request) {
		List<Rating> ratings = ratingRepository.findAll();
		List<RatingDTO> ratingsDTO = new ArrayList<RatingDTO>();
		for(Rating rating : ratings) {
			RatingDTO ratingDTO = new RatingDTO();
			ratingDTO.setId(rating.getId());
			ratingDTO.setValue(rating.getValue());
			ratingDTO.setReservation(rating.getReservation());
			ratingsDTO.add(ratingDTO);
		}
		AllRatingsResponse response = new AllRatingsResponse();
		response.getRatings().addAll(ratingsDTO);
		return response;
	}
	
}
