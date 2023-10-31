package com.loco.v1.wise.locomotive.dtos.TrainPassangerInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TrainPassengerInfoRequest {

    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String address;
    private String phone;
    private String email;
    private String nationality;

    private String trainId;
    private String trainName;
    private String trainNumber;


}
