package com.loco.v1.wise.locomotive.services;

import com.loco.v1.wise.locomotive.dtos.TrainRequests.TrainBogieRequest;
import com.loco.v1.wise.locomotive.dtos.TrainRequests.TrainBogieResponse;
import com.loco.v1.wise.locomotive.dtos.TrainResponse;
import com.loco.v1.wise.locomotive.entity.Trains.Train;
import com.loco.v1.wise.locomotive.entity.Trains.TrainBogie;
import com.loco.v1.wise.locomotive.exceptions.TrainServiceException;
import com.loco.v1.wise.locomotive.payloads.MyPayloads;
import com.loco.v1.wise.locomotive.repository.TrainBogieRepositories;
import com.loco.v1.wise.locomotive.repository.TrainRepositories;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

import static com.loco.v1.wise.locomotive.constants.TrainConstants.BOGIE_ADDED_SUCCESSFULLY;
import static com.loco.v1.wise.locomotive.constants.TrainConstants.TRAIN_ADDED_SUCCESSFULLY;


@Service
@Slf4j
public class TrainServicesImpl implements TrainServices {

    @Autowired
    private TrainRepositories trainRepositories;

    @Autowired
    private TrainBogieRepositories trainBogieRepositories;

    private final LocalDateTime localDateTime = LocalDateTime.now();


    @Override
    public TrainResponse addTrain(Train train) {

        String autoTrainId = UUID.randomUUID().toString();
        if (train.getTrainName().isBlank()) {

            log.info("Invalid Input: Train name is null.");
            throw new TrainServiceException("Train name should not be null.");

        } else if (train.getTrainName().matches(".*\\d.*")) {

            log.info("Invalid Input: Train name contains numbers.");
            throw new TrainServiceException("Invalid Input: Train name contains numbers.");

        }
        Train newTrain = Train.builder()
                .trainId(autoTrainId)
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

    //TODO: Need to handle existing train !
    @Override
    public List<TrainBogieResponse> addTrainBogies(List<TrainBogieRequest> Trainbogies) {

        List<TrainBogieResponse> trainBogieResponse = null;

        log.info("Getting Request ...");

        for (TrainBogieRequest bogies : Trainbogies) {
            Optional<Train> train = trainRepositories.findById(bogies.getTrainId());
            if (train.isPresent()) {
                Optional<TrainBogie> trainBogie = trainBogieRepositories.findById(bogies.getBogieId());

                if (trainBogie.isEmpty()) {
                    TrainBogie newTrain = TrainBogie.builder()
                            .bogieId(bogies.getBogieId())
                            .trainId(bogies.getTrainId())
                            .bogieNumber(bogies.getBogieNumber())
                            .bogieName(bogies.getBogieName())
                            .bogieType(bogies.getBogieType())
                            .bogieWeight(bogies.getBogieWeight())
                            .maxPassengerCapacity(bogies.getMaxPassengerCapacity())
                            .manufacturer(bogies.getManufacturer())
                            .numberOfWheels(bogies.getNumberOfWheels())
                            .isAirConditioned(bogies.isAirConditioned())
                            .bogieLength(bogies.getBogieLength())
                            .color(bogies.getColor())
                            .isElectric(bogies.isElectric())
                            .numberOfDoors(bogies.getNumberOfDoors())
                            .trainBogie(train.get())
                            .build();

                    trainBogieRepositories.save(newTrain);
                    log.info("Bogie is successfully connected to train");

                    trainBogieResponse = new ArrayList<>();
                    TrainBogieResponse response = new TrainBogieResponse();
                    response.setTranId(newTrain.getTrainId());
                    response.setBogieId(newTrain.getBogieId());
                    response.setBogieNumber(newTrain.getBogieNumber());
                    response.setMessage(BOGIE_ADDED_SUCCESSFULLY);
                    response.setLocalDateTime(LocalDateTime.now());
                    trainBogieResponse.add(response);

                } else {

                    throw new TrainServiceException("I think these bogies already exist.");
                }
            } else {
                throw new TrainServiceException("The train is not available on our server that you are trying to find.");
            }
        }

        return trainBogieResponse;
    }
}

