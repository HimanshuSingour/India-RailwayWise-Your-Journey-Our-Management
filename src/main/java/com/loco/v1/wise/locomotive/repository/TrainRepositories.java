package com.loco.v1.wise.locomotive.repository;

import com.loco.v1.wise.locomotive.entity.Trains.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainRepositories extends JpaRepository<Train, String> {
    Train findByTrainNumber(String trainNumber);
}
