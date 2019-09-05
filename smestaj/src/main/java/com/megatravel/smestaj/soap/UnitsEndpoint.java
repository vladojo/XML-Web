package com.megatravel.smestaj.soap;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.megatravel.smestaj.model.Address;
import com.megatravel.smestaj.model.BonusOption;
import com.megatravel.smestaj.model.BonusOptionUnit;
import com.megatravel.smestaj.model.HousingUnit;
import com.megatravel.smestaj.model.Reservation;
import com.megatravel.smestaj.model.UnitType;
import com.megatravel.smestaj.repositories.HousingUnitsRepository;
import com.megatravel.smestaj.repositories.OptionRepository;
import com.megatravel.smestaj.repositories.ReservationsRepository;
import com.megatravel.smestaj.repositories.UnitTypesRepository;
import com.megatravel.smestaj.services.HousingUnitsService;
import com.megatravel.smestaj.services.ReservationsService;

@Endpoint
public class UnitsEndpoint {

	private static final String NAMESPACE_URI = "http://www.megatravel.com/smestaj/soap";

	@Autowired
	private HousingUnitsRepository housingUnitsRepository;

	@Autowired
	private ReservationsRepository reservationsRepository;
	
	@Autowired
	private ReservationsService reservationsService;
	
	@Autowired
	private HousingUnitsService housingUnitsService;
	
	@Autowired
	private UnitTypesRepository unitTypesRepository;
	
	@Autowired
	private OptionRepository optionRepository;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "allOptionsRequest")
	@ResponsePayload
 	public AllOptionsResponse allOptions(@RequestPayload AllOptionsRequest request) {
		List<BonusOption> options = optionRepository.findAll();
		List<OptionDTO> optionsDTO = new ArrayList<OptionDTO>();
		for(BonusOption option : options) {
			optionsDTO.add(this.createOptionDTO(option));
		}
		AllOptionsResponse response = new AllOptionsResponse();
		response.getOptions().addAll(optionsDTO);
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "allTypesRequest")
	@ResponsePayload
 	public AllTypesResponse allTypes(@RequestPayload AllTypesRequest request) {
		List<UnitType> types = unitTypesRepository.findAll();
		List<TypeDTO> typesDTO = new ArrayList<TypeDTO>();
		for(UnitType type : types) {
			typesDTO.add(this.createTypeDTO(type));
		}
		AllTypesResponse response = new AllTypesResponse();
		response.getTypes().addAll(typesDTO);
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "allUnitsRequest")
	@ResponsePayload
 	public AllUnitsResponse allUnits(@RequestPayload AllUnitsRequest request) {
		List<HousingUnit> units = housingUnitsRepository.findAll();
		List<HousingUnitDTO> unitsDTO = new ArrayList<HousingUnitDTO>();
		for(HousingUnit unit : units) {
			HousingUnitDTO unitDTO = new HousingUnitDTO();
			unitDTO.setAddress(this.createAddressDTO(unit.getAdress()));
			unitDTO.setType(this.createTypeDTO(unit.getType()));
			for(BonusOptionUnit join : unit.getOptionJoins()) {
				unitDTO.getOptions().add(this.createOptionDTO(join.getOption()));
			}
			unitDTO.setId(unit.getId());
			unitDTO.setAllowedCancelling(unit.isAllowedCancelling());
			unitDTO.setAllowedPeople(unit.getAllowedPeople());
			unitDTO.setDaysForCancelling(unit.getDaysForCancelling());
			unitDTO.setDescription(unit.getDescription());
			unitDTO.setPrice(unit.getPrice());
			unitDTO.setRating(unit.getRating());
			unitDTO.setAgent(unit.getAgent());
			unitsDTO.add(unitDTO);
		}
		AllUnitsResponse response = new AllUnitsResponse();
		response.getUnits().addAll(unitsDTO);
		return response;
	}
	
	private OptionDTO createOptionDTO(BonusOption option) {
		OptionDTO optionDTO = new OptionDTO();
		optionDTO.setId(option.getId());
		optionDTO.setDescription(option.getDescription());
		optionDTO.setName(option.getName());
		return optionDTO;
	}
	
	private TypeDTO createTypeDTO(UnitType type) {
		TypeDTO typeDTO = new TypeDTO();
		typeDTO.setId(type.getId());
		typeDTO.setName(type.getName());
		return typeDTO;
	}

	private AddressDTO createAddressDTO(Address address) {
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setId(address.getId());
		addressDTO.setCity(address.getCity());
		addressDTO.setCountry(address.getCountry());
		return addressDTO;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "allReservationsRequest")
	@ResponsePayload
	public AllReservationsResponse allReservations(@RequestPayload AllReservationsRequest request) {
		List<Reservation> reservations = reservationsRepository.findAll();
		List<ReservationDTO> reservationsDTO = new ArrayList<ReservationDTO>();
		for(Reservation reservation : reservations) {
			ReservationDTO reservationDTO = new ReservationDTO();
			reservationDTO.setId(reservation.getId());
			reservationDTO.setPrice(reservation.getPrice());
			reservationDTO.setRealised(reservation.isRealised());
			reservationDTO.setUnit(reservation.getUnit().getId());
			GregorianCalendar calendar = new GregorianCalendar();
			try {
				calendar.setTime(reservation.getStart());
				reservationDTO.setStart(DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar));
				calendar.setTime(reservation.getEnd());
				reservationDTO.setEnd(DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar));
			} catch (DatatypeConfigurationException e) {
				e.printStackTrace();
			}
			reservationDTO.setUser(reservation.getUser());
			reservationsDTO.add(reservationDTO);
		}
		AllReservationsResponse response = new AllReservationsResponse();
		response.getReservations().addAll(reservationsDTO);
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "createReservationRequest")
	@ResponsePayload
	public CreateReservationResponse createReservation(@RequestPayload CreateReservationRequest request) {
		reservationsService.createReservationAsAgent(request.getReservation());
		CreateReservationResponse response = new CreateReservationResponse();
		response.setSuccess(true);
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "createUnitRequest")
	@ResponsePayload
	public CreateUnitResponse createUnit(@RequestPayload CreateUnitRequest request) {
		this.housingUnitsService.createUnit(request.getUnit());
		CreateUnitResponse response = new CreateUnitResponse();
		response.setSuccess(true);
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "confirmReservationRequest")
	@ResponsePayload
	public ConfirmReservationResponse confirmReservation(@RequestPayload ConfirmReservationRequest request) {
		this.reservationsService.confirmReservation(request.getReservation());
		ConfirmReservationResponse response = new ConfirmReservationResponse();
		response.setSuccess(true);
		return response;
	}
	
}
