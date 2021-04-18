package com.reflection.api.protocol;

import java.util.HashMap;
import java.util.Map;

public class QueryParamsBuilder {
	private Map<String, Object> queryParams = new HashMap<>();

	public QueryParamsBuilder withParams(String stringQueryParams) {
		String[] stringParams = stringQueryParams.split("&");
		
		for (String stringParam : stringParams) {
			String[] keyAndValue = stringParam.split("=");
			
			String key = keyAndValue[0];
			Object value = keyAndValue[1];
			
			queryParams.put(key, value);
		}
		
		return this;
	}
	
	public Map<String, Object> build() {
		return this.queryParams;
	}

}
