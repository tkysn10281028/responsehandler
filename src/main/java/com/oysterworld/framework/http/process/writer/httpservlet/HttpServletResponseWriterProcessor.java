package com.oysterworld.framework.http.process.writer.httpservlet;

import com.oysterworld.framework.http.process.helper.JsonBuilder;
import com.oysterworld.framework.http.process.writer.dto.ResponseObject;

import jakarta.servlet.http.HttpServletResponse;

public class HttpServletResponseWriterProcessor {
    public static void writeResponse(HttpServletResponse response, Object result) throws Exception {
        new HttpServletResponseWriterProcessor().handleSuccessHasResult(response, result);
    }

    public static void writeError(HttpServletResponse response, Exception result) {
        new HttpServletResponseWriterProcessor().handleError(response, result);
    }

    public void handleSuccessHasResult(HttpServletResponse response, Object result) {
        writeResponse(response, 200, new ResponseObject(result, "-> success."));
    }

    public void handleError(HttpServletResponse response, Exception result) {
        writeResponse(response, 500,
                new ResponseObject(result.toString(), "-> failure."));
    }

    public static void writeResponse(HttpServletResponse response, int statusCode, ResponseObject res) {
        response.setStatus(statusCode);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            String json = new JsonBuilder().writeValueAsString(res);
            response.getWriter().write(json);
            response.getWriter().flush();
        } catch (Exception e) {
            writeError(response, e);
        }
    }
}
