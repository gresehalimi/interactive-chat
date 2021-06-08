package com.assignment.interactivechat.exception;


public enum ResponseStatus {
    USER_BY_THIS_ID_NOT_FOUND(2000),
    USER_BY_THIS_USERNAME_ALREADY_EXISTS(2001),
    USER_MESSAGE_NOT_FOUND(2002),
    MESSAGE_ALREADY_SEEN(2003);


    private final int value;

    public int getValue() {
        return value;
    }

    ResponseStatus(Integer value) {
        this.value = value;
    }
}