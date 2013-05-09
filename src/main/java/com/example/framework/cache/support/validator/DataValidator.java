package com.example.framework.cache.support.validator;

import com.example.framework.core.ConfigurationLoader;


/**
 * 
 * Data Validator Interface
 *
 * @date 2013. 5. 8. 오후 4:58:57
 * @version $Id$
 */
public interface DataValidator {
	
	/**
	 * Validate
	 *
	 * @param configurationLoader ConfigurationLoader
	 * @param data Data Object
	 * @param parameters Etc parameters
	 * @return Result
	 */
	boolean validate(ConfigurationLoader configurationLoader, Object data, String... parameters);
	
}
