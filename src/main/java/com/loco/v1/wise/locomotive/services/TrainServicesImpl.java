package com.loco.v1.wise.locomotive.services;

import com.loco.v1.wise.locomotive.dtos.TrainPassangerInfo.TrainPassengerInfoRequest;
import com.loco.v1.wise.locomotive.dtos.TrainRequests.TrainBogieRequest;
import com.loco.v1.wise.locomotive.dtos.TrainRequests.TrainBogieResponse;
import com.loco.v1.wise.locomotive.dtos.TrainResponse;
import com.loco.v1.wise.locomotive.dtos.UpdateAccountBalance;
import com.loco.v1.wise.locomotive.entity.AccountInformation;
import com.loco.v1.wise.locomotive.entity.Trains.Train;
import com.loco.v1.wise.locomotive.entity.Trains.TrainBogie;
import com.loco.v1.wise.locomotive.entity.Trains.TrainPassengersInfo;
import com.loco.v1.wise.locomotive.exceptions.TrainServiceException;
import com.loco.v1.wise.locomotive.exceptions.SeatAlreadyBookedException;
import com.loco.v1.wise.locomotive.payloads.MyPayloads;
import com.loco.v1.wise.locomotive.repository.TrainBogieRepositories;
import com.loco.v1.wise.locomotive.repository.TrainPassengerInfoRepositories;
import com.loco.v1.wise.locomotive.repository.TrainRepositories;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.*;

import static com.loco.v1.wise.locomotive.config.ExternalServiceUrls.URL_FOR_ACCOUNT_SERVICE;
import static com.loco.v1.wise.locomotive.config.ExternalServiceUrls.URL_FOR_ACCOUNT_UPDATE_SERVICE;
import static com.loco.v1.wise.locomotive.constants.TrainConstants.*;


@Service
@Slf4j
public class TrainServicesImpl implements TrainServices {

    @Autowired
    private TrainRepositories trainRepositories;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TrainPassengerInfoRepositories trainPassengerInfoRepositories;

    @Autowired
    private TrainBogieRepositories trainBogieRepositories;

    private final LocalDateTime localDateTime = LocalDateTime.now();

    @Override
    public TrainPassengersInfo bookATrain(TrainPassengerInfoRequest trainPassengerInfoRequest) {

        Optional<Train> train = trainRepositories.findById(trainPassengerInfoRequest.getTrainId());
        if (train.isPresent()) {

            Train gettingTrainInfo = train.get();
            Optional<TrainPassengersInfo> existingSeat = trainPassengerInfoRepositories.findByPassengerIdAndSeatNumber(trainPassengerInfoRequest.getPassengerId(), trainPassengerInfoRequest.getSeatNumber());

            /* If already booked a seat by another passenger, that you are booking */
            if (existingSeat.isPresent()) {
                throw new SeatAlreadyBookedException("Seat " + trainPassengerInfoRequest.getSeatNumber() + " has already been booked by another passenger. Please choose a different seat.");

            } else {
                Optional<TrainPassengersInfo> trainPassengersInfo = trainPassengerInfoRepositories.findById(trainPassengerInfoRequest.getPassengerId());

                // If You are booking a same seat
                if (trainPassengersInfo.isPresent()) {

                    if (Objects.equals(trainPassengersInfo.get().getSeatNumber(), trainPassengerInfoRequest.getSeatNumber()) &&
                            Objects.equals(trainPassengersInfo.get().getPassengerId(), trainPassengerInfoRequest.getPassengerId())) {
                        throw new SeatAlreadyBookedException("Seat " + trainPassengersInfo.get().getSeatNumber() + " has already been booked by you. You will be notified on your mobile number soon.");
                    }
                    return createProfile(trainPassengerInfoRequest, gettingTrainInfo, train.get());
                }
                return createProfile(trainPassengerInfoRequest, gettingTrainInfo, train.get());
            }
        }
        throw new TrainServiceException("Train Not Found.");
    }

    public TrainPassengersInfo createProfile(TrainPassengerInfoRequest trainPassengerInfoRequest, Train gettingTrainInfo, Train train) {
        TrainPassengersInfo trainPassengersCreate = TrainPassengersInfo.builder()
                .passengerId(trainPassengerInfoRequest.getPassengerId())
                .trainId(gettingTrainInfo.getTrainId())
                .trainName(trainPassengerInfoRequest.getTrainName())
                .pnrNumber(MyPayloads.forPnrNumberGenerator())
                .ticketNumber(MyPayloads.generateTicketNumber())
                .seatNumber(trainPassengerInfoRequest.getSeatNumber())
                .trainNumber(gettingTrainInfo.getTrainNumber())
                .firstName(trainPassengerInfoRequest.getFirstName())
                .lastName(trainPassengerInfoRequest.getLastName())
                .age(trainPassengerInfoRequest.getAge())
                .address(trainPassengerInfoRequest.getAddress())
                .email(trainPassengerInfoRequest.getEmail())
                .phone(trainPassengerInfoRequest.getPhone())
                .gender(trainPassengerInfoRequest.getGender())
                .trainPassengerInfo(train)
                .passportNumber(trainPassengerInfoRequest.getPassportNumber())
                .nationality(trainPassengerInfoRequest.getNationality())
                .messageStatus(TICKET_BOOKED_SUCCESSFULLY)
                .build();

        trainPassengerInfoRepositories.save(trainPassengersCreate);

        ResponseEntity<AccountInformation> accountInformationResponseEntity = restTemplate.getForEntity(URL_FOR_ACCOUNT_SERVICE, AccountInformation.class);
        AccountInformation accountInformation = accountInformationResponseEntity.getBody();

        if (accountInformation != null) {
            double mainAccountBalance = accountInformation.getAccountBalance();
            double priceOfTicket = trainPassengerInfoRequest.getTicketPrice();
            double main_ticket = mainAccountBalance - priceOfTicket;
            UpdateAccountBalance updateAccountBalance = new UpdateAccountBalance();
            updateAccountBalance.setAccountNumber(accountInformation.getAccountNumber());
            updateAccountBalance.setAccountBalance(main_ticket);
            restTemplate.put(URL_FOR_ACCOUNT_UPDATE_SERVICE, updateAccountBalance);
        }
        return trainPassengersCreate;
    }


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


    @Override
    public List<TrainBogieResponse> addTrainBogies(List<TrainBogieRequest> Trainbogies) {

        if (Trainbogies.isEmpty()) {
            throw new TrainServiceException("No bogie is to be added");
        }

        List<TrainBogieResponse> trainBogieResponse = new ArrayList<>();

        log.info("Getting Request ...");

        for (TrainBogieRequest bogies : Trainbogies) {
            Optional<Train> train = trainRepositories.findById(bogies.getTrainId());
            if (train.isPresent()) {
                Optional<TrainBogie> trainBogie = trainBogieRepositories.findById(bogies.getBogieId());

                if (trainBogie.isPresent()) {
                    TrainBogie existingBogie = trainBogie.get();
                    TrainBogieResponse response = new TrainBogieResponse();
                    response.setTranId(existingBogie.getTrainId());
                    response.setBogieId(existingBogie.getBogieId());
                    response.setBogieNumber(existingBogie.getBogieNumber());
                    response.setMessage("Bogie already exists");
                    response.setLocalDateTime(LocalDateTime.now());
                    trainBogieResponse.add(response);

                } else {

                    TrainBogie newTrain = TrainBogie.builder()
                            .bogieId(bogies.getBogieId())
                            .trainId(bogies.getTrainId())
                            .bogieNumber(bogies.getBogieNumber())
                            .bogieName(MyPayloads.generateBogieName())
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
                    TrainBogieResponse response = new TrainBogieResponse();
                    response.setTranId(newTrain.getTrainId());
                    response.setBogieId(newTrain.getBogieId());
                    response.setBogieNumber(newTrain.getBogieNumber());
                    response.setMessage(BOGIE_ADDED_SUCCESSFULLY);
                    response.setLocalDateTime(LocalDateTime.now());
                    trainBogieResponse.add(response);

                }
            } else {
                throw new TrainServiceException("The train is not available on our server that you are trying to find.");
            }
        }

        return trainBogieResponse;
    }

    @Override
    public Train getTrainInformationByTrainName(String trainName) {

        Optional<Train> train = trainRepositories.findByTrainName(trainName);

        if (trainName.isEmpty() || trainName.isBlank())
            throw new TrainServiceException("Invalid Input !!");

        if (train.isPresent()) return train.get();

        throw new TrainServiceException("Invalid Number !! Train with this number is not exist.");
    }

    @Override
    public Train getTrainInformationByTrainNumber(String trainNumber) {

        Optional<Train> train = trainRepositories.findByTrainNumber(trainNumber);

        if (trainNumber.isEmpty() || trainNumber.isBlank())
            throw new TrainServiceException("Blank Is not allowed !!");

        if (train.isPresent()) return train.get();

        throw new TrainServiceException("Invalid Number !! Train with this number is not exist.");

    }

    @Override
    public List<Train> getAllTrainsBySourceAndDestination(String source, String destinations) {

        Optional<List<Train>> train = trainRepositories.findBySourceAndDestination(source, destinations);

        if (source.isEmpty() && source.isBlank() || source.isEmpty() || destinations.isBlank())
            throw new TrainServiceException("Blank Is not allowed !!");

        if (train.isPresent()) return train.get();

        throw new TrainServiceException("Invalid Number !! Train with this number is not exist.");

    }

    @Override
    public List<Train> getAllTheTrains() {
        return trainRepositories.findAll();
    }

    @Override
    public List<Train> getAllTrainsByDestination(String destination) {
        Optional<List<Train>> train = trainRepositories.findByDestination(destination);
        if (train.isPresent()) {
            return train.get();
        }
        throw new TrainServiceException("No train is found in your given destination");
    }

    @Override
    public List<Train> getAllTrainsBySource(String Source) {
        Optional<List<Train>> train = trainRepositories.findBySource(Source);
        if (train.isPresent()) {
            return train.get();
        }
        throw new TrainServiceException("No train is found in your given source");
    }


    @Override
    public String cancelBookingTrain(TrainPassengerInfoRequest trainPassengerInfoRequest) {
        return null;
    }

}



