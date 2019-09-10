package com.megatravel.agentskaaplikacija.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.agentskaaplikacija.dtos.UnitTypeDTO;
import com.megatravel.agentskaaplikacija.model.UnitType;
import com.megatravel.agentskaaplikacija.repositories.UnitTypesRepository;

@RestController
@RequestMapping(value = "/rest/unit-type", produces = MediaType.APPLICATION_JSON_VALUE)
public class UnitTypesController {

	@Autowired
	private UnitTypesRepository unitTypesRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UnitTypeDTO>> getAll() {
		List<UnitTypeDTO> result = new ArrayList<>();
		for(UnitType type : this.unitTypesRepository.findAll()) {
			result.add(new UnitTypeDTO(type));
		}
		return new ResponseEntity<List<UnitTypeDTO>>(result, HttpStatus.OK);
	}
	
}
