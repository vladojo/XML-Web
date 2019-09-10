package com.megatravel.agentskaaplikacija.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.agentskaaplikacija.model.Reservation;

@Repository
public interface ReservationsRepository extends JpaRepository<Reservation, Long> {

}
