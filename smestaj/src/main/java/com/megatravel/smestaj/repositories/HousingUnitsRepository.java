package com.megatravel.smestaj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.smestaj.model.HousingUnit;

@Repository
public interface HousingUnitsRepository extends JpaRepository<HousingUnit, Long> {

}
