package com.loco.v1.wise.locomotive.controller;


import com.loco.v1.wise.locomotive.dtos.TrainPassangerInfo.TrainPassengerInfoRequest;
import com.loco.v1.wise.locomotive.entity.Trains.Train;
import com.loco.v1.wise.locomotive.services.TrainServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/train/v7/passenger")
@Slf4j
public class PassangerController {

    @Autowired
    private TrainServices trainServices;

    @GetMapping("/book")
    public ResponseEntity<String> bookATrain(@RequestBody TrainPassengerInfoRequest trainPassengerInfoRequest) {
        log.info("Received a request to get train information by source and destination: Source={}", trainPassengerInfoRequest);
        String trainResponse = trainServices.bookATrain(trainPassengerInfoRequest);
        log.info("Train information retrieved successfully: {}", trainResponse);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(trainResponse);
    }
}
