package com.example.parkinglot.implemention.parkingstrategy;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class NearToExitParkingStrategy implements ParkingStrategy {
    @Override
    public int getParkingSlot(List<Integer> list) {
        list.sort(Collections.reverseOrder());
        return list.get(0);
    }
}
