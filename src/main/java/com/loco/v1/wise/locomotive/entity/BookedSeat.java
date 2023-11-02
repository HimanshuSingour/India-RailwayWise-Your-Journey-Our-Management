package com.loco.v1.wise.locomotive.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.loco.v1.wise.locomotive.entity.Trains.TrainPassengersInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "BOOKED_SEAT_INFO")
@Entity
@Builder
public class BookedSeat {

    @Id
    private String SeatNumber;
    private String trainNumber;
    private double priceOfTicket;
    private String message;

}
