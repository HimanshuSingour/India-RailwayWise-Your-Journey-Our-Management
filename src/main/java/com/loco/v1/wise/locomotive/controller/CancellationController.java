package com.loco.v1.wise.locomotive.controller;

import com.loco.v1.wise.locomotive.dtos.TrainBookCancellation;
import com.loco.v1.wise.locomotive.dtos.TrainBoolCancellationResponse;
import com.loco.v1.wise.locomotive.dtos.TrainPassangerInfo.TrainPassengerInfoRequest;
import com.loco.v1.wise.locomotive.entity.Trains.TrainPassengersInfo;
import com.loco.v1.wise.locomotive.services.TrainServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/train/v10/cancellation")
@Slf4j
public class CancellationController {

    @Autowired
    private TrainServices trainServices;

    @GetMapping("/cancel/{seatNumber}/{trainNumber}")
    public ResponseEntity<TrainBoolCancellationResponse> bookATrain(@PathVariable String seatNumber , @PathVariable String trainNumber) {
        log.info("Received a request to get train information by source and destination: Source={} , d={}", seatNumber , trainNumber);
        TrainBoolCancellationResponse trainResponse = trainServices.cancelBookingTrain(seatNumber , trainNumber);
        log.info("Train information retrieved successfully: {}", trainResponse);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(trainResponse);
    }
}
