package com.megatravel.smestaj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.smestaj.model.BonusOption;

@Repository
public interface OptionRepository extends JpaRepository<BonusOption, Long> { 

}
