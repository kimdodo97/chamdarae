package com.chamdarae.exception;

public class InvalidRequest extends ChamdaraeException{
    private static final String MESSAGE = "요청에 문제가 있습니다.";
    public InvalidRequest() {
        super(MESSAGE);
    }

    public InvalidRequest(String fieldName, String message) {
        super(MESSAGE);
        addValidation(fieldName,message);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
