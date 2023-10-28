package com.loco.v1.wise.locomotive.controller;

import com.loco.v1.wise.locomotive.services.TrainServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class TrainController {

    @Autowired
    private TrainServices trainServices;
}
