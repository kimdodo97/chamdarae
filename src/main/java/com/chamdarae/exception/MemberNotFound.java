package com.chamdarae.exception;

public class MemberNotFound extends ChamdaraeException{
    private static final String MESSAGE = "존재하지 않는 사용자";
    
    public MemberNotFound() {
        super(MESSAGE);
    }

    public MemberNotFound(Throwable cause) {
        super(MESSAGE, cause);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
