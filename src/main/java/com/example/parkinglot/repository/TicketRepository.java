package com.example.parkinglot.repository;

import com.example.parkinglot.entity.Ticket;
import org.springframework.stereotype.Repository;

@Repository
public class TicketRepository {

    public Ticket generateTicket() {
        Ticket ticket = Ticket.builder().build();

        return ticket;
    }

}
