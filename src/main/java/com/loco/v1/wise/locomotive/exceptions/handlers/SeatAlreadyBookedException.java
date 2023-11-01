package com.loco.v1.wise.locomotive.exceptions.handlers;

public class SeatAlreadyBookedException extends RuntimeException{

    public SeatAlreadyBookedException(String mess){
        super(mess);
    }
}
