package com.oysterworld.framework.http.process.writer.httpservlet;

import com.oysterworld.framework.http.process.exception.ResponseHandlerException;
import com.oysterworld.framework.http.process.helper.JsonBuilder;
import com.oysterworld.framework.http.process.writer.dto.ResponseObject;

import jakarta.servlet.http.HttpServletResponse;

public class HttpServletResponseWriterProcessor {
    private HttpServletResponseWriterProcessor() {

    }

    protected static void writeResponse(HttpServletResponse response, Object result) throws Exception {
        new HttpServletResponseWriterProcessor().handleSuccessHasResult(response, result);
    }

    protected static void writeError(HttpServletResponse response, Exception result) {
        new HttpServletResponseWriterProcessor().handleError(response, result);
    }

    private void handleSuccessHasResult(HttpServletResponse response, Object result) {
        writeResponse(response, 200, new ResponseObject(result, "-> success."));
    }

    private void handleError(HttpServletResponse response, Exception result) {
        if (result instanceof ResponseHandlerException) {
            var e = (ResponseHandlerException) result;
            writeResponse(response, e.getStatusCode(),
                    new ResponseObject(e.toString() + " message:" + e.getMessage(), "-> failure."));
            return;
        }
        writeResponse(response, 500,
                new ResponseObject(result.toString(), "-> failure."));
    }

    private static void writeResponse(HttpServletResponse response, int statusCode, ResponseObject res) {
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
