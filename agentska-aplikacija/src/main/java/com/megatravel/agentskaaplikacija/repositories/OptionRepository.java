package com.megatravel.agentskaaplikacija.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.agentskaaplikacija.model.BonusOption;

@Repository
public interface OptionRepository extends JpaRepository<BonusOption, Long> { 

}
