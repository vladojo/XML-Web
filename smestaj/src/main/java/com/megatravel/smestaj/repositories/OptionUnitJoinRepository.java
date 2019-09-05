package com.megatravel.smestaj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.smestaj.model.BonusOptionUnit;

@Repository
public interface OptionUnitJoinRepository extends JpaRepository<BonusOptionUnit, Long> {

}
