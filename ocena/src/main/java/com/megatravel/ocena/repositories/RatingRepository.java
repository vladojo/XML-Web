package com.megatravel.ocena.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.ocena.model.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

}
