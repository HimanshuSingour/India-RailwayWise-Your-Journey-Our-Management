package com.loco.v1.wise.locomotive.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainBoolCancellationResponse {

    private String message;
    private String reFund;
}
