package com.loco.v1.wise.locomotive.dtos.TrainRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainBogieRequest {

    private String bogieId;
    private String trainId; // UUID
    private String bogieNumber;
    private String bogieName;
    private String bogieType;
    private double bogieWeight;
    private int maxPassengerCapacity;
    private String manufacturer;
    private int numberOfWheels;
    private boolean isAirConditioned;
    private double bogieLength;
    private String color;
    private boolean isElectric;
    private int numberOfDoors;
}
