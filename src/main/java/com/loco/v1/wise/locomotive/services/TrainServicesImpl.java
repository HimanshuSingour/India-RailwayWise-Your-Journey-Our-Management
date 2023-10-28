package com.loco.v1.wise.locomotive.services;

import com.loco.v1.wise.locomotive.dtos.TrainResponse;
import com.loco.v1.wise.locomotive.entity.Trains.Train;
import com.loco.v1.wise.locomotive.exceptions.TrainServiceException;
import com.loco.v1.wise.locomotive.payloads.MyPayloads;
import com.loco.v1.wise.locomotive.repository.TrainRepositories;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static com.loco.v1.wise.locomotive.constants.TrainConstants.TRAIN_ADDED_SUCCESSFULLY;


@Service
@Slf4j
public class TrainServicesImpl implements TrainServices {

    @Autowired
    private TrainRepositories trainRepositories;

    private final LocalDateTime localDateTime = LocalDateTime.now();


    @Override
    public TrainResponse addTrain(Train train) {

        String trainId = UUID.randomUUID().toString();
        Optional<Train> findExistingTrain = trainRepositories.findById(train.getTrainId());

        Train takeTrain;

        if (findExistingTrain.isPresent()) {
            takeTrain = findExistingTrain.get();
        }

        if (train.getTrainName().isBlank()) {

            log.info("Invalid Input: Train name is null.");
            throw new TrainServiceException("Train name should not be null.");

        } else if (train.getTrainName().matches(".*\\d.*")) {

            log.info("Invalid Input: Train name contains numbers.");
            throw new TrainServiceException("Invalid Input: Train name contains numbers.");

            // TODO; Need to handle Exist Train

        log.info("Creating a new Train object");
        Train newTrain = Train.builder()
                .trainId(trainId)
                .trainNumber(MyPayloads.forTrainNumber())
                .trainInit(MyPayloads.forTrainInIt())
                .trainName(train.getTrainName())
                .sourceStation(train.getSourceStation())
                .destinationStation(train.getDestinationStation())
                .ticketPrice(train.getTicketPrice())
                .departureTime(train.getDepartureTime())
                .arrivalTime(train.getArrivalTime())
                .maxSpeed(train.getMaxSpeed())
                .trainStatus(train.getTrainStatus())
                .averageSpeed(train.getAverageSpeed())
                .trainAddTime(localDateTime)
                // Passenger info and bogie are null Currently because we need to build a train engine only
                .build();

        TrainResponse trainResponse = new TrainResponse();
        trainResponse.setTrainName(newTrain.getTrainName());
        trainResponse.setTrainInit(newTrain.getTrainInit());
        trainResponse.setTrainNumber(newTrain.getTrainNumber());
        trainResponse.setMessage(TRAIN_ADDED_SUCCESSFULLY);
        trainResponse.setTimeAdded(localDateTime);

        log.info("Saving the new train to the repository");
        trainRepositories.save(newTrain);
        log.info("Train saved successfully");


        return trainResponse;
    }
}

