package com.loco.v1.wise.locomotive.entity.Trains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "TRN_BOGIE_INFO")
public class TrainBogie {

    @Id
    private int bogieNumber;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pk_trainId")
    private Train trainBogie;

}
