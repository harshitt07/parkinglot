package com.example.parkinglot.implemention.parkingstrategy;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class NearToEntryParkingStrategy implements ParkingStrategy {
    @Override
    public int getParkingSlot(List<Integer> list) {
        Collections.sort(list);
        return list.get(0);
    }
}
