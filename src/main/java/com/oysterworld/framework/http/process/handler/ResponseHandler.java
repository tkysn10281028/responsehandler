package com.oysterworld.framework.http.process.handler;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import com.oysterworld.framework.http.process.invoker.MethodInvoker;
import com.oysterworld.framework.http.process.writer.ResponseWriter;

public class ResponseHandler {
    private ResponseWriter writer;
    private Object result;

    protected ResponseHandler(ResponseWriter writer) {
        this.writer = writer;
    }

    public void run(Runnable func) {
        runInner(func);
    }

    public <A> void run(Consumer<A> func, A arg) {
        runInner(func, arg);
    }

    public <A, B> void run(BiConsumer<A, B> func, A arg1, B arg2) {
        runInner(func, arg1, arg2);
    }

    public <A> void run(Supplier<A> func) {
        runInner(func);
    }

    public <A, B> void run(Function<A, B> func, A arg) {
        runInner(func, arg);
    }

    public <A, B, C> void run(BiFunction<A, B, C> func, A arg1, B arg2) {
        runInner(func, arg1, arg2);
    }

    private void runInner(Object func, Object... args) {
        execute(func, args);
        if (this.result instanceof Exception) {
            return;
        }
        writeResponse(this.result);
    }

    private void execute(Object func, Object[] args) {
        try {
            this.result = MethodInvoker.execute(func, args);
        } catch (Exception e) {
            this.result = e;
            this.writer.writeError(e);
        }
    }

    private void writeResponse(Object result) {
        try {
            this.writer.writeResponse(result);
        } catch (Exception e) {
            this.writer.writeError(e);
        }
    }

}
