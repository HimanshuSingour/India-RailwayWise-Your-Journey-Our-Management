package com.loco.v1.wise.locomotive.dtos.PnrStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PnrStatusResponse {

    private String pnrNumber;
    private String passengerName;
    private String seatNumber;
    private String trainNumber;
    private String trainName;
    private String ticketNumber;
    private String pnrStatus;
    private String departureStation;
    private String arrivalStation;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private LocalDateTime currentTime;
    private String message;
    private int totalPassengers;
    private double fare;
    private boolean confirmed;


}
