package com.megatravel.agentskaaplikacija.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.agentskaaplikacija.dtos.ReservationDTO;
import com.megatravel.agentskaaplikacija.model.Reservation;
import com.megatravel.agentskaaplikacija.services.HousingUnitsService;
import com.megatravel.agentskaaplikacija.services.ReservationsService;

@RestController
public class ReservationsController {

	@Autowired
	private ReservationsService reservationsService;
	
	@Autowired
	private HousingUnitsService housingUnitsService;
	
	@RequestMapping(value = "/rest/units/{id}/reservations",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReservationDTO>> findReservationsOfUnit(@PathVariable("id") Long id) {
		List<ReservationDTO> result = new ArrayList<ReservationDTO>();
		for(Reservation reservation : this.housingUnitsService.getReservationsOfHousingUnit(id)) {
			result.add(new ReservationDTO(reservation));
		}
		return new ResponseEntity<List<ReservationDTO>>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rest/reservations/{id}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReservationDTO> confirmReservation(@PathVariable("id") Long id) {
		return new ResponseEntity<ReservationDTO>(new ReservationDTO(this.reservationsService.confirmReservation(id)), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST,
			value = "/rest/reservations", 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO) {
		return new ResponseEntity<ReservationDTO>(new ReservationDTO(this.reservationsService.createReservation(reservationDTO)), HttpStatus.CREATED);
	}
	
}
