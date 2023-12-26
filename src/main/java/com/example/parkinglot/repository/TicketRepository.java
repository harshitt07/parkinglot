package com.example.parkinglot.repository;

import com.example.parkinglot.entity.Ticket;
import com.example.parkinglot.exception.TicketInvalidException;
import com.example.parkinglot.request.ParkRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class TicketRepository {

    HashMap<String, Ticket> ticketStore = new HashMap<>();

    public void save(String ticketId, Ticket ticket) {
        ticketStore.put(ticketId, ticket);
    }

    public Ticket getTicket(String ticketId) throws Exception {
        if(!ticketStore.containsKey(ticketId)) throw new TicketInvalidException("Ticket Id " + ticketId + " isn't valid!");
        return ticketStore.get(ticketId);
    }

    public void updateTicket(String ticketId, Ticket ticket) {
        ticketStore.put(ticketId, ticket);
    }
}
