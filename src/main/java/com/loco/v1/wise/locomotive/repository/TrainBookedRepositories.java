package com.loco.v1.wise.locomotive.repository;

import com.loco.v1.wise.locomotive.entity.BookedSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainBookedRepositories extends JpaRepository<BookedSeat , String> {


    @Query("SELECT u FROM BookedSeat u WHERE u.seatNumber = :seatNumber")
    Optional<BookedSeat> findBySeatNumber(String seatNumber);
    int countBySeatNumber(String seatNumber);
}
