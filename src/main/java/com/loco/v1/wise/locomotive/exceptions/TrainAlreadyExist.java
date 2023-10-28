package com.loco.v1.wise.locomotive.exceptions;

public class TrainAlreadyExist extends RuntimeException{

    public TrainAlreadyExist(String message){
        super(message);
    }
}
