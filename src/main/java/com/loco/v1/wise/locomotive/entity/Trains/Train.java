package com.loco.v1.wise.locomotive.entity.Trains;

import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name = "TRAIN_INFO")
public class Train {

        private String trainNumber;
        private String trainInit;
        private String trainName;
        private String sourceStation;
        private String destinationStation;
        private int numberOfPassengers;
        private double ticketPrice;
        private boolean isExpress;
        private boolean isAC;
        private boolean isWiFiAvailable;
        private boolean isOnTime;
        private String departureTime;
        private String arrivalTime;
        private int numberOfCoaches;
        private int maxSpeed;
        private String locomotiveType;
        private String driverName;
        private String conductorName;
        private int seatAvailability;
        private int totalPassengerCapacity;
        private boolean isCateringAvailable;
        private boolean isWheelchairAccessible;
        private String trainType;
        private String trainStatus;
        private String trainEmergencyContacts;
        private int averageSpeed;
        private String trainCapacityStats;
        private boolean isChildFriendly;
        private String trainEmissionsInfo;

        @OneToOne
        private TrainSaftyFeature safetyFeatures;

        @OneToMany
        private List<TrainPassangersInfo> passengerInfo;

        @OneToMany
        private List<TrainBogie> coach;


}
