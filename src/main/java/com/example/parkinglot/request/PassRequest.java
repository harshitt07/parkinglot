package com.example.parkinglot.request;

import lombok.Data;

@Data
public class PassRequest {
    String vehicleNumber;
    int days;
}
