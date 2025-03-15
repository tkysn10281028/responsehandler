package com.oysterworld.framework.http.process.writer.httpservlet;

import jakarta.servlet.http.HttpServletResponse;

public class HttpServletResponseWriterFactory {
    public static HttpServletResponseWriter createInstance(HttpServletResponse response) {
        return new HttpServletResponseWriter(response);
    }
}
