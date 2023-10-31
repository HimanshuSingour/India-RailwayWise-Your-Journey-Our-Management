package com.loco.v1.wise.locomotive.entity.Trains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TRN_PASS_INFO")
public class TrainPassengersInfo {

    @Id
    private String passengerId;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String passportNumber;
    private String address;
    private String phone;
    private String email;
    private String nationality;
    private String seatNumber;
    private String flightNumber;
    private String ticketNumber;
    private String pnrNumber;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pk_trainId")
    private Train trainPassengerInfo;
}
