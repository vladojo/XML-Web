package com.megatravel.agentskaaplikacija.services;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.agentskaaplikacija.dtos.ReservationDTO;
import com.megatravel.agentskaaplikacija.model.HousingUnit;
import com.megatravel.agentskaaplikacija.model.Reservation;
import com.megatravel.agentskaaplikacija.repositories.HousingUnitsRepository;
import com.megatravel.agentskaaplikacija.repositories.ReservationsRepository;
import com.megatravel.agentskaaplikacija.soap.communication.SmestajCommunication;

@Service
public class ReservationsService {

	@Autowired
	private ReservationsRepository reservationsRepository;

	@Autowired
	private HousingUnitsService housingUnitsService;

	@Autowired
	private HousingUnitsRepository housingUnitsRepository;

	@Autowired
	private SmestajCommunication smestajCommunication;
	
	public Reservation createReservation(ReservationDTO reservationDTO) {
		Optional<HousingUnit> unit = this.housingUnitsRepository.findById(reservationDTO.getUnit().getId());
		if(!unit.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} else {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date start = format.parse(reservationDTO.getStart());
				Date end = format.parse(reservationDTO.getEnd());
				if(this.housingUnitsService.unitIsFree(unit.get(), start, end)) {
					Reservation reservation = new Reservation();
					reservation.setRealised(false);
					reservation.setUnit(unit.get());
					reservation.setStart(start);
					reservation.setEnd(end);
					reservation.setPrice(unit.get().getPrice() * (end.getTime() - start.getTime()) / 86400000);
					reservation.setRating(null);
					reservation.setUser(null);
					this.smestajCommunication.addReservation(reservationDTO);
					return this.reservationsRepository.save(reservation);
				} else {
					throw new ResponseStatusException(HttpStatus.CONFLICT);
				}
			} catch(ParseException e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			}
		}
	}

	public Reservation confirmReservation(Long id) {
		Optional<Reservation> reservation = this.reservationsRepository.findById(id);
		if(!reservation.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} else {
			Reservation res = reservation.get();
			res.setRealised(true);
			return this.reservationsRepository.save(res);
		}
	}

}
