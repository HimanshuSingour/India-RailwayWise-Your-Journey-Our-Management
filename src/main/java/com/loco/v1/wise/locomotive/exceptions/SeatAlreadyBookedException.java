package com.loco.v1.wise.locomotive.exceptions;

public class SeatAlreadyBookedException extends RuntimeException{

    public SeatAlreadyBookedException(String mess){
        super(mess);
    }
}
