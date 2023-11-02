package com.loco.v1.wise.locomotive.controller;


import com.loco.v1.wise.locomotive.dtos.PnrStatus.PnrStatusResponse;
import com.loco.v1.wise.locomotive.dtos.TrainPassangerInfo.TrainPassengerInfoRequest;
import com.loco.v1.wise.locomotive.entity.Trains.TrainPassengersInfo;
import com.loco.v1.wise.locomotive.services.TrainServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/train/v7/passenger")
@Slf4j
public class PassengerController {

    @Autowired
    private TrainServices trainServices;

    @GetMapping("/book")
    public ResponseEntity<TrainPassengersInfo> bookATrain(@RequestBody TrainPassengerInfoRequest trainPassengerInfoRequest) {
        log.info("Received a request to get train information by source and destination: Source={}", trainPassengerInfoRequest);
        TrainPassengersInfo trainResponse = trainServices.bookATrain(trainPassengerInfoRequest);
        log.info("Train information retrieved successfully: {}", trainResponse);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(trainResponse);
    }


    @GetMapping("/get/info-pnr/{pnrNumber}")
    public ResponseEntity<PnrStatusResponse> pnrInfoNumber(@PathVariable String pnrNumber) {
        log.info("Received a request to get train information by source and destination: Source={}", pnrNumber);
        PnrStatusResponse trainResponse = trainServices.getPassengerInfoByPNRNumber(pnrNumber);
        log.info("Train information retrieved successfully: {}", trainResponse);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(trainResponse);
    }
}
