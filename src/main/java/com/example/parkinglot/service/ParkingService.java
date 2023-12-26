package com.example.parkinglot.service;

import com.example.parkinglot.exception.TicketAlreadyUsedException;
import com.example.parkinglot.request.ParkRequest;
import com.example.parkinglot.entity.Ticket;
import com.example.parkinglot.repository.ParkingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParkingService {

    private final PassService passService;
    private final TicketService ticketService;
    private final ParkingRepository parkingRepository;
    private final PriceCalculatorService priceCalculatorService;

    public Ticket parkAndGetTicket(ParkRequest parkRequest) throws Exception {
        int slotId = parkingRepository.getParkingSlot(parkRequest.getVehicleType(), parkRequest.getParkingStrategyType());
        Ticket ticket = ticketService.generateTicket(slotId, parkRequest);
        parkingRepository.removeSlot(parkRequest.getVehicleType(), slotId);
        return ticket;
    }

    public Ticket updateExit(String ticketId) throws Exception {
        Ticket ticket = ticketService.getTicket(ticketId);
        if(ticket.getExitTime() > 0) throw new TicketAlreadyUsedException("Ticket Id " + ticketId + " is already used! You Cheater!");
        ticket.setExitTime(System.currentTimeMillis());
        if(passService.isPassValid(ticket.getVehicleNumber()))
            ticket.setPrice(0);
        else
            ticket.setPrice(priceCalculatorService.calculatePrice(ticket));
        ticketService.updateTicket(ticketId, ticket);
        parkingRepository.addEmptySlot(ticket.getVehicleType().toString(), ticket.getSlotId());
        return ticket;
    }
}
