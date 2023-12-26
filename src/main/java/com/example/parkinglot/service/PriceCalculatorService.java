package com.example.parkinglot.service;

import com.example.parkinglot.entity.Ticket;
import com.example.parkinglot.factory.PriceCalculatorFactory;
import com.example.parkinglot.implemention.price.PriceCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PriceCalculatorService {

    @Autowired
    @Qualifier(value = "bikePriceCalculator")
    private PriceCalculator priceCalculator;

    @Autowired
    private PriceCalculatorFactory priceCalculatorFactory;

    public int calculatePrice(Ticket ticket) {
        priceCalculator = priceCalculatorFactory.getPriceCaculatorInstance(ticket.getVehicleType());
        return priceCalculator.calculatePrice(ticket) + priceCalculator.calculateFine(ticket);
    }
}
