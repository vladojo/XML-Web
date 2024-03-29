package com.megatravel.agentskaaplikacija.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.agentskaaplikacija.dtos.HousingUnitDTO;
import com.megatravel.agentskaaplikacija.model.HousingUnit;
import com.megatravel.agentskaaplikacija.services.HousingUnitsService;

@RestController
public class HousingUnitsController {

	@Autowired
	private HousingUnitsService housingUnitsService;
	
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
	
	@RequestMapping(method = RequestMethod.GET,
			value = "/rest/units",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<HousingUnitDTO>> getAgentsUnits(@RequestParam("agent") Long agent) {
		List<HousingUnitDTO> results = new ArrayList<HousingUnitDTO>();
		for(HousingUnit unit : this.housingUnitsService.getAgentsUnits(agent)) {
			results.add(new HousingUnitDTO(unit));
		}
		return new ResponseEntity<List<HousingUnitDTO>>(results, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST,
			value = "/rest/units",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HousingUnitDTO> create(@RequestBody HousingUnitDTO housingUnitDTO,
			@RequestParam("agent") Long agent) {
		return new ResponseEntity<HousingUnitDTO>(new HousingUnitDTO(this.housingUnitsService.create(housingUnitDTO, agent)), HttpStatus.CREATED);
	}
	
}
