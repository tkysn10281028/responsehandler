package com.oysterworld.framework.http.process.writer;

public interface ResponseWriter {
    void writeResponse(Object result);
    void writeError(Exception e);
}