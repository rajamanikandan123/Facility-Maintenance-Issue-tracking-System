package com.examly.springapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.examly.springapp.model.Ticket;
import com.examly.springapp.repository.TicketRepo;
@Service
public class TicketService {

  @Autowired
  private TicketRepo ticketRepo;

  public Ticket updateTicket(Long id,Ticket ticket){
    Optional<Ticket> existingTicket = ticketRepo.findById(id);

    if(existingTicket.isPresent()){
        ticket.setTicketId(id);
        return ticketRepo.save(ticket);
    }
    return null;
  }

}

