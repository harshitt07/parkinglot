package com.example.parkinglot.repository;

import com.example.parkinglot.entity.ParkingStrategyType;
import com.example.parkinglot.entity.VehicleType;
import com.example.parkinglot.exception.SlotNotAvailableException;
import com.example.parkinglot.factory.ParkingStrategyFactory;
import com.example.parkinglot.implemention.parkingstrategy.ParkingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

@Repository
@RequiredArgsConstructor
public class ParkingRepository {
    private final static int limit = 5;
    HashMap<String, List<Integer>> slotStore = new HashMap<>();
    private final ParkingStrategyFactory parkingStrategyFactory;

    public int getParkingSlot(VehicleType vehicleType, ParkingStrategyType parkingStrategyType) throws Exception {
        String vehicleTypeString = String.valueOf(vehicleType);
        if(!slotStore.containsKey(vehicleTypeString)) {
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i < limit; i++)
                list.add(i);
            slotStore.put(vehicleTypeString, list);
        }
        if (slotStore.get(vehicleTypeString).isEmpty())
            throw new SlotNotAvailableException("Slot are Not Available for the vehicle of type " + vehicleType);
        List<Integer> list = slotStore.get(vehicleTypeString);
        ParkingStrategy parkingStrategy = parkingStrategyFactory.getParkingStrategyInstance(parkingStrategyType);
        return parkingStrategy.getParkingSlot(list);
    }

    public void removeSlot(VehicleType vehicleType, int slotId) {
        List<Integer> list = slotStore.get(vehicleType.toString());
        int index = -1;
        for(int i = 0; i < list.size(); i++)
            if(list.get(i) == slotId)
                index = i;
        int temp = list.get(list.size() - 1);
        list.set(index, temp);
        list.remove(list.size() - 1);
        slotStore.put(vehicleType.toString(), list);
    }

    public void addEmptySlot(String vehicleType, int slotId) {
        slotStore.get(vehicleType).add(slotId);
    }
}
