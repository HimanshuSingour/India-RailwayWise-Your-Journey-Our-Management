package com.loco.v1.wise.locomotive.dtos.TrainRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainBogieResponse {

    private String tranId;
    private String bogieId;
    private String bogieNumber;
    private String message;
    private LocalDateTime localDateTime;
}
