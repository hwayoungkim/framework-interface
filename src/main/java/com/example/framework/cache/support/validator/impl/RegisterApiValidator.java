package com.example.framework.cache.support.validator.impl;

import com.example.framework.cache.support.validator.DataValidator;
import com.example.framework.core.ConfigurationLoader;
import com.example.framework.core.model.Api;


/**
 * 
 * Register API Validator
 * @date 2013. 5. 8. 오후 5:01:31
 * @version $Id$
 */
public class RegisterApiValidator implements DataValidator {

	/* (non-Javadoc)
	 * @see com.skp.openplatform.framework.cache.support.validator.DataValidator#validate(com.skp.openplatform.framework.core.ConfigurationLoader, java.lang.Object, java.lang.String[])
	 */
	@Override
	public boolean validate(ConfigurationLoader configurationLoader,
			Object data, String... parameters) {
		Api api = (Api) data;
		Api tmpService = configurationLoader.getApi(api.getApiId());
		return (tmpService == null);
	}

}
