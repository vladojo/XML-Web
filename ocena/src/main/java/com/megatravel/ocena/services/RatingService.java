package com.megatravel.ocena.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.ocena.communications.SmestajCommunication;
import com.megatravel.ocena.model.Rating;
import com.megatravel.ocena.repositories.RatingRepository;

@Component
public class RatingService {

	@Autowired
	private RatingRepository ratingRepository;
	
	@Autowired
	private SmestajCommunication smestajCommunication;
	
	public Rating giveRating(Long reservation, int value, Long user) {
		if(!this.userCanRate(reservation, user)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		Rating rating = new Rating();
		rating.setReservation(reservation);
		rating.setValue(value);
		this.ratingRepository.save(rating);
		return rating;
	}
	
	private boolean userCanRate(Long reservation, Long user) {
		ResponseEntity<Void> response = smestajCommunication.userCanRate(user, reservation);
		return response.getStatusCode().is2xxSuccessful();
	}
	
}
