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

import java.time.LocalDate;
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

    List<Train> searchTrains(String source, String destination, LocalDate travelDate);

    PnrStatusResponse getPassengerInfoByPNRNumber(String PNRNum);

    // TODO; Need to implements


    //SeatAvailabilityResponse checkSeatAvailability(String trainNumber, LocalDate travelDate, String coachClass, int numberOfSeats);
    //TrainRouteInfoResponse getTrainRouteInfo(String trainNumber);
    //List<TrainStation> getAllTrainStations();
    //TrainStation getTrainStationDetails(String stationCode);
    //SpecialServiceReservationResponse reserveSpecialServices(String trainNumber, List<SpecialServiceRequest> specialServices);
    //TrainFareResponse calculateTrainFare(String trainNumber, String source, String destination, String coachClass);
    //DiscountPromotionResponse applyDiscountsAndPromotions(String trainNumber, String passengerType, String promoCode);
    //GroupBookingResponse reserveGroupBookings(String trainNumber, List<GroupBookingRequest> groupBookings);
    //TrainSafetyInfoResponse getTrainSafetyInfo(String trainNumber);
    //TrainFeedbackResponse handleTrainComplaintsAndFeedback(String trainNumber, List<TrainComplaint> complaints);
    //TrainWifiEntertainmentResponse getTrainWifiAndEntertainmentInfo(String trainNumber);
    //TrainLostAndFoundResponse getLostAnd FoundInfo(String trainNumber);

}
