package com.reflection.api.handler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class HandlerMethod {

    private BiFunction<Method, InvocationTargetException, Object> exceptionHandling;

    private Object instance;
    private Method method;
    private Map<String, Object> params;

    public HandlerMethod(Object instance, Method method, Map<String, Object> params) {
        this.instance = instance;
        this.method = method;
        this.params = params;
    }

    public HandlerMethod withExceptionHandling(BiFunction<Method, InvocationTargetException, Object> exceptionHandling) {
        this.exceptionHandling = exceptionHandling;
        return this;
    }

    public Object invoke(){
        List<Object> params = new ArrayList<>();
        Stream.of(method.getParameters())
                .forEach(p -> params.add(this.params.get(p.getName())));
        try {
           return method.invoke(instance, params.toArray());
        } catch (IllegalAccessException | IllegalArgumentException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            if (exceptionHandling != null) {
                return exceptionHandling.apply(method, e);
            }
            e.printStackTrace();
            throw new RuntimeException("Method Error", e.getTargetException());
        }
    }

}