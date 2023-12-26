package com.example.parkinglot.repository;

import com.example.parkinglot.entity.VehicleType;
import com.example.parkinglot.exception.SlotNotAvailableException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class ParkingRepository {
    private final static int limit = 2;
    HashMap<String, Queue<Integer>> slotStore = new HashMap<>();

    public int getParkingSlot(VehicleType vehicleType) throws Exception {
        String vehicleTypeString = String.valueOf(vehicleType);
        if(!slotStore.containsKey(vehicleTypeString)) {
            Queue<Integer> queue = new LinkedList<>();
            for(int i = 0; i < limit; i++) {
                queue.add(i);
            }
            slotStore.put(vehicleTypeString, queue);
        }
        if (slotStore.get(vehicleTypeString).isEmpty())
            throw new SlotNotAvailableException("Slot are Not Available for the vehicle of type " + vehicleType);
        return slotStore.get(vehicleTypeString).peek();
    }

    public void removeSlot(VehicleType vehicleType) {
        slotStore.get(vehicleType.toString()).poll();
    }

    public void addEmptySlot(String vehicleType, int slotId) throws Exception {
        slotStore.get(vehicleType).add(slotId);
    }
}
