package com.loco.v1.wise.locomotive.repository;

import com.loco.v1.wise.locomotive.entity.Trains.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainRepositories extends JpaRepository<Train, String> {

}
