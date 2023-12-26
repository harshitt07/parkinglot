package com.example.parkinglot.service;

import com.example.parkinglot.entity.Ticket;
import com.example.parkinglot.repository.TicketRepository;
import com.example.parkinglot.request.ParkRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    public Ticket generateTicket(int slotId, ParkRequest parkRequest) {
        UUID ticketUuid = UUID.randomUUID();
        Ticket ticket = Ticket.builder()
                .ticketId(ticketUuid.toString())
                .entryTime(System.currentTimeMillis())
                .slotId(slotId)
                .vehicleType(parkRequest.getVehicleType())
                .vehicleNumber(parkRequest.getVehicleNumber())
                .build();
        ticketRepository.save(ticketUuid.toString(), ticket);
        return ticket;
    }

    public Ticket getTicket(String ticketId) throws Exception {
        return ticketRepository.getTicket(ticketId);
    }

    public void updateTicket(String ticketId, Ticket ticket) {
        ticketRepository.updateTicket(ticketId, ticket);
    }

}
