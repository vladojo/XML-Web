package com.megatravel.smestaj.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.smestaj.communications.PorukaCommunication;
import com.megatravel.smestaj.dtos.MessageDTO;
import com.megatravel.smestaj.dtos.ReservationDTO;
import com.megatravel.smestaj.model.HousingUnit;
import com.megatravel.smestaj.model.Reservation;
import com.megatravel.smestaj.repositories.HousingUnitsRepository;
import com.megatravel.smestaj.repositories.ReservationsRepository;

@Service
public class ReservationsService {

	@Autowired
	private ReservationsRepository reservationsRepository;

	@Autowired
	private HousingUnitsService housingUnitsService;

	@Autowired
	private HousingUnitsRepository housingUnitsRepository;

	@Autowired
	private PorukaCommunication porukaCommunication;
	
	public Reservation createReservation(ReservationDTO reservationDTO, Long userId) {
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
					Long differenceInDays = TimeUnit.DAYS.convert((end.getTime() - start.getTime()), TimeUnit.MILLISECONDS);
					reservation.setPrice(unit.get().getPrice() * differenceInDays);
					reservation.setRating(null);
					reservation.setUser(userId);
					this.reservationsRepository.save(reservation);
					this.sendMessage(userId, unit.get().getAgent());
					return reservation;
				} else {
					throw new ResponseStatusException(HttpStatus.CONFLICT);
				}
			} catch(ParseException e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			}
		}
	}

	public void cancelReservation(Long id) {
		Optional<Reservation> resOpt = this.reservationsRepository.findById(id);
		if(!resOpt.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} else {
			Reservation reservation = resOpt.get();
			HousingUnit unit = reservation.getUnit();
			Date currentTime = new Date();
			Long differenceInDays = TimeUnit.DAYS.convert((reservation.getStart().getTime() - currentTime.getTime()), TimeUnit.MILLISECONDS);
			if(unit.isAllowedCancelling() && differenceInDays > unit.getDaysForCancelling()) {
				this.reservationsRepository.delete(reservation);
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			}
		}
	}

	public List<Reservation> getUserReservations(Long id) {
		List<Reservation> reservations = this.reservationsRepository.findAll();
		List<Reservation> userReservations = new ArrayList<Reservation>();
		for(Reservation reservation : reservations) {
			if(reservation.getUser().equals(id)) {
				userReservations.add(reservation);
			}
		}
		return userReservations;
	}

	public void createReservationAsAgent(com.megatravel.smestaj.soap.ReservationDTO reservationDTO) {
		Optional<HousingUnit> unit = this.housingUnitsRepository.findById(reservationDTO.getUnit());
		if(!unit.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} else {
			Date start = reservationDTO.getStart().toGregorianCalendar().getTime();
			Date end = reservationDTO.getEnd().toGregorianCalendar().getTime();
			if(this.housingUnitsService.unitIsFree(unit.get(), start, end)) {
				Reservation reservation = new Reservation();
				reservation.setRealised(false);
				reservation.setUnit(unit.get());
				reservation.setStart(start);
				reservation.setEnd(end);
				reservation.setPrice(unit.get().getPrice() * (end.getTime() - start.getTime()) / 86400000);
				reservation.setRating(null);
				reservation.setUser(null);
				this.reservationsRepository.save(reservation);
			} else {
				throw new ResponseStatusException(HttpStatus.CONFLICT);
			}
		}		
	}
	
	public void confirmReservation(Long id) {
		Optional<Reservation> reservation = this.reservationsRepository.findById(id);
		if(!reservation.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} else {
			Reservation res = reservation.get();
			res.setRealised(true);
			this.reservationsRepository.save(res);
		}
	}
	
	private void sendMessage(Long user, Long agent) {
		MessageDTO messageDTO = new MessageDTO();
		messageDTO.setContent("Ovom agentu možete slati poruke za detaljnije informacije.");
		this.porukaCommunication.sendMessage(user, messageDTO, agent);
	}
	
}

