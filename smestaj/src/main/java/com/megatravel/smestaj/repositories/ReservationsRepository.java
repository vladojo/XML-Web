package com.megatravel.smestaj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.smestaj.model.Reservation;

@Repository
public interface ReservationsRepository extends JpaRepository<Reservation, Long> {

}
