package com.loco.v1.wise.locomotive.dtos;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainResponse {

    private String trainNumber; // 6 length
    private String trainInit; // 6 alphabetic
    private String trainName;
    private String message;
    private LocalDateTime timeAdded;

}
