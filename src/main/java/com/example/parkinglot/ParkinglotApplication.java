package com.example.parkinglot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * <p>
 * -----> /park/parkAndGetTicket() :
 * 			This will generate a ticket based on vehicleType
 * 			Steps : (1) Find Slots of Vehicle Type if available then generate ticket
 * 						=> Add Ticket and Update Slot
 * 						=> Response(Ticket => ticketId, entryTime, vehicleType, slotId)
 * 					(2) Else return error of full slots
 * 				</p>
 *
 * 		<p>
 *
 * -----> /park/onExit/{ticketId}
 * 			Steps : (1) This will calculate the price and update it in the tickets
 * 					(2) Empty the slot from the map
 * </p>
 *
 * <p>
 * ------> DS which I need is
 * 			=> Slot Map => { "vehicleType" : List : {Slots} }
 * 				slotType => BikeSlot, CarSlot, TruckSlot
 * 			=> Ticket Map => { "ticketId" : Ticket }
 * </p>
 *
 * <p>
 * ------> entity :
 * 			=> Ticket (ticketId, entryTime, slotId, vehicleType)
 * 			=> ParkRequest (vehicleType)
 * </p>
 *
 */

@SpringBootApplication
public class ParkinglotApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkinglotApplication.class, args);
	}

}