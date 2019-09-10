package com.megatravel.agentskaaplikacija.soap.communication;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.megatravel.agentskaaplikacija.model.Address;
import com.megatravel.agentskaaplikacija.model.BonusOption;
import com.megatravel.agentskaaplikacija.model.BonusOptionUnit;
import com.megatravel.agentskaaplikacija.model.HousingUnit;
import com.megatravel.agentskaaplikacija.model.Reservation;
import com.megatravel.agentskaaplikacija.model.UnitType;
import com.megatravel.agentskaaplikacija.repositories.AddressRepository;
import com.megatravel.agentskaaplikacija.repositories.HousingUnitsRepository;
import com.megatravel.agentskaaplikacija.repositories.OptionRepository;
import com.megatravel.agentskaaplikacija.repositories.OptionUnitJoinRepository;
import com.megatravel.agentskaaplikacija.repositories.ReservationsRepository;
import com.megatravel.agentskaaplikacija.repositories.UnitTypesRepository;
import com.megatravel.agentskaaplikacija.repositories.UserRepository;
import com.megatravel.agentskaaplikacija.soap.AddressDTO;
import com.megatravel.agentskaaplikacija.soap.AllOptionsRequest;
import com.megatravel.agentskaaplikacija.soap.AllOptionsResponse;
import com.megatravel.agentskaaplikacija.soap.AllReservationsRequest;
import com.megatravel.agentskaaplikacija.soap.AllReservationsResponse;
import com.megatravel.agentskaaplikacija.soap.AllTypesRequest;
import com.megatravel.agentskaaplikacija.soap.AllTypesResponse;
import com.megatravel.agentskaaplikacija.soap.AllUnitsRequest;
import com.megatravel.agentskaaplikacija.soap.AllUnitsResponse;
import com.megatravel.agentskaaplikacija.soap.CreateReservationRequest;
import com.megatravel.agentskaaplikacija.soap.CreateReservationResponse;
import com.megatravel.agentskaaplikacija.soap.CreateUnitRequest;
import com.megatravel.agentskaaplikacija.soap.CreateUnitResponse;
import com.megatravel.agentskaaplikacija.soap.HousingUnitDTO;
import com.megatravel.agentskaaplikacija.soap.OptionDTO;
import com.megatravel.agentskaaplikacija.soap.ReservationDTO;
import com.megatravel.agentskaaplikacija.soap.TypeDTO;

@Service
public class SmestajCommunication {

	private static final String URI = "http://localhost:8082/smestaj/services";
	
	@Autowired
	private Jaxb2Marshaller marshaller;
	
	private WebServiceTemplate template;
	
	@Autowired
	private UnitTypesRepository unitTypesRepository;
	
	@Autowired
	private OptionRepository optionRepository;
	
	@Autowired
	private HousingUnitsRepository housingUnitsRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OptionUnitJoinRepository optionUnitJoinRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private ReservationsRepository reservationsRepository;
	
	public void syncTypes() {
		this.template = new WebServiceTemplate(this.marshaller);
		AllTypesResponse response = (AllTypesResponse) template.marshalSendAndReceive(URI, new AllTypesRequest());
		for(TypeDTO typeDTO : response.getTypes()) {
			if(unitTypesRepository.findById(typeDTO.getId()).isPresent()) continue;
			UnitType type = new UnitType();
			type.setId(typeDTO.getId());
			type.setName(typeDTO.getName());
			unitTypesRepository.save(type);
		}
	}
	
	public void syncOptions() {
		this.template = new WebServiceTemplate(this.marshaller);
		AllOptionsResponse response = (AllOptionsResponse) template.marshalSendAndReceive(URI, new AllOptionsRequest());
		for(OptionDTO optionDTO : response.getOptions()) {
			if(optionRepository.findById(optionDTO.getId()).isPresent()) continue;
			BonusOption option = new BonusOption();
			option.setId(optionDTO.getId());
			option.setName(optionDTO.getName());
			option.setDescription(optionDTO.getDescription());
			optionRepository.save(option);
		}
	}
	
	public void syncUnits() {
		this.template = new WebServiceTemplate(this.marshaller);
		AllUnitsResponse response = (AllUnitsResponse) template.marshalSendAndReceive(URI, new AllUnitsRequest());
		for(HousingUnitDTO unitDTO : response.getUnits()) {
			if(housingUnitsRepository.findById(unitDTO.getId()).isPresent()) continue;
			HousingUnit unit = new HousingUnit();
			unit.setAdress(this.createAddress(unitDTO.getAddress()));
			unit.setType(unitTypesRepository.getOne(unitDTO.getType().getId()));
			unit.setId(unitDTO.getId());
			unit.setAllowedCancelling(unitDTO.isAllowedCancelling());
			unit.setAllowedPeople(unitDTO.getAllowedPeople());
			unit.setDaysForCancelling(unitDTO.getDaysForCancelling());
			unit.setDescription(unitDTO.getDescription());
			unit.setPrice(unitDTO.getPrice());
			unit.setRating(unitDTO.getRating());
			unit.setAgent(userRepository.getOne(unitDTO.getAgent()));
			housingUnitsRepository.save(unit);
			for(OptionDTO optionDTO : unitDTO.getOptions()) {
				BonusOptionUnit join = new BonusOptionUnit();
				join.setUnit(unit);
				join.setOption(optionRepository.getOne(optionDTO.getId()));
				optionUnitJoinRepository.save(join);
			}
		}
	}
	
	private Address createAddress(AddressDTO addressDTO) {
		Address address = new Address();
		address.setCity(addressDTO.getCity());
		address.setCountry(addressDTO.getCountry());
		address.setId(addressDTO.getId());
		return addressRepository.save(address);
	}

	public void syncReservations() {
		this.template = new WebServiceTemplate(this.marshaller);
		AllReservationsResponse response = (AllReservationsResponse) template.marshalSendAndReceive(URI, new AllReservationsRequest());
		for(ReservationDTO reservationDTO : response.getReservations()) {
			if(reservationsRepository.findById(reservationDTO.getId()).isPresent()) continue;
			Reservation reservation = new Reservation();
			reservation.setEnd(reservationDTO.getEnd().toGregorianCalendar().getTime());
			reservation.setStart(reservationDTO.getStart().toGregorianCalendar().getTime());
			reservation.setPrice(reservationDTO.getPrice());
			reservation.setRealised(reservationDTO.isRealised());
			reservation.setId(reservationDTO.getId());
			reservation.setUnit(housingUnitsRepository.getOne(reservationDTO.getUnit()));
			if(!reservationDTO.getUser().equals(0L)) {
				reservation.setUser(userRepository.getOne(reservationDTO.getUser()));
			}
			reservationsRepository.save(reservation);
		}
	}
	
	public boolean addUnit(com.megatravel.agentskaaplikacija.dtos.HousingUnitDTO housingUnitDTO, Long agentId) {
		this.template = new WebServiceTemplate(this.marshaller);
		CreateUnitRequest request = new CreateUnitRequest();
		HousingUnitDTO unit = new HousingUnitDTO();
		unit.setDaysForCancelling(housingUnitDTO.getDaysForCancelling());
		unit.setAllowedPeople(housingUnitDTO.getAllowedPeople());
		unit.setAllowedCancelling(housingUnitDTO.isAllowedCancelling());
		unit.setDescription(housingUnitDTO.getDescription());
		unit.setPrice(housingUnitDTO.getPrice());
		unit.setRating(0);
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setCity(housingUnitDTO.getAdress().getCity());
		addressDTO.setCountry(housingUnitDTO.getAdress().getCountry());
		unit.setAddress(addressDTO);
		TypeDTO typeDTO = new TypeDTO();
		typeDTO.setId(housingUnitDTO.getType().getId());
		unit.setType(typeDTO);
		unit.setAgent(agentId);
		request.setUnit(unit);
		CreateUnitResponse response = (CreateUnitResponse) template.marshalSendAndReceive(URI, request);
		return response.isSuccess();
	}
	
	public boolean addReservation(com.megatravel.agentskaaplikacija.dtos.ReservationDTO reservationDTO) {
		this.template = new WebServiceTemplate(this.marshaller);
		CreateReservationRequest request = new CreateReservationRequest();
		ReservationDTO reservation = new ReservationDTO();
		reservation.setRealised(false);
		reservation.setUnit(reservationDTO.getUnit().getId());
		GregorianCalendar calendar = new GregorianCalendar();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date start = format.parse(reservationDTO.getStart());
			Date end = format.parse(reservationDTO.getEnd());
			calendar.setTime(start);
			reservation.setStart(DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar));
			calendar.setTime(end);
			reservation.setEnd(DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar));
			reservation.setPrice(reservationDTO.getUnit().getPrice() * (end.getTime() - start.getTime()) / 86400000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		reservation.setUser(null);
		request.setReservation(reservation);
		CreateReservationResponse response = (CreateReservationResponse) template.marshalSendAndReceive(URI, request);
		return response.isSuccess();
	}
	
}
