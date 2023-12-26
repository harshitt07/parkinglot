package com.example.parkinglot.factory;

import com.example.parkinglot.entity.VehicleType;
import com.example.parkinglot.implemention.price.BikePriceCalculator;
import com.example.parkinglot.implemention.price.CarPriceCalculator;
import com.example.parkinglot.implemention.price.PriceCalculator;
import com.example.parkinglot.implemention.price.TruckPriceCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PriceCalculatorFactory {

    private final CarPriceCalculator carPriceCalculator;
    private final BikePriceCalculator bikePriceCalculator;
    private final TruckPriceCalculator truckPriceCalculator;

    public PriceCalculator getPriceCaculatorInstance(VehicleType vehicleType) {
        if(vehicleType.equals(VehicleType.BIKE)) {
            return bikePriceCalculator;
        } else if(vehicleType.equals(VehicleType.CAR)) {
            return carPriceCalculator;
        } else if(vehicleType.equals(VehicleType.TRUCK)) {
            return truckPriceCalculator;
        }
        return null;
    }

}
