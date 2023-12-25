package com.example.parkinglot.rest;


import com.example.parkinglot.request.ParkRequest;
import com.example.parkinglot.service.ParkingService;
import com.example.parkinglot.entity.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/park")
public class ParkingLotController {

    private final ParkingService parkingService;

    @PostMapping
    public ResponseEntity<Ticket> parkAndGetTicket(@RequestBody ParkRequest parkRequest) throws Exception {
        return ResponseEntity.ok().body(parkingService.parkAndGetTicket(parkRequest));
    }

    @PutMapping("/onExit/{ticketId}")
    public ResponseEntity<Ticket> updateExit(@PathVariable(name = "ticketId") String ticketId) throws Exception {
        return ResponseEntity.ok().body(parkingService.updateExit(ticketId));
    }

}
