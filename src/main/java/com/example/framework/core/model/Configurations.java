package com.example.framework.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Configurations Wrapper
 *
 * @date 2013. 5. 8. 오전 10:38:41
 * @version $Id$
 */
public class Configurations {
	
	private List<Api> apiList = new ArrayList<Api>();

	/**
	 * @return the apiList
	 */
	public List<Api> getApiList() {
		return apiList;
	}
	
	/**
	 * Get Api
	 *
	 * @param apiId API ID
	 * @return Api Value Object
	 */
	public Api getApi(String apiId) {
		List<Api> apiList = getApiList();
		int size = apiList.size();
		Api api = null;
		
		for (int i = 0; i < size; i++) {
			Api tmpApi = apiList.get(i);
			
			if (tmpApi.getApiId().equals(apiId)) {
				api = tmpApi;
				
				break;
			}
		}
		
		return api;
	}
	
	/**
	 * @param apiList
	 *            the apiList to set
	 */
	public void setApiList(List<Api> apiList) {
		this.apiList = apiList;
	}
	
}
