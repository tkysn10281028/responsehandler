package com.oysterworld.framework.http.process.writer.dto;

public class ResponseObject {
    private Object data;
    private String message;

    public ResponseObject(Object data, String message) {
        this.data = data;
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
