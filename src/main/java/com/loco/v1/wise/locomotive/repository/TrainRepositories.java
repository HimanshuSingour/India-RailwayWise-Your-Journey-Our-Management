package com.loco.v1.wise.locomotive.repository;

import com.loco.v1.wise.locomotive.entity.Trains.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainRepositories extends JpaRepository<Train, String> {

    Optional<Train> findByTrainName(String trainName);

    Optional<Train> findByTrainNumber(String trainNumber);

    @Query("SELECT u FROM Train u WHERE u.sourceStation = :sourceStation AND u.destinationStation = :destinationStation ")
    Optional<List<Train>> findBySourceAndDestination(String sourceStation, String destinationStation);

    @Query("SELECT u FROM Train u WHERE u.destinationStation = :destinationStation")
    Optional<List<Train>> findByDestination(String destinationStation);

    @Query("SELECT u FROM Train u WHERE u.sourceStation = :sourceStation")
    Optional<List<Train>> findBySource(String sourceStation);
}
