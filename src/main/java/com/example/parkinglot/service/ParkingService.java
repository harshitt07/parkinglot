package com.example.parkinglot.service;

import com.example.parkinglot.request.ParkRequest;
import com.example.parkinglot.entity.Ticket;
import com.example.parkinglot.repository.ParkingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParkingService {

    private final ParkingRepository parkingRepository;

    public Ticket parkAndGetTicket(ParkRequest parkRequest) throws Exception {
        return parkingRepository.parkAndGetTicket(parkRequest.getVehicleType());
    }

    public Ticket updateExit(String ticketId) throws Exception {
        return parkingRepository.updateExit(ticketId);
    }
}
