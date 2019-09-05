package com.megatravel.smestaj.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.smestaj.dtos.ReservationDTO;
import com.megatravel.smestaj.model.Reservation;
import com.megatravel.smestaj.services.ReservationsService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReservationsController {

	@Autowired
	private ReservationsService reservationsService;
	
	@RequestMapping(method = RequestMethod.POST,
			value = "/rest/{user}/reservations", 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO, @PathVariable("user") Long id) {
		return new ResponseEntity<ReservationDTO>(new ReservationDTO(this.reservationsService.createReservation(reservationDTO, id)), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.DELETE,
			value = "/rest/{user}/reservations/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> cancelReservation(@PathVariable("id") Long id) {
		this.reservationsService.cancelReservation(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET,
			value = "/rest/{user}/reservations",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReservationDTO>> getReservations(@PathVariable("user") Long id) {
		List<Reservation> reservations = this.reservationsService.getUserReservations(id);
		List<ReservationDTO> reservationsDTO = new ArrayList<ReservationDTO>();
		for(Reservation reservation : reservations) {
			reservationsDTO.add(new ReservationDTO(reservation));
		}
		return new ResponseEntity<List<ReservationDTO>>(reservationsDTO, HttpStatus.OK);
	}
	
}
