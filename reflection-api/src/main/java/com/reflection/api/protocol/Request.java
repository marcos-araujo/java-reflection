package com.reflection.api.protocol;

import java.util.HashMap;
import java.util.Map;

public class Request {

	private String controllerName;
	private String methodName;
	private Map<String, Object> queryParams;

	public Request(String url) {

		String[] UrlParts = url.replaceFirst("/", "").split("[?]");

		String[] controllerAndMethod = UrlParts[0].split("/");

		controllerName = Character.toUpperCase(controllerAndMethod[0].charAt(0)) + controllerAndMethod[0].substring(1) + "Controller";

		methodName = controllerAndMethod[1];

		queryParams = UrlParts.length > 1
				? new QueryParamsBuilder().withParams(UrlParts[1]).build()
				: new HashMap<String, Object>();
	}

	public String getControllerName() {
		return controllerName;
	}

	public String getMethodName() {
		return methodName;
	}

	public Map<String, Object> getQueryParams() {
		return queryParams;
	}

}