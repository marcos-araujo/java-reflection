package com.reflection.api.handler;

import java.lang.reflect.Constructor;

public class HandlerClass {

    private Class<?> classObject;

    public HandlerClass(Class<?> classObject) {
        this.classObject = classObject;
    }

    public HandlerConstructor getDefaultConstructor() {
        try {
            Constructor<?> defaultConstructor = classObject.getDeclaredConstructor();
            return new HandlerConstructor(defaultConstructor);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public HandlerObject instantiate() {
        Object instance = getDefaultConstructor().invoke();
        return new HandlerObject(instance);
    }
    
}
