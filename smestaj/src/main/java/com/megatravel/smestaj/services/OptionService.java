package com.megatravel.smestaj.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.smestaj.dtos.OptionDTO;
import com.megatravel.smestaj.model.BonusOption;
import com.megatravel.smestaj.repositories.OptionRepository;

@Component
public class OptionService {

	@Autowired
	private OptionRepository repository;

	public List<BonusOption> findAll() {
		return repository.findAll();
	}

	public BonusOption findOptionById(Long id) {
		Optional<BonusOption> optionOpt = this.repository.findById(id);
		if(optionOpt.isPresent()) {
			return optionOpt.get();
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	public BonusOption create(OptionDTO optionDTO) {
		BonusOption bonusOption = new BonusOption();
		bonusOption.setDescription(optionDTO.getDescription());
		bonusOption.setName(optionDTO.getName());
		this.repository.save(bonusOption);
		return bonusOption;
	}

	public BonusOption update(Long id, OptionDTO optionDTO) {
		Optional<BonusOption> optionOpt = this.repository.findById(id);
		if(!optionOpt.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		BonusOption option = optionOpt.get();
		option.setDescription(optionDTO.getDescription());
		option.setName(optionDTO.getName());
		this.repository.save(option);
		return option;
	}

	public void delete(Long id) {
		Optional<BonusOption> optionOpt = this.repository.findById(id);
		if(!optionOpt.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		BonusOption option = optionOpt.get();
		if(!option.getUnitJoins().isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		this.repository.delete(option);
	}
	
}
