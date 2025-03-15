package com.oysterworld.framework.http.process.handler;

import com.oysterworld.framework.http.process.writer.ResponseWriter;

public class ResponseHandlerFactory {
    public static ResponseHandler createInstance(ResponseWriter writer) {
        return new ResponseHandler(writer);
    }
}
