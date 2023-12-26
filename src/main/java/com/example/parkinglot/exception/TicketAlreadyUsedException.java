package com.example.parkinglot.exception;

import com.example.parkinglot.entity.Ticket;

public class TicketAlreadyUsedException extends Exception {
    public TicketAlreadyUsedException(String message) {
        super(message);
    }
}
