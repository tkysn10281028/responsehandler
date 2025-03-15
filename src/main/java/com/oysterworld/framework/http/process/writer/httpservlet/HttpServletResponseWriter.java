package com.oysterworld.framework.http.process.writer.httpservlet;

import com.oysterworld.framework.http.process.writer.ResponseWriter;

import jakarta.servlet.http.HttpServletResponse;

public class HttpServletResponseWriter implements ResponseWriter {
    private final HttpServletResponse response;

    protected HttpServletResponseWriter(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public void writeResponse(Object result) {
        try {
            HttpServletResponseWriterProcessor.writeResponse(this.response, result);
        } catch (Exception e) {
            writeError(e);
        }
    }

    @Override
    public void writeError(Exception e) {
        HttpServletResponseWriterProcessor.writeError(this.response, e);
    }

}
