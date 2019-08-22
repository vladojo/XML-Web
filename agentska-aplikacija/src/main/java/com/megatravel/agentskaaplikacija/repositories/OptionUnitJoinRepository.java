package com.megatravel.agentskaaplikacija.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.agentskaaplikacija.model.BonusOptionUnit;

@Repository
public interface OptionUnitJoinRepository extends JpaRepository<BonusOptionUnit, Long> {

}
