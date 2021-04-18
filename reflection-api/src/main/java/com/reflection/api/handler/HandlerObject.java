package com.reflection.api.handler;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.stream.Stream;

public class HandlerObject {

    private Object instance;

    public HandlerObject(Object instance) {
        this.instance = instance;
    }

    public HandlerMethod getMethod(String methodName, Map<String, Object> params) {

        Stream<Method> methods = Stream.of(instance.getClass().getDeclaredMethods());

        Method selectedMethod = methods.filter(method -> method.getName().equals(methodName)
                        && method.getParameterCount() == params.values().size()
                        && Stream.of(method.getParameters())
                        .allMatch(arg -> params.keySet().contains(arg.getName())
                                && params.get(arg.getName()).getClass().equals(arg.getType())))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Method not found!"));

        return new HandlerMethod(instance, selectedMethod, params);

    }
}
