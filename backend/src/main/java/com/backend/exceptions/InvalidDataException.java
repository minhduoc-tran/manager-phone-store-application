package com.backend.exceptions;

public class InvalidDataException extends  RuntimeException{
    public  InvalidDataException(String message){
        super(message);
    }
}
