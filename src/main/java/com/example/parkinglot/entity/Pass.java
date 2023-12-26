package com.example.parkinglot.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pass {
    String vehicleNumber;
    Long issueDate;
    Long duration;
}
