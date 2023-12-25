package com.example.parkinglot.request;

import com.example.parkinglot.entity.VEHICLE_TYPE;
import lombok.Data;

@Data
public class ParkRequest {

    private VEHICLE_TYPE vehicleType;
}
