package com.loco.v1.wise.locomotive.entity.Trains;

import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Data
@Table(name = "TRN_BOGIE_INFO")
public class TrainBogie {

    private int bogieNumber;
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


    private List<TrainCoach> trainCoach;
}
