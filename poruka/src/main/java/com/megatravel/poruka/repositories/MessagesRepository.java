package com.megatravel.poruka.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.poruka.model.Message;

@Repository
public interface MessagesRepository extends JpaRepository<Message, Long> {

}
