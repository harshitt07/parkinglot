package com.example.parkinglot.repository;

import com.example.parkinglot.entity.Pass;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class PassRepository {

    HashMap<String, Pass> passStore = new HashMap<>();

    public void addPass(String vehicleNumber, Pass pass) {
        passStore.put(vehicleNumber, pass);
        System.out.println(passStore.get(vehicleNumber));
    }

    public Pass getPass(String vehicleNumber) {
        if (passStore.containsKey(vehicleNumber)) return passStore.get(vehicleNumber);
        return null;
    }

}
