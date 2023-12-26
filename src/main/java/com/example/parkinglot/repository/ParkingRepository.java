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
    HashMap<String, Queue<Integer>> slotStore = new HashMap<>();

    private final PriceCalculatorService priceCalculatorService;

    public Ticket parkAndGetTicket(VEHICLE_TYPE vehicleType) throws Exception {
        String vehicleTypeString = String.valueOf(vehicleType);
        if(!slotStore.containsKey(vehicleTypeString)) {
            Queue<Integer> queue = new LinkedList<>();
            for(int i = 0; i < limit; i++) {
                queue.add(i);
            }
            slotStore.put(vehicleTypeString, queue);
        }
        if(slotStore.get(vehicleTypeString).isEmpty()) {
            throw new Exception("Slot are Not Available for the vehicle of type " + vehicleTypeString);
        }
        UUID ticketUuid = UUID.randomUUID();
        int slotId = getSlot(vehicleTypeString);
        Ticket ticket = Ticket.builder()
                .ticketId(ticketUuid.toString())
                .entryTime(System.currentTimeMillis())
                .slotId(slotId)
                .vehicleType(vehicleType)
                .build();
        ticketStore.put(ticketUuid.toString(), ticket);
        removeSlot(vehicleTypeString);
        return ticket;
    }

    public Ticket updateExit(String ticketId) throws Exception {
        if(!ticketStore.containsKey(ticketId)) throw new Exception("Ticket Id " + ticketId + " isn't valid!");
        Ticket ticket = ticketStore.get(ticketId);
        if(ticket.getExitTime() > 0) throw new Exception("Ticket Id " + ticketId + " is already used! You Cheater!");
        ticket.setExitTime(System.currentTimeMillis());
        ticket.setPrice(10L);
        ticketStore.put(ticketId, ticket);
        int removedSlotId = ticket.getSlotId();
        slotStore.get((ticket.getVehicleType()).toString()).add(removedSlotId);
        return ticket;
    }

    public int getSlot(String vehicleType) throws Exception {
        if(!slotStore.containsKey(vehicleType)) {
            throw new Exception("This Vehicle " + vehicleType + " can't be parked!");
        }
        if (slotStore.get(vehicleType).isEmpty())
            throw new Exception("Slot are Not Available for the vehicle of type " + vehicleType);
        return slotStore.get(vehicleType).peek();
    }

    public void removeSlot(String vehicleType) throws Exception {
        slotStore.get(vehicleType).poll();
    }
}
