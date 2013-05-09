/*
 * Copyright (c) 2011 SK planet.
 * All right reserved.
 *
 * This software is the confidential and proprietary information of SK planet.
 * You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with SK planet.
 */
package com.example.framework.cache.service;

import net.sf.json.JSONObject;

import com.example.framework.core.model.Api;

/**
 * Data Regenerate Service Interface
 *
 * @date 2012. 5. 14. 오후 4:01:18
 * @version $Id: RegenerateService.java 14950 2013-04-09 05:14:00Z kobi97 $
 */
public interface RegenerateService {

	String API_ID = "apiId";
	String SERVICE_ID = "serviceId";
	String ADAPTOR_ID = "adaptorId";
	String ENABLE = "enable";
	String API_PATH_PARAMETER_LIST = "apiPathParameterList";
	String API_QUERY_PARAMETER_LIST = "apiQueryParameterList";

	
	String REG_EXP = "regExp";
	String NAME = "name";
	String MANDATORY = "mandatory";
	String API_ENABLE = "apiEnable";
	String API_HTTP_METHOD = "apiHttpMethod";
	String API_URI = "apiUri";
	String API_STATUS = "apiStatus";
	String API_VERSION = "apiVersion";
	String BACKEND_API_HTTP_METHOD = "backendApiHttpMethod";
	String BACKEND_API_URI = "backendApiUri";
	String BACKEND_API_STATUS = "backendApiStatus";
	String BACKEND_API_VERSION = "backendApiVersion";
	String REQUEST_CONTENT_TYPE_LIST = "requestContentTypeList";
	String RESPONSE_CONTENT_TYPE_LIST = "responseContentTypeList";
	String CONTENT_TYPE = "contentType";

	/**
	 * Register API Information
	 *
	 * @param jsonObject JSONObject
	 * @param args Arguments
	 * @return Configurations
	 */
	Api registerApi(JSONObject jsonObject, String... args);

	

}
