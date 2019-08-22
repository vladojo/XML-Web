package com.megatravel.agentskaaplikacija.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.agentskaaplikacija.model.UnitType;

@Repository
public interface UnitTypesRepository extends JpaRepository<UnitType, Long> {

}
