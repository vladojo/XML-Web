package com.megatravel.agentskaaplikacija.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.agentskaaplikacija.dtos.HousingUnitDTO;
import com.megatravel.agentskaaplikacija.model.HousingUnit;
import com.megatravel.agentskaaplikacija.services.HousingUnitsService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HousingUnitsController {

	@Autowired
	private HousingUnitsService housingUnitsService;
	
	@RequestMapping(method = RequestMethod.GET,
			value = "/rest/units/search",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<HousingUnitDTO>> search(@RequestParam("country") String country,
			@RequestParam(name = "city", required = false) String city,
			@RequestParam(name = "people", required = false) int people,
			@RequestParam(name = "start", required = false) Date start,
			@RequestParam(name = "end", required = false) Date end) {
		List<HousingUnitDTO> results = new ArrayList<HousingUnitDTO>();
		for(HousingUnit unit : this.housingUnitsService.search(country, city, people, start, end)) {
			results.add(new HousingUnitDTO(unit));
		}
		return new ResponseEntity<List<HousingUnitDTO>>(results, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST,
			value = "/rest/units",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HousingUnitDTO> create(@RequestBody HousingUnitDTO housingUnitDTO) {
		return new ResponseEntity<HousingUnitDTO>(new HousingUnitDTO(this.housingUnitsService.create(housingUnitDTO)), HttpStatus.CREATED);
	}
	
}
