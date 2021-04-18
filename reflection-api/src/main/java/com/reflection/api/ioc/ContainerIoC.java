package com.reflection.api.ioc;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.stream.Stream;

public class ContainerIoC {

	private Map<Class<?>, Class<?>> mapTypes = new HashMap<>();

	public Object getInstance(Class<?> sourceType) {

		Class<?> targetType = mapTypes.get(sourceType);

		if (targetType != null)
			return getInstance(targetType);

		Stream<Constructor<?>> constructors = Stream.of(sourceType.getDeclaredConstructors());

		Optional<Constructor<?>> defaultConstructor = constructors
				.filter(constructor -> constructor.getParameterCount() == 0).findFirst();

		try {
			if (defaultConstructor.isPresent()) {
				Object instance = defaultConstructor.get().newInstance();
				return instance;
			} else {
				Constructor<?> constructor = sourceType.getDeclaredConstructors()[0];

				List<Object> params = new ArrayList<>();
				for (Parameter param : constructor.getParameters()) {
					Class<?> parameterType = param.getType();
					params.add(getInstance(parameterType));
				}

				return constructor.newInstance(params.toArray());
			}

		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	public <T, K extends T> void register(Class<T> sourceType, Class<K> targetType) {
		mapTypes.put(sourceType, targetType);
	}

}