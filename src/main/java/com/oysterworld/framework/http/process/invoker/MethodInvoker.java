package com.oysterworld.framework.http.process.invoker;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodInvoker {
    @SuppressWarnings("unchecked")
    public static Object execute(Object func, Object... args) throws Exception {
        try {
            if (func instanceof Runnable r) {
                r.run();
                return null;
            } else if (func instanceof Supplier<?> s) {
                return s.get();
            } else if (func instanceof Consumer<?> c && args.length == 1) {
                ((Consumer<Object>) c).accept(args[0]);
                return null;
            } else if (func instanceof BiConsumer<?, ?> bc && args.length == 2) {
                ((BiConsumer<Object, Object>) bc).accept(args[0], args[1]);
                return null;
            } else if (func instanceof Function<?, ?> f && args.length == 1) {
                return ((Function<Object, Object>) f).apply(args[0]);
            } else if (func instanceof BiFunction<?, ?, ?> bf && args.length == 2) {
                return ((BiFunction<Object, Object, Object>) bf).apply(args[0], args[1]);
            } else {
                throw new IllegalArgumentException("サポートされていない関数型インタフェース: " + func.getClass());
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
