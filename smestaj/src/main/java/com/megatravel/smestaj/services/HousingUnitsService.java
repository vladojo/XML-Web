package com.megatravel.smestaj.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.smestaj.communications.KorisnikCommunication;
import com.megatravel.smestaj.dtos.UserDTO;
import com.megatravel.smestaj.model.Address;
import com.megatravel.smestaj.model.BonusOption;
import com.megatravel.smestaj.model.BonusOptionUnit;
import com.megatravel.smestaj.model.HousingUnit;
import com.megatravel.smestaj.model.Reservation;
import com.megatravel.smestaj.model.UnitType;
import com.megatravel.smestaj.repositories.AddressRepository;
import com.megatravel.smestaj.repositories.HousingUnitsRepository;
import com.megatravel.smestaj.repositories.OptionRepository;
import com.megatravel.smestaj.repositories.OptionUnitJoinRepository;
import com.megatravel.smestaj.repositories.ReservationsRepository;
import com.megatravel.smestaj.repositories.UnitTypesRepository;
import com.megatravel.smestaj.soap.AddressDTO;
import com.megatravel.smestaj.soap.HousingUnitDTO;
import com.megatravel.smestaj.soap.OptionDTO;

@Service
public class HousingUnitsService {

	@Autowired
	private HousingUnitsRepository housingUnitsRepository;

	@Autowired
	private KorisnikCommunication korisnikCommunication;
	
	@Autowired
	private ReservationsRepository reservationsRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UnitTypesRepository unitTypesRepository;
	
	@Autowired
	private OptionUnitJoinRepository optionUnitJoinRepository;
	
	@Autowired
	private OptionRepository optionRepository;
	
	public List<HousingUnit> search(String country, String city, Integer people, Date start, Date end) {
		List<HousingUnit> units = this.housingUnitsRepository.findAll();
		if(country == null || city == null || people == null || start == null || end == null) {
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
	
	public boolean unitIsFree(HousingUnit unit, Date start, Date end) {
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

	public List<HousingUnit> findAll() {
		return this.housingUnitsRepository.findAll();
	}

	public void usersCanCommunicate(Long agent, Long user) {
		userExists(agent);
		userExists(user);
		List<HousingUnit> units = this.housingUnitsRepository.findAll();
		List<HousingUnit> agentsUnits = new ArrayList<HousingUnit>();
		for(HousingUnit unit : units) {
			if(unit.getAgent().equals(agent)) {
				agentsUnits.add(unit);
			}
		}
		for(HousingUnit unit : agentsUnits) {
			for(Reservation reservation : unit.getReservations()) {
				if(reservation.getUser().equals(user)) {
					return;
				}
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	private void userExists(Long id) {
		ResponseEntity<UserDTO> response = korisnikCommunication.findUser(id);
		if(!response.getStatusCode().is2xxSuccessful()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	public void userCanRate(Long user, Long reservation) {
		Optional<Reservation> reservationOpt = this.reservationsRepository.findById(reservation);
		if(reservationOpt.isPresent()) {
			if(reservationOpt.get().getUser().equals(user)) {
				return;
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			}
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	public void createUnit(HousingUnitDTO housingUnitDTO) {
		HousingUnit unit = new HousingUnit();
		unit.setDaysForCancelling(housingUnitDTO.getDaysForCancelling());
		unit.setAllowedPeople(housingUnitDTO.getAllowedPeople());
		unit.setAllowedCancelling(housingUnitDTO.isAllowedCancelling());
		unit.setDescription(housingUnitDTO.getDescription());
		unit.setPrice(housingUnitDTO.getPrice());
		Address address = this.createAddress(housingUnitDTO.getAddress());
		UnitType type = this.findTypeById(housingUnitDTO.getType().getId());
		unit.setType(type);
		unit.setAdress(address);
		unit.setAgent(housingUnitDTO.getAgent());
		this.housingUnitsRepository.save(unit);
		this.createJoins(housingUnitDTO.getOptions(), unit);
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
