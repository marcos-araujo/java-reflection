package com.reflection.api.handler;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class HandlerConstructor {

    private Constructor<?> constructor;

    public HandlerConstructor(Constructor<?> constructor) {
        this.constructor = constructor;
    }

    public Object invoke() {
        try {
            return constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException e) {
            e.printStackTrace();
            return new RuntimeException(e);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return new RuntimeException("Constructor Error", e.getTargetException());
        }
    }
}
