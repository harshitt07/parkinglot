package com.example.parkinglot.implemention.price;

import com.example.parkinglot.entity.Ticket;
import org.springframework.stereotype.Component;

@Component
public class BikePriceCalculator implements PriceCalculator {
    @Override
    public int calculatePrice(Ticket ticket) {
        return 20;
    }

    @Override
    public int calculateFine(Ticket ticket) {
        int fine = 0;
        long totalTime = ticket.getExitTime() - ticket.getEntryTime();
        double timeInHours = (double) totalTime / (1000 * 60 * 60); // 2 12 24
        if(timeInHours >= 24.0) {
            fine += 4;
        } else if(timeInHours >= 12.0) {
            fine += 2;
        } else if(timeInHours >= 2.0) {
            fine += 1;
        }
        return fine;
    }
}
