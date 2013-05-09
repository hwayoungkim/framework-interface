package com.example.framework.cache.support.validator.impl;

import com.example.framework.cache.support.validator.DataValidator;
import com.example.framework.core.ConfigurationLoader;


/**
 * 
 * Create API Service Validator
 *
 * @author Gil-Won Oh, Bluedigm
 * @date 2013. 5. 9. 오후 1:47:48
 * @version $Id$
 */
public class HasApiValidator implements DataValidator {

	@Override
	public boolean validate(ConfigurationLoader configurationLoader,
			Object data, String... parameters) {
		return configurationLoader.getApi(parameters[0]) != null;
	}

}
