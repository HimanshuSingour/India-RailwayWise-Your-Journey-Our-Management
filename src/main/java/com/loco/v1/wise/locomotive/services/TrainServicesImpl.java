package com.loco.v1.wise.locomotive.services;

import com.loco.v1.wise.locomotive.repository.TrainBogieRepositories;
import com.loco.v1.wise.locomotive.repository.TrainCoachRepositories;
import com.loco.v1.wise.locomotive.repository.TrainPassengerInfoRepositories;
import com.loco.v1.wise.locomotive.repository.TrainRepositories;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TrainServicesImpl implements TrainServices{

    @Autowired
    private TrainBogieRepositories trainBogieRepositories;

    @Autowired
    private TrainCoachRepositories trainCoachRepositories;

    @Autowired
    private TrainRepositories trainRepositories;

    @Autowired
    private TrainPassengerInfoRepositories trainPassengerInfoRepositories;





}
