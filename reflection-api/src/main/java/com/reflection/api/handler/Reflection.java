package com.reflection.api.handler;

public class Reflection {

    public Class<?> getClass(String fqn){
        try {
        	return Class.forName(fqn);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}