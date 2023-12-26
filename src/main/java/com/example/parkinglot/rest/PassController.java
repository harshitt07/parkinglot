package com.example.parkinglot.rest;

import com.example.parkinglot.entity.Pass;
import com.example.parkinglot.request.PassRequest;
import com.example.parkinglot.service.PassService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pass")
@RequiredArgsConstructor
public class PassController {

    private final PassService passService;

    @PostMapping
    public Pass addPass(@RequestBody PassRequest passRequest) {
        return passService.addPass(passRequest);
    }

}
