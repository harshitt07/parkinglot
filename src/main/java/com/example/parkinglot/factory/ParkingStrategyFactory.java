package com.example.parkinglot.factory;

import com.example.parkinglot.entity.ParkingStrategyType;
import com.example.parkinglot.implemention.parkingstrategy.NearToEntryParkingStrategy;
import com.example.parkinglot.implemention.parkingstrategy.NearToExitParkingStrategy;
import com.example.parkinglot.implemention.parkingstrategy.ParkingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParkingStrategyFactory {

    private final NearToExitParkingStrategy nearToExitParkingStrategy;
    private final NearToEntryParkingStrategy nearToEntryParkingStrategy;

    public ParkingStrategy getParkingStrategyInstance(ParkingStrategyType parkingStrategyType) {
        if(parkingStrategyType.equals(ParkingStrategyType.NEAR_TO_ENTRY)) {
            return nearToEntryParkingStrategy;
        } else if(parkingStrategyType.equals(ParkingStrategyType.NEAR_TO_EXIT)) {
            return nearToExitParkingStrategy;
        }
        return null;
    }
}
