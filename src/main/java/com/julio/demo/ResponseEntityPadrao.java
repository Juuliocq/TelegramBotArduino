package com.julio.demo;

public class ResponseEntityPadrao {

    protected final String message;

    public ResponseEntityPadrao(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
