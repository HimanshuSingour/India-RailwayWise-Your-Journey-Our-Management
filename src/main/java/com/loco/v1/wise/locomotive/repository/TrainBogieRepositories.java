package com.loco.v1.wise.locomotive.repository;

import com.loco.v1.wise.locomotive.entity.Trains.TrainBogie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainBogieRepositories extends JpaRepository<TrainBogie, String> {
}
