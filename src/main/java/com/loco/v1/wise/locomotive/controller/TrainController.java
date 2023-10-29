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

import java.util.List;


@RestController
@RequestMapping("/train/v7/server")
public class TrainController {

    @Autowired
    private TrainServices trainServices;

    @PostMapping("/add")
    ResponseEntity<TrainResponse> addTrain(@RequestBody Train train) {
        TrainResponse trainResponse = trainServices.addTrain(train);
        return new ResponseEntity<>(trainResponse, HttpStatus.ACCEPTED);

    }

    @PostMapping("/add-bogies")
    public ResponseEntity<List<TrainBogieResponse>> addTrainBogies(@RequestBody List<TrainBogieRequest> trainBogies) {
        List<TrainBogieResponse> trainResponse = trainServices.addTrainBogies(trainBogies);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(trainResponse);
    }

}
