package com.loco.v1.wise.locomotive.dtos;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainBookCancellation {

    @Id
    private String SeatNumber;
    private String trainNumber;
    private String message;
    private String RE_FUND;
}
