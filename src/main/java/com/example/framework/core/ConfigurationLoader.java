/*
 * Copyright (c) 2011 SK planet.
 * All right reserved.
 *
 * This software is the confidential and proprietary information of SK planet.
 * You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with SK planet.
 */
package com.example.framework.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.framework.core.model.Api;
import com.example.framework.core.model.ApiPathParameter;
import com.example.framework.core.model.ApiQueryParameter;
import com.example.framework.core.model.Configurations;


/**
 * Configuration Loader
 *
 * @author Xenomity™ a.k.a P-slinc'
 * @date 2012. 1. 20. 오후 2:08:51
 * @version $Id: ConfigurationLoader.java 15128 2013-04-16 06:22:10Z xenomity $
 */
@Component
public class ConfigurationLoader {

	/** API Cache Name */
	public static final String API_CACHE = "apiCache";

	@Autowired(required = false)
	private CacheManager cacheManager;
	private String serverId;

	
	/**
	 * @param apiList
	 *            the apiList to set
	 */
	public void setApiList(
			List<Api> apiList) {
		Ehcache ehcache = cacheManager.getEhcache(API_CACHE);
		ehcache.removeAll();
		if (apiList != null) {
			List<Element> elementList = new ArrayList<Element>();
			for (Api api: apiList) {
				elementList.add(new Element(api.getApiId(), api));
			}
			ehcache.putAll(elementList);
		}
	}
	
	public void updatePathParameterList(String apiId, List<ApiPathParameter> apiPathParameterList) {
		 Api targetApi = getApi(apiId);
		 if (targetApi != null) {
			 targetApi.setApiPathParameterList(apiPathParameterList);
		 }
		 setApi(targetApi);
	}

	
	public void updateQueryParameterList(String apiId, List<ApiQueryParameter> apiQueryParameterList) {
		 Api targetApi = getApi(apiId);
		 if (targetApi != null) {
			 targetApi.setApiQueryParameterList(apiQueryParameterList);
		 }
		 setApi(targetApi);
	}

	/**
	 * Update Application Informations
	 * @param apiList
	 *            the apiList to set
	 */
	public void updateApiList(
			List<Api> apiList) {
		Ehcache ehcache = cacheManager.getEhcache(API_CACHE);

		if (apiList != null) {
			Set<String> keys = new HashSet<String>();
			List<Element> elementList = new ArrayList<Element>();
			for (Api api: apiList) {
				String key = api.getApiId();
				keys.add(key);
				elementList.add(new Element(key, api));
			}
			ehcache.removeAll(keys);
			ehcache.putAll(elementList);
		}
	}


	/**
	 * @param apis
	 *            the api to set
	 */
	public void setApi(
			Api api) {
		Ehcache ehcache = cacheManager.getEhcache(API_CACHE);

		ehcache.remove(api.getApiId());
		ehcache.put(new Element(api.getApiId(), api));
	}

	/**
	 * Remove Api
	 * @param apiId
	 * @return boolean
	 */
	public boolean removeApi(String apiId) {
		Ehcache ehcache = cacheManager.getEhcache(API_CACHE);
		return ehcache.remove(apiId);
	}




	/**
	 * Get Api List
	 *
	 * @return Api List
	 */
	@SuppressWarnings("unchecked")
	public List<Api> getApiList() {
		Ehcache ehcache = cacheManager.getEhcache(API_CACHE);
		List<Api> apiList= new ArrayList<Api>();
		List<String> keys = ehcache.getKeys();
		for (int i = 0; i<keys.size(); i++)  {
			String key = keys.get(i);
			apiList.add((Api)ehcache.get(key).getValue());
		}
		return apiList;
	}

	/**
	 * Get Api
	 *
	 * @param apiId
	 * @return Api
	 */
	public Api getApi(String apiId) {

		Ehcache ehcache = cacheManager.getEhcache(API_CACHE);

		 Element element = ehcache.get(apiId);
		 if (element != null) {
			 return (Api) element.getValue();
		 }
		 return null;

	}




	/**
	 * Get Current Server ID
	 *
	 * @return Server ID
	 */
	public synchronized String getCurrentServerId() {
		if (serverId == null) {
			serverId = System.getProperty("serverId");

			if (serverId == null) {
				throw new IllegalStateException("Not found Server ID.");
			}
		}

		return serverId;
	}

	/**
	 * Load data
	 *
	 * @param cfgs Configurations
	 */
	public void load(Configurations cfgs) {
		this.setApiList(cfgs.getApiList());
		
	}

}
