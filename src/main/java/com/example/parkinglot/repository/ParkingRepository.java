package com.example.parkinglot.repository;

import com.example.parkinglot.service.PriceCalculatorService;
import com.example.parkinglot.entity.Ticket;
import com.example.parkinglot.entity.VEHICLE_TYPE;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class ParkingRepository {
    private final static int limit = 2;
    HashMap<String, Ticket> ticketStore = new HashMap<>();
    HashMap<String, List<String>> slotStore = new HashMap<>();

    private final PriceCalculatorService priceCalculatorService;

    public Ticket parkAndGetTicket(VEHICLE_TYPE vehicleType) throws Exception {
        String vehicleTypeString = String.valueOf(vehicleType);
        if(!slotStore.containsKey(vehicleTypeString)) {
            slotStore.put(vehicleTypeString, new ArrayList<>());
        }
        if(slotStore.get(vehicleTypeString).size() < limit) {
            UUID ticketUuid = UUID.randomUUID();
            UUID slotUuid = UUID.randomUUID();
            Ticket ticket = Ticket.builder()
                    .ticketId(ticketUuid.toString())
                    .entryTime(System.currentTimeMillis())
                    .slotId(slotUuid.toString())
                    .vehicleType(vehicleType)
                    .build();
            ticketStore.put(ticketUuid.toString(), ticket);
            slotStore.get(vehicleTypeString).add(slotUuid.toString());
            return ticket;
        }
        throw new Exception("Slot are Not Available for the vehicle of type " + vehicleTypeString);
    }

    public Ticket updateExit(String ticketId) throws Exception {
        if(!ticketStore.containsKey(ticketId)) throw new Exception("Ticket Id " + ticketId + " isn't valid!");
        Ticket ticket = ticketStore.get(ticketId);
        if(ticket.getExitTime() > 0) throw new Exception("Ticket Id " + ticketId + " is already used! You Cheater!");
        ticket.setExitTime(System.currentTimeMillis());
        ticket.setPrice(10L);
        ticketStore.put(ticketId, ticket);
        slotStore.get((ticket.getVehicleType()).toString()).remove(ticket.getSlotId());
        return ticket;
    }

}
