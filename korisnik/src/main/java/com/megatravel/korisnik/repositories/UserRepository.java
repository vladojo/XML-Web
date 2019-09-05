package com.megatravel.korisnik.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.korisnik.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
}
