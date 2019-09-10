package com.megatravel.agentskaaplikacija.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.agentskaaplikacija.model.Message;

@Repository
public interface MessagesRepository extends JpaRepository<Message, Long> {

}
