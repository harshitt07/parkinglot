package com.example.parkinglot.service;

import com.example.parkinglot.exception.TicketAlreadyUsedException;
import com.example.parkinglot.repository.TicketRepository;
import com.example.parkinglot.request.ParkRequest;
import com.example.parkinglot.entity.Ticket;
import com.example.parkinglot.repository.ParkingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParkingService {

    private final ParkingRepository parkingRepository;
    private final TicketRepository ticketRepository;
    private final PriceCalculatorService priceCalculatorService;

    public Ticket parkAndGetTicket(ParkRequest parkRequest) throws Exception {
        int slotId = parkingRepository.getParkingSlot(parkRequest.getVehicleType());
        Ticket ticket = ticketRepository.generateTicket(slotId, parkRequest.getVehicleType());
        parkingRepository.removeSlot(parkRequest.getVehicleType());
        return ticket;
    }

    public Ticket updateExit(String ticketId) throws Exception {
        Ticket ticket = ticketRepository.getTicket(ticketId);
        if(ticket.getExitTime() > 0) throw new TicketAlreadyUsedException("Ticket Id " + ticketId + " is already used! You Cheater!");
        ticket.setExitTime(System.currentTimeMillis());
        ticket.setPrice(priceCalculatorService.calculatePrice(ticket));
        ticketRepository.updateTicket(ticketId, ticket);
        parkingRepository.addEmptySlot(ticket.getVehicleType().toString(), ticket.getSlotId());
        return ticket;
    }
}
