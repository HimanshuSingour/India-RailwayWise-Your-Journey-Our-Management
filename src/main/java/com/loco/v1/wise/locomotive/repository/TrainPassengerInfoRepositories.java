package com.loco.v1.wise.locomotive.repository;

import com.loco.v1.wise.locomotive.entity.Trains.TrainPassengersInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainPassengerInfoRepositories extends JpaRepository<TrainPassengersInfo , String> {


    Optional<TrainPassengersInfo> findBySeatNumber(String seatNumber);
}
