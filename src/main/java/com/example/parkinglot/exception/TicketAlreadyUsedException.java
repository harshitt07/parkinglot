package com.example.parkinglot.exception;

public class TicketAlreadyUsedException extends Exception {
    public TicketAlreadyUsedException(String message) {
        super(message);
    }
}
