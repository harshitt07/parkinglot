package com.example.parkinglot.request;

import com.example.parkinglot.entity.VehicleType;
import lombok.Data;

@Data
public class ParkRequest {

    private VehicleType vehicleType;
}
