package com.assignment.interactivechat.exception;

public class ApiException extends Exception{

    private final Integer errorCode;

    public ApiException(String errorMessage, Integer errorCode) {
        super(errorMessage);
        this.errorCode=errorCode;
    }

}
