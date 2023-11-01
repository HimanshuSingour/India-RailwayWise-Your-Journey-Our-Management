package com.loco.v1.wise.locomotive.dtos.TrainPassangerInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainPassengerInfoRequest {

    private String passengerId;
    private String trainId;
    private String firstName;
    private String lastName;
    private int age;
    private String passportNumber;
    private String gender;
    private String address;
    private String phone;
    private String email;
    private String nationality;
    private String trainName;
    private String seatNumber;
    private String trainNumber;
    private String accountNumber;
    private String ifscCode;
    private String password;
    private double ticketPrice;


}
