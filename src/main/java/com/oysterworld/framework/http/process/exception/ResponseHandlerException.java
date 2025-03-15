package com.oysterworld.framework.http.process.exception;

public class ResponseHandlerException extends RuntimeException {
    private int statusCode;

    public ResponseHandlerException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
