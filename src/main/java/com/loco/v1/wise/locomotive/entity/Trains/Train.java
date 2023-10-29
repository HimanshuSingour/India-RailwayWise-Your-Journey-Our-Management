package com.loco.v1.wise.locomotive.entity.Trains;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "TRAIN_INFO")
public class Train {

        @Id
        private String trainId; // UUID
        private String trainNumber; // 6 length
        private String trainInit; // 6 alphabetic
        private LocalDateTime trainAddTime;
        private String trainName;
        private String sourceStation;
        private String destinationStation;
        private double ticketPrice;
        private String departureTime;
        private String arrivalTime;
        private int maxSpeed;
        private String trainStatus;
        private int averageSpeed;


        @OneToMany(mappedBy = "trainPassengerInfo" , cascade = CascadeType.ALL)
        private List<TrainPassengersInfo> passengerInfo;

        @OneToMany(mappedBy = "trainBogie" , cascade = CascadeType.ALL)
        private List<TrainBogie> bogie;

}
