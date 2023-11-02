package com.loco.v1.wise.locomotive.services;

import com.loco.v1.wise.locomotive.dtos.PnrStatus.PnrStatusResponse;
import com.loco.v1.wise.locomotive.dtos.TrainBookCancellation;
import com.loco.v1.wise.locomotive.dtos.TrainBoolCancellationResponse;
import com.loco.v1.wise.locomotive.dtos.TrainPassangerInfo.TrainPassengerInfoRequest;
import com.loco.v1.wise.locomotive.dtos.TrainRequests.TrainBogieRequest;
import com.loco.v1.wise.locomotive.dtos.TrainRequests.TrainBogieResponse;
import com.loco.v1.wise.locomotive.dtos.TrainResponse;
import com.loco.v1.wise.locomotive.entity.BookedSeat;
import com.loco.v1.wise.locomotive.entity.Trains.Train;
import com.loco.v1.wise.locomotive.entity.Trains.TrainPassengersInfo;

import java.util.List;

public interface TrainServices {

    TrainResponse addTrain(Train train);

    List<TrainBogieResponse> addTrainBogies(List<TrainBogieRequest> trainBogies);

    Train getTrainInformationByTrainName(String trainName);

    Train getTrainInformationByTrainNumber(String trainNumber);

    List<Train> getAllTrainsBySourceAndDestination(String source, String destinations);

    List<Train> getAllTheTrains();

    List<Train> getAllTrainsByDestination(String Destination);

    List<Train> getAllTrainsBySource(String Source);

    TrainPassengersInfo bookATrain(TrainPassengerInfoRequest trainPassengerInfoRequest);

    TrainBoolCancellationResponse cancelBookingTrain(String seatNumber , String trainNumber, String accountNumber);

    PnrStatusResponse getPassengerInfoByPNRNumber(String PNRNum);

    // TODO; Need to implements
//    TrainResponse getAvailableTrains(TrainRequest trainRequest);
//    TrainResponse getTrainStatus(TrainRequest trainRequest);
//    TrainPassengersInfo getPassengerInfoByName(String passengerName);
//    TrainPassengersInfo getPassengerInfoById(String passengerId);
//    TrainResponse getStatusByPNRNumber(TrainRequest trainRequest);
//    TrainPassengersInfo getPassengerInfoByPNRNumber(String PNRNum);


}
