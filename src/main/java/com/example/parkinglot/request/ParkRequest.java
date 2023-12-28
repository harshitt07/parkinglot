package com.example.parkinglot.request;

import com.example.parkinglot.entity.ParkingStrategyType;
import com.example.parkinglot.entity.VehicleType;
import lombok.Data;

@Data
public class ParkRequest {
    private VehicleType vehicleType;
    private String vehicleNumber;
    private ParkingStrategyType parkingStrategyType;
}
