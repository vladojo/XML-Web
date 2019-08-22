package com.megatravel.agentskaaplikacija.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.agentskaaplikacija.model.HousingUnit;

@Repository
public interface HousingUnitsRepository extends JpaRepository<HousingUnit, Long> {

}
