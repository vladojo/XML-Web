package com.megatravel.ocena.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.ocena.dtos.RatingDTO;
import com.megatravel.ocena.services.RatingService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RatingController {

	@Autowired
	private RatingService ratingService;
	
	@RequestMapping(value = "/rest/reservations/{id}",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RatingDTO> giveRating(@PathVariable("id") Long id, @RequestParam("value") int value, @RequestParam("user") Long user) {
		RatingDTO ratingDTO = new RatingDTO(this.ratingService.giveRating(id, value, user));
		return new ResponseEntity<RatingDTO>(ratingDTO, HttpStatus.CREATED);
	}
	
}
