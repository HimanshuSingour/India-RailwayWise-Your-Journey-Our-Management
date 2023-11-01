package com.loco.v1.wise.locomotive.exceptions.handlers;

import com.loco.v1.wise.locomotive.exceptions.PassengerAlreadyBookedException;
import com.loco.v1.wise.locomotive.exceptions.TrainServiceException;
import com.loco.v1.wise.locomotive.payloads.ErrorMessages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlers {

    @ExceptionHandler(TrainServiceException.class)
    ResponseEntity<ErrorMessages> serviceProviderNullException(TrainServiceException ex){
        ErrorMessages errorMessages = new ErrorMessages();
        errorMessages.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorMessages , HttpStatus.OK);
    }

    @ExceptionHandler(PassengerAlreadyBookedException.class)
    ResponseEntity<ErrorMessages> alreadyBookedASeat(PassengerAlreadyBookedException ex){
        ErrorMessages errorMessages = new ErrorMessages();
        errorMessages.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorMessages , HttpStatus.OK);
    }

}
