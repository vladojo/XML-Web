package com.megatravel.agentskaaplikacija.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.agentskaaplikacija.dtos.AddressDTO;
import com.megatravel.agentskaaplikacija.dtos.HousingUnitDTO;
import com.megatravel.agentskaaplikacija.dtos.OptionDTO;
import com.megatravel.agentskaaplikacija.model.Address;
import com.megatravel.agentskaaplikacija.model.HousingUnit;
import com.megatravel.agentskaaplikacija.model.BonusOption;
import com.megatravel.agentskaaplikacija.model.BonusOptionUnit;
import com.megatravel.agentskaaplikacija.model.Reservation;
import com.megatravel.agentskaaplikacija.model.UnitType;
import com.megatravel.agentskaaplikacija.repositories.AddressRepository;
import com.megatravel.agentskaaplikacija.repositories.HousingUnitsRepository;
import com.megatravel.agentskaaplikacija.repositories.OptionRepository;
import com.megatravel.agentskaaplikacija.repositories.OptionUnitJoinRepository;
import com.megatravel.agentskaaplikacija.repositories.UnitTypesRepository;

@Service
public class HousingUnitsService {

	@Autowired
	private HousingUnitsRepository housingUnitsRepository;

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UnitTypesRepository unitTypesRepository;
	
	@Autowired
	private OptionUnitJoinRepository optionUnitJoinRepository;
	
	@Autowired
	private OptionRepository optionRepository;
	
	public HousingUnit create(HousingUnitDTO housingUnitDTO) {
		HousingUnit unit = new HousingUnit();
		unit.setDaysForCancelling(housingUnitDTO.getDaysForCancelling());
		unit.setAllowedPeople(housingUnitDTO.getAllowedPeople());
		unit.setAllowedCancelling(housingUnitDTO.isAllowedCancelling());
		unit.setDescription(housingUnitDTO.getDescription());
		unit.setPrice(housingUnitDTO.getPrice());
		unit.setRating(0);
		Address address = this.createAddress(housingUnitDTO.getAdress());
		UnitType type = this.findTypeById(housingUnitDTO.getType().getId());
		unit.setType(type);
		unit.setAdress(address);
		this.housingUnitsRepository.save(unit);
		this.createJoins(housingUnitDTO.getOptions(), unit);
		return unit;
	}

	public List<HousingUnit> search(String country, String city, int people, Date start, Date end) {
		List<HousingUnit> units = this.housingUnitsRepository.findAll();
		if(country == null || city == null || people < 1 || start == null || end == null) {
			return units;
		} else {
			List<HousingUnit> result = new ArrayList<HousingUnit>();
			for(HousingUnit unit : units) {
				if(!unit.getAdress().getCountry().toUpperCase().equals(country.toUpperCase())) continue;
				if(!unit.getAdress().getCity().toUpperCase().equals(city.toUpperCase())) continue;
				if(unit.getAllowedPeople() < people) continue;
				if(!this.unitIsFree(unit, start, end)) continue;
				result.add(unit);
			}
			return result;
		}
	}
	
	private boolean unitIsFree(HousingUnit unit, Date start, Date end) {
		for(Reservation reservation : unit.getReservations()) {
			Date first = reservation.getStart();
			Date last = reservation.getEnd();
			if(start.after(first) && start.before(last)) {
				return false;
			} else if(end.after(first) && end.before(last)) {
				return false;
			} else if(end.after(last) && start.before(first)) {
				return false;
			} 
		}
		return true;
	}
	
	private Address createAddress(AddressDTO addressDTO) {
		Address address = new Address();
		address.setCountry(addressDTO.getCountry());
		address.setCity(addressDTO.getCity());
		return this.addressRepository.save(address);
	}
	
	private UnitType findTypeById(Long id) {
		Optional<UnitType> type = this.unitTypesRepository.findById(id);
		if(!type.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		return type.get();
	}
	
	private BonusOption findOptionById(Long id) {
		Optional<BonusOption> option = this.optionRepository.findById(id);
		if(option.isPresent()) {
			return option.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	private void createJoins(List<OptionDTO> options, HousingUnit unit) {
		for(OptionDTO optionDTO : options) {
			BonusOption option = this.findOptionById(optionDTO.getId());
			BonusOptionUnit join = new BonusOptionUnit();
			join.setOption(option);
			join.setUnit(unit);
			this.optionUnitJoinRepository.save(join);
		}
	}
	
}
