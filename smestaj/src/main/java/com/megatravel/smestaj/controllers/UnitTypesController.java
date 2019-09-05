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

import com.megatravel.smestaj.dtos.UnitTypeDTO;
import com.megatravel.smestaj.model.UnitType;
import com.megatravel.smestaj.services.UnitTypesService;

@RestController
@RequestMapping(value = "/rest/unit-type", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UnitTypesController {

	@Autowired
	private UnitTypesService unitTypesService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UnitTypeDTO>> findAll() {
		List<UnitTypeDTO> result = new ArrayList<>();
		for(UnitType type : this.unitTypesService.findAll()) {
			result.add(new UnitTypeDTO(type));
		}
		return new ResponseEntity<List<UnitTypeDTO>>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}",
			method = RequestMethod.GET)
	public ResponseEntity<UnitTypeDTO> findById(@PathVariable("id") Long id) {
		return new ResponseEntity<UnitTypeDTO>(new UnitTypeDTO(this.unitTypesService.findTypeById(id)), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UnitTypeDTO> create(@RequestBody UnitTypeDTO unitTypeDTO) {
		return new ResponseEntity<UnitTypeDTO>(new UnitTypeDTO(this.unitTypesService.create(unitTypeDTO)), HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			value = "/{id}")
	public ResponseEntity<UnitTypeDTO> update(@PathVariable("id") Long id,
			@RequestBody UnitTypeDTO unitTypeDTO) {
		return new ResponseEntity<UnitTypeDTO>(new UnitTypeDTO(this.unitTypesService.update(id, unitTypeDTO)), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE,
			value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		this.unitTypesService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
