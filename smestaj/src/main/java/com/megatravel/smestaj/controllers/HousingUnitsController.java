package com.megatravel.smestaj.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.smestaj.dtos.HousingUnitDTO;
import com.megatravel.smestaj.model.HousingUnit;
import com.megatravel.smestaj.services.HousingUnitsService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HousingUnitsController {

	@Autowired
	private HousingUnitsService housingUnitsService;
	
	@RequestMapping(method = RequestMethod.GET,
			value = "/rest/units",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<HousingUnitDTO>> findAll() {
			List<HousingUnitDTO> results = new ArrayList<HousingUnitDTO>();
			for(HousingUnit unit : this.housingUnitsService.findAll()) {
				results.add(new HousingUnitDTO(unit));
			}
			return new ResponseEntity<List<HousingUnitDTO>>(results, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET,
			value = "/rest/units/search",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<HousingUnitDTO>> search(@RequestParam(name = "country", required = false) String country,
			@RequestParam(name = "city", required = false) String city,
			@RequestParam(name = "people", required = false) Integer people,
			@RequestParam(name = "start", required = false) String startString,
			@RequestParam(name = "end", required = false) String endString) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date start = null;
			Date end = null;
			if(startString != null && endString != null) {
				start = format.parse(startString);
				end = format.parse(endString);
			}
			List<HousingUnitDTO> results = new ArrayList<HousingUnitDTO>();
			for(HousingUnit unit : this.housingUnitsService.search(country, city, people, start, end)) {
				results.add(new HousingUnitDTO(unit));
			}
			return new ResponseEntity<List<HousingUnitDTO>>(results, HttpStatus.OK);
		} catch (ParseException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/rest/units/users-can-communicate",
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.GET)
	public ResponseEntity<Void> usersCanCommunicate(@RequestParam("agent") Long agent, @RequestParam("user") Long user) {
		this.housingUnitsService.usersCanCommunicate(agent, user);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rest/units/user-can-rate",
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.GET)
	public ResponseEntity<Void> userCanRate(@RequestParam("user") Long user, @RequestParam("reservation") Long reservation) {
		this.housingUnitsService.userCanRate(user, reservation);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
