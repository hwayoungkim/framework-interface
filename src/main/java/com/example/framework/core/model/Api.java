package com.example.framework.core.model;

import java.util.List;

import com.example.framework.common.Entity;

/**
 * OpenAPI Value Object
 *
 * @date 2013. 4. 30. 오후 3:04:48
 * @version $Id$
 */
public class Api extends Entity {

	private static final long serialVersionUID = -5349216894869427468L;

	private boolean /** 서비스 이용 가능*/ apiEnable;
	private String serviceId, adaptorId, apiId;
	private String apiVersion, apiUri, apiStatus;
	private String /** https or https */ apiProtocol;
	private String /* api 패턴 매칭을 위한 정규식 */ apiUriRegExp;
	private String apiHttpMethod,backendApiHttpMethod;
	private String backendApiVersion, backendApiUri, backendApiStatus;
	
	private List<String> requestContentTypeList;
	private List<String> responseContentTypeList;
	private List<ApiPathParameter> apiPathParameterList;
	private List<ApiQueryParameter> apiQueryParameterList;
	
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getAdaptorId() {
		return adaptorId;
	}
	public void setAdaptorId(String adaptorId) {
		this.adaptorId = adaptorId;
	}
	public String getApiId() {
		return apiId;
	}
	public void setApiId(String apiId) {
		this.apiId = apiId;
	}
	public String getApiVersion() {
		return apiVersion;
	}
	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}
	public String getApiUri() {
		return apiUri;
	}
	public void setApiUri(String apiUri) {
		this.apiUri = apiUri;
	}
	public String getApiStatus() {
		return apiStatus;
	}
	public void setApiStatus(String apiStatus) {
		this.apiStatus = apiStatus;
	}
	public String getApiProtocol() {
		return apiProtocol;
	}
	public void setApiProtocol(String apiProtocol) {
		this.apiProtocol = apiProtocol;
	}
	public String getApiUriRegExp() {
		return apiUriRegExp;
	}
	public void setApiUriRegExp(String apiUriRegExp) {
		this.apiUriRegExp = apiUriRegExp;
	}
	
	public boolean isApiEnable() {
		return apiEnable;
	}
	public void setApiEnable(boolean apiEnable) {
		this.apiEnable = apiEnable;
	}

	public String getApiHttpMethod() {
		return apiHttpMethod;
	}
	public void setApiHttpMethod(String apiHttpMethod) {
		this.apiHttpMethod = apiHttpMethod;
	}
	public String getBackendApiHttpMethod() {
		return backendApiHttpMethod;
	}
	public void setBackendApiHttpMethod(String backendApiHttpMethod) {
		this.backendApiHttpMethod = backendApiHttpMethod;
	}
	public List<String> getRequestContentTypeList() {
		return requestContentTypeList;
	}
	public void setRequestContentTypeList(List<String> requestContentTypeList) {
		this.requestContentTypeList = requestContentTypeList;
	}
	public List<String> getResponseContentTypeList() {
		return responseContentTypeList;
	}
	public void setResponseContentTypeList(List<String> responseContentTypeList) {
		this.responseContentTypeList = responseContentTypeList;
	}
	public String getBackendApiVersion() {
		return backendApiVersion;
	}
	public void setBackendApiVersion(String backendApiVersion) {
		this.backendApiVersion = backendApiVersion;
	}

	public String getBackendApiUri() {
		return backendApiUri;
	}
	public void setBackendApiUri(String backendApiUri) {
		this.backendApiUri = backendApiUri;
	}
	public String getBackendApiStatus() {
		return backendApiStatus;
	}
	public void setBackendApiStatus(String backendApiStatus) {
		this.backendApiStatus = backendApiStatus;
	}
	public List<ApiPathParameter> getApiPathParameterList() {
		return apiPathParameterList;
	}
	public void setApiPathParameterList(List<ApiPathParameter> apiPathParameterList) {
		this.apiPathParameterList = apiPathParameterList;
	}
	public List<ApiQueryParameter> getApiQueryParameterList() {
		return apiQueryParameterList;
	}
	public void setApiQueryParameterList(List<ApiQueryParameter> apiQueryParameterList) {
		this.apiQueryParameterList = apiQueryParameterList;
	}
	

}
