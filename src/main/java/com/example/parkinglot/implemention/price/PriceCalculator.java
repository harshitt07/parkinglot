package com.example.parkinglot.implemention.price;

import com.example.parkinglot.entity.Ticket;

public interface PriceCalculator {

    int calculatePrice(Ticket ticket);

    int calculateFine(Ticket ticket);

}
