package com.chamdarae.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class ChamdaraeException extends RuntimeException{
    public final Map<String,String> validation = new HashMap<>();

    public ChamdaraeException(String message) {
        super(message);
    }

    public ChamdaraeException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode();

    public void addValidation(String fieldName,String message){
        validation.put(fieldName,message);
    }
}
