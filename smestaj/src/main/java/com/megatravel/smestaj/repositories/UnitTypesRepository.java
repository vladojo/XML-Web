package com.megatravel.smestaj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.smestaj.model.UnitType;

@Repository
public interface UnitTypesRepository extends JpaRepository<UnitType, Long> {

}
