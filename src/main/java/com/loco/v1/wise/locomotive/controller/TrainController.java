package com.loco.v1.wise.locomotive.controller;

import com.loco.v1.wise.locomotive.dtos.TrainRequests.TrainBogieRequest;
import com.loco.v1.wise.locomotive.dtos.TrainRequests.TrainBogieResponse;
import com.loco.v1.wise.locomotive.dtos.TrainResponse;
import com.loco.v1.wise.locomotive.entity.Trains.Train;
import com.loco.v1.wise.locomotive.entity.Trains.TrainBogie;
import com.loco.v1.wise.locomotive.services.TrainServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/train/v7/server")
public class TrainController {

    private final Logger log = LoggerFactory.getLogger(TrainController.class);

    @Autowired
    private TrainServices trainServices;

    @PostMapping("/add-train")
    public ResponseEntity<TrainResponse> addTrain(@RequestBody Train train) {
        log.info("Received a request to add a train.");
        TrainResponse trainResponse = trainServices.addTrain(train);
        log.info("Train added successfully: {}", trainResponse);
        return new ResponseEntity<>(trainResponse, HttpStatus.ACCEPTED);
    }

    @PostMapping("/add-bogies")
    public ResponseEntity<List<TrainBogieResponse>> addTrainBogies(@RequestBody List<TrainBogieRequest> trainBogies) {
        log.info("Received a request to add train bogies.");
        List<TrainBogieResponse> trainResponse = trainServices.addTrainBogies(trainBogies);
        log.info("Train bogies added successfully: {}", trainResponse);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(trainResponse);
    }

    @GetMapping("/get/info/v1/{trainName}")
    public ResponseEntity<Train> getTrainInfoByTrainName(@PathVariable String trainName) {
        log.info("Received a request to get train information by name: {}", trainName);
        Train trainResponse = trainServices.getTrainInformationByTrainName(trainName);
        log.info("Train information retrieved successfully: {}", trainResponse);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(trainResponse);
    }

    @GetMapping("/get/info/v2/{trainNumber}")
    public ResponseEntity<Train> getTrainInfoByTrainNumber(@PathVariable String trainNumber) {
        log.info("Received a request to get train information by number: {}", trainNumber);
        Train trainResponse = trainServices.getTrainInformationByTrainNumber(trainNumber);
        log.info("Train information retrieved successfully: {}", trainResponse);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(trainResponse);
    }

    @GetMapping("/get/info/v3/{source}/{destination}")
    public ResponseEntity<List<Train>> getTrainInfoBySourceAndDestination(@PathVariable String source, @PathVariable String destination) {
        log.info("Received a request to get train information by source and destination: Source={}, Destination={}", source, destination);
        List<Train> trainResponse = trainServices.getAllTrainsBySourceAndDestination(source, destination);
        log.info("Train information retrieved successfully: {}", trainResponse);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(trainResponse);
    }

    @GetMapping("/get/info/destination/v4/{destination}")
    public ResponseEntity<List<Train>> getTrainByDestinationStation(@PathVariable String destination) {
        log.info("Received a request to get train information by source and destination: Destination={}",destination);
        List<Train> trainResponse = trainServices.getAllTrainsByDestination(destination);
        log.info("Train information retrieved successfully: {}", trainResponse);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(trainResponse);
    }

    @GetMapping("/get/info/source/v4/{source}")
    public ResponseEntity<List<Train>> getTrainBySourceStation(@PathVariable String source) {
        log.info("Received a request to get train information by source and destination: Source={}", source);
        List<Train> trainResponse = trainServices.getAllTrainsBySource(source);
        log.info("Train information retrieved successfully: {}", trainResponse);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(trainResponse);
    }



}
