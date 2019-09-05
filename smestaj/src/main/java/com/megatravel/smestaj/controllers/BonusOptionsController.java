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

import com.megatravel.smestaj.dtos.OptionDTO;
import com.megatravel.smestaj.model.BonusOption;
import com.megatravel.smestaj.services.OptionService;

@RestController
@RequestMapping(value = "/rest/bonus-options", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BonusOptionsController {

	@Autowired
	private OptionService optionService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<OptionDTO>> findAll() {
		List<OptionDTO> result = new ArrayList<>();
		for(BonusOption option : this.optionService.findAll()) {
			result.add(new OptionDTO(option));
		}
		return new ResponseEntity<List<OptionDTO>>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}",
			method = RequestMethod.GET)
	public ResponseEntity<OptionDTO> findById(@PathVariable("id") Long id) {
		return new ResponseEntity<OptionDTO>(new OptionDTO(this.optionService.findOptionById(id)), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OptionDTO> create(@RequestBody OptionDTO optionDTO) {
		return new ResponseEntity<OptionDTO>(new OptionDTO(this.optionService.create(optionDTO)), HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			value = "/{id}")
	public ResponseEntity<OptionDTO> update(@PathVariable("id") Long id,
			@RequestBody OptionDTO optionDTO) {
		return new ResponseEntity<OptionDTO>(new OptionDTO(this.optionService.update(id, optionDTO)), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE,
			value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		this.optionService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
