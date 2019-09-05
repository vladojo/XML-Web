package com.megatravel.smestaj.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.smestaj.dtos.UnitTypeDTO;
import com.megatravel.smestaj.model.UnitType;
import com.megatravel.smestaj.repositories.UnitTypesRepository;

@Component
public class UnitTypesService {

	@Autowired
	private UnitTypesRepository unitTypesRepository;
	
	public UnitType findTypeById(Long id) {
		Optional<UnitType> type = this.unitTypesRepository.findById(id);
		if(!type.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		return type.get();
	}
	
	public List<UnitType> findAll() {
		return this.unitTypesRepository.findAll();
	}
	
	public void delete(Long id) {
		Optional<UnitType> type = this.unitTypesRepository.findById(id);
		if(!type.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		if(!type.get().getUnits().isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		this.unitTypesRepository.delete(type.get());
	}
	
	public UnitType create(UnitTypeDTO unitTypeDTO) {
		UnitType type = new UnitType();
		type.setName(unitTypeDTO.getName());
		this.unitTypesRepository.save(type);
		return type;
	}
	
	public UnitType update(Long id, UnitTypeDTO unitTypeDTO) {
		Optional<UnitType> typeOpt = this.unitTypesRepository.findById(id);
		if(!typeOpt.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		UnitType type = typeOpt.get();
		type.setName(unitTypeDTO.getName());
		this.unitTypesRepository.save(type);
		return type;
	}
	
}
