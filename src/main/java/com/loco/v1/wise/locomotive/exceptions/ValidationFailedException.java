package com.loco.v1.wise.locomotive.exceptions;

public class ValidationFailedException extends RuntimeException{

    public ValidationFailedException(String message){
        super(message);
    }
}
