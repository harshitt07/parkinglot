package com.example.parkinglot.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Ticket {

    String ticketId;
    long entryTime;
    long exitTime;
    String slotId;
    VEHICLE_TYPE vehicleType;
    Long price;

}