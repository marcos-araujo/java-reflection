package com.reflection.api;

import com.reflection.api.marshall.XMLMarshaller;
import com.reflection.api.ioc.ContainerIoC;
import com.reflection.api.protocol.Request;
import com.reflection.api.handler.HandlerObject;
import com.reflection.api.handler.Reflection;

import java.util.Map;

public class ReflectionAPI {

	private String basePackage;
	private ContainerIoC container;

	public ReflectionAPI(String basePackage) {
		this.basePackage = basePackage;
		this.container = new ContainerIoC();
	}
	
	public Object execute(String url) {

		Request request = new Request(url);

		String controllerName = request.getControllerName();
		String methodName = request.getMethodName();
		Map<String, Object> params = request.getQueryParams();

		Class<?> controllerClass = new Reflection().getClass(basePackage + controllerName);
		Object controller = container.getInstance(controllerClass);

		Object object = new HandlerObject(controller)
							.getMethod(methodName, params)
							.withExceptionHandling((method, ex) -> {throw new RuntimeException("Method Error");})
							.invoke();

		Object xml = new XMLMarshaller().marshall(object);

		return xml;

	}

	public <T, K extends T> void registerClasses(Class<T> tipoFonte, Class<K> tipoDestino) {
		container.register(tipoFonte, tipoDestino);
	}

}