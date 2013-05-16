package com.example.framework.cache.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.framework.cache.service.RegenerateService;
import com.example.framework.cache.support.validator.ValidationException;
import com.example.framework.core.model.Api;
import com.example.framework.core.model.ApiPathParameter;
import com.example.framework.core.model.ApiQueryParameter;

/**
 * 
 * Regeneration Service Implementation
 *
 * @date 2013. 5. 8. 오후 3:03:47
 * @version $Id$
 */
@Service
public class RegenerateServiceImpl implements RegenerateService {

	private static final String PROTOCOL_POSTFIX = "://";

	@Value("#{config['idf.pathparam']}")
	private String uriPathIdentifier;

	/* (non-Javadoc)
	 * @see com.skp.openplatform.framework.cache.service.RegenerateService#registerApi(net.sf.json.JSONObject, java.lang.String[])
	 */
	@Override
	public Api registerApi(JSONObject jsonObject, String... args) {

		// #1. Create Api
		Api api = new Api();
		api.setApiId(jsonObject.getString(API_ID));
		api.setAdaptorId(jsonObject.getString(ADAPTOR_ID));
		api.setApiVersion(jsonObject.getString(API_VERSION));
		api.setApiStatus(jsonObject.getString(API_STATUS));
		api.setBackendApiVersion(jsonObject.getString(BACKEND_API_VERSION));
		api.setBackendApiStatus(jsonObject.getString(BACKEND_API_STATUS));
		api.setBackendApiUri(jsonObject.getString(BACKEND_API_URI));
		
		String apiUri = jsonObject.getString(API_URI).replaceAll("\\{[^/]+\\}", uriPathIdentifier);
		api.setApiUri(apiUri);


		int startApiProtocol = apiUri.indexOf(PROTOCOL_POSTFIX);
		int startOrgApiUri = apiUri.indexOf('/', 10);

		if (startApiProtocol < 0 || startOrgApiUri <0) {
			throw new ValidationException("API URL error!");
		}

		String apiProtocol = apiUri.substring(0, startApiProtocol);
		String apiUriRegExp = apiUri.substring(startOrgApiUri).replaceAll(uriPathIdentifier, "([^/]*)");
		api.setApiProtocol(apiProtocol);
		api.setApiUriRegExp(apiUriRegExp);

		api.setApiHttpMethod(jsonObject.getString(API_HTTP_METHOD));
		api.setApiEnable(isYNStatus(jsonObject.getString(API_ENABLE)));
		api.setServiceId(jsonObject.getString(SERVICE_ID));
		api.setBackendApiHttpMethod(jsonObject.getString(BACKEND_API_HTTP_METHOD));

		JSONArray requestArray = jsonObject.getJSONArray(REQUEST_CONTENT_TYPE_LIST);
		int reqContentSize = requestArray.size();
		List<String> requestContentTypeList = new ArrayList<String>();

		for (int i = 0; i < reqContentSize; i++) {
			String contentType = requestArray.getString(i);
			requestContentTypeList.add(contentType);
		}

		JSONArray responseArray = jsonObject.getJSONArray(RESPONSE_CONTENT_TYPE_LIST);
		int resContentSize = responseArray.size();
		List<String> responseContentTypeList = new ArrayList<String>();

		for (int i = 0; i < resContentSize; i++) {
			String contentType = responseArray.getString(i);
			responseContentTypeList.add(contentType);
		}

		api.setRequestContentTypeList(requestContentTypeList);
		api.setResponseContentTypeList(responseContentTypeList);

		// #2. Create Parameter rules
		List<ApiPathParameter> apiPathParameterList = new ArrayList<ApiPathParameter>();
		List<ApiQueryParameter> apiQueryParameterList = new ArrayList<ApiQueryParameter>();

		api.setApiPathParameterList(apiPathParameterList);
		api.setApiQueryParameterList(apiQueryParameterList);
		
		JSONArray parameterList = jsonObject.getJSONArray(API_PATH_PARAMETER_LIST);
		int paramSize = parameterList.size();
		int pathIndex = 0;

		for (int i = 0; i < paramSize; i++) {
			JSONObject paramObject = parameterList.getJSONObject(i);
			ApiPathParameter apiPathParameter = new ApiPathParameter();
			apiPathParameter.setIndex(pathIndex++);
			apiPathParameter.setName(paramObject.getString(NAME));
			apiPathParameterList.add(apiPathParameter);
		}
		
		parameterList = jsonObject.getJSONArray(API_QUERY_PARAMETER_LIST);
		paramSize = parameterList.size();
		for (int i = 0; i < paramSize; i++) {
			JSONObject paramObject = parameterList.getJSONObject(i);
			ApiQueryParameter apiQueryParameter = new ApiQueryParameter();
			apiQueryParameter.setMandatory(isYNStatus(paramObject.getString(MANDATORY)));
			apiQueryParameter.setName(paramObject.getString(NAME));
			apiQueryParameterList.add(apiQueryParameter);
		}
		return api;
	}
	
	// check enable flag
	private boolean isYNStatus(String flag) {
		return "Y".equals(flag) ? true : false;
	}
}
