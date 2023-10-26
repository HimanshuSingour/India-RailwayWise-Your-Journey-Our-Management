package com.loco.v1.wise.locomotive.entity.Locomotive;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "LOCO_INFO")
public class LocoTrain {

    private int locoId;
    private String locomotiveName;
    private String manufacturer;
    private int maxSpeed;
    private int yearBuilt;
    private double length;
    private double weight;
    private String fuelType;
    private boolean isElectric;
    private boolean isOperational;
    private int numberOfWheels;
    private boolean loaded;
    private String[] materialsCarry;
}
