package com.oneifm.challenge.wos.wo.exceptions;

public class InvalidInputException extends RuntimeException{
    public static final String INVALID_ROOM_NUMBER = "No of rooms should be less than 100";

    public InvalidInputException(String message){
        super(message);
    }
}
