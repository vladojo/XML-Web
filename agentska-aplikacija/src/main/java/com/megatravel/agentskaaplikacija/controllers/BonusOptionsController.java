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

import com.megatravel.agentskaaplikacija.dtos.OptionDTO;
import com.megatravel.agentskaaplikacija.model.BonusOption;
import com.megatravel.agentskaaplikacija.repositories.OptionRepository;

@RestController
@RequestMapping(value = "/rest/bonus-options", produces = MediaType.APPLICATION_JSON_VALUE)
public class BonusOptionsController {

	@Autowired
	private OptionRepository bonusOptionRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<OptionDTO>> getAll() {
		List<OptionDTO> result = new ArrayList<>();
		for(BonusOption option : this.bonusOptionRepository.findAll()) {
			result.add(new OptionDTO(option));
		}
		return new ResponseEntity<List<OptionDTO>>(result, HttpStatus.OK);
	}
	
}
