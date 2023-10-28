package com.loco.v1.wise.locomotive.repository;

import com.loco.v1.wise.locomotive.entity.Trains.TrainPassengersInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainPassengerInfoRepositories extends JpaRepository<TrainPassengersInfo, String> {
}
