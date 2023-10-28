package com.loco.v1.wise.locomotive.repository;

import com.loco.v1.wise.locomotive.entity.Trains.TrainCoach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainCoachRepositories extends JpaRepository<TrainCoach , String> {
}
