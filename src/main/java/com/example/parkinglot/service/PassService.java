package com.example.parkinglot.service;

import com.example.parkinglot.entity.Pass;
import com.example.parkinglot.repository.PassRepository;
import com.example.parkinglot.request.PassRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PassService {

    private final PassRepository passRepository;

    public Pass addPass(PassRequest passRequest) {
        Pass pass = Pass.builder()
                .issueDate(System.currentTimeMillis())
                .duration((long) passRequest.getDays() * 60 * 60 * 1000)
                .vehicleNumber(passRequest.getVehicleNumber())
                .build();
        passRepository.addPass(passRequest.getVehicleNumber(), pass);
        return pass;
    }

    public boolean isPassValid(String vehicleNumber) {
        Pass pass = passRepository.getPass(vehicleNumber);
        return (pass != null && System.currentTimeMillis() - pass.getIssueDate() <= pass.getDuration());
    }

}
