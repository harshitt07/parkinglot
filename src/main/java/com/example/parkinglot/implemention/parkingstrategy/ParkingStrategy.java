package com.example.parkinglot.implemention.parkingstrategy;

import java.util.List;

public interface ParkingStrategy {
    int getParkingSlot(List<Integer> queue);
}
