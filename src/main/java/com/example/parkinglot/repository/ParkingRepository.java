package com.example.parkinglot.repository;

import com.example.parkinglot.entity.VehicleType;
import com.example.parkinglot.exception.SlotNotAvailableException;
import com.example.parkinglot.entity.Ticket;
import com.example.parkinglot.exception.TicketAlreadyUsedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class ParkingRepository {
    private final static int limit = 2;
    HashMap<String, Ticket> ticketStore = new HashMap<>();
    HashMap<String, Queue<Integer>> slotStore = new HashMap<>();

    public Ticket parkAndGetTicket(VehicleType vehicleType) throws Exception {
        String vehicleTypeString = String.valueOf(vehicleType);
        if(!slotStore.containsKey(vehicleTypeString)) {
            Queue<Integer> queue = new LinkedList<>();
            for(int i = 0; i < limit; i++) {
                queue.add(i);
            }
            slotStore.put(vehicleTypeString, queue);
        }
        int slotId = getSlot(vehicleTypeString);
        UUID ticketUuid = UUID.randomUUID();
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
        if(ticket.getExitTime() > 0) throw new TicketAlreadyUsedException("Ticket Id " + ticketId + " is already used! You Cheater!");
        ticket.setExitTime(System.currentTimeMillis());
        ticket.setPrice(10L);
        ticketStore.put(ticketId, ticket);
        int removedSlotId = ticket.getSlotId();
        slotStore.get((ticket.getVehicleType()).toString()).add(removedSlotId);
        return ticket;
    }

    public int getSlot(String vehicleType) throws Exception {
        if (slotStore.get(vehicleType).isEmpty())
            throw new SlotNotAvailableException("Slot are Not Available for the vehicle of type " + vehicleType);
        return slotStore.get(vehicleType).peek();
    }

    public void removeSlot(String vehicleType) throws Exception {
        slotStore.get(vehicleType).poll();
    }
}
