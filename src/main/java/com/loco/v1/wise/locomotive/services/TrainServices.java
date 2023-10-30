package com.loco.v1.wise.locomotive.services;

import com.loco.v1.wise.locomotive.dtos.TrainRequest;
import com.loco.v1.wise.locomotive.dtos.TrainRequests.TrainBogieRequest;
import com.loco.v1.wise.locomotive.dtos.TrainRequests.TrainBogieResponse;
import com.loco.v1.wise.locomotive.dtos.TrainResponse;
import com.loco.v1.wise.locomotive.entity.Trains.Train;
import com.loco.v1.wise.locomotive.entity.Trains.TrainBogie;
import com.loco.v1.wise.locomotive.entity.Trains.TrainPassengersInfo;

import java.util.List;

public interface TrainServices {

    TrainResponse addTrain(Train train);
    List<TrainBogieResponse> addTrainBogies(List<TrainBogieRequest> trainBogies);
    Train getTrainInformationByTrainName(String trainName);
    Train getTrainInformationByTrainNumber(String trainNumber);
    List<Train> getAllTrainsBySourceAndDestination(String source, String destinations);

    // TODO; Need to implements
//    TrainResponse bookATrain(TrainPassengersInfo trainPassengersInfo, TrainRequest trainRequest);
//    TrainResponse cancelBookingTrain(TrainRequest trainRequest);
//    TrainResponse getAvailableTrains(TrainRequest trainRequest);
//    TrainResponse getTrainStatus(TrainRequest trainRequest);
//    TrainPassengersInfo getPassengerInfoByName(String passengerName);
//    TrainPassengersInfo getPassengerInfoById(String passengerId);
//    TrainResponse getStatusByPNRNumber(TrainRequest trainRequest);
//    TrainResponse updatePassengerInfo(TrainRequest trainRequest);
//    List<Train> getAllTheTrains();
//    List<Train> getAllTrainsByDestination(String Destination);
//    List<Train> getAllTrainsBySource(String Source);
//    TrainPassengersInfo getPassengerInfoByPNRNumber(String PNRNum);


}
